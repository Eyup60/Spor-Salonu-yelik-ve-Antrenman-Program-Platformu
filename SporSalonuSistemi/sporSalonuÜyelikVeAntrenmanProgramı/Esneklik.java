package sporSalonuÜyelikVeAntrenmanProgramı;

// ESNEKLİK ANTRENMANI SINIFI
public class Esneklik extends Antrenman {
    
    private static final long serialVersionUID = 1L;
    private String ekolTuru; 
    private int pozdaKalmaSuresiSaniye;

    public Esneklik(String isim, String kategori, int sureDakika, String zorlukSeviyesi, 
                    String ekolTuru, int pozdaKalmaSuresiSaniye) {
        super(isim, kategori, sureDakika, zorlukSeviyesi);
        setEkolTuru(ekolTuru);
        setPozdaKalmaSuresiSaniye(pozdaKalmaSuresiSaniye);
    }

    public String getEkolTuru() { return ekolTuru; }

    public void setEkolTuru(String ekolTuru) {
        if (ekolTuru == null || ekolTuru.trim().isEmpty()) {
            throw new IllegalArgumentException("Ekol türü (Yoga/Pilates vb.) boş olamaz!");
        }
        this.ekolTuru = ekolTuru.trim();
    }

    public int getPozdaKalmaSuresiSaniye() { return pozdaKalmaSuresiSaniye; }

    public void setPozdaKalmaSuresiSaniye(int pozdaKalmaSuresiSaniye) {
        if (pozdaKalmaSuresiSaniye < 5 || pozdaKalmaSuresiSaniye > 300) {
            throw new IllegalArgumentException("Pozda kalma süresi 5 ile 300 saniye arasında olmalıdır!");
        }
        this.pozdaKalmaSuresiSaniye = pozdaKalmaSuresiSaniye;
    }

    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.4;
        if (z.contains("orta")) return 1.2;
        return 1.0;
    }

    private double getEkolCarpani() {
        String e = ekolTuru.toLowerCase();
        if (e.contains("pilates")) return 1.3; 
        if (e.contains("yoga")) return 1.2; 
        return 1.1; 
    }

    // KALORİ HESAPLA
    @Override
    public double kaloriHesapla(Uye uye) {
        double sureSaat = getSureDakika() / 60.0;
        // Temel kalori + pozda kalma süresinin getirdiği statik yük
        double temelEfor = sureSaat * 3.0 * uye.getKilo(); 
        double pozEforu = (pozdaKalmaSuresiSaniye * 0.05);
        
        return (temelEfor + pozEforu) * getZorlukCarpani() * getEkolCarpani();
    }

    // DETAY GÖSTER
    @Override
    public void detayGoster() {
        System.out.println("Esneklik: " + getIsim() + " [" + ekolTuru + "] | Pozda Kalma: " + 
                           pozdaKalmaSuresiSaniye + "sn | Zorluk: " + getZorlukSeviyesi());
    }
}
