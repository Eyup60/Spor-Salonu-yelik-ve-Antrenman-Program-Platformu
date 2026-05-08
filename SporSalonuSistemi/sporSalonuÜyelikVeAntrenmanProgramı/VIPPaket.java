package sporSalonuÜyelikVeAntrenmanProgramı;

// VIP PAKET SINIFI
public class VIPPaket extends UyelikPaketi {

    // Yapıcı metot (Constructor)
    public VIPPaket() {
        // "super" anahtar kelimesi ile üst sınıfın yapıcısına; 
        // paket adı, taban fiyat ve süre (gün) bilgileri gönderilir.
        super("VIP Paket", 1200.0, 30);
    }
	
    // ÜCRET HESAPLA
    @Override
    public double ucretHesapla() {
        // VIP paketin kendi fiyatlandırma mantığı: Temel fiyatın %25 fazlası
        return getTemelFiyat() * 1.25;
    }

}