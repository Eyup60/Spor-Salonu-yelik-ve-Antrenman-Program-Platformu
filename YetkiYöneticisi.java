package sporSalonuÜyelikVeAntrenmanPlatformu;

public class YetkiYöneticisi {
	
	public YetkiYöneticisi(){
	}
	
	public Kullanici giriş(String email,String şifre) {
		if(email == null || email.isEmpty() || şifre == null || şifre.isEmpty()) {
			return null;
		}
		for(Kullanici nesne : Admin.getKullanicilar()) {
			if(nesne.getEmail().equalsIgnoreCase(email.trim()) && nesne.getPassword().equals(şifre)) {
				return nesne;
			}
		}
		return null;
		
	}
	
}
