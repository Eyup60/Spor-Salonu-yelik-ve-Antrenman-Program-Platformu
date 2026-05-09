package sporSalonuÜyelikVeAntrenmanProgramı;

// ÜCRETLANDIRMA HESAPLAYICI SINIFI
public class UcretHesaplayici {
	
    // ÜCRETLERİ HESAPLA
    public static double hesapla(UyelikPaketi paket, int ozelDers, double indirim) {

        // Exception Handling (Hata Yönetimi): Mantıksal veri doğrulama işlemleri.
        if (ozelDers < 0) {
            throw new IllegalArgumentException("Özel ders sayısı negatif olamaz!");
        }
        
        // 🚨 İŞTE EKSİK OLAN VE SİSTEMİ KORUYACAK KONTROL BURASI 🚨
        if (ozelDers > paket.getMaksimumOzelDers()) {
            throw new IllegalArgumentException("Bu paket en fazla " + paket.getMaksimumOzelDers() + " özel ders alabilir!");
        }

        if (indirim < 0 || indirim > 1) {
            throw new IllegalArgumentException("İndirim oranı 0 ile 1 arasında olmalıdır!");
        }
		
        // Polimorfizm sayesinde paket tipine göre dinamik ücret hesaplanır
        double toplam = paket.ucretHesapla();
		
        // Ek hizmet bedelleri (Her bir özel ders 100 TL)
        toplam += ozelDers * 100;
		
        // İndirim uygulanır
        toplam -= toplam * indirim;
		
        return toplam;
    }
}