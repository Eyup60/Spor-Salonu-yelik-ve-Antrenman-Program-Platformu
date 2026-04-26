package sporSalonuÜyelikVeAntrenmanProgramı;

public class YetkiYöneticisi {

    public Kullanici giriş(String email, String password) {
        if (email == null || password == null) {
            return null;
        }

        email = email.trim();
        password = password.trim();

        for (Kullanici k : Admin.getKullanicilar()) {
            System.out.println(k.getEmail() + " - " + k.getPassword() + " - " + k.getRole());

            if (k.getEmail().trim().equalsIgnoreCase(email)
                    && k.getPassword().trim().equals(password)) {
                return k;
            }
        }

        return null;
    }
}