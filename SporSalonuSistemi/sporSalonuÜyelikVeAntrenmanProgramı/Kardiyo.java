package sporSalonuÜyelikVeAntrenmanProgramı;

/**
 * KARDİYO ANTRENMANI SINIFI
 * Koşu bandı, bisiklet, ip atlama gibi nabız odaklı egzersizleri temsil eder.
 */
public class Kardiyo extends Antrenman {
    
    private static final long serialVersionUID = 1L;
    
    // Kardiyo antrenmanına özgü teknik değişkenler
    private double tempo;           // Hız, atlama sayısı veya çekiş hızı
    private String aletTuru;        // Kullanılan ekipman (Koşu bandı, bisiklet vb.)
    private double egim;            // Yüzde cinsinden eğim (koşu bandı için)
    private int direncSeviyesi;     // Makine zorluk kademesi

    /**
     * Yapıcı Metot: Temel antrenman verilerini ve kardiyo özelliklerini başlatır.
     */
    public Kardiyo(String isim, String kategori, int sureDakika, String zorlukSeviyesi, double tempo, String aletTuru, double egim, int direncSeviyesi) {
        super(isim, kategori, sureDakika, zorlukSeviyesi);
        setTempo(tempo);
        setAletTuru(aletTuru);
        setEgim(egim);
        setDirencSeviyesi(direncSeviyesi);
    }

    // Tempo değerini günceller ve 1-150 arası güvenlik kontrolü yapar.
    public void setTempo(double tempo) {
        if (tempo < 1.0 || tempo > 150.0) {
            throw new IllegalArgumentException("Tempo değeri 1 ile 150 arasında olmalıdır!");
        }
        this.tempo = tempo;
    }

    public double getTempo() { return tempo; }

    // Alet türünü ayarlar ve geçersiz veri girişini engeller.
    public void setAletTuru(String aletTuru) {
        if (aletTuru == null || aletTuru.trim().isEmpty()) {
            throw new IllegalArgumentException("Alet türü boş olamaz!");
        }
        this.aletTuru = aletTuru.trim();
    }

    public String getAletTuru() { return aletTuru; }

    // Makine eğimini ayarlar (Geçerli aralık: %0-%25).
    public void setEgim(double egim) {
        if (egim < 0.0 || egim > 25.0) {
            throw new IllegalArgumentException("Makine eğimi %0 ile %25 arasında olmalıdır!");
        }
        this.egim = egim;
    }

    public double getEgim() { return egim; }

    // Direnç seviyesini ayarlar (1-30 arası kademe).
    public void setDirencSeviyesi(int direncSeviyesi) {
        if (direncSeviyesi < 1 || direncSeviyesi > 30) {
            throw new IllegalArgumentException("Direnç seviyesi 1 ile 30 arasında olmalıdır!");
        }
        this.direncSeviyesi = direncSeviyesi;
    }

    public int getDirencSeviyesi() { return direncSeviyesi; }

    // Antrenman zorluğuna göre hesaplamada kullanılacak katsayıyı belirler.
    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.15; 
        if (z.contains("orta")) return 1.05;
        return 1.0;
    }

    /**
     * ALET ÇARPANI HESABI
     * Seçilen aletin türüne göre MET (Metabolik Eşdeğer) bazlı katsayı döndürür.
     */
    private double getAletCarpani() {
        String alet = aletTuru.toLowerCase();
        
        // Koşu bandı: Hız ve kalori dengesi
        if (alet.contains("koşu") || alet.contains("kosu") || alet.contains("treadmill")) return 1.0;
        
        // İp atlama: Dakikadaki atlama sayısı çarpanı
        if (alet.contains("ip") || alet.contains("atlama")) return 0.07;
        
        // Kondisyon bisikleti çarpanı
        if (alet.contains("bisiklet") || alet.contains("bike")) return 0.35;
        
        // Kürek aleti SPM (Stroke Per Minute) çarpanı
        if (alet.contains("kürek") || alet.contains("kurek") || alet.contains("rowing")) return 0.35;
        
        return 0.5; 
    }

    /**
     * KALORİ HESAPLAMA MANTIĞI
     * Süre, tempo, alet tipi, üye kilosu, eğim ve direnç verilerini harmanlayarak
     * bilimsel MET standartlarına yakın bir sonuç üretir.
     */
    @Override
    public double kaloriHesapla(Uye uye) {
        double sureSaat = getSureDakika() / 60.0;
        
        // Normalize edilmiş metabolik değer (MET) hesabı
        double metDegeri = (tempo * getAletCarpani()); 
        
        // Eğim ve direncin kalori yakımına olan lineer etkileri
        double egimCarpani = 1.0 + (egim * 0.02); 
        double direncCarpani = 1.0 + ((direncSeviyesi - 1) * 0.012);
        
        // Tüm faktörlerin birleştiği nihai hesaplama
        return sureSaat * metDegeri * uye.getKilo() * getZorlukCarpani() * egimCarpani * direncCarpani;
    }

    // Antrenman özetini teknik detaylarıyla ekrana yazdırır.
    @Override
    public void detayGoster() {
        System.out.println("Kardiyo: " + getIsim() + " | Alet: " + aletTuru + 
                           " | Tempo: " + tempo + " | Eğim: %" + egim + 
                           " | Direnç: " + direncSeviyesi);
    }
}
