package sporSalonuÜyelikVeAntrenmanProgramı;

public class NakitOdeme extends OdemeYontemi {
    
    public NakitOdeme(double tutar) {
        super(tutar);
    }

    @Override
    public String odemeAl() throws GecersizOdemeException {
        if (tutar <= 0) {
            throw new GecersizOdemeException("Hata: Ödenecek tutar 0 veya negatif olamaz!");
        }
        return tutar + " TL nakit olarak kasaya tahsil edildi.";
    }
}