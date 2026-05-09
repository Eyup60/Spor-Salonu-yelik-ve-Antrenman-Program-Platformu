package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

// ANTRENÖR ÜYE SERVİS SINIFI
public class AntrenorUyeService implements VeriYöneticisi<AntrenorUye> {

    private List<AntrenorUye> liste = new ArrayList<>();

    @Override
    public void ekle(AntrenorUye nesne) {
        if (nesne == null) {
            throw new NullPointerException("Eklenen nesne boş (null) olamaz!");
        }
        liste.add(nesne);
    }

    @Override
    public void sil(String id) {
        liste.removeIf(a -> String.valueOf(a.getUyeID()).equals(id));
    }

    @Override
    public void guncelle(AntrenorUye yeniNesne) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).getUyeID() == yeniNesne.getUyeID()) {
                liste.set(i, yeniNesne);
                return;
            }
        }
    }

    @Override
    public List<AntrenorUye> listele() {
        return new ArrayList<>(liste);
    }

    @Override
    public AntrenorUye bul(String id) {
        for (AntrenorUye au : liste) {
            if (String.valueOf(au.getUyeID()).equals(id)) {
                return au;
            }
        }
        return null;
    }
    
    public void ata(String antrenorId, String uyeId) {
        liste.add(new AntrenorUye(antrenorId, uyeId));
    }
}