package sporSalonuÜyelikVeAntrenmanProgramı;

import com.google.gson.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// VERİ YÖNETİMİ VE KALICI SAKLAMA SINIFI
public class DosyaYoneticisi {

    // DOSYA KONUMU VE FİZİKSEL ADRES TANIMI
    private static final String DOSYA_YOLU =
        System.getProperty("user.home") + File.separator + 
        ".sporSalonu" + File.separator + "kullanicilar.json";

    // JSON DÖNÜŞTÜRÜCÜ VE TARİH ADAPTÖRLERİ YAPILANDIRMASI
    private static Gson buildGson() {
        return new GsonBuilder()
            .setPrettyPrinting()
            // LOCALDATE TİPİNİ METNE ÇEVİRİR
            .registerTypeAdapter(LocalDate.class,
                (JsonSerializer<LocalDate>) (src, t, ctx) ->
                    new JsonPrimitive(src.toString()))
            // METNİ TEKRAR LOCALDATE NESNESİNE ÇEVİRİR
            .registerTypeAdapter(LocalDate.class,
                (JsonDeserializer<LocalDate>) (json, t, ctx) ->
                    LocalDate.parse(json.getAsString()))
            // LOCALDATETIME NESNESİNİ JSON UYUMLU YAPAR
            .registerTypeAdapter(LocalDateTime.class,
                (JsonSerializer<LocalDateTime>) (src, t, ctx) ->
                    new JsonPrimitive(src.toString()))
            // JSON VERİSİNİ LOCALDATETIME NESNESİNE DÖNÜŞTÜRÜR
            .registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, t, ctx) ->
                    LocalDateTime.parse(json.getAsString()))
            .create();
    }
    
    // BELLEKTEKİ VERİLERİ DOSYAYA YAZMA İŞLEMİ
    public static void verileriKaydet() {
        try {
            File dosya = new File(DOSYA_YOLU);
            dosya.getParentFile().mkdirs(); // KLASÖR YOKSA OTOMATİK OLUŞTURMA
            Gson gson = buildGson(); 
            List<JsonObject> kayitlar = new ArrayList<>();
            
            // NESNE TİPİNİ KORUYARAK LİSTEYİ TARAMA
            for (Kullanici k : Admin.getKullanicilar()) {
                JsonObject obj = gson.toJsonTree(k).getAsJsonObject(); 
                obj.addProperty("tip", k.getClass().getSimpleName()); // ALT SINIF BİLGİSİNİ JSONA EKLEME
                
                // ANTRENÖR VE ÜYE BAĞLARINI ID ÜZERİNDEN SAKLAMA
                if (k instanceof Antrenor antrenor) {
                    JsonArray ids = new JsonArray();
                    antrenor.listele().forEach(u -> ids.add(u.getId()));
                    obj.add("antrenorUyeIds", ids);
                }

                kayitlar.add(obj);
            }
            // TÜM VERİLERİ JSON DOSYASINA AKTARMA
            try (Writer w = new FileWriter(dosya)) {
                gson.toJson(kayitlar, w);
                System.out.println("Veriler kaydedildi: " + DOSYA_YOLU);
            }

        } catch (IOException e) {
            System.err.println("Kaydetme hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // DOSYADAN VERİ OKUMA VE NESNELEŞTİRME İŞLEMİ
    public static void verileriYukle() {
        File dosya = new File(DOSYA_YOLU);
        
        // DOSYA YOKSA İLK ADMİN HESABINI OLUŞTURMA
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

            // JSON VERİSİNİ DOĞRU SINIF TİPİNE DÖNÜŞTÜRME
            for (JsonElement el : dizi) {
                JsonObject obj = el.getAsJsonObject();
                
                // TİP BİLGİSİNİ OKUMA VE FORMATLAMA
                String tipRaw = obj.get("tip").getAsString().trim().toLowerCase();
                
                // SINIF TİPİNE GÖRE NESNE ÜRETME
                Kullanici k = switch (tipRaw) {
                    case "admin"    -> gson.fromJson(obj, Admin.class);
                    case "antrenor" -> gson.fromJson(obj, Antrenor.class);
                    case "uye"      -> gson.fromJson(obj, Uye.class);
                    default -> throw new IllegalStateException("BİLİNMEYEN KULLANICI TİPİ: " + tipRaw);
                };

                liste.add(k);
                
                // ANTRENÖR VE ÜYE İLİŞKİLERİNİ EŞLEŞTİRMEK İÇİN SAKLAMA
                if (tipRaw.equals("antrenor") && obj.has("antrenorUyeIds")) {
                    List<String> ids = new ArrayList<>();
                    obj.get("antrenorUyeIds").getAsJsonArray()
                       .forEach(e -> ids.add(e.getAsString()));
                    antrenorMap.put(k.getId(), ids);
                }
            }
            
            // YÜKLENEN LİSTEYİ SİSTEME AKTARMA
            Admin.setKullanicilar(liste);
            
            // ID BİLGİLERİNİ GERÇEK NESNE BAĞLANTILARINA ÇEVİRME
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
            
            // ANTRENÖRÜ OLMAYAN ÜYELERİ OTOMATİK ATAMA SİSTEMİNE GÖNDERME
            for (Kullanici k : Admin.getKullanicilar()) {
                if (k instanceof Uye uye) {
                    if (Admin.anternorBulUyeIle(uye).equals("Henüz Atanmadı")) {
                        System.out.println("Hocasız üye tespit edildi, atama yapılıyor: " + uye.getIsim());
                        AtamaMotoru.otomatikAtamaYap(uye);
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