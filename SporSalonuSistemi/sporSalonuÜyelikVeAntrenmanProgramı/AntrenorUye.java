package sporSalonuÜyelikVeAntrenmanProgramı;

// Antrenör ve Üye arasındaki atama ilişkisini tutan veri modeli (Entity sınıfı)
// OOP Prensibi: Kapsülleme (Encapsulation) uygulanarak alanlar korunmuştur.
public class AntrenorUye {
	
    private int antrenorID;
    private int uyeID;
	
    // Constructor (Yapıcı Metot) - Nesne oluşturulurken ID'leri atar
    public AntrenorUye(int antrenorID, int uyeID) {
        this.antrenorID = antrenorID;
        this.uyeID = uyeID;
    }
		
    // --- Getter ve Setter Metotları ---
    // Verilere dışarıdan kontrollü erişim ve değişiklik imkanı sağlar

    public int getAntrenorID() {
        return antrenorID;
    }

    public void setAntrenorID(int antrenorID) {
        this.antrenorID = antrenorID;
    }

    public int getUyeID() {
        return uyeID;
    }

    public void setUyeID(int uyeID) {
        this.uyeID = uyeID;
    }
	
}