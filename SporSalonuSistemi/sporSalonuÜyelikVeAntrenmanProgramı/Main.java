package sporSalonuÜyelikVeAntrenmanProgramı;

// Tüm dosyalar aynı pakette olduğu için ekstra import kullanmaya gerek kalmadı.

// ANA SINIF
// UYGULAMANIN BAŞLANGIÇ NOKTASI
// MODÜL TESTLERİNİ GERÇEKLEŞTİRİR
public class Main {

    // ANA METOT
    // SİSTEMİ BAŞLATIR VE TEST İŞLEMLERİNİ YÜRÜTÜR
    public static void main(String[] args) {
        
        // Exception Handling: Hatalara karşı sistemi korumak için try-catch-finally yapısı kullanıldı
        try {
            // Servis sınıflarından nesneler türetiliyor
            PaketService paketService = new PaketService();
            AntrenorUyeService antrenorService = new AntrenorUyeService();
            
            // OOP Prensibi: Polimorfizm (Çok Biçimlilik)
            // Üst sınıf referansı (UyelikPaketi) ile alt sınıf nesneleri (Standart, Premium, VIP) oluşturuldu
            UyelikPaketi p1 = new StandartPaket();
            UyelikPaketi p2 = new PremiumPaket();
            UyelikPaketi p3 = new VIPPaket();

            // Veri yöneticisi kullanılarak paketler sisteme ekleniyor
            paketService.ekle(p1);
            paketService.ekle(p2);
            paketService.ekle(p3);

            // MEVCUT ÜYELİK PAKETLERİNİ LİSTELE
            System.out.println("--- Mevcut Üyelik Paketleri ---");
            for (UyelikPaketi p : paketService.listele()) {
                // Polimorfizm sayesinde her nesne kendi ucretHesapla() metodunu çalıştırır
                System.out.println("Paket: " + p.getPaketAdi() + " | Taban Aylık Ücret: " + p.ucretHesapla() + " TL");
            }

            // DİNAMİK ÜCRET HESAPLAMA MOTORU TESTİ
            System.out.println("\n--- Dinamik Ücret Hesaplama Motoru Testi ---");
            // Premium paket, 2 adet özel ders ve %10 (0.1) kampanya indirimi ile hesaplama yapılıyor
            double toplam = UcretHesaplayici.hesapla(p2, 2, 0.1); 
            System.out.println("Hesaplanan Toplam Ücret: " + toplam + " TL");

            // ANTRENÖR-ÜYE İLİŞKİ ATAMALARI TESTİ
            System.out.println("\n--- Antrenör-Üye İlişki Atamaları Testi ---");
            // ID'ler üzerinden atama işlemleri gerçekleştiriliyor
            antrenorService.ata("1", "101");
            antrenorService.ata("2", "102");

            for (AntrenorUye a : antrenorService.listele()) {
                System.out.println("Atama Kaydı -> Antrenör ID: " + a.getAntrenorID() + " | Üye ID: " + a.getUyeID());
            }

        } catch (IllegalArgumentException e) {
            // Yanlış değer girişlerini yakalayan blok
            System.err.println("Girdi Hatası: Lütfen verileri kontrol ediniz! " + e.getMessage());
        } catch (Exception e) {
            // Beklenmeyen sistem hatalarını yakalayan genel blok
            System.err.println("Kritik Sistem Hatası: " + e.getMessage());
        } finally {
            // Hata olsun veya olmasın her zaman çalışacak kapanış bloğu
            System.out.println("\n--- İşlem Sonu: Modül Testi Başarıyla Tamamlandı ---");
        }
    }
}