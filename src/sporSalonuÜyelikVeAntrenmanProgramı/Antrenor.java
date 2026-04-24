package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

public class Antrenor extends Kullanici implements VeriYöneticisi<Uye> {

	private String uzmanlıkAlanı;
	private List<Uye> uyeler = new ArrayList<>();
	
	public Antrenor(String email, String password,String uzmanlıkAlanı) {
		super(email, password, Role.ANTRENOR);
		setUzmanlıkAlanı(uzmanlıkAlanı);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		System.out.println("--- ANTRENÖR ---");
	    System.out.println("Email: " + getEmail());
	    System.out.println("Uzmanlık: " + getUzmanlıkAlanı());
	    System.out.println("Üye sayısı: "+ uyeler.size());
	}

	@Override
	public void ekle(Uye nesne) {
		// TODO Auto-generated method stub
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
	    System.out.println("Üye antrenöre başarıyla atandı!");
	}

	@Override
	public void sil(String id) {
		// TODO Auto-generated method stub
		boolean silindi = uyeler.removeIf(nesne -> nesne.getId().equals(id) && (nesne instanceof Kullanici && nesne.getRole() == Role.UYE));
		if(silindi){
			System.out.println("Uye silme işlemi başarılı!");
		} else {
			System.out.println("ID bulunamadı!");
		}
	}

	@Override
	public void guncelle(Uye nesne) {
		// TODO Auto-generated method stub
		
		if(nesne.getRole() != Role.UYE) {
			return;
		}
		
		for(int i = 0;i < uyeler.size();i++) {
			if(uyeler.get(i).getId().equals(nesne.getId())) {
				uyeler.set(i, nesne);
				System.out.println("Uye bilgilerini güncelleme başarılı!");
				return;
			}
		}
		System.out.println("Uye bilgilerini guncelleme başarısız!");
	}

	@Override
	public List<Uye> listele() {
		// TODO Auto-generated method stub
		return new ArrayList<>(uyeler);
	}

}
