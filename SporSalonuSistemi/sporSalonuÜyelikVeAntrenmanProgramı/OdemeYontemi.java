package sporSalonuÜyelikVeAntrenmanProgramı;

// ÖDEME YÖNTEMİ SINIFI
public abstract class OdemeYontemi {
    protected double tutar; // Alt sınıflardan erişilebilmesi için protected

    // ÖDEME YÖNTEMİ YAPICI METOT
    public OdemeYontemi(double tutar) {
        this.tutar = tutar;
    }

    // ÖDEME AL
    public abstract String odemeAl() throws GecersizOdemeException;

    // TUTAR GETİR
    public double getTutar() {
        return tutar;
    }
}