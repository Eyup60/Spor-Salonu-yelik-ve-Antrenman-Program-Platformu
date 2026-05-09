package sporSalonuÜyelikVeAntrenmanProgramı;

// ESNEKLİK ANTRENMANI SINIFI
// ANTRENMAN SINIFINDAN MİRAS ALIR
// YOGA PILATES GİBİ ESNEKLİK ÇALIŞMALARINI TEMSİL EDER
public class Esneklik extends Antrenman {
    
    // SERİALİZASYON VERSİYON NUMARASI
    private static final long serialVersionUID = 1L;
    
    // ESNEKLİK ÖZELLİKLERİ
    private String ekolTuru; 
    private int pozdaKalmaSuresiSaniye;

    // ESNEKLİK YAPICI METOT
    // TEMEL ANTRENMAN BİLGİLERİ VE ESNEKLİK ÖZELLİKLERİNİ AYARLAR
    public Esneklik(String isim, String kategori, int sureDakika, String zorlukSeviyesi, 
                    String ekolTuru, int pozdaKalmaSuresiSaniye) {
        super(isim, kategori, sureDakika, zorlukSeviyesi);
        setEkolTuru(ekolTuru);
        setPozdaKalmaSuresiSaniye(pozdaKalmaSuresiSaniye);
    }

    // EKOL TÜRÜ GETİR
    public String getEkolTuru() { return ekolTuru; }

    // EKOL TÜRÜ AYARLA
    // BOŞ DEĞER KONTROLÜ YAPAR İSTİSNA FIRLATIR
    public void setEkolTuru(String ekolTuru) {
        if (ekolTuru == null || ekolTuru.trim().isEmpty()) {
            throw new IllegalArgumentException("Ekol türü (Yoga/Pilates vb.) boş olamaz!");
        }
        this.ekolTuru = ekolTuru.trim();
    }

    // POZDA KALMA SÜRESİ GETİR
    public int getPozdaKalmaSuresiSaniye() { return pozdaKalmaSuresiSaniye; }

    // POZDA KALMA SÜRESİ AYARLA
    // 5 İLE 300 SANİYE ARASI DEĞER KONTROLÜ
    public void setPozdaKalmaSuresiSaniye(int pozdaKalmaSuresiSaniye) {
        if (pozdaKalmaSuresiSaniye < 5 || pozdaKalmaSuresiSaniye > 300) {
            throw new IllegalArgumentException("Pozda kalma süresi 5 ile 300 saniye arasında olmalıdır!");
        }
        this.pozdaKalmaSuresiSaniye = pozdaKalmaSuresiSaniye;
    }

    // ZORLUK ÇARPANI HESAPLA
    // ZORLUK SEVİYESİNE GÖRE KATSAYI DÖNDÜRÜR
    private double getZorlukCarpani() {
        String z = getZorlukSeviyesi().toLowerCase();
        if (z.contains("ileri")) return 1.4;
        if (z.contains("orta")) return 1.2;
        return 1.0;
    }

    // EKOL ÇARPANI HESAPLA
    // EKOL TÜRÜNE GÖRE KATSAYI DÖNDÜRÜR
    private double getEkolCarpani() {
        String e = ekolTuru.toLowerCase();
        if (e.contains("pilates")) return 1.3; 
        if (e.contains("yoga")) return 1.2; 
        return 1.1; 
    }

    // KALORİ HESAPLA
    // ESNEKLİK ANTRENMANI İÇİN ÖZEL KALORİ HESABI
    @Override
    public double kaloriHesapla(Uye uye) {
        double sureSaat = getSureDakika() / 60.0;
        // TEMEL KALORİ VE POZDA KALMA SÜRESİNİN GETİRDİĞİ STATİK YÜK
        double temelEfor = sureSaat * 3.0 * uye.getKilo(); 
        double pozEforu = (pozdaKalmaSuresiSaniye * 0.05);
        
        return (temelEfor + pozEforu) * getZorlukCarpani() * getEkolCarpani();
    }

    // DETAY GÖSTER
    // ESNEKLİK ANTRENMANI BİLGİLERİNİ EKRANA YAZDIRIR
    @Override
    public void detayGoster() {
        System.out.println("Esneklik: " + getIsim() + " [" + ekolTuru + "] | Pozda Kalma: " + 
                           pozdaKalmaSuresiSaniye + "sn | Zorluk: " + getZorlukSeviyesi());
    }
}
