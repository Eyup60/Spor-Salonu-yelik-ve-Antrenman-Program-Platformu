package sporSalonuÜyelikVeAntrenmanProgramı;

// NAKİT ÖDEME SINIFI
public class NakitOdeme extends OdemeYontemi {
    
    // NAKİT ÖDEME YAPICI METOT
    public NakitOdeme(double tutar) {
        super(tutar);
    }

    // ÖDEME AL
    @Override
    public String odemeAl() throws GecersizOdemeException {
        if (tutar <= 0) {
            throw new GecersizOdemeException("Hata: Ödenecek tutar 0 veya negatif olamaz!");
        }
        return tutar + " TL nakit olarak kasaya tahsil edildi.";
    }
}