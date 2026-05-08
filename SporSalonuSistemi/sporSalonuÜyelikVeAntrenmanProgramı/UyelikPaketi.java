package sporSalonuÜyelikVeAntrenmanProgramı;

// Projedeki tüm paketlerin ortak özelliklerini taşıyan Soyut Üst Sınıf (Abstract Class).
// OOP Prensibi: Soyutlama (Abstraction) - Bu sınıftan doğrudan "new" ile nesne üretilemez, sadece miras alınabilir.
public abstract class UyelikPaketi {

    // OOP Prensibi: Kapsülleme (Encapsulation) - Değişkenler private yapılarak dışarıdan doğrudan erişime kapatılmıştır.
    private String ad;
    private double temelFiyat;
    private int sure; // Paket süresi (Gün cinsinden)
	
    // Yapıcı Metot (Constructor) - Alt sınıflar oluşturulurken bu üst sınıfın verilerini doldurur
    public UyelikPaketi(String ad, double temelFiyat, int sure) {
        this.ad = ad;
        this.temelFiyat = temelFiyat;
        this.sure = sure;
    }
	
    // Gövdesiz (Abstract) Metot: Miras alan her alt sınıf (Standart, Premium, VIP)
    // bu metodu kendine özgü kurallarla ezmek (Override) ve doldurmak zorundadır.
    public abstract double ucretHesapla();

    // --- Getter ve Setter Metotları ---
    // Private değişkenlere güvenli bir şekilde erişim ve müdahale edilmesini sağlar.

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public double getTemelFiyat() {
        return temelFiyat;
    }

    public void setTemelFiyat(double temelFiyat) {
        this.temelFiyat = temelFiyat;
    }

    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }
	
}