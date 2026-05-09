package sporSalonuÜyelikVeAntrenmanProgramı;

public class PremiumPaket extends UyelikPaketi {
    private static double baslangicFiyat = 800.0;

    public PremiumPaket() {
        super("Premium Paket", baslangicFiyat, 30);
    }

    public static void setBaslangicFiyat(double yeniFiyat) {
        baslangicFiyat = yeniFiyat;
    }

    @Override
    public double ucretHesapla() {
        return getTemelFiyat() * 1.1; // Premium %10 ek ücret
    }

    @Override
    public int getMaksimumOzelDers() {
        return 5; // Premium paket sınırı
    }
}