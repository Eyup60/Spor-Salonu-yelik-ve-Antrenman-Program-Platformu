package sporSalonuÜyelikVeAntrenmanPlatformu;

import java.util.List;

public interface VeriYöneticisi {
	public void ekle(Kullanici nesne);
	public void sil(String id);
	public void guncelle(Kullanici nesne);
	public List<Kullanici> listele();
}
