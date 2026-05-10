package sporSalonuÜyelikVeAntrenmanProgramı;

// KALİSTENİKS ANTRENMANI SINIFI
// ANTRENMAN SINIFINDAN MİRAS ALIR
// VÜCUT AĞIRLIĞI VE KALİSTENİK HAREKETLERİNİ TEMSİL EDER
public class Kalisteniks extends Antrenman {
    
    // SERİALİZASYON VERSİYON NUMARASI
    private static final long serialVersionUID = 1L;
    
    // KALİSTENİK ÖZELLİKLERİ
    private int setSayisi;
    private int tekrarSayisi;
    private double vucutAgirligiCarpani;
    private double ekstraAgirlikKg;
    private String hareketTuru;

    // KALİSTENİK YAPICI METOT
    // TEMEL ANTRENMAN BİLGİLERİ VE KALİSTENİK ÖZELLİKLERİNİ AYARLAR
    public Kalisteniks(String isim, String kategori, int sureDakika, String zorlukSeviyesi, 
                       int setSayisi, int tekrarSayisi, double vucutAgirligiCarpani, 
                       double ekstraAgirlikKg, String hareketTuru) {
        super(isim, kategori, sureDakika, zorlukSeviyesi);
        setSetSayisi(setSayisi);
        setTekrarSayisi(tekrarSayisi);
        setVucutAgirligiCarpani(vucutAgirligiCarpani);
        setEkstraAgirlikKg(ekstraAgirlikKg);
        setHareketTuru(hareketTuru);
    }

    // SET SAYISI GETİR
    public int getSetSayisi() { return setSayisi; }

    // SET SAYISI AYARLA
    // 1 İLE 15 ARASI DEĞER KONTROLÜ
    public void setSetSayisi(int setSayisi) {
        if (setSayisi < 1 || setSayisi > 15) throw new IllegalArgumentException("Set sayısı 1 ile 15 arasında olmalıdır!");
        this.setSayisi = setSayisi;
    }

    // TEKRAR SAYISI GETİR
    public int getTekrarSayisi() { return tekrarSayisi; }

    // TEKRAR SAYISI AYARLA
    // 1 İLE 100 ARASI DEĞER KONTROLÜ
    public void setTekrarSayisi(int tekrarSayisi) {
        if (tekrarSayisi < 1 || tekrarSayisi > 100) throw new IllegalArgumentException("Tekrar sayısı 1 ile 100 arasında olmalıdır!");
        this.tekrarSayisi = tekrarSayisi;
    }

    // VÜCUT AĞIRLIĞI ÇARPANI GETİR
    public double getVucutAgirligiCarpani() { return vucutAgirligiCarpani; }

    // VÜCUT AĞIRLIĞI ÇARPANI AYARLA
    // 0.0 İLE 2.0 ARASI DEĞER KONTROLÜ
    public void setVucutAgirligiCarpani(double vucutAgirligiCarpani) {
        if (vucutAgirligiCarpani <= 0.0 || vucutAgirligiCarpani > 2.0) throw new IllegalArgumentException("Vücut ağırlığı çarpanı 0.0 ile 2.0 arasında olmalıdır!");
        this.vucutAgirligiCarpani = vucutAgirligiCarpani;
    }

    // EKSTRA AĞIRLIK GETİR
    public double getEkstraAgirlikKg() { return ekstraAgirlikKg; }

    // EKSTRA AĞIRLIK AYARLA
    // 0 İLE 150 KG ARASI DEĞER KONTROLÜ
    public void setEkstraAgirlikKg(double ekstraAgirlikKg) {
        if (ekstraAgirlikKg < 0.0 || ekstraAgirlikKg > 150.0) throw new IllegalArgumentException("Ekstra ağırlık 0 ile 150 kg arasında olmalıdır!");
        this.ekstraAgirlikKg = ekstraAgirlikKg;
    }

    // HAREKET TÜRÜ GETİR
    public String getHareketTuru() { return hareketTuru; }

    // HAREKET TÜRÜ AYARLA
    // BOŞ DEĞER KONTROLÜ YAPAR İSTİSNA FIRLATIR
    public void setHareketTuru(String hareketTuru) {
        if (hareketTuru == null || hareketTuru.trim().isEmpty()) throw new IllegalArgumentException("Hareket türü boş olamaz!");
        this.hareketTuru = hareketTuru.trim();
    }

    // ZORLUK ÇARPANI HESAPLA
    // ZORLUK SEVİYESİNE GÖRE KATSAYI DÖNDÜRÜR
    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.6;
        if (z.contains("orta")) return 1.3;
        return 1.0;
    }

    // HAREKET ÇARPANI HESAPLA
    // HAREKET TÜRÜNE GÖRE KATSAYI DÖNDÜRÜR
    private double getHareketCarpani() {
        String h = hareketTuru.toLowerCase();
        if (h.contains("statik") || h.contains("izometrik")) return 1.4;
        if (h.contains("çekme") || h.contains("cekme") || h.contains("pull")) return 1.2;
        if (h.contains("bacak") || h.contains("alt")) return 1.3;
        return 1.1; 
    }

    // KALORİ HESAPLA
    // KALİSTENİK ANTRENMANI İÇİN ÖZEL KALORİ HESABI
    @Override
    public double kaloriHesapla(Uye uye) {
        double etkiKilosu = (uye.getKilo() * vucutAgirligiCarpani) + ekstraAgirlikKg;
        
        double aktifSureSaat = (getSureDakika() / 60.0);
        double sureEforu = aktifSureSaat * 4.0 * uye.getKilo();
        
        double toplamEfor = setSayisi * tekrarSayisi * etkiKilosu * 0.02;
        
        return (toplamEfor + sureEforu) * getZorlukCarpani() * getHareketCarpani();
    }

    // DETAY GÖSTER
    // KALİSTENİK ANTRENMANI BİLGİLERİNİ EKRANA YAZDIRIR
    @Override
    public void detayGoster() {
        String ekstraBilgi = ekstraAgirlikKg > 0 ? " | +Ağırlık: " + ekstraAgirlikKg + " kg" : "";
        System.out.println("Kalisteniks: " + getIsim() + " [" + hareketTuru + "] | " + 
                           setSayisi + "x" + tekrarSayisi + ekstraBilgi + " | Zorluk: " + getZorlukSeviyesi());
    }
}
