package sporSalonuÜyelikVeAntrenmanProgramı;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

// GÜVENLİK YARDIMCISI SINIFI
public class GuvenlikYardimcisi {
    // ŞİFRELE
    public static String sifrele(String sifre) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(sifre.getBytes());
            // OLUŞAN BAYT DİZİSİNİ DOSYADA SAKLANABİLİR METİN FORMATINA ÇEVİRİR
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Kriptografik algoritma hatası!");
        }
    }
}