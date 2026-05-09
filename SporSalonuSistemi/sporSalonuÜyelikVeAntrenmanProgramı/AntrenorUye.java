package sporSalonuÜyelikVeAntrenmanProgramı;

// ANTRENÖR ÜYE SINIFI
public class AntrenorUye {
	
    private String antrenorID;
    private String uyeID;
	
    // Constructor (Yapıcı Metot) - Nesne oluşturulurken ID'leri atar
    public AntrenorUye(String antrenorID, String uyeID) {
        this.antrenorID = antrenorID;
        this.uyeID = uyeID;
    }
		
    // --- Getter ve Setter Metotları ---
    // Verilere dışarıdan kontrollü erişim ve değişiklik imkanı sağlar

    public String getAntrenorID() {
        return antrenorID;
    }

    public void setAntrenorID(String antrenorID) {
        this.antrenorID = antrenorID;
    }

    public String getUyeID() {
        return uyeID;
    }

    public void setUyeID(String uyeID) {
        this.uyeID = uyeID;
    }
	
}