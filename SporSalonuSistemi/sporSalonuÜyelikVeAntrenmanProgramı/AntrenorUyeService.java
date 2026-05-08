package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

// Antrenör ve Üye ilişkilerinin yönetimini sağlayan servis (Business Logic) sınıfı.
// OOP Prensibi: IVeriYoneticisi arayüzü (Interface) implemente edilerek metotlar standartlaştırılmıştır.
public class AntrenorUyeService implements IVeriYoneticisi<AntrenorUye> {

    // Verileri bellekte tutan dinamik liste
    private List<AntrenorUye> liste = new ArrayList<>();

    @Override
    public void ekle(AntrenorUye nesne) {
        // Exception Handling: Boş (null) nesne eklenmeye çalışıldığında hata fırlatır
        if (nesne == null) {
            throw new NullPointerException("Eklenen nesne boş (null) olamaz!");
        }
        liste.add(nesne);
    }

    @Override
    public void sil(int index) {
        try {
            liste.remove(index);
        } catch (IndexOutOfBoundsException e) {
            // Exception Handling: Olmayan bir indeksi silmeye çalışırsa programın çökmesini engeller
            System.err.println("Silme hatası: Geçersiz sıra numarası!");
        }
    }

    @Override
    public void guncelle(int index, AntrenorUye nesne) {
        try {
            liste.set(index, nesne);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Güncelleme hatası: Belirtilen kayıt bulunamadı!");
        }
    }

    @Override
    public List<AntrenorUye> listele() {
        return liste;
    }
    
    // Antrenör ve Üye ID'lerini alarak doğrudan ilişki nesnesi oluşturan yardımcı metot
    public void ata(int antrenorId, int uyeId) {
        liste.add(new AntrenorUye(antrenorId, uyeId));
    }
}