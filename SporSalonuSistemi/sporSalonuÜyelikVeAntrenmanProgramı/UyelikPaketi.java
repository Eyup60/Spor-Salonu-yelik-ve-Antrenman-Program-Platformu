package sporSalonuÜyelikVeAntrenmanProgramı;

import java.io.Serializable;

/**
 * UyelikPaketi: Projenin "Abstraction" (Soyutlama) temelidir.
 * Bu sınıf 'abstract' olduğu için direkt nesnesi oluşturulamaz (new UyelikPaketi() yapılamaz).
 * Sadece Standart, Premium veya VIP paketler üzerinden kalıtım alınarak kullanılır.
 */
public abstract class UyelikPaketi implements Serializable {
    private static final long serialVersionUID = 1L;

    // Encapsulation: Değişkenler private tutularak doğrudan erişim engellenmiştir.
    private String paketAdi;
    private double temelFiyat;
    private int sureAy;

    // Yapıcı Metot (Constructor)
    public UyelikPaketi(String paketAdi, double temelFiyat, int sureAy) {
        this.paketAdi = paketAdi;
        this.temelFiyat = temelFiyat;
        this.sureAy = sureAy;
    }

    /**
     * Polymorphism (Çok Biçimlilik) İsteri:
     * Bu metot abstract tanımlanmıştır. Her alt sınıf (Standart, Premium, VIP)
     * kendi fiyat hesaplama mantığını bu metodu @Override ederek uygulayacaktır.
     */
    public abstract double ucretHesapla();

    /**
     * UcretHesaplayici sınıfında yaşadığımız hatayı çözen kritik metot.
     * Her paketin kendine has bir özel ders sınırı olacağını garanti eder.
     */
    public abstract int getMaksimumOzelDers();

    // --- GETTER VE SETTER METOTLARI (Encapsulation) ---

    public String getPaketAdi() {
        return paketAdi;
    }

    public void setPaketAdi(String paketAdi) {
        this.paketAdi = paketAdi;
    }

    public double getTemelFiyat() {
        return temelFiyat;
    }

    // Mantıksal kontrol eklenmiş Setter örneği
    public void setTemelFiyat(double temelFiyat) {
        if (temelFiyat < 0) {
            throw new IllegalArgumentException("Fiyat negatif olamaz!");
        }
        this.temelFiyat = temelFiyat;
    }

    public int getSureAy() {
        return sureAy;
    }

    public void setSureAy(int sureAy) {
        if (sureAy <= 0) {
            throw new IllegalArgumentException("Süre en az 1 ay olmalıdır!");
        }
        this.sureAy = sureAy;
    }

    /**
     * Nesnenin yazdırılabilir halini döndürür.
     */
    @Override
    public String toString() {
        return paketAdi + " (" + sureAy + " Ay)";
    }
}