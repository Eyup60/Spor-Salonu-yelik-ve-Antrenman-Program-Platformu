package sporSalonuÜyelikVeAntrenmanProgramı;

// Kalıtım (Inheritance): UyelikPaketi'nin özelliklerini devralıyor.
public class StandartPaket extends UyelikPaketi {
    // Fiyat güncellemeleri tüm standart paketleri etkilesin diye static tanımladık.
    private static double baslangicFiyat = 500.0;

    public StandartPaket() { 
        super("Standart Paket", baslangicFiyat, 30); 
    }
    
    public static void setBaslangicFiyat(double yeniFiyat) { 
        baslangicFiyat = yeniFiyat; 
    }
    
    @Override 
    public double ucretHesapla() { 
        return getTemelFiyat(); // Standart pakette ek ücret yok
    }
    
    @Override 
    public int getMaksimumOzelDers() { 
        return 2; // Bu paketin maksimum limiti
    }
}