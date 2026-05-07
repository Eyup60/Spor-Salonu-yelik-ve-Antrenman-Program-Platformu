package sporSalonuÜyelikVeAntrenmanProgramı;

// KREDİ KARTI ÖDEME SINIFI
public class KrediKartiOdeme extends OdemeYontemi {
    private String kartNumarasi;

    // KREDİ KARTI ÖDEME YAPICI METOT
    public KrediKartiOdeme(double tutar, String kartNumarasi) {
        super(tutar);
        this.kartNumarasi = kartNumarasi;
    }

    // ÖDEME AL
    @Override
    public String odemeAl() throws GecersizOdemeException {
        if (tutar <= 0) {
            throw new GecersizOdemeException("Hata: Ödenecek tutar 0 veya negatif olamaz!");
        }
        return tutar + " TL, " + kartNumarasi + " numaralı kart ile başarıyla ödendi.";
    }
}