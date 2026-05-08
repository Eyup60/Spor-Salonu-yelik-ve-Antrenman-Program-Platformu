package sporSalonuÜyelikVeAntrenmanProgramı;

// UyelikPaketi üst sınıfından (Superclass) miras alan alt sınıf (Subclass).
// OOP Prensibi: Kalıtım (Inheritance)
public class PremiumPaket extends UyelikPaketi {

    // Yapıcı metot (Constructor)
    public PremiumPaket() {
        // "super" anahtar kelimesi ile üst sınıfın yapıcısına; 
        // paket adı, taban fiyat ve süre (gün) bilgileri gönderilir.
        super("Premium Paket", 800.0, 30);
    }
	
    // OOP Prensibi: Polimorfizm (Çok Biçimlilik) - Metot Ezme (Overriding)
    // Üst sınıftaki genel hesaplama metodunu, Premium pakete özel kurallarla yeniden yazar.
    @Override
    public double ucretHesapla() {
        // Premium paketin kendi fiyatlandırma mantığı: Temel fiyatın %10 fazlası
        return getTemelFiyat() * 1.1;
    }

}