package sporSalonuÜyelikVeAntrenmanProgramı;

// VIP PAKET SINIFI
public class VIPPaket extends UyelikPaketi {

    // 1. Fiyat güncelleme ekranı için statik taban fiyat değişkeni
    private static double baslangicFiyat = 1200.0;

    // Yapıcı metot (Constructor)
    public VIPPaket() {
        // "super" ile üst sınıfa; ad, dinamik fiyat ve süre gönderilir
        super("VIP Paket", baslangicFiyat, 30);
    }
	
    // Fiyat güncelleme arayüzünün kullandığı eksik metot
    public static void setBaslangicFiyat(double yeniFiyat) {
        baslangicFiyat = yeniFiyat;
    }

    // ÜCRET HESAPLA
    @Override
    public double ucretHesapla() {
        // VIP paketin kendi fiyatlandırma mantığı: Temel fiyatın %25 fazlası
        return getTemelFiyat() * 1.25;
    }

    // --- AŞAĞIDAKİLER ANA SINIFTAN GELEN ZORUNLU METOTLAR ---

    @Override
    public String getHizmetDetayi() {
        return "• 7/24 Sınırsız giriş + Özel VIP Alanı\n• Havuz, Sauna, Hamam ve Masaj\n• Ücretsiz havlu ve VIP dolap hizmeti";
    }

    @Override
    public int getMaksimumOzelDers() {
        return 15; // VIP paket maksimum 15 özel ders alabilir
    }
}