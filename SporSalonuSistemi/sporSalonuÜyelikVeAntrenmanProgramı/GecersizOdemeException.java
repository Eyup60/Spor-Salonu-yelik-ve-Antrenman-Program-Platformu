package sporSalonuÜyelikVeAntrenmanProgramı;

public class GecersizOdemeException extends Exception {
    private static final long serialVersionUID = 1L;

    public GecersizOdemeException(String mesaj) {
        super(mesaj);
    }
}