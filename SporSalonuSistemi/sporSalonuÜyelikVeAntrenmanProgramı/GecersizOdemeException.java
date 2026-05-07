package sporSalonuÜyelikVeAntrenmanProgramı;

// GEÇERSİZ ÖDEME İSTİSNASI SINIFI
public class GecersizOdemeException extends Exception {
    private static final long serialVersionUID = 1L;

    // YAPICI METOT
    public GecersizOdemeException(String mesaj) {
        super(mesaj);
    }
}