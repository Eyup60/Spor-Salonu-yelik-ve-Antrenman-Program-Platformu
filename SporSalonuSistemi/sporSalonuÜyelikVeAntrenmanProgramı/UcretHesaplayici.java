package sporSalonuÜyelikVeAntrenmanProgramı;

// Arayüzlerden (GUI) gelen verilere göre nihai ücreti hesaplayan yardımcı (Utility) sınıf.
public class UcretHesaplayici {
	
    // Static metot: Sınıftan "new" ile nesne üretilmeden doğrudan UcretHesaplayici.hesapla() şeklinde çağrılır.
    // OOP Prensibi: Polimorfizm (UyelikPaketi referansı, o anki duruma göre alt sınıfların davranışını sergiler)
    public static double hesapla(UyelikPaketi paket, int ozelDers, double indirim) {

        // Exception Handling (Hata Yönetimi): Mantıksal veri doğrulama işlemleri.
        // Hatalı veya eksi bir değer geldiğinde hesaplama yapmadan işlemi durdurur ve hata fırlatır.
        if (ozelDers < 0) {
            throw new IllegalArgumentException("Özel ders sayısı negatif olamaz!");
        }
        if (indirim < 0 || indirim > 1) {
            throw new IllegalArgumentException("İndirim oranı 0 ile 1 arasında olmalıdır (Örn: %10 indirim için 0.1)!");
        }
		
        // Polimorfizm sayesinde, parametre olarak gelen paketin gerçek tipine 
        // (VIP, Premium veya Standart) göre ilgili sınıfın kendi ucretHesapla() metodu dinamik olarak çalışır.
        double toplam = paket.ucretHesapla();
		
        // Ek hizmet bedelleri (Her bir özel ders 100 TL olarak ücretlendirilir)
        toplam += ozelDers * 100;
		
        // Toplam tutar üzerinden indirim oranı düşülür
        toplam -= toplam * indirim;
		
        return toplam;
    }

}