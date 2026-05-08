package sporSalonuÜyelikVeAntrenmanProgramı;

// UyelikPaketi üst sınıfından (Superclass) miras alan VIP paket sınıfı.
// OOP Prensibi: Kalıtım (Inheritance)
public class VIPPaket extends UyelikPaketi {

    // Yapıcı metot (Constructor)
    public VIPPaket() {
        // "super" anahtar kelimesi ile üst sınıfın yapıcısına; 
        // paket adı, taban fiyat ve süre (gün) bilgileri gönderilir.
        super("VIP Paket", 1200.0, 30);
    }
	
    // OOP Prensibi: Polimorfizm (Çok Biçimlilik) - Metot Ezme (Overriding)
    // Üst sınıftaki genel hesaplama metodunu, VIP pakete özel kurallarla yeniden yazar.
    @Override
    public double ucretHesapla() {
        // VIP paketin kendi fiyatlandırma mantığı: Temel fiyatın %25 fazlası
        return getTemelFiyat() * 1.25;
    }

}