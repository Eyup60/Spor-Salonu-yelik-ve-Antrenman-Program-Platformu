package sporSalonuÜyelikVeAntrenmanProgramı;

// KALİSTENİKS ANTRENMANI SINIFI
public class Kalisteniks extends Antrenman {
    
    private static final long serialVersionUID = 1L;
    private int setSayisi;
    private int tekrarSayisi;
    private double vucutAgirligiCarpani;
    private double ekstraAgirlikKg;
    private String hareketTuru;

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

    public int getSetSayisi() { return setSayisi; }

    public void setSetSayisi(int setSayisi) {
        if (setSayisi < 1 || setSayisi > 15) throw new IllegalArgumentException("Set sayısı 1 ile 15 arasında olmalıdır!");
        this.setSayisi = setSayisi;
    }

    public int getTekrarSayisi() { return tekrarSayisi; }

    public void setTekrarSayisi(int tekrarSayisi) {
        if (tekrarSayisi < 1 || tekrarSayisi > 100) throw new IllegalArgumentException("Tekrar sayısı 1 ile 100 arasında olmalıdır!");
        this.tekrarSayisi = tekrarSayisi;
    }

    public double getVucutAgirligiCarpani() { return vucutAgirligiCarpani; }

    public void setVucutAgirligiCarpani(double vucutAgirligiCarpani) {
        if (vucutAgirligiCarpani <= 0.0 || vucutAgirligiCarpani > 2.0) throw new IllegalArgumentException("Vücut ağırlığı çarpanı 0.0 ile 2.0 arasında olmalıdır!");
        this.vucutAgirligiCarpani = vucutAgirligiCarpani;
    }

    public double getEkstraAgirlikKg() { return ekstraAgirlikKg; }

    public void setEkstraAgirlikKg(double ekstraAgirlikKg) {
        if (ekstraAgirlikKg < 0.0 || ekstraAgirlikKg > 150.0) throw new IllegalArgumentException("Ekstra ağırlık 0 ile 150 kg arasında olmalıdır!");
        this.ekstraAgirlikKg = ekstraAgirlikKg;
    }

    public String getHareketTuru() { return hareketTuru; }

    public void setHareketTuru(String hareketTuru) {
        if (hareketTuru == null || hareketTuru.trim().isEmpty()) throw new IllegalArgumentException("Hareket türü boş olamaz!");
        this.hareketTuru = hareketTuru.trim();
    }

    // Zorluk çarpanları
    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.25; 
        if (z.contains("orta")) return 1.1;
        return 1.0;
    }

    // Hareket türü çarpanları, statik tutuşlar ve büyük kas grupları dikkate alındı.
    private double getHareketCarpani() {
        String h = hareketTuru.toLowerCase();
        if (h.contains("statik") || h.contains("izometrik")) return 1.15;
        if (h.contains("çekme") || h.contains("cekme") || h.contains("pull")) return 1.1;
        if (h.contains("bacak") || h.contains("alt")) return 1.1;
        return 1.05; 
    }

    @Override
    public double kaloriHesapla(Uye uye) {
        // Kullanılan vücut ağırlığı yüzdesi ve ek yükün toplamı
        double etkiKilosu = (uye.getKilo() * vucutAgirligiCarpani) + ekstraAgirlikKg;
        
        double aktifSureSaat = (getSureDakika() / 60.0);
        // Kalisteniks için temel efor katsayısı 3.5-4.0 (orta-yüksek yoğunluk) olarak belirlendi.
        double sureEforu = aktifSureSaat * 3.8 * uye.getKilo();
        
         
        // Bu sayede yapılan her tekrar, toplam kaloriye küçük ama gerçekçi bir ek yük bindirir.
        double toplamEfor = setSayisi * tekrarSayisi * etkiKilosu * 0.004;
        
        return (toplamEfor + sureEforu) * getZorlukCarpani() * getHareketCarpani();
    }

    @Override
    public void detayGoster() {
        String ekstraBilgi = ekstraAgirlikKg > 0 ? " | +Ağırlık: " + ekstraAgirlikKg + " kg" : "";
        System.out.println("Kalisteniks: " + getIsim() + " [" + hareketTuru + "] | " + 
                           setSayisi + "x" + tekrarSayisi + ekstraBilgi + " | Zorluk: " + getZorlukSeviyesi());
    }
}
