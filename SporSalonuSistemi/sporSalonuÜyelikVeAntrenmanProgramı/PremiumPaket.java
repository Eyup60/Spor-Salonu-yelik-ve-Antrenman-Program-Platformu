package sporSalonuÜyelikVeAntrenmanProgramı;

// PREMIUM PAKET SINIFI
public class PremiumPaket extends UyelikPaketi {

    // Yapıcı metot (Constructor)
    public PremiumPaket() {
        // "super" anahtar kelimesi ile üst sınıfın yapıcısına; 
        // paket adı, taban fiyat ve süre (gün) bilgileri gönderilir.
        super("Premium Paket", 800.0, 30);
    }
	
    // ÜCRET HESAPLA
    @Override
    public double ucretHesapla() {
        // Premium paketin kendi fiyatlandırma mantığı: Temel fiyatın %10 fazlası
        return getTemelFiyat() * 1.1;
    }

}