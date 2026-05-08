package sporSalonuÜyelikVeAntrenmanProgramı;

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

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        if (tempo < 1.0 || tempo > 150.0) {
            throw new IllegalArgumentException("Tempo değeri 1 ile 150 arasında olmalıdır!");
        }
        this.tempo = tempo;
    }

    public String getAletTuru() {
        return aletTuru;
    }

    public void setAletTuru(String aletTuru) {
        if (aletTuru == null || aletTuru.trim().isEmpty()) {
            throw new IllegalArgumentException("Alet türü boş olamaz!");
        }
        this.aletTuru = aletTuru.trim();
    }

    public double getEgim() {
        return egim;
    }

    public void setEgim(double egim) {
        if (egim < 0.0 || egim > 25.0) {
            throw new IllegalArgumentException("Makine eğimi %0 ile %25 arasında olmalıdır!");
        }
        this.egim = egim;
    }

    public int getDirencSeviyesi() {
        return direncSeviyesi;
    }

    public void setDirencSeviyesi(int direncSeviyesi) {
        if (direncSeviyesi < 1 || direncSeviyesi > 30) {
            throw new IllegalArgumentException("Direnç seviyesi 1 ile 30 arasında olmalıdır!");
        }
        this.direncSeviyesi = direncSeviyesi;
    }

    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.5;
        if (z.contains("orta")) return 1.2;
        return 1.0;
    }

    private double getAletCarpani() {
        String alet = aletTuru.toLowerCase();
        if (alet.contains("koşu") || alet.contains("kosu") || alet.contains("treadmill")) return 1.2;
        if (alet.contains("bisiklet") || alet.contains("bike")) return 0.8;
        if (alet.contains("kürek") || alet.contains("kurek") || alet.contains("rowing")) return 1.1;
        if (alet.contains("eliptik") || alet.contains("merdiven")) return 1.0;
        return 1.0; 
    }

    @Override
    public double kaloriHesapla(Uye uye) {
        double sureSaat = getSureDakika() / 60.0;
        double metDegeri = (tempo * 0.5) * getAletCarpani(); 
        
        double egimCarpani = 1.0 + (egim * 0.10);
        double direncCarpani = 1.0 + ((direncSeviyesi - 1) * 0.05);
        
        return sureSaat * metDegeri * uye.getKilo() * getZorlukCarpani() * egimCarpani * direncCarpani;
    }

    @Override
    public void detayGoster() {
        System.out.println("Kardiyo: " + getIsim() + " | Alet: " + aletTuru + 
                           " | Tempo: " + tempo + " | Eğim: %" + egim + 
                           " | Direnç: " + direncSeviyesi);
    }
}
