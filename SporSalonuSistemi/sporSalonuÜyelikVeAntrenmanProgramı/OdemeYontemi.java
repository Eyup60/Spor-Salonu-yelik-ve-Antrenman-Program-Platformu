package sporSalonuÜyelikVeAntrenmanProgramı;

public abstract class OdemeYontemi {
    protected double tutar; // Alt sınıflardan erişilebilmesi için protected

    public OdemeYontemi(double tutar) {
        this.tutar = tutar;
    }

    // Çok biçimlilik (Polymorphism) için alt sınıfların ezeceği metot
    public abstract String odemeAl() throws GecersizOdemeException;

    public double getTutar() {
        return tutar;
    }
}