package sporSalonuÜyelikVeAntrenmanProgramı;

public class YetkiYöneticisi {
    // HATALI GİRİŞ DENEMELERİNİ TAKİP EDEN DEĞİŞKEN
    private int basarisizGirisSayisi = 0;
    // GÜVENLİK İÇİN BELİRLENEN MAKSİMUM DENEME SINIRI
    private static final int MAX_DENEME = 5;

    public Kullanici giriş(String email, String password) {
        // BELİRLENEN DENEME SINIRI AŞILDIYSA SİSTEME GİRİŞİ ENGELLER
        if (basarisizGirisSayisi >= MAX_DENEME) {
            throw new IllegalStateException("Çok fazla başarısız deneme! Lütfen bekleyin.");
        }
        
        // GİRDİLERİN BOŞ OLUP OLMADIĞINI KONTROL EDER
        if (email == null || password == null) return null;
        
        email = email.trim();
        
        // SİSTEMDE KAYITLI TÜM KULLANICILAR İÇİNDE EŞLEŞME ARAR
        for (Kullanici k : Admin.getKullanicilar()) {
            if (k.login(email, password)) {
                // GİRİŞ BAŞARILIYSA HATA SAYACINI SIFIRLAR VE KULLANICIYI DÖNDÜRÜR
                basarisizGirisSayisi = 0; 
                return k;
            }
        }
        
        // EŞLEŞME BULUNAMAZSA HATA SAYACINI BİR ARTIRIR
        basarisizGirisSayisi++;
        return null;
    }
}