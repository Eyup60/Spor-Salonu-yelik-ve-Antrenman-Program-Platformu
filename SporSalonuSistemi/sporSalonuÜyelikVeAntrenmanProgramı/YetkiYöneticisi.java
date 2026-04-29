package sporSalonuÜyelikVeAntrenmanProgramı;

public class YetkiYöneticisi {
    private int basarisizGirisSayisi = 0;
    private static final int MAX_DENEME = 5;

    public Kullanici giriş(String email, String password) {
        if (basarisizGirisSayisi >= MAX_DENEME) {
            throw new IllegalStateException("Çok fazla başarısız deneme! Lütfen bekleyin.");
        }
        if (email == null || password == null) return null;
        
        email = email.trim();
        for (Kullanici k : Admin.getKullanicilar()) {
            if (k.login(email, password)) {
                basarisizGirisSayisi = 0; 
                return k;
            }
        }
        basarisizGirisSayisi++;
        return null;
    }
}