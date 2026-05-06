package sporSalonuÜyelikVeAntrenmanProgramı;

import com.google.gson.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DosyaYoneticisi {

    // VERİ TABANI DOSYASININ BİLGİSAYARDAKİ FİZİKSEL ADRESİNİ BELİRLER
    private static final String DOSYA_YOLU =
        System.getProperty("user.home") + File.separator + 
        ".sporSalonu" + File.separator + "kullanicilar.json";

    // GSON NESNESİNİ ÖZEL AYARLARLA (TARİH FORMATLARI VE ŞIK YAZIM) YAPILANDIRIR
    private static Gson buildGson() {
        return new GsonBuilder()
            .setPrettyPrinting()
            // JAVA LOCALDATE VE JSON METİN DÖNÜŞÜMÜNÜ SAĞLAYAN ADAPTÖRLER
            .registerTypeAdapter(LocalDate.class,
                (JsonSerializer<LocalDate>) (src, t, ctx) ->
                    new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDate.class,
                (JsonDeserializer<LocalDate>) (json, t, ctx) ->
                    LocalDate.parse(json.getAsString()))
            // JAVA LOCALDATETIME (SAAT VE TARİH) İÇİN DÖNÜŞÜM AYARLARI
            .registerTypeAdapter(LocalDateTime.class,
                (JsonSerializer<LocalDateTime>) (src, t, ctx) ->
                    new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, t, ctx) ->
                    LocalDateTime.parse(json.getAsString()))
            .create();
    }
    
    // RAM'DEKİ TÜM KULLANICI VE İLİŞKİ VERİLERİNİ JSON DOSYASINA KAYDEDER
    public static void verileriKaydet() {
        try {
            File dosya = new File(DOSYA_YOLU);
            dosya.getParentFile().mkdirs(); // EKSİK KLASÖRLERİ OLUŞTURUR
            Gson gson = buildGson(); 
            List<JsonObject> kayitlar = new ArrayList<>();
            
            // HER KULLANICIYI TİPİYLE (ADMİN/ÜYE/ANTRENÖR) BİRLİKTE JSON'A DÖNÜŞTÜRÜR
            for (Kullanici k : Admin.getKullanicilar()) {
                JsonObject obj = gson.toJsonTree(k).getAsJsonObject();
                obj.addProperty("tip", k.getClass().getSimpleName()); // POLİMORFİZM KORUMASI
                
                // ANTRENÖR VE ÜYE ARASINDAKİ BAĞLARI SADECE ID OLARAK SAKLAR
                if (k instanceof Antrenor antrenor) {
                    JsonArray ids = new JsonArray();
                    antrenor.listele().forEach(u -> ids.add(u.getId()));
                    obj.add("antrenorUyeIds", ids);
                }

                kayitlar.add(obj);
            }
            // TÜM LİSTEYİ DOSYAYA FİZİKSEL OLARAK YAZAR
            try (Writer w = new FileWriter(dosya)) {
                gson.toJson(kayitlar, w);
                System.out.println("Veriler kaydedildi: " + DOSYA_YOLU);
            }

        } catch (IOException e) {
            System.err.println("Kaydetme hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // PROGRAM AÇILIŞINDA DOSYADAKİ VERİLERİ OKUYUP CANLI NESNELERE DÖNÜŞTÜRÜR
    public static void verileriYukle() {
        File dosya = new File(DOSYA_YOLU);
        
        // EĞER DOSYA YOKSA SİSTEMİ İLK KEZ ÇALIŞTIRIP VARSAYILAN ADMİN OLUŞTURUR
        if (!dosya.exists()) {
            System.out.println("Kayıt dosyası bulunamadı. İlk yönetici hesabı oluşturuluyor...");
            Admin ilkAdmin = new Admin("İlk","Admin","admin@gym.com", "123456");
            Admin.doğrudanEkle(ilkAdmin); 
            return;
        }

        try (Reader r = new FileReader(dosya)) {
            Gson gson = buildGson(); 
            JsonArray dizi = JsonParser.parseReader(r).getAsJsonArray();

            List<Kullanici> liste = new ArrayList<>();
            Map<String, List<String>> antrenorMap = new HashMap<>();

            // DOSYADAN OKUNAN HER JSON ÖGESİNİ DOĞRU JAVA SINIFINA (CLASS) ÇEVİRİR
            for (JsonElement el : dizi) {
                JsonObject obj = el.getAsJsonObject();
                String tip = obj.get("tip").getAsString();
                
                // TİP ETİKETİNE GÖRE NESNE ÜRETİMİ (SWITCH-CASE YAPISI)
                Kullanici k = switch (tip) {
                    case "Admin"    -> gson.fromJson(obj, Admin.class);
                    case "Antrenor" -> gson.fromJson(obj, Antrenor.class);
                    case "Uye"      -> gson.fromJson(obj, Uye.class);
                    default -> throw new IllegalStateException("Bilinmeyen kullanıcı tipi: " + tip);
                };

                liste.add(k);
                // ANTRENÖRÜN ÖĞRENCİ LİSTESİNİ (ID OLARAK) GEÇİCİ BELLEĞE ALIR
                if (tip.equals("Antrenor") && obj.has("antrenorUyeIds")) {
                    List<String> ids = new ArrayList<>();
                    obj.get("antrenorUyeIds").getAsJsonArray()
                       .forEach(e -> ids.add(e.getAsString()));
                    antrenorMap.put(k.getId(), ids);
                }
            }
            
            // TÜM KULLANICILARI MERKEZİ LİSTEYE AKTARIR
            Admin.setKullanicilar(liste);
            
            // ID OLARAK OKUNAN İLİŞKİLERİ GERÇEK NESNE REFERANSLARINA DÖNÜŞTÜRÜR
            for (Kullanici k : liste) {
                if (k instanceof Antrenor antrenor) {
                    List<String> uyeIds = antrenorMap.getOrDefault(k.getId(), List.of());

                    for (String id : uyeIds) {
                        liste.stream()
                             .filter(x -> x.getId().equals(id) && x instanceof Uye)
                             .findFirst()
                             .ifPresent(u -> antrenor.ekle((Uye) u));
                    }
                }
            }
            System.out.println("Veriler yüklendi. Toplam kullanıcı: " + liste.size());
        } catch (IOException e) {
            System.err.println(" Dosya okuma hatası: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Yükleme hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }
}