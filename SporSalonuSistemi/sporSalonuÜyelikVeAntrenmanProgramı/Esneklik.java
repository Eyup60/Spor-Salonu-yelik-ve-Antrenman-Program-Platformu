package sporSalonuÜyelikVeAntrenmanProgramı;

// ESNEKLİK ANTRENMANI SINIFI
// Antrenman sınıfından türetilen, esneme ve mobilite odaklı hareketleri temsil eder.
public class Esneklik extends Antrenman {
    
    private static final long serialVersionUID = 1L;
    private String ekolTuru; // Yoga, Pilates, Statik Esneme vb.
    private int pozdaKalmaSuresiSaniye; // Hareketin ne kadar süreyle sabit tutulduğu

    // Yapıcı Metod (Constructor)
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
        if (z.contains("ileri")) return 1.2;
        if (z.contains("orta")) return 1.1;
        return 1.0;
    }

    // Ekol türüne göre enerji harcama farkı 
    private double getEkolCarpani() {
        String e = ekolTuru.toLowerCase();
        if (e.contains("pilates")) return 1.15; // Pilates, statik esnemeye göre daha aktiftir.
        if (e.contains("yoga")) return 1.1; 
        return 1.05; 
    }

    // KALORİ HESAPLA
    @Override
    public double kaloriHesapla(Uye uye) {
        double sureSaat = getSureDakika() / 60.0;
        
        double temelEfor = sureSaat * 2.5 * uye.getKilo(); 
        
        // Esnemede uzun süre kalmak eforu değil, esnekliği artırır.
        double pozEforu = (pozdaKalmaSuresiSaniye * 0.01);
        
        return (temelEfor + pozEforu) * getZorlukCarpani() * getEkolCarpani();
    }

    @Override
    public void detayGoster() {
        System.out.println("Esneklik: " + getIsim() + " [" + ekolTuru + "] | Pozda Kalma: " + 
                           pozdaKalmaSuresiSaniye + "sn | Zorluk: " + getZorlukSeviyesi());
    }
}
