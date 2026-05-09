package sporSalonuÜyelikVeAntrenmanProgramı;

/**
 * AĞIRLIK ANTRENMANI SINIFI
 * Ağırlık kaldırma, hipertrofi ve kuvvet antrenmanlarını modeller.
 * Antrenman hacmi (set x tekrar x ağırlık) ve dinlenme sürelerini baz alarak kalori hesaplar.
 */
public class Agirlik extends Antrenman {
    
    private static final long serialVersionUID = 1L;
    
    // Antrenman hacmini ve şiddetini belirleyen teknik değişkenler
    private int setSayisi;              // Yapılan toplam set sayısı
    private int tekrarSayisi;           // Set başına yapılan tekrar sayısı
    private double ortalamaAgirlik;     // Kullanılan ortalama yük (kg)
    private String hedefKasGrubu;       // Çalıştırılan ana kas grubu (Bacak, Göğüs vb.)
    private String ekipmanTuru;         // Kullanılan ekipman (Dumbbell, Barbell, Makine)
    private int dinlenmeSuresiSaniye;   // Set aralarındaki dinlenme süresi

    /**
     * Yapıcı Metot: Ağırlık antrenmanı nesnesini başlatır.
     */
    public Agirlik(String isim, String kategori, int sureDakika, String zorlukSeviyesi, 
                   int setSayisi, int tekrarSayisi, double ortalamaAgirlik, 
                   String hedefKasGrubu, String ekipmanTuru, int dinlenmeSuresiSaniye) {
        super(isim, kategori, sureDakika, zorlukSeviyesi);
        setSetSayisi(setSayisi);
        setTekrarSayisi(tekrarSayisi);
        setOrtalamaAgirlik(ortalamaAgirlik);
        setHedefKasGrubu(hedefKasGrubu);
        setEkipmanTuru(ekipmanTuru);
        setDinlenmeSuresiSaniye(dinlenmeSuresiSaniye);
    }

    // --- Erişimci ve Mutatör Metotlar (Doğrulama İçerir) ---

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

    public double getOrtalamaAgirlik() { return ortalamaAgirlik; }

    public void setOrtalamaAgirlik(double ortalamaAgirlik) {
        if (ortalamaAgirlik < 1.0 || ortalamaAgirlik > 400.0) throw new IllegalArgumentException("Kaldırılan ağırlık 1 ile 400 kg arasında olmalıdır!");
        this.ortalamaAgirlik = ortalamaAgirlik;
    }

    public String getHedefKasGrubu() { return hedefKasGrubu; }

    public void setHedefKasGrubu(String hedefKasGrubu) {
        if (hedefKasGrubu == null || hedefKasGrubu.trim().isEmpty()) throw new IllegalArgumentException("Hedef kas grubu boş olamaz!");
        this.hedefKasGrubu = hedefKasGrubu.trim();
    }

    public String getEkipmanTuru() { return ekipmanTuru; }

    public void setEkipmanTuru(String ekipmanTuru) {
        if (ekipmanTuru == null || ekipmanTuru.trim().isEmpty()) throw new IllegalArgumentException("Ekipman türü boş olamaz!");
        this.ekipmanTuru = ekipmanTuru.trim();
    }

    public int getDinlenmeSuresiSaniye() { return dinlenmeSuresiSaniye; }

    public void setDinlenmeSuresiSaniye(int dinlenmeSuresiSaniye) {
        if (dinlenmeSuresiSaniye < 10 || dinlenmeSuresiSaniye > 300) throw new IllegalArgumentException("Dinlenme süresi 10 ile 300 saniye arasında olmalıdır!");
        this.dinlenmeSuresiSaniye = dinlenmeSuresiSaniye;
    }

    // --- Hesaplama Çarpanları ---

    /**
     * Antrenman zorluk seviyesine göre katsayı döner.
     */
    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.2; // Bilimsel olarak %20 artış daha makul.
        if (z.contains("orta")) return 1.1;
        return 1.0;
    }

    /**
     * Kullanılan ekipmanın dengeleyici kaslara ve efora etkisini belirler.
     */
    private double getEkipmanCarpani() {
        String e = ekipmanTuru.toLowerCase();
        if (e.contains("dumbbell") || e.contains("serbest")) return 1.1; // Serbest ağırlıklar daha fazla enerji harcatır.
        if (e.contains("barbell") || e.contains("halter")) return 1.05; 
        if (e.contains("makine") || e.contains("kablo")) return 1.0; 
        return 1.0;
    }

    /**
     * Hedeflenen kas grubunun büyüklüğüne göre enerji tüketim farkını belirler.
     */
    private double getKasGrubuCarpani() {
        String k = hedefKasGrubu.toLowerCase();
        if (k.contains("bacak") || k.contains("sırt") || k.contains("sirt")) return 1.15; // Büyük kaslar daha fazla yakıt yakar.
        if (k.contains("göğüs") || k.contains("gogus") || k.contains("omuz")) return 1.05;
        if (k.contains("bilek") || k.contains("kol") || k.contains("biceps") || 
            k.contains("triceps") || k.contains("karın") || k.contains("karin") || 
            k.contains("kalf") || k.contains("baldır")) return 0.9;
        return 1.0; 
    }

    /**
     * KALORİ HESAPLAMA MANTIĞI
     * Net aktif süre ve toplam antrenman hacmini harmanlayarak gerçekçi bir sonuç üretir.
     */
    @Override
    public double kaloriHesapla(Uye uye) {
        // Toplam süreden dinlenme sürelerini çıkararak gerçek çalışma süresini (saat cinsinden) bulur.
        double aktifSureSaat = (getSureDakika() - ((setSayisi * dinlenmeSuresiSaniye) / 60.0)) / 60.0;
        if(aktifSureSaat <= 0) aktifSureSaat = 0.1; 
        
        // Temel Efor: Ağırlık çalışması için ortalama 3.5 MET değeri kullanılır.
        double temelEfor = aktifSureSaat * 3.5 * uye.getKilo(); 
        
        // Hacim Eforu: Kaldırılan tonajın (set x tekrar x kg) metabolik yükü (0.015'ten 0.002'ye çekildi).
        double toplamHacim = setSayisi * tekrarSayisi * ortalamaAgirlik;
        double hacimEforu = toplamHacim * 0.002;
        
        // Zorluk, ekipman ve kas grubu çarpanları ile nihai değer hesaplanır.
        return (temelEfor + hacimEforu) * getZorlukCarpani() * getEkipmanCarpani() * getKasGrubuCarpani();
    }

    /**
     * Antrenmanın teknik özetini formatlı bir şekilde ekrana basar.
     */
    @Override
    public void detayGoster() {
        System.out.println("Ağırlık: " + getIsim() + " [" + hedefKasGrubu + " - " + ekipmanTuru + "] | " + 
                           setSayisi + "x" + tekrarSayisi + " | Ağırlık: " + ortalamaAgirlik + " kg | " +
                           "Dinlenme: " + dinlenmeSuresiSaniye + "sn");
    }
}
