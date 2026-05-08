package sporSalonuÜyelikVeAntrenmanProgramı;

import java.time.LocalDate;

// ÜYE SINIFI
public class Uye extends Kullanici {
    private static final long serialVersionUID = 1L;

    // Üyenin mevcut paket bilgisi (null ise paket seçilmemiş demektir)
    private UyelikPaketi paket;
    private double boy;
    private double kilo;
    private int yas;
    private double yağOrani;
    private LocalDate kayitTarihi;

    // ÜYE YAPICI METOT
    protected Uye(String isim,String soyisim,String email, String password, double boy, double kilo, int yas, double yağOrani) {
        // ÜST SINIFIN (KULLANICI) YAPICI METODUNU ÇAĞIRIR VE ROLÜ ÜYE OLARAK BELİRLER
        super(isim,soyisim,email, password, Role.UYE);
        setBoy(boy);
        setKilo(kilo);
        setYas(yas);
        setYağOrani(yağOrani);
        // ÜYELİK BAŞLANGIÇ TARİHİNİ SİSTEMİN O ANKİ TARİHİNE GÖRE OTOMATİK ATAR
        setKayitTarihi(LocalDate.now());
    }

    // Kolay test ve çağrılar için daha kısa bir yapıcı metot (varsayılan fiziksel değerlerle)
    public Uye(String isim, String soyisim, String email, String password) {
        // Varsayılan anlamlı değerler atıyoruz
        this(isim, soyisim, email, password, 170.0, 70.0, 30, 20.0);
    }
    
    // BOY GETİR
    public double getBoy() {
        return this.boy;
    }

    // BOY AYARLA
    public void setBoy(double boy) {
        // BOY VERİSİ İÇİN MANTIKSAL SINIRLARI KONTROL EDER (120-250 CM)
        if (boy < 120.0) {
            throw new IllegalArgumentException("Boy için minimum girdi 120 cm'dir.");
        } else if (boy > 250.0) {
            throw new IllegalArgumentException("Boy için maksimum girdi 250 cm'dir.");
        } else {
            this.boy = boy;
        }
    }

    // KİLO GETİR
    public double getKilo() {
        return this.kilo;
    }

    // KİLO AYARLA
    public void setKilo(double kilo) {
        // KİLO VERİSİ İÇİN MANTIKSAL SINIRLARI KONTROL EDER (40-250 KG)
        if (kilo < 40.0) {
            throw new IllegalArgumentException("Kilo için minimum girdi 40 kg'dir.");
        } else if (kilo > 250.0) {
            throw new IllegalArgumentException("Kilo için maksimum girdi 250 kg'dir.");
        } else {
            this.kilo = kilo;
        }
    }

    // YAŞ GETİR
    public int getYas() {
        return this.yas;
    }

    // YAŞ AYARLA
    public void setYas(int yas) {
        // ÜYELİK İÇİN YAŞ SINIRLAMASINI DENETLER (15-65 YAŞ)
        if (yas < 15) {
            throw new IllegalArgumentException("Minimum yaş 15 olmalıdır!");
        } else if (yas > 65) {
            throw new IllegalArgumentException("Maksimum yaş 65 olmalıdır!");
        } else {
            this.yas = yas;
        }
    }

    // YAĞ ORANI GETİR
    public double getYağOrani() {
        return this.yağOrani;
    }

    // YAĞ ORANI AYARLA
    public void setYağOrani(double yağOrani) {
        // YAĞ ORANI GİRDİSİNİN DOĞRULUĞUNU TEYİT EDER
        if (yağOrani < 3.0) {
            throw new IllegalArgumentException("Yağ oranı 3'ün altında olamaz!");
        } else if (yağOrani > 55.0) {
            throw new IllegalArgumentException("Yağ oranı 55'in üstünde olamaz!");
        } else {
            this.yağOrani = yağOrani;
        }
    }

    // KAYIT TARİHİ GETİR
    public LocalDate getKayitTarihi() {
        return this.kayitTarihi;
    }

    // KAYIT TARİHİ AYARLA
    public void setKayitTarihi(LocalDate kayitTarihi) {
        // KAYIT TARİHİNİN BOŞ OLMASINI VEYA GELECEK BİR TARİH OLMASINI ENGELLER
        if (kayitTarihi == null) {
            throw new IllegalArgumentException("Kayıt tarihi boş olamaz!");
        } else if (kayitTarihi.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Kayıt tarihi bugünden ileri bir tarih olamaz!");
        } else {
            this.kayitTarihi = kayitTarihi;
        }
    }
    
    // ANTRENORUM
    public String antrenorum() {
        // ADMİN ÜZERİNDEN BU ÜYEDEN SORUMLU OLAN ANTRENÖRÜN İSMİNİ GETİRİR
        return Admin.anternorBulUyeIle(this);
    }

    @Override
    public void displayInfo() {
        // BİLGİ GÖSTER
        System.out.println("--- ÜYE PROFİLİ ---");
        System.out.println("İsim :"+getIsim());
        System.out.println("Soyisim: "+getSoyisim());
        System.out.println("Atanan Koc: "+this.antrenorum());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Kayıt Tarihi: " + this.kayitTarihi);
        System.out.println("Fiziksel Veriler: " + this.boy + " cm / " + this.kilo + " kg");
        System.out.printf("Vücut Kitle Endeksi: %.2f\n", this.vucutKitleEndeksiHesapla());
    }

    // VÜCUT KİTLE ENDEKSİ HESAPLA
    public double vucutKitleEndeksiHesapla() {
        // BOY VE KİLO VERİLERİNİ KULLANARAK VÜCUT KİTLE ENDEKSİNİ (BMI) HESAPLAR
        double boyMetre = this.boy / 100.0;
        return this.kilo / (boyMetre * boyMetre);
    }

    // Paket getter ve setter
    public UyelikPaketi getPaket() {
        return this.paket;
    }

    public void setPaket(UyelikPaketi paket) {
        this.paket = paket;
    }
}