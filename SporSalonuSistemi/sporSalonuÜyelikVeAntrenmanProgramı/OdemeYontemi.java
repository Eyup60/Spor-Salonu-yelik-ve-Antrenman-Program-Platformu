package sporSalonuÜyelikVeAntrenmanProgramı;

// ÖDEME YÖNTEMİ SINIFI
// SOYUT SINIF OLARAK TÜM ÖDEME YÖNTEMLERİNİ TEMSİL EDER
// POLİMORFİK YAPI SAĞLAR
public abstract class OdemeYontemi {
    // TUTAR DEĞİŞKENİ
    // ALT SINIFLARDAN ERİŞİLEBİLMESİ İÇİN PROTECTED
    protected double tutar;

    // ÖDEME YÖNTEMİ YAPICI METOT
    // TUTAR BİLGİSİNİ ALIR VE DEĞİŞKENE ATAR
    public OdemeYontemi(double tutar) {
        this.tutar = tutar;
    }

    // ÖDEME AL METOTU
    // SOYUT METOT OLARAK ALT SINIFLAR TARAFINDAN UYGULANMALIDIR
    // GEÇERSİZ ÖDEME DURUMUNDA İSTİSNA FIRLATIR
    public abstract String odemeAl() throws GecersizOdemeException;

    // TUTAR GETİR METOTU
    // ÖDEME TUTARINI DÖNDÜRÜR
    public double getTutar() {
        return tutar;
    }
}