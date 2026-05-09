package sporSalonuÜyelikVeAntrenmanProgramı;

// AĞIRLIK ANTRENMANI VERİLERİNİ VE ÖZEL KALORİ HESAPLAMA MANTIĞINI BARINDIRAN SINIF
public class Agirlik extends Antrenman {
    
    private static final long serialVersionUID = 1L;
    private int setSayisi;
    private int tekrarSayisi;
    private double ortalamaAgirlik;
    private String hedefKasGrubu; 
    private String ekipmanTuru; 
    private int dinlenmeSuresiSaniye; 

    // AĞIRLIK ANTRENMANI NESNESİNİ TÜM ÖZELLİKLERİYLE BAŞLATAN YAPICI METOT
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

    public int getSetSayisi() { return setSayisi; }

    // SET SAYISINI GEÇERLİ SINIRLARDA DENETLEYEREK GÜNCELLEYEN METOT
    public void setSetSayisi(int setSayisi) {
        if (setSayisi < 1 || setSayisi > 15) throw new IllegalArgumentException("Set sayısı 1 ile 15 arasında olmalıdır!");
        this.setSayisi = setSayisi;
    }

    public int getTekrarSayisi() { return tekrarSayisi; }

    // TEKRAR SAYISINI MANTIKSAL SINIRLARDA KONTROL EDEN METOT
    public void setTekrarSayisi(int tekrarSayisi) {
        if (tekrarSayisi < 1 || tekrarSayisi > 100) throw new IllegalArgumentException("Tekrar sayısı 1 ile 100 arasında olmalıdır!");
        this.tekrarSayisi = tekrarSayisi;
    }

    public double getOrtalamaAgirlik() { return ortalamaAgirlik; }

    // KALDIRILAN AĞIRLIĞIN GÜVENLİ ARALIKTA OLUP OLMADIĞINI DENETLEYEN METOT
    public void setOrtalamaAgirlik(double ortalamaAgirlik) {
        if (ortalamaAgirlik < 1.0 || ortalamaAgirlik > 400.0) throw new IllegalArgumentException("Kaldırılan ağırlık 1 ile 400 kg arasında olmalıdır!");
        this.ortalamaAgirlik = ortalamaAgirlik;
    }

    public String getHedefKasGrubu() { return hedefKasGrubu; }

    // HEDEF KAS GRUBU BİLGİSİNİN BOŞ BIRAKILMASINI ENGELLEYEN METOT
    public void setHedefKasGrubu(String hedefKasGrubu) {
        if (hedefKasGrubu == null || hedefKasGrubu.trim().isEmpty()) throw new IllegalArgumentException("Hedef kas grubu boş olamaz!");
        this.hedefKasGrubu = hedefKasGrubu.trim();
    }

    public String getEkipmanTuru() { return ekipmanTuru; }

    // KULLANILAN EKİPMAN BİLGİSİNİN DOĞRULUĞUNU KONTROL EDEN METOT
    public void setEkipmanTuru(String ekipmanTuru) {
        if (ekipmanTuru == null || ekipmanTuru.trim().isEmpty()) throw new IllegalArgumentException("Ekipman türü boş olamaz!");
        this.ekipmanTuru = ekipmanTuru.trim();
    }

    public int getDinlenmeSuresiSaniye() { return dinlenmeSuresiSaniye; }

    // DİNLENME SÜRESİNİN ANTRENMAN VERİMİ İÇİN UYGUN ARALIKTA OLMASINI SAĞLAYAN METOT
    public void setDinlenmeSuresiSaniye(int dinlenmeSuresiSaniye) {
        if (dinlenmeSuresiSaniye < 10 || dinlenmeSuresiSaniye > 300) throw new IllegalArgumentException("Dinlenme süresi 10 ile 300 saniye arasında olmalıdır!");
        this.dinlenmeSuresiSaniye = dinlenmeSuresiSaniye;
    }

    // ANTRENMANIN ZORLUK DÜZEYİNE GÖRE KALORİ ÇARPANI DÖNDÜREN YARDIMCI METOT
    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.3;
        if (z.contains("orta")) return 1.1;
        return 1.0;
    }

    // KULLANILAN EKİPMANIN STABİLİTE VE EFOR ETKİSİNE GÖRE ÇARPAN DÖNDÜREN METOT
    private double getEkipmanCarpani() {
        String e = ekipmanTuru.toLowerCase();
        if (e.contains("dumbbell") || e.contains("serbest")) return 1.15; 
        if (e.contains("barbell") || e.contains("halter")) return 1.10; 
        if (e.contains("makine") || e.contains("kablo")) return 1.0; 
        return 1.0;
    }

    // ÇALIŞTIRILAN KAS GRUBUNUN BÜYÜKLÜĞÜNE GÖRE ENERJİ TÜKETİM ÇARPANI DÖNDÜREN METOT
    private double getKasGrubuCarpani() {
        String k = hedefKasGrubu.toLowerCase();
        if (k.contains("bacak") || k.contains("sırt") || k.contains("sirt")) return 1.2; 
        if (k.contains("göğüs") || k.contains("gogus") || k.contains("omuz")) return 1.1;
        if (k.contains("bilek") || k.contains("kol") || k.contains("biceps") || 
            k.contains("triceps") || k.contains("karın") || k.contains("karin") || 
            k.contains("kalf") || k.contains("baldır")) return 0.9;
        return 1.0; 
    }

    // ÜYENİN FİZİKSEL VERİLERİNİ VE ANTRENMAN HACMİNİ KULLANARAK TOPLAM YAKILAN KALORİYİ HESAPLAR
    @Override
    public double kaloriHesapla(Uye uye) {
        // TOPLAM SÜREDEN DİNLENME SÜRELERİNİ ÇIKARTARAK NET AKTİF SÜREYİ BULUR
        double aktifSureSaat = (getSureDakika() - ((setSayisi * dinlenmeSuresiSaniye) / 60.0)) / 60.0;
        if(aktifSureSaat <= 0) aktifSureSaat = 0.1; 
        
        // METABOLİK EŞDEĞER ÜZERİNDEN TEMEL ENERJİ TÜKETİMİNİ HESAPLAR
        double temelEfor = aktifSureSaat * 5.0 * uye.getKilo(); 
        
        // KALDIRILAN TOPLAM YÜK (SET x TEKRAR x AĞIRLIK) ÜZERİNDEN EKSTRA EFORU HESAPLAR
        double toplamHacim = setSayisi * tekrarSayisi * ortalamaAgirlik;
        double hacimEforu = toplamHacim * 0.015;
        
        // TÜM ÇARPANLARI UYGULAYARAK NİHAİ KALORİ DEĞERİNİ DÖNDÜRÜR
        return (temelEfor + hacimEforu) * getZorlukCarpani() * getEkipmanCarpani() * getKasGrubuCarpani();
    }

    // ANTRENMANIN TÜM TEKNİK DETAYLARINI KONSOLA ANLAŞILIR BİR BİÇİMDE YAZDIRIR
    @Override
    public void detayGoster() {
        System.out.println("Ağırlık: " + getIsim() + " [" + hedefKasGrubu + " - " + ekipmanTuru + "] | " + 
                           setSayisi + "x" + tekrarSayisi + " | Ağırlık: " + ortalamaAgirlik + " kg | " +
                           "Dinlenme: " + dinlenmeSuresiSaniye + "sn");
    }
}