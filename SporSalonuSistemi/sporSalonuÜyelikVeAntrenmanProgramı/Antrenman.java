package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.UUID;

// TÜM ANTRENMAN TÜRLERİ İÇİN TEMEL ŞABLON OLUŞTURAN VE ORTAK ÖZELLİKLERİ BARINDIRAN SOYUT SINIF
public abstract class Antrenman implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String isim;
    private String kategori;
    private int sureDakika;
    private String zorlukSeviyesi;
    
    // YENİ BİR ANTRENMAN OLUŞTURULURKEN OTOMATİK BENZERSİZ ID ATAYAN YAPICI METOT
    protected Antrenman(String isim, String kategori, int sureDakika, String zorlukSeviyesi) {
        this.id = UUID.randomUUID().toString();
        setIsim(isim);
        setKategori(kategori);
        setSureDakika(sureDakika);
        setZorlukSeviyesi(zorlukSeviyesi);
    }
    
    public String getId() {
        return id;
    }
    
    public String getIsim() {
        return isim;
    }
    
    // ANTRENMAN İSMİNİN GEÇERLİLİĞİNİ DENETLEYEN VE BOŞ VERİ GİRİŞİNİ ENGELLEYEN METOT
    public void setIsim(String isim) {
        if (isim == null || isim.trim().isEmpty()) {
            throw new IllegalArgumentException("Antrenman ismi boş olamaz!");
        }
        this.isim = isim.trim();
    }

    public String getKategori() {
        return kategori;
    }

    // ANTRENMAN KATEGORİSİNİN BOŞ BIRAKILMAMASINI SAĞLAYAN DENETLEYİCİ METOT
    public void setKategori(String kategori) {
        if (kategori == null || kategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori boş olamaz!");
        }
        this.kategori = kategori.trim();
    }

    public int getSureDakika() {
        return sureDakika;
    }

    // ANTRENMAN SÜRESİNİN MANTIKSAL SINIRLAR (5-240 DK) İÇERİSİNDE OLMASINI SAĞLAYAN METOT
    public void setSureDakika(int sureDakika) {
        if (sureDakika < 5 || sureDakika > 240) {
            throw new IllegalArgumentException("Antrenman süresi 5 ile 240 dakika arasında olmalıdır!");
        }
        this.sureDakika = sureDakika;
    }

    public String getZorlukSeviyesi() {
        return zorlukSeviyesi;
    }

    // ZORLUK SEVİYESİ BİLGİSİNİN DOĞRU FORMATTA ALINMASINI SAĞLAYAN METOT
    public void setZorlukSeviyesi(String zorlukSeviyesi) {
        if (zorlukSeviyesi == null || zorlukSeviyesi.trim().isEmpty()) {
            throw new IllegalArgumentException("Zorluk seviyesi boş olamaz!");
        }
        this.zorlukSeviyesi = zorlukSeviyesi.trim();
    }
    
    // ALT SINIFLARIN KENDİNE HAS MANTIKLA KALORİ HESAPLAMASI İÇİN ZORUNLU TUTULAN SOYUT METOT
    public abstract double kaloriHesapla(Uye uye);
    
    // ANTRENMANA ÖZEL DETAYLARIN YAZDIRILMASINI SAĞLAYAN SOYUT METOT
    public abstract void detayGoster();

    // İKİ ANTRENMAN NESNESİNİN BENZERSİZ ID NUMARALARINA GÖRE AYNI OLUP OLMADIĞINI KONTROL EDER
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || !(obj instanceof Antrenman)) return false;
        
        Antrenman a = (Antrenman) obj;
        return getId().equals(a.getId());
    }
    
    // NESNENİN BELLEK ADRESİ YERİNE ID ÜZERİNDEN HASH KODU ÜRETMESİNİ SAĞLAR
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    
    // NESNE YAZDIRILDIĞINDA ANLAŞILIR BİR ÖZET BİLGİ SUNAN METOT
    @Override
    public String toString() {
        return kategori + " - " + isim + " (" + sureDakika + " dk)";
    }
}