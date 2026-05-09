package sporSalonuÜyelikVeAntrenmanProgramı;

// PREMIUM PAKET SINIFI
public class PremiumPaket extends UyelikPaketi {

    // 1. Fiyat güncelleme ekranı için statik taban fiyat değişkeni
    private static double baslangicFiyat = 800.0;

    // Yapıcı metot (Constructor)
    public PremiumPaket() {
        // "super" ile üst sınıfa; ad, dinamik fiyat ve süre gönderilir
        super("Premium Paket", baslangicFiyat, 30);
    }
	
    // Fiyat güncelleme arayüzünün kullandığı eksik metot
    public static void setBaslangicFiyat(double yeniFiyat) {
        baslangicFiyat = yeniFiyat;
    }

    // ÜCRET HESAPLA
    @Override
    public double ucretHesapla() {
        // Premium paketin kendi fiyatlandırma mantığı: Temel fiyatın %10 fazlası
        return getTemelFiyat() * 1.1;
    }

    // --- AŞAĞIDAKİLER ANA SINIFTAN GELEN ZORUNLU METOTLAR ---

    @Override
    public String getHizmetDetayi() {
        return "• 7/24 Sınırsız giriş hakkı\n• Fitness, Kardiyo ve Grup dersleri\n• Havuz ve Sauna kullanımı";
    }

    @Override
    public int getMaksimumOzelDers() {
        return 5; // Premium paket maksimum 5 özel ders alabilir
    }
}