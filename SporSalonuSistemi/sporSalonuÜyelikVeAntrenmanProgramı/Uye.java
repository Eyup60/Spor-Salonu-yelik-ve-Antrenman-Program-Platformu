package sporSalonuÜyelikVeAntrenmanProgramı;

import java.time.LocalDate;

public class Uye extends Kullanici {
    private static final long serialVersionUID = 1L;

    private double boy;
    private double kilo;
    private int yas;
    private double yağOrani;
    private LocalDate kayitTarihi;

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
    
    public double getBoy() {
        return this.boy;
    }

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

    public double getKilo() {
        return this.kilo;
    }

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

    public int getYas() {
        return this.yas;
    }

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

    public double getYağOrani() {
        return this.yağOrani;
    }

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

    public LocalDate getKayitTarihi() {
        return this.kayitTarihi;
    }

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
    
    public String antrenorum() {
        // ADMİN ÜZERİNDEN BU ÜYEDEN SORUMLU OLAN ANTRENÖRÜN İSMİNİ GETİRİR
        return Admin.anternorBulUyeIle(this);
    }

    @Override
    public void displayInfo() {
        // ÜYENİN TÜM PROFİL BİLGİLERİNİ VE ANALİZ SONUÇLARINI KONSOLA YAZDIRIR
        System.out.println("--- ÜYE PROFİLİ ---");
        System.out.println("İsim :"+getIsim());
        System.out.println("Soyisim: "+getSoyisim());
        System.out.println("Atanan Koc: "+this.antrenorum());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Kayıt Tarihi: " + this.kayitTarihi);
        System.out.println("Fiziksel Veriler: " + this.boy + " cm / " + this.kilo + " kg");
        System.out.printf("Vücut Kitle Endeksi: %.2f\n", this.vucutKitleEndeksiHesapla());
    }

    public double vucutKitleEndeksiHesapla() {
        // BOY VE KİLO VERİLERİNİ KULLANARAK VÜCUT KİTLE ENDEKSİNİ (BMI) HESAPLAR
        double boyMetre = this.boy / 100.0;
        return this.kilo / (boyMetre * boyMetre);
    }
}