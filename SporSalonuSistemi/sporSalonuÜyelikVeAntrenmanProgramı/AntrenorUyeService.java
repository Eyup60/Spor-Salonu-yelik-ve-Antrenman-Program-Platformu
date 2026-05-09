package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

// ANTRENÖR ÜYE SERVİS SINIFI
// ANTRENÖR ÜYE İLİŞKİLERİNİ YÖNETİR
// VERİ YÖNETİCİ ARAYÜZÜNÜ UYGULAR
public class AntrenorUyeService implements VeriYöneticisi<AntrenorUye> {

    // ANTRENÖR ÜYE LİSTESİ
    private List<AntrenorUye> liste = new ArrayList<>();

    // ANTRENÖR ÜYE EKLE
    // NULL KONTROLÜ YAPAR VE LİSTEYE EKLER
    @Override
    public void ekle(AntrenorUye nesne) {
        if (nesne == null) {
            throw new NullPointerException("Eklenen nesne boş (null) olamaz!");
        }
        liste.add(nesne);
    }

    // ANTRENÖR ÜYE SİL
    // İD İLE EŞLEŞEN KAYDI LİSTEDEN KALDIRIR
    @Override
    public void sil(String id) {
        liste.removeIf(a -> String.valueOf(a.getUyeID()).equals(id));
    }

    // ANTRENÖR ÜYE GÜNCELLE
    // MEVCUT KAYDI YENİ BİLGİLERLE GÜNCELLER
    @Override
    public void guncelle(AntrenorUye yeniNesne) {
        for (int i = 0; i < liste.size(); i++) {
            // ÜYE ID ESASINA GÖRE GÜNCELLEME
            if (liste.get(i).getUyeID() == yeniNesne.getUyeID()) {
                liste.set(i, yeniNesne);
                return;
            }
        }
    }

    // ANTRENÖR ÜYE LİSTELE
    // TÜM İLİŞKİLERİ KOPYA LİSTE OLARAK DÖNDÜRÜR
    @Override
    public List<AntrenorUye> listele() {
        return new ArrayList<>(liste);
    }

    // ANTRENÖR ÜYE BUL
    // İD İLE İLİŞKİ ARA VE DÖNDÜR
    @Override
    public AntrenorUye bul(String id) {
        for (AntrenorUye au : liste) {
            // ÜYE ID İLE KARŞILAŞTIRMA
            if (String.valueOf(au.getUyeID()).equals(id)) {
                return au;
            }
        }
        return null;
    }
    
    // ANTRENÖR ÜYE ATA
    // YENİ İLİŞKİ OLUŞTUR VE LİSTEYE EKLE
    public void ata(String antrenorId, String uyeId) {
        liste.add(new AntrenorUye(antrenorId, uyeId));
    }
}