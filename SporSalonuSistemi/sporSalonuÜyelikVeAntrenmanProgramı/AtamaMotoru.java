package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

// Sisteme yeni katılan üyeyi en az öğrencisi olan antrenöre atayan sınıf
public class AtamaMotoru {

    public static void otomatikAtamaYap(Uye uye) {
        
        // Sistemdeki tüm kullanıcıları tarayıp sadece 'Antrenör' olanları ayıklıyoruz
        List<Antrenor> antrenorList = new ArrayList<>();
        for (Kullanici k : Admin.getKullanicilar()) {
            if (k instanceof Antrenor) {
                antrenorList.add((Antrenor) k);
            }
        }

        // Eğer sistemde hiç antrenör yoksa metottan çık 
        if (antrenorList.isEmpty()) return;

        // En az öğrenciye sahip antrenörü bulma algoritması
        Antrenor enUygunHoca = antrenorList.get(0);
        for (Antrenor k : antrenorList) {
            
            // NullPointerException yememek için liste null ise öğrenci sayısını 0 kabul ediyoruz
            int kSize = (k.listele() == null) ? 0 : k.listele().size();
            int enSize = (enUygunHoca.listele() == null) ? 0 : enUygunHoca.listele().size();
            
            // Daha az öğrencisi olan bir hoca bulursak yeni uygun hocamız o oluyor
            if (kSize < enSize) {
                enUygunHoca = k;
            }
        }

        try {
            // Üyeyi bulduğumuz hocaya atayıp veri kaybı olmasın diye dosyaya kaydediyoruz
            enUygunHoca.ekle(uye);
            DosyaYoneticisi.verileriKaydet();
        } catch (Exception ex) {
            ex.printStackTrace(); // Beklenmedik bir hatada konsola bilgi basıyoruz
        }
    }
}