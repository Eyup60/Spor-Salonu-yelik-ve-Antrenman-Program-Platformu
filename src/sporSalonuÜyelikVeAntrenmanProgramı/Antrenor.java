package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

public class Antrenor extends Kullanici implements VeriYöneticisi<Uye> {

	private static final long serialVersionUID = 1L;
	private String uzmanlıkAlanı;
	private List<Uye> uyeler = new ArrayList<>();
	
	protected Antrenor(String email, String password,String uzmanlıkAlanı) {
		super(email, password, Role.ANTRENOR);
		setUzmanlıkAlanı(uzmanlıkAlanı);
	}
	
	public String getUzmanlıkAlanı() {
		return uzmanlıkAlanı;
	}

	public void setUzmanlıkAlanı(String uzmanlıkAlanı) {
		if(uzmanlıkAlanı == null || uzmanlıkAlanı.isEmpty()) {
			throw new IllegalArgumentException("Uzmanlık alanı boş olamaz!");
		}
		this.uzmanlıkAlanı = uzmanlıkAlanı;
	}

	@Override
	public void displayInfo() {
		System.out.println("--- ANTRENÖR ---");
	    System.out.println("Email: " + getEmail());
	    System.out.println("Uzmanlık: " + getUzmanlıkAlanı());
	    System.out.println("Üye sayısı: "+ uyeler.size());
	}

	@Override
	public void ekle(Uye nesne) {
		if (nesne == null) return;
		
	    if (!Admin.getKullanicilar().contains(nesne)) {
	        System.out.println("Bu üye sistemde kayıtlı değil!");
	        return;
	    }
	    
	    if (uyeler.contains(nesne)) {
	        System.out.println("Bu üye zaten listende!");
	        return;
	    }
	    
	    if (nesne.getRole() != Role.UYE) {
	        System.out.println("Sadece üye rolündekiler eklenebilir!");
	        return;
	    }
	    
	    uyeler.add(nesne);
	    DosyaYoneticisi.verileriKaydet();
	    System.out.println("Üye antrenöre başarıyla atandı!");
	}

	@Override
	public void sil(String id) {
		boolean silindi = uyeler.removeIf(nesne -> nesne.getId().equals(id));
		if(silindi){
			DosyaYoneticisi.verileriKaydet();
			System.out.println("Uye silme işlemi başarılı!");
		} else {
			System.out.println("ID bulunamadı!");
		}
	}

	@Override
	public void guncelle(Uye nesne) {
		
		if(nesne.getRole() != Role.UYE) {
			return;
		}
		
		for(int i = 0;i < uyeler.size();i++) {
			if(uyeler.get(i).getId().equals(nesne.getId())) {
				uyeler.set(i, nesne);
				DosyaYoneticisi.verileriKaydet();
				System.out.println("Uye bilgilerini güncelleme başarılı!");
				return;
			}
		}
		System.out.println("Uye bilgilerini guncelleme başarısız!");
	}

	@Override
	public List<Uye> listele() {
		return new ArrayList<>(uyeler);
	}
	
	@Override
	public Uye bul(String id) {
		for (Uye u : uyeler) {
			if (u.getId().equals(id)) return u;
		}
		return null;
	}

}
