package sporSalonuÜyelikVeAntrenmanProgramı;

// KARDİYO ANTRENMANI SINIFI
public class Kardiyo extends Antrenman {
    
    private static final long serialVersionUID = 1L;
    
    private double tempo; 
    private String aletTuru; 
    private double egim;
    private int direncSeviyesi;

    public Kardiyo(String isim, String kategori, int sureDakika, String zorlukSeviyesi, double tempo, String aletTuru, double egim, int direncSeviyesi) {
        super(isim, kategori, sureDakika, zorlukSeviyesi);
        setTempo(tempo);
        setAletTuru(aletTuru);
        setEgim(egim);
        setDirencSeviyesi(direncSeviyesi);
    }

    public double getTempo() { return tempo; }

    public void setTempo(double tempo) {
        if (tempo < 1.0 || tempo > 150.0) {
            throw new IllegalArgumentException("Tempo değeri 1 ile 150 arasında olmalıdır!");
        }
        this.tempo = tempo;
    }

    public String getAletTuru() { return aletTuru; }

    public void setAletTuru(String aletTuru) {
        if (aletTuru == null || aletTuru.trim().isEmpty()) {
            throw new IllegalArgumentException("Alet türü boş olamaz!");
        }
        this.aletTuru = aletTuru.trim();
    }

    public double getEgim() { return egim; }

    public void setEgim(double egim) {
        if (egim < 0.0 || egim > 25.0) {
            throw new IllegalArgumentException("Makine eğimi %0 ile %25 arasında olmalıdır!");
        }
        this.egim = egim;
    }

    public int getDirencSeviyesi() { return direncSeviyesi; }

    public void setDirencSeviyesi(int direncSeviyesi) {
        if (direncSeviyesi < 1 || direncSeviyesi > 30) {
            throw new IllegalArgumentException("Direnç seviyesi 1 ile 30 arasında olmalıdır!");
        }
        this.direncSeviyesi = direncSeviyesi;
    }

    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.15; // Çarpanı biraz daha kıstık
        if (z.contains("orta")) return 1.05;
        return 1.0;
    }

    // ALET ÇARPANI - DAHA GERÇEKÇİ KATSAYILAR
    private double getAletCarpani() {
        String alet = aletTuru.toLowerCase();
        // Koşu: 10 km/s hızda çarpan 0.9 (Yaklaşık 9 MET)
        if (alet.contains("koşu") || alet.contains("kosu") || alet.contains("treadmill")) return 0.9;
        
        // İp Atlama: Dakikada 120 atlama yapan 100 kilo biri için çarpan 0.08
        // Hesap: 120 * 0.08 = 9.6 MET. (Orta-Yüksek yoğunluk)
        if (alet.contains("ip") || alet.contains("atlama")) return 0.08;
        
        // Bisiklet: Çarpan 0.3
        if (alet.contains("bisiklet") || alet.contains("bike")) return 0.3;
        
        // Kürek: Çarpan 0.65
        if (alet.contains("kürek") || alet.contains("kurek") || alet.contains("rowing")) return 0.65;
        
        return 0.5; 
    }

    @Override
    public double kaloriHesapla(Uye uye) {
        double sureSaat = getSureDakika() / 60.0;
        
        // Yeni çarpanla daha dengeli MET hesabı
        double metDegeri = (tempo * getAletCarpani()); 
        
        // Eğim ve direnç artışlarını daha lineer hale getirdik
        double egimCarpani = 1.0 + (egim * 0.02); // Eğim etkisi azaltıldı
        double direncCarpani = 1.0 + ((direncSeviyesi - 1) * 0.015);
        
        return sureSaat * metDegeri * uye.getKilo() * getZorlukCarpani() * egimCarpani * direncCarpani;
    }

    @Override
    public void detayGoster() {
        System.out.println("Kardiyo: " + getIsim() + " | Alet: " + aletTuru + 
                           " | Tempo: " + tempo + " | Eğim: %" + egim + 
                           " | Direnç: " + direncSeviyesi);
    }
}
