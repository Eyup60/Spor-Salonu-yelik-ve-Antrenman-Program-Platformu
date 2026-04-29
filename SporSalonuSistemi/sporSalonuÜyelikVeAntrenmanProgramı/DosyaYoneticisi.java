package sporSalonuÜyelikVeAntrenmanProgramı;
import com.google.gson.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DosyaYoneticisi {

    private static final String DOSYA_YOLU =
        System.getProperty("user.home") + File.separator + 
        ".sporSalonu" + File.separator + "kullanicilar.json";

    private static Gson buildGson() {
        return new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class,
                (JsonSerializer<LocalDate>) (src, t, ctx) ->
                    new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDate.class,
                (JsonDeserializer<LocalDate>) (json, t, ctx) ->
                    LocalDate.parse(json.getAsString()))
            .create();
    }
    public static void verileriKaydet() {
        try {
            File dosya = new File(DOSYA_YOLU);
            dosya.getParentFile().mkdirs();
            Gson gson = buildGson(); 
            List<JsonObject> kayitlar = new ArrayList<>();
            
            for (Kullanici k : Admin.getKullanicilar()) {
                JsonObject obj = gson.toJsonTree(k).getAsJsonObject();
                obj.addProperty("tip", k.getClass().getSimpleName());
                if (k instanceof Antrenor antrenor) {
                    JsonArray ids = new JsonArray();
                    antrenor.listele().forEach(u -> ids.add(u.getId()));
                    obj.add("antrenorUyeIds", ids);
                }

                kayitlar.add(obj);
            }
            try (Writer w = new FileWriter(dosya)) {
                gson.toJson(kayitlar, w);
                System.out.println("Veriler kaydedildi: " + DOSYA_YOLU);
            }

        } catch (IOException e) {
            System.err.println("Kaydetme hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void verileriYukle() {
        File dosya = new File(DOSYA_YOLU);
        if (!dosya.exists()) {
            System.out.println("Kayıt dosyası bulunamadı. İlk yönetici hesabı oluşturuluyor...");
            Admin ilkAdmin = new Admin("Admin","ADMIN","admin@admin.com", "admin123");
            Admin.doğrudanEkle(ilkAdmin); 
            
            return;
        }

        try (Reader r = new FileReader(dosya)) {
            Gson gson = buildGson(); 

            JsonArray dizi = JsonParser.parseReader(r).getAsJsonArray();

            List<Kullanici> liste = new ArrayList<>();
            Map<String, List<String>> antrenorMap = new HashMap<>();

            for (JsonElement el : dizi) {
                JsonObject obj = el.getAsJsonObject();
                String tip = obj.get("tip").getAsString();
                Kullanici k = switch (tip) {
                    case "Admin"    -> gson.fromJson(obj, Admin.class);
                    case "Antrenor" -> gson.fromJson(obj, Antrenor.class);
                    case "Uye"      -> gson.fromJson(obj, Uye.class);
                    default -> throw new IllegalStateException("Bilinmeyen kullanıcı tipi: " + tip);
                };

                liste.add(k);
                if (tip.equals("Antrenor") && obj.has("antrenorUyeIds")) {
                    List<String> ids = new ArrayList<>();
                    obj.get("antrenorUyeIds").getAsJsonArray()
                       .forEach(e -> ids.add(e.getAsString()));
                    antrenorMap.put(k.getId(), ids);
                }
            }
            Admin.setKullanicilar(liste);
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