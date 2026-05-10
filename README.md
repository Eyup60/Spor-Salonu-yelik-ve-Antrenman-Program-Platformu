Spor Salonu Üyelik ve Antrenman Programı Platformu
Proje Bilgileri
Ders: CENG106 - Object Oriented Programming
Proje Türü: Java Masaüstü Uygulaması
Arayüz: Java Swing / WindowBuilder
Veri Saklama: JSON / CSV Dosya Sistemi

Proje Ekibi
Ekip Üyesi	Öğrenci No	Şube
Eyüp Sansar	24118080085	1. Şube
Alperen Sezen	24118080035	1. Şube
Ali Aydın	24118080066	1. Şube
Yusuf Buğra Aydoğan	24118080093	1. Şube
Proje Konusu ve Amacı
Bu proje, bir spor salonunun günlük operasyonlarını dijitalleştirmek amacıyla geliştirilmiş masaüstü tabanlı bir yönetim platformudur. Sistem; üye yönetimi, antrenör takibi, üyelik paketleri, antrenman programları, ödeme işlemleri ve raporlama süreçlerini tek bir uygulama altında toplamayı hedeflemektedir.

Projenin temel amacı, spor salonlarında manuel olarak yürütülen kayıt, takip ve ödeme işlemlerini daha düzenli, hızlı ve hatasız bir hale getirmektir. Kullanıcıların fiziksel bilgileri, üyelik durumları, antrenman programları ve ödeme kayıtları sistem üzerinden yönetilebilmektedir.

Ayrıca proje, Nesne Yönelimli Programlama (OOP) prensiplerini uygulamalı olarak kullanmak amacıyla tasarlanmıştır. Bu kapsamda kalıtım, soyutlama, kapsülleme, çok biçimlilik, interface kullanımı ve hata yönetimi gibi temel OOP kavramları proje içerisinde uygulanmıştır.

Projenin Genel Özellikleri
Sistem aşağıdaki ana modüllerden oluşmaktadır:

1. Akıllı Üye Kayıt ve Profilleme
Sistem yalnızca temel kullanıcı bilgilerini değil, aynı zamanda üyelerin fiziksel verilerini de kayıt altında tutmaktadır.

Tutulan bilgilerden bazıları:

E-posta
Şifre
Boy
Kilo
Yaş
Yağ oranı
Vücut kitle endeksi
Bu veriler sayesinde üyelerin fiziksel durumu takip edilebilir ve kişiye özel değerlendirme yapılabilir.

2. Dinamik Antrenman Programlayıcı
Üyenin fiziksel bilgilerine ve hedeflerine göre farklı antrenman türleri oluşturulabilir. Kardiyo ve ağırlık gibi farklı antrenman türleri için ayrı hesaplama mantıkları kullanılabilir.

Bu modülde özellikle polymorphism yapısından yararlanılması hedeflenmiştir. Böylece her antrenman türü kendi kalori hesaplama veya program oluşturma mantığına sahip olabilir.

3. Antrenör - Üye İlişki Modülü
Sistemde antrenörler ve üyeler arasında ilişki kurulabilir. Antrenörler uzmanlık alanlarına göre üyelere atanabilir ve kendi sorumluluğundaki üyeleri listeleyebilir.

Bu modül sayesinde:

Üye-antrenör eşleştirmesi yapılabilir.
Antrenör kendi üye listesini görüntüleyebilir.
Üye bilgileri antrenör panelinden takip edilebilir.
Üye güncelleme ve silme işlemleri yapılabilir.
4. Üyelik Paketleri ve Dinamik Ücretlendirme
Sistemde farklı üyelik paketleri tanımlanması hedeflenmiştir:

Standart Paket
Premium Paket
VIP Paket
Dinamik ücretlendirme motoru sayesinde seçilen paket türüne, özel ders durumuna veya kampanya koşullarına göre ücret hesaplanabilir. Böylece manuel hesaplama hatalarının önüne geçilmesi amaçlanmıştır.

5. Finansal Takip ve Raporlama
Sistem ödeme işlemlerini kayıt altına alabilecek şekilde tasarlanmıştır. Ödeme bilgileri ve üyelik durumları takip edilerek yöneticinin finansal süreçleri daha kolay yönetmesi hedeflenmiştir.

Bu kapsamda:

Ödeme kayıtları tutulabilir.
Üyelik paketi bilgileri takip edilebilir.
Finansal veriler listelenebilir.
CSV formatında raporlama yapılabilir.
Kullanılan Teknolojiler
Projede kullanılan temel teknolojiler şunlardır:

Java
Java Swing
AWT
WindowBuilder
JSON Dosya Sistemi
CSV Dosya Sistemi
Eclipse IDE
Proje Tasarımı
Proje, Java Swing kütüphanesi kullanılarak masaüstü tabanlı bir uygulama olarak geliştirilmiştir. Swing tercih edilmesinin temel nedenleri şunlardır:

Java ile doğrudan uyumlu olması
Nesne yönelimli programlama yapısına uygun olması
Harici web sunucusu veya veritabanı gerektirmemesi
Yerel bilgisayarda hızlı çalışabilmesi
Akademik proje kapsamına uygun olması
WindowBuilder ile görsel arayüz tasarımına destek vermesi
Veri Saklama Yapısı
Projede harici bir veritabanı sunucusu kullanılmamaktadır. Bunun yerine veriler yerel dosya sistemi üzerinde saklanmaktadır.

Ana veri saklama formatı: JSON
Raporlama çıktısı formatı: CSV

Kodu Çalıştırma Talimatları
Bu bölüm README dosyasında yer almalıdır.

Gereksinimler
Projeyi çalıştırmadan önce aşağıdaki yazılımların bilgisayarınızda kurulu olması gerekmektedir:

Gereksinim	Versiyon	Açıklama
Java Development Kit (JDK)	11 veya üzeri	Java programını derlemek ve çalıştırmak için
Eclipse IDE	2021 veya üzeri	Projeyi açmak ve geliştirmek için
WindowBuilder Plugin	Eclipse için	GUI bileşenlerini görsel olarak düzenlemek için
Gson Kütüphanesi	2.10.1	JSON okuma ve yazma işlemleri için
Projeyi İndirme
GitHub üzerinden klonlama:

Bash

git clone https://github.com/kullanici-adi/fitcore-pro.git
ZIP olarak indirme:

GitHub sayfasında Code butonuna tıklayın.
Download ZIP seçeneğini seçin.
İndirilen ZIP dosyasını bir klasöre çıkartın.
Eclipse ile Projeyi Açma
Eclipse IDE'yi başlatın.
Üst menüden File → Import seçeneğine tıklayın.
Açılan pencerede General → Existing Projects into Workspace seçeneğini seçin ve Next butonuna tıklayın.
Browse butonuna tıklayarak projeyi çıkarttığınız klasörü seçin.
Proje listesinde FitCorePro projesinin seçili olduğunu doğrulayın.
Finish butonuna tıklayın.
Gson Kütüphanesini Projeye Ekleme
Proje JSON işlemleri için Google Gson kütüphanesini kullanmaktadır. Bu kütüphaneyi projeye eklemek için aşağıdaki adımları izleyin:

JAR dosyasını indirme:

Maven Repository - Gson adresine gidin.
2.10.1 sürümünü seçin.
jar dosyasını indirin.
Eclipse'e ekleme:

Eclipse'de proje adına sağ tıklayın.
Build Path → Configure Build Path seçeneğine tıklayın.
Libraries sekmesine geçin.
Add External JARs butonuna tıklayın.
İndirdiğiniz gson-2.10.1.jar dosyasını seçin.
Apply and Close butonuna tıklayın.
Veri Klasörünü Oluşturma
Uygulama başlatılmadan önce veri dosyalarının saklanacağı klasör yapısının hazır olması gerekmektedir. Proje ana dizininde aşağıdaki klasör yapısını oluşturun:

text

FitCorePro/
├── src/
├── data/
│   ├── uyeler.json
│   ├── antrenorler.json
│   ├── adminler.json
│   ├── programlar.json
│   ├── uyelikler.json
│   └── odemeler.json
└── raporlar/
Not: Uygulama ilk çalıştırıldığında data/ klasörü ve JSON dosyaları otomatik olarak oluşturulacaktır. Eğer otomatik oluşturulmazsa yukarıdaki yapıyı manuel olarak oluşturun ve JSON dosyalarının içine [] yazın.

Boş JSON dosyası içeriği:

JSON

[]
Uygulamayı Çalıştırma
Eclipse'de src klasörünü genişletin.
main paketini açın.
Main.java dosyasına sağ tıklayın.
Run As → Java Application seçeneğine tıklayın.
Uygulama başlatılacak ve giriş ekranı açılacaktır.
Varsayılan Giriş Bilgileri
Uygulama ilk kez çalıştırıldığında sisteme giriş yapabilmek için aşağıdaki varsayılan yönetici hesabı tanımlanmıştır:

Alan	Değer
Kullanıcı ID	admin01
Şifre	admin123
Rol	Admin
Not: İlk girişten sonra şifrenizi değiştirmeniz önerilir.

Sık Karşılaşılan Hatalar ve Çözümleri
Hata	Olası Neden	Çözüm
ClassNotFoundException: com.google.gson.Gson	Gson JAR dosyası projeye eklenmemiş	Gson kütüphanesini Build Path'e ekleyin
FileNotFoundException: data/uyeler.json	data/ klasörü mevcut değil	data/ klasörünü oluşturun ve içine [] yazılmış JSON dosyaları ekleyin
NullPointerException giriş ekranında	JSON dosyaları boş veya bozuk	JSON dosyalarının içeriğinin [] olduğunu kontrol edin
Ekran açılmıyor	JDK sürümü uyumsuz	JDK 11 veya üzeri sürüm kullandığınızı doğrulayın
WindowBuilder görünmüyor	Plugin kurulu değil	Eclipse Marketplace'ten WindowBuilder'ı yükleyin
Proje Klasör Yapısı
text

FitCorePro/
│
├── src/
│   ├── main/
│   │   └── Main.java                  ← Uygulamanın başlangıç noktası
│   │
│   ├── model/
│   │   ├── Kullanici.java             ← Abstract sınıf
│   │   ├── Admin.java
│   │   ├── Antrenor.java
│   │   ├── Uye.java
│   │   ├── Antrenman.java             ← Abstract sınıf
│   │   ├── Kardiyo.java
│   │   ├── AgirlikAntrenman.java
│   │   ├── UyelikPaket.java           ← Abstract sınıf
│   │   ├── StandartPaket.java
│   │   ├── PremiumPaket.java
│   │   ├── VIPPaket.java
│   │   ├── OdemeYontemi.java          ← Abstract sınıf
│   │   ├── NakitOdeme.java
│   │   └── KrediKartiOdeme.java
│   │
│   ├── interface_/
│   │   └── IVeriYoneticisi.java       ← Generic interface
│   │
│   ├── veri/
│   │   ├── UyeVeriYoneticisi.java
│   │   ├── AntrenorVeriYoneticisi.java
│   │   ├── ProgramVeriYoneticisi.java
│   │   └── OdemeVeriYoneticisi.java
│   │
│   ├── gui/
│   │   ├── GirisEkrani.java
│   │   ├── KayitEkrani.java
│   │   ├── AdminPaneli.java
│   │   ├── AntrenorPaneli.java
│   │   ├── UyePaneli.java
│   │   ├── AntrenmanPaneli.java
│   │   ├── PaketSecimEkrani.java
│   │   ├── OdemeEkrani.java
│   │   └── RaporlamaPaneli.java
│   │
│   └── exception/
│       ├── VeriHatasi.java
│       ├── OdemeHatasi.java
│       └── YetkiHatasi.java
│
├── data/
│   ├── uyeler.json
│   ├── antrenorler.json
│   ├── adminler.json
│   ├── programlar.json
│   ├── uyelikler.json
│   └── odemeler.json
│
├── raporlar/
│   └── (CSV raporları buraya kaydedilir)
│
└── README.md
İletişim
Proje ile ilgili sorularınız için aşağıdaki ekip üyeleriyle iletişime geçebilirsiniz:

Ad Soyad	Öğrenci No	Konu
Eyüp Sansar	24118080085	Sistem altyapısı, giriş ekranı
Alperen Sezen	24118080035	Antrenman modülü
Ali Aydın	24118080066	Üyelik paketleri, antrenör-üye ilişkisi
Yusuf Buğra Aydoğan	24118080093	Ödeme sistemi, veri kalıcılığı
