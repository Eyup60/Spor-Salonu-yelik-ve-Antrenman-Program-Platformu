package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.UUID;

// KULLANICI SINIFI
public abstract class Kullanici implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    private String id;
    private String isim;
    private String soyisim;
    private String email;
    private String password;
    private Role role;
    
    // KULLANICI YAPICI METOT
    protected Kullanici(String isim, String soyisim, String email, String password, Role role) {
        // HER KULLANICI İÇİN BENZERSİZ BİR KİMLİK (ID) OLUŞTURUR
        this.id = UUID.randomUUID().toString();
        setIsim(isim);
        setSoyisim(soyisim);
        setEmail(email);
        setPassword(password);
        setRole(role);
    }
    
    // İSİM GETİR
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getIsim() { return isim; }

    // İSİM AYARLA
    public void setIsim(String isim) {
        // İSİM ALANININ BOŞ GEÇİLMESİNİ ENGELLER
        if(isim == null || isim.isEmpty()) {
            throw new IllegalArgumentException("İsim bos olamaz");
        }
        this.isim = isim;
    }
    
    // SOYİSİM GETİR
    public String getSoyisim() { return soyisim; }

    // SOYİSİM AYARLA
    public void setSoyisim(String soyisim) {
        // SOYİSİM ALANININ BOŞ GEÇİLMESİNİ ENGELLER
        if(soyisim == null || soyisim.isEmpty()) {
            throw new IllegalArgumentException("Soyisim bos olamaz");
        }
        this.soyisim = soyisim;
    }
    
    // EMAIL AYARLA
    public void setEmail(String email) {
        // EMAİL ADRESİNİN GEÇERLİ BİR FORMATTA OLMASINI ZORUNLU KILAR
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email boş olamaz!");
        }
        if(!email.contains("@") || !email.contains(".") || email.indexOf("@") > email.lastIndexOf(".")) {
            throw new IllegalArgumentException("Geçersiz email formatı!");
        }
        this.email = email.trim();
    }
    
    // ŞİFRE GETİR
    public String getPassword() { return password; }
    
    // ŞİFRE AYARLA
    public void setPassword(String password) {
        // GÜVENLİK İÇİN ŞİFRENİN EN AZ 6 KARAKTER OLMASINI SAĞLAR
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Şifre boş olamaz!");
        }
        if(password.length() < 6) {
            throw new IllegalArgumentException("Şifre en az 6 haneli olmalıdır");
        }
     // ŞİFREYİ DÜZ METİN OLARAK DEĞİL, HASH'LENMİŞ HALİYLE DEĞİŞKENE ATAR
        this.password = GuvenlikYardimcisi.sifrele(password);
    }
    
    // ROL GETİR
    public Role getRole() { return role; }
    
    // ROL AYARLA
    public void setRole(Role role) {
        // KULLANICININ SİSTEMDEKİ YETKİ SEVİYESİNİ BELİRLER
        if(role == null) {
            throw new IllegalArgumentException("Rol boş olamaz!");
        }
        this.role = role;
    }
    
    // BİLGİ GÖSTER
    public abstract void displayInfo();
    
    // GİRİŞ YAP
 public boolean login(String email, String password) {
        if(email == null || password == null) return false;
        // GİRİLEN ŞİFREYİ HASH'LEYİP DOSYADAKİ HASH İLE KARŞILAŞTIRIR
        String hashedInput = GuvenlikYardimcisi.sifrele(password);
        return this.email.equalsIgnoreCase(email.trim()) && this.password.equals(hashedInput);
    }
    
    // EŞİT
    @Override
    public boolean equals(Object obj) {
        // NESNE KARŞILAŞTIRMALARINDA ID BAZLI KONTROL YAPAR
        if(this == obj) return true;
        if(obj == null || !(obj instanceof Kullanici)) return false;
        
        Kullanici k = (Kullanici) obj;
        return getId().equals(k.getId());
    }
    
    // HASH KOD
    @Override
    public int hashCode() {
        // NESNE İÇİN BENZERSİZ BİR SAYISAL DEĞER ÜRETİR
        return getId().hashCode();
    }
    
    // STRİNG
    @Override
    public String toString() {
        // KULLANICIYI ÖZETLEYEN METİN BİLGİSİ DÖNDÜRÜR
        return "Email: " + email + " | Role: " + role;
    }
}