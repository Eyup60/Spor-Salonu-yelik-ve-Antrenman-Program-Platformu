package sporSalonuÜyelikVeAntrenmanProgramı;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JOptionPane;

// YÖNETİCİ SINIFI
public class Admin extends Kullanici implements VeriYöneticisi<Kullanici>{
    
    private static final long serialVersionUID = 1L;
    
    // TÜM SİSTEM KULLANICILARINI RAM ÜZERİNDE TUTAN ANA VERİ LİSTESİ
    private static List<Kullanici> kullanicilar = new CopyOnWriteArrayList<>();
    
    private LocalDateTime sonGiriş;

    // YÖNETİCİ YAPICI METOT
    protected Admin(String isim ,String soyisim,String email,String password) {
        super(isim ,soyisim,email, password, Role.ADMIN);
    }
    
    // KULLANICILARI GETİR
    public static List<Kullanici> getKullanicilar() {
        return new ArrayList<>(kullanicilar);
    }

    // KULLANICILARI AYARLA
    public static void setKullanicilar(List<Kullanici> kullanicilar) {
        if(kullanicilar == null) {
            throw new IllegalArgumentException("Kullanıcı listesi sisteme yüklenemedi!");
        }
        Admin.kullanicilar = kullanicilar;
    }
    
    // SON GİRİŞ GETİR
    public LocalDateTime getSonGiriş() {
        return sonGiriş;
    }

    // SON GİRİŞ AYARLA
    public void setSonGiriş(LocalDateTime sonGiriş) {
        if(sonGiriş == null) {
            throw new IllegalArgumentException("Son giriş zamanı boş olamaz!");
        }
        if(sonGiriş.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Son giriş gelecekte olamaz!");
        }
        this.sonGiriş = sonGiriş;
    }
    
    public void sistemBakimiYap() {
        List<Kullanici> tumKullanicilar = getKullanicilar();
        int atananSayisi = 0;

        for (Kullanici k : tumKullanicilar) {
            if (k instanceof Uye uye) {
                String mevcutHoca = anternorBulUyeIle(uye);
                
                if (mevcutHoca.equals("Henüz Atanmadı")) {
                    AtamaMotoru.otomatikAtamaYap(uye);
                    atananSayisi++;
                }
            }
        }

        if (atananSayisi > 0) {
            System.out.println("Sistem Bakımı: " + atananSayisi + " adet sahipsiz üye antrenörlere atandı.");
            DosyaYoneticisi.verileriKaydet();
        } else {
            System.out.println("Sistem Bakımı: Atanmamış üye bulunamadı.");
        }
    }
    
    // ANTRENÖR BUL
    public static String anternorBulUyeIle(Uye uye) {
        if (uye == null || uye.getId() == null) return "Henüz Atanmadı";

        for (Kullanici k : Admin.getKullanicilar()) {
            if (k instanceof Antrenor antrenor) {
                List<Uye> hocaUyelari = antrenor.listele();
                if (hocaUyelari != null) {
                    for (Uye u : hocaUyelari) {
                        if (u.getId().trim().equals(uye.getId().trim())) { 
                            return antrenor.getIsim() + " " + antrenor.getSoyisim();
                        }
                    }
                }
            }
        }
        return "Henüz Atanmadı";
    }

    // DOĞRUDAN EKLE
    public static void doğrudanEkle(Kullanici k) {
        if (!kullanicilar.contains(k)) {
            kullanicilar.add(k);
            DosyaYoneticisi.verileriKaydet(); 
        }
    }
    
    // BİLGİ GÖSTER
    @Override
    public void displayInfo() {
        System.out.println("--- YÖNETİCİ PANELİ ---");
        System.out.println("İsim: "+getIsim());
        System.out.println("Soyisim: "+getSoyisim());
        System.out.println("Email: " + getEmail());
        System.out.println("Son Erişim: " + sonGiriş);
    }

    // EKLE
    @Override
    public void ekle(Kullanici nesne) {
    	if(kullanicilar.contains(nesne)) {
            System.out.println("Bu kullanici zaten var!");
            return;
        }
        
        kullanicilar.add(nesne);
        
        // KULLANICI UYE İSE OTOMATİK ATAMA YAPILIR
        if (nesne instanceof Uye yeniUye) {
            AtamaMotoru.otomatikAtamaYap(yeniUye);
        }
        
        DosyaYoneticisi.verileriKaydet(); // VERİLER KAYDEDİLİR
        System.out.println("Ekleme işlemi başarılı.");
    }

    // SİL
    @Override
    public void sil(String id) {
        Kullanici silinecek = bul(id);
        if (silinecek instanceof Admin && kullanicilar.stream().filter(k -> k instanceof Admin).count() <= 1) {
            JOptionPane.showMessageDialog(null, "Son yönetici hesabı silinemez!", "Kritik Yetki Hatası", JOptionPane.ERROR_MESSAGE);
            throw new IllegalStateException("Sistemde en az 1 Admin kalmak zorundadır!");
        }
        boolean silindi = kullanicilar.removeIf(nesne -> nesne.getId().equals(id));
        if(silindi) {
            DosyaYoneticisi.verileriKaydet();
            System.out.println("Silme işlemi başarılı. ID: "+id);
        } else {
            System.out.println("ID bulunamadı!");
        }
    }

    // GÜNCELLE
    @Override
    public void guncelle(Kullanici nesne) {
        for(int i = 0; i < kullanicilar.size(); i++) {
            if(kullanicilar.get(i).getId().equals(nesne.getId())) {
                kullanicilar.set(i, nesne);
                DosyaYoneticisi.verileriKaydet();
                System.out.println("Güncelleme başarılı!");
                return;
            }
        }
        System.out.println("Güncelleme başarısız!");
    }

    // LİSTELE
    @Override
    public List<Kullanici> listele() {
        return new ArrayList<>(kullanicilar);
    }
    
    // BUL
    @Override
    public Kullanici bul(String id) {
        for(Kullanici nesne : kullanicilar) {
            if(nesne.getId().equals(id)) {
                return nesne;
            }
        }
        return null;
    }
}