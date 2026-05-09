package sporSalonuÜyelikVeAntrenmanProgramı;

public class VIPPaket extends UyelikPaketi {
    private static double baslangicFiyat = 1200.0;

    public VIPPaket() {
        super("VIP Paket", baslangicFiyat, 30);
    }

    public static void setBaslangicFiyat(double yeniFiyat) {
        baslangicFiyat = yeniFiyat;
    }

    @Override
    public double ucretHesapla() {
        return getTemelFiyat() * 1.25; // VIP %25 ek ücret
    }

    @Override
    public int getMaksimumOzelDers() {
        return 15; // VIP paket sınırı
    }
}