package sporSalonuÜyelikVeAntrenmanProgramı;

public class YetkiYöneticisi {
    public Kullanici giriş(String email, String şifre) {
        if (email != null && !email.isEmpty() && şifre != null && !şifre.isEmpty()) {
            for(Kullanici nesne : Admin.getKullanicilar()) {
                if(nesne.login(email, şifre)) {
                    return nesne;
                }
            }
        }
        return null;
    }
}