package sporSalonuÜyelikVeAntrenmanProgramı;

// UyelikPaketi üst sınıfından miras alan temel paket sınıfı.
// OOP Prensibi: Kalıtım (Inheritance)
public class StandartPaket extends UyelikPaketi {

    // Yapıcı metot (Constructor)
    public StandartPaket() {
        // "super" anahtar kelimesi ile üst sınıfın yapıcısına temel değerler gönderilir.
        // (Paket Adı: Standart Paket, Fiyat: 500.0, Süre: 30 gün)
        super("Standart Paket", 500.0, 30);
    }
	
    // OOP Prensibi: Polimorfizm (Çok Biçimlilik) - Metot Ezme (Overriding)
    @Override
    public double ucretHesapla() {
        // Standart pakette fiyat artışı veya özel bir çarpan olmadığı için 
        // doğrudan üst sınıfın temel fiyatı döndürülür.
        return getTemelFiyat();
    }

}