package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

// ANTRENÖR SINIFI
public class Antrenor extends Kullanici implements VeriYöneticisi<Uye> {

    private static final long serialVersionUID = 1L;
    private String uzmanlıkAlanı;
    
    // ANTRENÖRÜN SORUMLU OLDUĞU ÜYELERİ TUTAN ÖZEL LİSTE
    private List<Uye> uyeler = new ArrayList<>();
    
    // ANTRENÖR YAPICI METOT
    protected Antrenor(String isim,String soyisim,String email, String password,String uzmanlıkAlanı) {
        super(isim,soyisim,email, password, Role.ANTRENOR);
        setUzmanlıkAlanı(uzmanlıkAlanı);
    }
    
    // UZMANLIK ALANI GETİR
    public String getUzmanlıkAlanı() {
        return uzmanlıkAlanı;
    }

    // UZMANLIK ALANI AYARLA
    public void setUzmanlıkAlanı(String uzmanlıkAlanı) {
        if(uzmanlıkAlanı == null || uzmanlıkAlanı.isEmpty()) {
            throw new IllegalArgumentException("Uzmanlık alanı boş olamaz!");
        }
        this.uzmanlıkAlanı = uzmanlıkAlanı;
    }
    
    // KONTROL
    public void kontrol() {
        if(uyeler == null || uyeler.isEmpty()) {
            uyeler = new ArrayList<>();
        }
    }
    
    // BİLGİ GÖSTER
    @Override
    public void displayInfo() {
        System.out.println("--- ANTRENÖR ---");
        System.out.println("İsim :"+getIsim());
        System.out.println("Soyisim: "+getSoyisim());
        System.out.println("Email: " + getEmail());
        System.out.println("Uzmanlık: " + getUzmanlıkAlanı());
        System.out.println("Üye sayısı: "+ uyeler.size());
    }

    // EKLE
    @Override
    public void ekle(Uye nesne) {
    	kontrol();
        
        // YETKİ KONTROLÜ: BU METODUN SADECE ATAMAMOTORU TARAFINDAN ÇAĞRILDIĞINDAN EMİN OLUNMALI
        
        if (!Admin.getKullanicilar().contains(nesne)) return;
        if (uyeler.contains(nesne)) return;
        if (nesne.getRole() != Role.UYE) return;
        
        uyeler.add(nesne);
        DosyaYoneticisi.verileriKaydet();
    }

    // SİL
    @Override
    public void sil(String id) {
        kontrol();
        boolean silindi = uyeler.removeIf(nesne -> nesne.getId().equals(id));
        if(silindi){
            DosyaYoneticisi.verileriKaydet();
            System.out.println("Uye silme işlemi başarılı!");
        } else {
            System.out.println("ID bulunamadı!");
        }
    }

    // GÜNCELLE
    @Override
    public void guncelle(Uye nesne) {
        kontrol();
        if(nesne.getRole() != Role.UYE) {
            return;
        }
        
        for(int i = 0;i < uyeler.size();i++) {
            if(uyeler.get(i).getId().equals(nesne.getId())) {
                uyeler.set(i, nesne);
                DosyaYoneticisi.verileriKaydet();
                System.out.println("Uye bilgilerini güncelleme başarılı!");
                return;
            }
        }
        System.out.println("Uye bilgilerini guncelleme başarısız!");
    }

    // LİSTELE
    @Override
    public List<Uye> listele() {
        return new ArrayList<>(uyeler);
    }
    
    // BUL
    @Override
    public Uye bul(String id) {
        for (Uye u : uyeler) {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }
}