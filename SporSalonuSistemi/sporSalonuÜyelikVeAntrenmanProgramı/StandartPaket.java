package sporSalonuÜyelikVeAntrenmanProgramı;

public class StandartPaket extends UyelikPaketi {
    private static double baslangicFiyat = 500.0;

    public StandartPaket() {
        super("Standart Paket", baslangicFiyat, 30);
    }

    public static void setBaslangicFiyat(double yeniFiyat) {
        baslangicFiyat = yeniFiyat;
    }

    @Override
    public double ucretHesapla() {
        return getTemelFiyat();
    }

    @Override
    public int getMaksimumOzelDers() {
        return 2; // Standart paket sınırı
    }
}