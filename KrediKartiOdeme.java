package sporSalonuÜyelikVeAntrenmanProgramı;

public class KrediKartiOdeme extends OdemeYontemi {
    private String kartNumarasi;

    public KrediKartiOdeme(double tutar, String kartNumarasi) {
        super(tutar);
        this.kartNumarasi = kartNumarasi;
    }

    @Override
    public String odemeAl() throws GecersizOdemeException {
        if (tutar <= 0) {
            throw new GecersizOdemeException("Hata: Ödenecek tutar 0 veya negatif olamaz!");
        }
        return tutar + " TL, " + kartNumarasi + " numaralı kart ile başarıyla ödendi.";
    }
}