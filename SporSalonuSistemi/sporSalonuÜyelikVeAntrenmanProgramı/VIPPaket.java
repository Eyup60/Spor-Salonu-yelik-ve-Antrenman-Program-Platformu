package sporSalonuÜyelikVeAntrenmanProgramı;

public class VIPPaket extends UyelikPaketi {
    private static double baslangicFiyat = 1200.0;
    
    public VIPPaket() { 
        super("VIP Paket", baslangicFiyat, 30); 
    }
    
    public static void setBaslangicFiyat(double yeniFiyat) { 
        baslangicFiyat = yeniFiyat; 
    }
    
    @Override 
    public double ucretHesapla() { 
        return getTemelFiyat() * 1.25; // VIP paket %25 daha pahalıdır
    }
    
    @Override 
    public int getMaksimumOzelDers() { 
        return 15; // VIP sınır
    }
}