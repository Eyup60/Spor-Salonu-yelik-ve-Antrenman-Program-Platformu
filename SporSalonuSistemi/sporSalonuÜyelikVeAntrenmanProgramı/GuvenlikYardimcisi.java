package sporSalonuÜyelikVeAntrenmanProgramı;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

// GÜVENLİK YARDIMCISI SINIFI
// ŞİFRELEME VE GÜVENLİK İŞLEMLERİNİ YÖNETİR
// SHA-256 KRIPTOGRAFİSİ KULLANIR
public class GuvenlikYardimcisi {
    // ŞİFRELE METOTU
    // SHA-256 İLE ŞİFRELERİ GÜVENLİ HALE GETİRİR
    public static String sifrele(String sifre) {
        try {
            // SHA-256 MESAJ ÖZETİ ALGORİTMASI
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(sifre.getBytes());
            // OLUŞAN BAYT DİZİSİNİ DOSYADA SAKLANABİLİR METİN FORMATINA ÇEVİRİR
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            // KRİPTOGRAFİK ALGORİTMA HATASI DURUMUNDA
            throw new RuntimeException("Kriptografik algoritma hatası!");
        }
    }
}