package sporSalonuÜyelikVeAntrenmanProgramı;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Kullanici implements VeriYöneticisi<Kullanici>{
	
	private static List<Kullanici> kullanicilar = new ArrayList<>();
	private LocalDateTime sonGiriş;

	public Admin(String email,String password) {
		super(email, password, Role.ADMIN);
		// TODO Auto-generated constructor stub
	}
	
	public static List<Kullanici> getKullanicilar() {
		return kullanicilar;
	}

	public static void setKullanicilar(List<Kullanici> kullanicilar) {
		if(kullanicilar == null) {
			throw new IllegalArgumentException("Kullanıcı listesi sisteme yüklenemedi!");
		}
		Admin.kullanicilar = kullanicilar;
	}
	
	public LocalDateTime getSonGiriş() {
		return sonGiriş;
	}

	public void setSonGiriş(LocalDateTime sonGiriş) {
		if(sonGiriş == null) {
			throw new IllegalArgumentException("Son giriş zamanı boş olamaz!");
		}
		if(sonGiriş.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("Son giriş gelecekte olamaz!");
		}
		this.sonGiriş = sonGiriş;
	}

	@Override
	public void displayInfo() {
		// TODO Auto-generated method stub
		System.out.println("--- YÖNETİCİ PANELİ ---");
        System.out.println("Email: " + getEmail());
        System.out.println("Son Erişim: " + sonGiriş);

	}

	@Override
	public void ekle(Kullanici nesne) {
		// TODO Auto-generated method stub
		if(kullanicilar.contains(nesne)) {
			System.out.println("Bu kullanici zaten var!");
			return;
		}
		kullanicilar.add(nesne);
		System.out.println("Ekleme işlemi başarılı. ID: "+nesne.getId());
	}

	@Override
	public void sil(String id) {
		// TODO Auto-generated method stub
		boolean silindi = kullanicilar.removeIf(nesne -> nesne.getId().equals(id));
		if(silindi) {
			System.out.println("Silme işlemi başarılı. ID: "+id);
		} else {
			System.out.println("ID bulunamadı!");
		}
		
	}

	@Override
	public void guncelle(Kullanici nesne) {
		// TODO Auto-generated method stub
		for(int i = 0;i < kullanicilar.size();i++) {
			if(kullanicilar.get(i).getId().equals(nesne.getId())) {
				kullanicilar.set(i, nesne);
				System.out.println("Güncelleme başarılı!");
				return;
			}
		}
		System.out.println("Güncelleme başarısız!");
	}

	@Override
	public List<Kullanici> listele() {
		// TODO Auto-generated method stub
		return new ArrayList<>(kullanicilar);
	}
	
	public Kullanici bul(String id) {
		for(Kullanici nesne : kullanicilar) {
			if(nesne.getId().equals(id)) {
				return nesne;
			}
		}
		return null;
	}

}
