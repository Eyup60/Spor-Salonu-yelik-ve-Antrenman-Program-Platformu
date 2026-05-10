# Spor Salonu Üyelik ve Antrenman Programı Platformu

## Proje Bilgileri

**Ders:** CENG106 - Object Oriented Programming

**Proje Türü:** Java Masaüstü Uygulaması

**Arayüz:** Java Swing / WindowBuilder

**Veri Saklama:** JSON / CSV Dosya Sistemi

---

## Proje Ekibi

| Ekip Üyesi | Öğrenci No | Şube |
| --- | --- | --- |
| Eyüp Sansar | 24118080085 | 1. Şube |
| Alperen Sezen | 24118080035 | 1. Şube |
| Ali Aydın | 24118080066 | 1. Şube |
| Yusuf Buğra Aydoğan | 24118080093 | 1. Şube |

---

## Proje Konusu ve Amacı

Bu proje, bir spor salonunun günlük operasyonlarını dijitalleştirmek amacıyla geliştirilmiş masaüstü tabanlı bir yönetim platformudur. Sistem; üye yönetimi, antrenör takibi, üyelik paketleri, antrenman programları, ödeme işlemleri ve raporlama süreçlerini tek bir uygulama altında toplamayı hedeflemektedir.

Projenin temel amacı, spor salonlarında manuel olarak yürütülen kayıt, takip ve ödeme işlemlerini daha düzenli, hızlı ve hatasız bir hale getirmektir. Kullanıcıların fiziksel bilgileri, üyelik durumları, antrenman programları ve ödeme kayıtları sistem üzerinden yönetilebilmektedir.

Ayrıca proje, Nesne Yönelimli Programlama (OOP) prensiplerini uygulamalı olarak kullanmak amacıyla tasarlanmıştır. Bu kapsamda kalıtım, soyutlama, kapsülleme, çok biçimlilik, interface kullanımı ve hata yönetimi gibi temel OOP kavramları proje içerisinde uygulanmıştır.

---

## Projenin Genel Özellikleri

### 1. Akıllı Üye Kayıt ve Profilleme

Sistem yalnızca temel kullanıcı bilgilerini değil, aynı zamanda üyelerin fiziksel verilerini de kayıt altında tutmaktadır. Boy, kilo, yaş ve yağ oranı gibi veriler üzerinden Vücut Kitle Endeksi (VKE) hesaplamaları yapılmaktadır.

### 2. Dinamik Antrenman Programlayıcı

Üyenin fiziksel bilgilerine göre kardiyo veya ağırlık odaklı antrenman türleri oluşturulur. **Polymorphism** yapısı kullanılarak her antrenman türü için özelleşmiş kalori hesaplama mantığı devreye girer.

### 3. Antrenör - Üye İlişki Modülü

Antrenörler uzmanlık alanlarına göre üyelere sistem tarafından atanır. Antrenörler kendi panelleri üzerinden sorumlu oldukları üyeleri listeleyebilir, gelişimlerini takip edebilir veya üye üzerinde düzenleme yapabilirler.

### 4. Üyelik Paketleri ve Dinamik Ücretlendirme

Standart, Premium ve VIP paket seçenekleri mevcuttur. Ücretlendirme motoru; paket türü, ek özel ders sayısı ve vergi oranlarını hesaplayarak hata payını minimize eder.

### 5. Finansal Takip ve Raporlama

Yönetici paneli üzerinden tüm üyelerin ödeme durumları ve paket bilgileri izlenebilir. Veriler finansal raporlama amacıyla listelenebilir ve takip edilebilir.

---

## Kullanılan Teknolojiler

* **Dil:** Java
* **Arayüz:** Java Swing / AWT / WindowBuilder
* **Veri Yapısı:** JSON / CSV (Dosya Sistemi)
* **IDE:** Eclipse IDE
* **Versiyon Kontrol:** GitHub
* **Harici Kütüphaneler:** JSON işleme ve veri yönetimi için ek kütüphaneler kullanılmıştır.

---

## Veri Saklama Yapısı

Projede harici bir veritabanı sunucusu kullanılmamaktadır. Veriler taşınabilirlik ve akademik standartlar gereği yerel dosya sistemi üzerinde **JSON** formatında saklanmaktadır.

---

## Kodu Çalıştırma Talimatları

### 1. Repository'den Çekme

Projeyi GitHub üzerinden `git clone` komutuyla veya **Download ZIP** seçeneğiyle bilgisayarınıza indirin.

### 2. Ön Hazırlık

* Bilgisayarınızda **Java JDK 17** veya üzeri bir sürümün kurulu olduğundan emin olun.
* Geliştirme ortamı olarak **Eclipse IDE** kullanılması önerilir.

### 3. Kütüphane Bağımlılıkları (Önemli)

Proje içerisinde kullanılan ek kütüphaneler (örneğin JSON işleme kütüphaneleri), GitHub repository'sindeki **`lib/`** klasörü altında yer almaktadır.

* Eclipse üzerinde projeye sağ tıklayın.
* `Properties -> Java Build Path -> Libraries -> Add JARs` yolunu izleyin.
* `lib` klasöründeki tüm `.jar` dosyalarını seçerek projeye dahil edin.

### 4. Uygulamanın Başlatılması

* Proje yapısı içindeki `GirişEkranı.java` dosyasına sağ tıklayın.
* `Run As -> Java Application` seçeneği ile uygulamayı başlatın.

### 5. Veri Dosyaları

Uygulama ilk kez çalıştığında gerekli veri dosyalarını (kullanicilar.json vb.) otomatik olarak oluşturacaktır. Yazma hataları almamak için projenin bulunduğu klasörde yazma izinlerinin açık olduğundan emin olun.
