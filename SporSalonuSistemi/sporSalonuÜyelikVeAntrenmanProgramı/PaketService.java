package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

// Üyelik paketlerinin (Standart, Premium, VIP) yönetimini sağlayan servis sınıfı.
// OOP Prensibi: IVeriYoneticisi arayüzü (Interface) implemente edilerek metotlar zorunlu kılınmıştır.
public class PaketService implements IVeriYoneticisi<UyelikPaketi> {

    // Farklı paket türlerini (Polimorfizm) tek bir listede tutan veri yapısı
    private List<UyelikPaketi> paketler = new ArrayList<>();

    @Override
    public void ekle(UyelikPaketi paket) {
        // Exception Handling: Boş veri eklenmesini engeller
        if (paket == null) {
            throw new NullPointerException("Eklenecek paket boş (null) olamaz!");
        }
        paketler.add(paket);
    }

    @Override
    public void sil(int index) {
        try {
            paketler.remove(index);
        } catch (IndexOutOfBoundsException e) {
            // Exception Handling: Olmayan bir index silinmeye çalışıldığında program çökmez
            System.err.println("Silme hatası: Geçersiz paket sıra numarası!");
        }
    }

    @Override
    public void guncelle(int index, UyelikPaketi paket) {
        try {
            paketler.set(index, paket);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Güncelleme hatası: Belirtilen paket kaydı bulunamadı!");
        }
    }

    @Override
    public List<UyelikPaketi> listele() {
        // Sisteme ekli olan tüm paketleri geri döndürür
        return paketler;
    }
}