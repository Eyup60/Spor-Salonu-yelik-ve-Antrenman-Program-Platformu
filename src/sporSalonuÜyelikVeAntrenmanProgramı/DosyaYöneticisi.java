
package sporSalonuÜyelikVeAntrenmanProgramı;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DosyaYöneticisi {

    private static final String UYGULAMA_KLASORU = System.getProperty("user.home") + File.separator + ".sporSalonu";
    private static final String DOSYA_YOLU = UYGULAMA_KLASORU + File.separator + "kullanicilar.json";
    
    private static String escapeJson(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public static void verileriKaydet() {
        new File(UYGULAMA_KLASORU).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_YOLU))) {
            writer.write("[\n");
            List<Kullanici> list = Admin.getKullanicilar();
            for (int i = 0; i < list.size(); i++) {
                Kullanici k = list.get(i);
                writer.write("{\n");
                writer.write("  \"tip\": \"" + escapeJson(k.getClass().getSimpleName()) + "\",\n");
                writer.write("  \"id\": \"" + escapeJson(k.getId()) + "\",\n");
                writer.write("  \"email\": \"" + escapeJson(k.getEmail()) + "\",\n");
                writer.write("  \"password\": \"" + escapeJson(k.getPassword()) + "\"");

                if (k instanceof Uye) {
                    Uye u = (Uye) k;
                    writer.write(",\n  \"boy\": " + u.getBoy() + ",\n");
                    writer.write("  \"kilo\": " + u.getKilo() + ",\n");
                    writer.write("  \"yas\": " + u.getYas() + ",\n");
                    writer.write("  \"yagOrani\": " + u.getYağOrani() + "\n");
                } else if (k instanceof Antrenor) {
                    Antrenor a = (Antrenor) k;
                    writer.write(",\n  \"uzmanlikAlani\": \"" + escapeJson(a.getUzmanlıkAlanı()) + "\",\n");
                    writer.write("  \"antrenorUyeIds\": [");
                    List<Uye> uyelerListesi = a.listele();
                    for(int j=0; j<uyelerListesi.size(); j++) {
                        writer.write("\"" + escapeJson(uyelerListesi.get(j).getId()) + "\"");
                        if(j < uyelerListesi.size() - 1) writer.write(", ");
                    }
                    writer.write("]\n");
                } else {
                    writer.write("\n");
                }

                if (i < list.size() - 1) {
                    writer.write("},\n");
                } else {
                    writer.write("}\n");
                }
            }
            writer.write("]\n");
        } catch (IOException e) {
            System.err.println("HATA (G/Ç Durumu): Veriler kaydedilirken sistemsel bir hata oluştu!");
            e.printStackTrace();
        }
    }

    public static void verileriYukle() {
        File dosya = new File(DOSYA_YOLU);
        
        if (!dosya.exists()) {
            System.err.println("UYARI: Kayıtlı veritabanı bulunamadı (" + DOSYA_YOLU + "). Yeni JSON dosyası otomatik oluşturulacaktır.");
            return;
        }

        List<Kullanici> yuklenenListe = new ArrayList<>();
        Map<String, List<String>> antrenorUyeMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String satir;
            
            String tip = null, id = null, email = null, password = null, uzmanlik = null;
            double boy = 0, kilo = 0, yagOrani = 0;
            int yas = 0;
            List<String> geciciUyeIds = new ArrayList<>();
            
            while ((satir = reader.readLine()) != null) {
                satir = satir.trim();
                
                if (satir.equals("{")) {
                    tip = id = email = password = uzmanlik = null;
                    boy = kilo = yagOrani = yas = 0;
                    geciciUyeIds = new ArrayList<>();
                } else if (satir.equals("}") || satir.equals("},")) {
                    
                    if (tip == null || id == null || email == null || password == null) {
                        throw new Exception("JSON sözdiziminde zorunlu anahtarlar eksik!");
                    }
                    
                    Kullanici k = null;
                    if (tip.equals("Admin")) {
                        k = new Admin(email, password);
                    } else if (tip.equals("Antrenor")) {
                        k = new Antrenor(email, password, uzmanlik);
                        antrenorUyeMap.put(id, geciciUyeIds);
                    } else if (tip.equals("Uye")) {
                        k = new Uye(email, password, boy, kilo, yas, yagOrani);
                    } else {
                        throw new Exception("Bilinmeyen Kullanıcı Tipi Algılandı: " + tip);
                    }
                    
                    Field idField = Kullanici.class.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(k, id);
                    
                    yuklenenListe.add(k);
                } else {
                    if (satir.startsWith("\"tip\"")) tip = extractString(satir);
                    else if (satir.startsWith("\"id\"")) id = extractString(satir);
                    else if (satir.startsWith("\"email\"")) email = extractString(satir);
                    else if (satir.startsWith("\"password\"")) password = extractString(satir);
                    else if (satir.startsWith("\"uzmanlikAlani\"")) uzmanlik = extractString(satir);
                    else if (satir.startsWith("\"boy\"")) boy = extractDouble(satir);
                    else if (satir.startsWith("\"kilo\"")) kilo = extractDouble(satir);
                    else if (satir.startsWith("\"yas\"")) yas = extractInt(satir);
                    else if (satir.startsWith("\"yagOrani\"")) yagOrani = extractDouble(satir);
                    else if (satir.startsWith("\"antrenorUyeIds\"")) {
                        int bas = satir.indexOf("[");
                        int bitis = satir.lastIndexOf("]");
                        if (bas != -1 && bitis != -1 && bas < bitis) {
                            String icerik = satir.substring(bas + 1, bitis);
                            if (!icerik.trim().isEmpty()) {
                                String[] idDizisi = icerik.split(",");
                                for (String s : idDizisi) {
                                    geciciUyeIds.add(s.replace("\"", "").trim());
                                }
                            }
                        }
                    }
                }
            }
            
            // Aşama 2: Antrenörlere üyelerini atama
            Admin.setKullanicilar(yuklenenListe);
            for (Kullanici k : yuklenenListe) {
                if (k instanceof Antrenor) {
                    List<String> list = antrenorUyeMap.get(k.getId());
                    if (list != null) {
                        for (String uyeId : list) {
                            Kullanici u = Admin.getKullanicilar().stream().filter(x -> x.getId().equals(uyeId)).findFirst().orElse(null);
                            if (u instanceof Uye) {
                                ((Antrenor) k).ekle((Uye) u);
                            }
                        }
                    }
                }
            }
            // Özel not: Antrenor'un .ekle() metodu Admin listesinde olmasını ve role=UYE olmasını check eder
            // Ama deserialize sırasında setter kullanmak yerine direkt property ayarlamak daha sağlıklı olabilir, 
            // Veya ekle metodu da çağrılabilir. Prompta göre: antrenor.ekle(uye) çağır:
            // Sadece .add direkt listeye ekliyor diyebiliriz, ama promptta "antrenor.ekle(uye) çağır" denilmiş. 
            // O yüzden aşağıda düzeltelim.

            System.out.println("Sistem Verileri JSON Üzerinden Başarıyla Yüklendi!");
            
        } catch (FileNotFoundException e) {
            System.err.println("HATA (Dosya Hatası): Veri dosyasına erişilemiyor!");
        } catch (IOException e) {
            System.err.println("HATA (Okuma Hatası): Veri dosyasından değerler okunurken hata oluştu - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("KRİTİK HATA (Bozuk Veri): JSON dosyası yapısı bozulmuş! Lütfen dosyayı silin veya düzeltin.");
            System.err.println("Detay: " + e.getMessage());
        }
    }

    private static String extractString(String line) {
        int ilkTirnak = line.indexOf(":", line.indexOf("\"")) + 1;
        ilkTirnak = line.indexOf("\"", ilkTirnak);
        int sonTirnak = line.lastIndexOf("\"");
        if (ilkTirnak != -1 && sonTirnak != -1 && ilkTirnak < sonTirnak) {
            return line.substring(ilkTirnak + 1, sonTirnak);
        }
        return "";
    }

    private static double extractDouble(String line) {
        try {
            String ayrilan = extractRaw(line);
            return ayrilan.isEmpty() ? 0 : Double.parseDouble(ayrilan);
        } catch (Exception e) {
            return 0;
        }
    }

    private static int extractInt(String line) {
        try {
            String ayrilan = extractRaw(line);
            return ayrilan.isEmpty() ? 0 : Integer.parseInt(ayrilan);
        } catch (Exception e) {
            return 0;
        }
    }

    private static String extractRaw(String line) {
        int ikiNoktaIndex = line.indexOf(":");
        if (ikiNoktaIndex == -1) return "";
        
        String deger = line.substring(ikiNoktaIndex + 1).trim();
        if (deger.endsWith(",")) {
            deger = deger.substring(0, deger.length() - 1);
        }
        return deger;
    }
}
