package sporSalonuÜyelikVeAntrenmanProgramı;
import com.google.gson.*;
import java.io.*;
import java.util.*;

public class DosyaYoneticisi {

    private static final String DOSYA_YOLU = 
        System.getProperty("user.home") + File.separator + ".sporSalonu" + File.separator + "kullanicilar.json";

        public static void verileriKaydet() {
        new File(DOSYA_YOLU).getParentFile().mkdirs();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        List<JsonObject> kayitlar = new ArrayList<>();
        for (Kullanici k : Admin.getKullanicilar()) {
            JsonObject obj = gson.toJsonTree(k).getAsJsonObject();
            obj.addProperty("tip", k.getClass().getSimpleName());
            if (k instanceof Antrenor) {
                JsonArray ids = new JsonArray();
                ((Antrenor) k).listele().forEach(u -> ids.add(u.getId()));
                obj.add("antrenorUyeIds", ids);
            }
            kayitlar.add(obj);
        }
        try (Writer w = new FileWriter(DOSYA_YOLU)) {
            gson.toJson(kayitlar, w);
        } catch (IOException e) {
            System.err.println("Kaydetme hatası: " + e.getMessage());
        }
    }

    public static void verileriYukle() {
        File dosya = new File(DOSYA_YOLU);
        if (!dosya.exists()) return;

        try (Reader r = new FileReader(dosya)) {
            JsonArray dizi = JsonParser.parseReader(r).getAsJsonArray();
            Gson gson = new Gson();
            List<Kullanici> liste = new ArrayList<>();
            Map<String, List<String>> antrenorMap = new HashMap<>();

            for (JsonElement el : dizi) {
                JsonObject obj = el.getAsJsonObject();
                String tip = obj.get("tip").getAsString();
                Kullanici k = switch (tip) {
                    case "Admin"    -> gson.fromJson(obj, Admin.class);
                    case "Antrenor" -> gson.fromJson(obj, Antrenor.class);
                    case "Uye"      -> gson.fromJson(obj, Uye.class);
                    default -> throw new IllegalStateException("Bilinmeyen tip: " + tip);
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
                if (k instanceof Antrenor a) {
                    antrenorMap.getOrDefault(k.getId(), List.of()).forEach(id ->
                        liste.stream().filter(x -> x.getId().equals(id) && x instanceof Uye)
                             .findFirst().ifPresent(u -> a.ekle((Uye) u))
                    );
                }
            }
        } catch (Exception e) {
            System.err.println("Yükleme hatası: " + e.getMessage());
        }
    }
}