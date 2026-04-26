package sporSalonuÜyelikVeAntrenmanProgramı;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

public class Admin extends Kullanici implements VeriYöneticisi<Kullanici>{
	
	private static final long serialVersionUID = 1L;
	private static List<Kullanici> kullanicilar = new CopyOnWriteArrayList<>();
	private LocalDateTime sonGiriş;

	protected Admin(String email,String password) {
		super(email, password, Role.ADMIN);
	}
	
	public static List<Kullanici> getKullanicilar() {
		return new ArrayList<>(kullanicilar);
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

	public static void doğrudanEkle(Kullanici k) {
	    if (!kullanicilar.contains(k)) {
	        kullanicilar.add(k);
	        DosyaYoneticisi.verileriKaydet();
	    }
	}
	
	@Override
	public void displayInfo() {
		System.out.println("--- YÖNETİCİ PANELİ ---");
        System.out.println("Email: " + getEmail());
        System.out.println("Son Erişim: " + sonGiriş);

	}

	@Override
	public void ekle(Kullanici nesne) {
		if(kullanicilar.contains(nesne)) {
			System.out.println("Bu kullanici zaten var!");
			return;
		}
		kullanicilar.add(nesne);
		DosyaYoneticisi.verileriKaydet();
		System.out.println("Ekleme işlemi başarılı. ID: "+nesne.getId());
	}

	@Override
	public void sil(String id) {
		Kullanici silinecek = bul(id);
		if (silinecek instanceof Admin && kullanicilar.stream().filter(k -> k instanceof Admin).count() <= 1) {
			JOptionPane.showMessageDialog(null, "Son yönetici hesabı silinemez!", "Kritik Yetki Hatası", JOptionPane.ERROR_MESSAGE);
			throw new IllegalStateException("Sistemde en az 1 Admin kalmak zorundadır!");
		}
		boolean silindi = kullanicilar.removeIf(nesne -> nesne.getId().equals(id));
		if(silindi) {
			DosyaYoneticisi.verileriKaydet();
			System.out.println("Silme işlemi başarılı. ID: "+id);
		} else {
			System.out.println("ID bulunamadı!");
		}
		
	}

	@Override
	public void guncelle(Kullanici nesne) {
		for(int i = 0;i < kullanicilar.size();i++) {
			if(kullanicilar.get(i).getId().equals(nesne.getId())) {
				kullanicilar.set(i, nesne);
				DosyaYoneticisi.verileriKaydet();
				System.out.println("Güncelleme başarılı!");
				return;
			}
		}
		System.out.println("Güncelleme başarısız!");
	}

	@Override
	public List<Kullanici> listele() {
		return new ArrayList<>(kullanicilar);
	}
	
	@Override
	public Kullanici bul(String id) {
		for(Kullanici nesne : kullanicilar) {
			if(nesne.getId().equals(id)) {
				return nesne;
			}
		}
		return null;
	}

}
