Proje Ekibi: Proje İsmi: Proje Konusu ve
Amacı:
12 Nisan 2026
CENG106
OBJECT ORIENTED PROGRAMMING
Project RoadMap Template
Eyüp Sansar 24118080085 1. Şube
Alperen Sezen 24118080035 1. Şube
Ali Aydın 24118080066 1.Şube
Yusuf Buğra Aydoğan 24118080093 1.Şube
Spor Salonu Üyelik ve Antrenman Programı Platformu
Bu proje, bir spor salonunun günlük operasyonlarını dijitalleştirmenin
ötesine geçerek; üye yönetimi, antrenör takibi, veri analitiği ve kapsamlı
finansal yönetim süreçlerini birleştiren entegre bir masaüstü platformu
sunar. Projenin temel amacı, karmaşık verileri (vücut kitle endeksi,
antrenman hacmi, ödeme periyotları) nesne yönelimli programlama
(OOP) prensipleriyle yapılandırarak, hem yönetici hem de son kullanıcı için
hatasız ve ölçeklenebilir bir deneyim sağlamaktır.
Sistem şu ana modüllerden oluşmaktadır:
• Akıllı Üye Kayıt ve Profilleme: Sadece isim-soyisim değil; boy, kilo,
yağ oranı ve yaş gibi fiziksel verilerin tutulduğu, bu verilerin zaman
içindeki değişiminin takip edildiği dinamik bir yapı.
• Dinamik Antrenman Programlayıcı: Üyenin fiziksel verilerine ve
hedefine (Kilo verme, Kas kütlesi artırma, Kondisyon) göre
sistemin "Çok Biçimlilik" (Polymorphism) kullanarak farklı egzersiz
setleri ve kalori hesaplamaları önermesi.
• Antrenör-Müşteri İlişkileri Modülü: Antrenörlerin uzmanlık
alanlarına göre üyelere atanması ve eğitmenlerin kendi
altlarındaki üye grubunu listeleyebilmesi.
• Finansal Takip Sistemi: Üyelik paketlerinin (Standart, Premium,
VIP) sürelerinin, son ödeme tarihlerinin ve abonelik durumlarının
otomatik kontrolü.
• Dinamik Ücretlendirme Motoru: Manuel hesaplama hatalarını
engellemek amacıyla; seçilen üyelik paketi (Standart, Premium,
VIP), eklenen özel dersler ve kampanya dönemlerine göre ücreti
anlık hesaplayan bir yapı sisteme dahil edilecektir.
• Finansal Takip ve Raporlama: Sistemin sadece mevcut ödemeleri
değil, paket getirilerini analiz ederek yöneticiye finansal öngörü
sunması hedeflenmektedir.
12 Nisan 2026
Proje Tasarımı: Projemiz, Java Swing kütüphanesi kullanılarak Masaüstü Tabanlı bir
uygulama olarak geliştirilecektir. Bu seçimin nedenleri şunlardır:
• Ders Kapsamına Uygunluk: Nesne Yönelimli Programlama dersi
kapsamında öğretilen Java temel kütüphaneleri (Swing, AWT) ile
tam uyumlu çalışması ve akademik isterleri en doğrudan yoldan
karşılaması nedeniyle tercih edilmiştir.
• Hız ve Performans: Spor salonu gibi yerel bir işletmede, internet
bağlantısına ihtiyaç duymadan verilerin anlık işlenmesi ve hızlı bir
arayüz deneyimi sunulması hedeflenmiştir.
Donanım Erişimi ve Yerel Depolama: Verilerin JSON/CSV
• Dinamik Tarife Yönetimi: Paket fiyatları ve indirim oranları kod
içerisine sabitlenmek yerine, yerel JSON/CSV dosyalarında
tutulacaktır. Bu sayede programın kaynak kodunu değiştirmeden
fiyat güncellemesi yapılabilecektir.
• Hata Yönetimi (Exception Handling): Ödeme alma veya ücret
girişi sırasında oluşabilecek hatalar (negatif tutar girişi, geçersiz
ödeme formatı) try-catch blokları ile yakalanarak sistemin
stabilitesi korunacaktır.
• dosyaları aracılığıyla doğrudan yerel diskte saklanması, harici
sunucu masraflarını ortadan kaldırır ve veritabanı kurulumu
gerektirmeyen "taşınabilir" bir yapı sunar.
Bu proje kapsamında kullanacağımız OOP teknikleri:
• Çok Biçimlilik (Polymorphism): UyelikPaketi abstract sınıfı
altında tanımlanan ucretHesapla() metodu, alt sınıflarda
(Standart, Premium, VIP) ezilerek (Override) her pakete özgü
farklı maliyet algoritmalarıyla çalışacaktır.
• Kalıtım (Inheritance): Tüm finansal işlemler ve paket türleri,
temel bir FinansalVarlık veya Paket sınıfından türetilerek ortak
değişkenlerin (KDV, indirim oranları) merkezi yönetimi
sağlanacaktır.
• Kapsülleme (Encapsulation): Ücret miktarları, ödeme tarihleri ve
kullanıcı borç bilgileri private olarak tanımlanacak; bu verilere
erişim yalnızca güvenli getter/setter metotları ile yapılarak veri
güvenliği korunacaktır.
• Soyutlama (Abstraction): Veri işleme operasyonları (Ekleme,
Silme, Güncelleme) bir interface üzerinden
standartlaştırılarak kodun sürdürülebilirliği artırılacaktır.
• Dosya sistemi olarak ise JSON/CSV Projenin taşınabilir olması ve
harici bir veritabanı sunucusu kurulumu gerektirmeden her
bilgisayarda çalışabilmesi için veriler yerel JSON dosyalarında
saklanacaktır. Bu yöntem, veri okuma/yazma hızını artırırken,
"Exception Handling" (Hata Yönetimi) tekniklerini (Dosya
bulunamadı, bozuk veri vb.) uygulama fırsatı sunacaktır.
• Arayüz (GUI) yapımında ise standart Java bileşenleri ve sürükle-
bırak tasarım desteği sunan Swing & WindowBuilder
kullanılacaktır.
12 Nisan 2026
12 Nisan 2026
