package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.UUID;

public abstract class Antrenman implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String isim;
    private String kategori;
    private int sureDakika;
    private String zorlukSeviyesi;
    
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
    
    public void setIsim(String isim) {
        if (isim == null || isim.trim().isEmpty()) {
            throw new IllegalArgumentException("Antrenman ismi boş olamaz!");
        }
        this.isim = isim.trim();
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        if (kategori == null || kategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori boş olamaz!");
        }
        this.kategori = kategori.trim();
    }

    public int getSureDakika() {
        return sureDakika;
    }

    public void setSureDakika(int sureDakika) {
        if (sureDakika < 5 || sureDakika > 240) {
            throw new IllegalArgumentException("Antrenman süresi 5 ile 240 dakika arasında olmalıdır!");
        }
        this.sureDakika = sureDakika;
    }

    public String getZorlukSeviyesi() {
        return zorlukSeviyesi;
    }

    public void setZorlukSeviyesi(String zorlukSeviyesi) {
        if (zorlukSeviyesi == null || zorlukSeviyesi.trim().isEmpty()) {
            throw new IllegalArgumentException("Zorluk seviyesi boş olamaz!");
        }
        this.zorlukSeviyesi = zorlukSeviyesi.trim();
    }
    
    public abstract double kaloriHesapla(Uye uye);
    
    public abstract void detayGoster();

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || !(obj instanceof Antrenman)) return false;
        
        Antrenman a = (Antrenman) obj;
        return getId().equals(a.getId());
    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    
    @Override
    public String toString() {
        return kategori + " - " + isim + " (" + sureDakika + " dk)";
    }
}
