package sporSalonuÜyelikVeAntrenmanProgramı;

// STANDART PAKET SINIFI
public class StandartPaket extends UyelikPaketi {

    // 1. Fiyat güncelleme ekranının hata vermemesi için statik değişken (Az önceki hata)
    private static double baslangicFiyat = 500.0;

    // Yapıcı metot (Constructor)
    public StandartPaket() {
        super("Standart Paket", baslangicFiyat, 30);
    }

    // Fiyat güncelleme arayüzünün kullandığı eksik metot
    public static void setBaslangicFiyat(double yeniFiyat) {
        baslangicFiyat = yeniFiyat;
    }

    // ÜCRET HESAPLA
    @Override
    public double ucretHesapla() {
        return getTemelFiyat();
    }

    // --- AŞAĞIDAKİLER ANA SINIFTAN GELEN ZORUNLU METOTLAR ---

    @Override
    public String getHizmetDetayi() {
        return "• Hafta içi 09:00-21:00 arası giriş\n• Fitness ve Kardiyo alanı kullanımı\n• Standart soyunma kabini";
    }

    @Override
    public int getMaksimumOzelDers() {
        return 2; // Standart paket maksimum 2 özel ders alabilir
    }
}