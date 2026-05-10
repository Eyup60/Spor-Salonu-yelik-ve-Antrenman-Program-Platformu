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

Projenin temel amacı, spor salonlarında manuel olarak yürütülen kayıt, takip ve ödeme işlemlerini daha düzenli, hızlı ve hatasız bir hale getirmektir. Ayrıca proje, Nesne Yönelimli Programlama (OOP) prensiplerini (kalıtım, soyutlama, kapsülleme, çok biçimlilik) uygulamalı olarak kullanmak amacıyla tasarlanmıştır.

---

## Projenin Genel Özellikleri

### 1. Akıllı Üye Kayıt ve Profilleme

Üyelerin fiziksel verileri (boy, kilo, yaş, yağ oranı) üzerinden Vücut Kitle Endeksi (VKE) hesaplamaları yapılarak gelişim takibi sağlanır.

### 2. Dinamik Antrenman Programlayıcı

**Polymorphism** yapısı kullanılarak kardiyo ve ağırlık odaklı antrenman türleri için özelleşmiş kalori hesaplama ve program oluşturma mantığı sunulur.

### 3. Antrenör - Üye İlişki Modülü

Antrenörler uzmanlık alanlarına göre üyelere atanır. Antrenörler kendi panelleri üzerinden sorumlu oldukları üyeleri listeleyebilir ve programlarını güncelleyebilirler.

### 4. Üyelik Paketleri ve Dinamik Ücretlendirme

Standart, Premium ve VIP paket seçenekleri, özel ders limitleri ve vergi oranları dahil edilerek dinamik bir ücretlendirme motoru ile yönetilir.

### 5. Finansal Takip ve Raporlama

Yönetici paneli üzerinden tüm üyelerin ödeme durumları izlenebilir ve finansal veriler listelenebilir.

---

## Kullanılan Teknolojiler

* **Dil:** Java
* **Arayüz:** Java Swing / AWT / WindowBuilder
* **Veri Yapısı:** JSON / CSV (Dosya Sistemi)
* **Güvenlik:** SHA-256 Şifreleme Algoritması
* **Versiyon Kontrol:** GitHub
* **Harici Kütüphaneler:** GSON (JSON işleme için kullanılmıştır ve repository içerisinde yer almaktadır).

---

## Veri Saklama Yapısı

Proje, profesyonel yazılım standartlarına uygun olarak tasarlanmış bir veri saklama mimarisine sahiptir:

* **Platform Bağımsızlığı:** Uygulama, platform bağımsız çalışabilmesi için verileri işletim sistemine göre dinamik olarak belirler ve kullanıcının ana dizinindeki (`user.home`) `.sporSalonu` klasöründe saklar.
* **Veri Güvenliği:** Kullanıcı şifreleri, veri güvenliği protokolü gereği JSON dosyası içerisinde **SHA-256** hash algoritması ile şifrelenmiş olarak tutulur.

---

## Kodu Çalıştırma Talimatları

### 1. Repository'den Çekme

Projeyi GitHub üzerinden `git clone` komutuyla veya **Download ZIP** seçeneğiyle bilgisayarınıza indirin.

### 2. Ön Hazırlık

* Bilgisayarınızda **Java JDK 17** veya üzeri bir sürümün kurulu olduğundan emin olun.
* Geliştirme ortamı olarak **Eclipse IDE** kullanılması önerilir.

### 3. Kütüphane Bağımlılıkları (Önemli)

Proje içerisinde kullanılan harici kütüphaneler repository'deki **`lib/`** klasörü altındadır.

* Eclipse üzerinden `Properties -> Java Build Path -> Libraries -> Add JARs` yolunu izleyerek `lib` klasöründeki `.jar` dosyalarını projeye dahil edin.

### 4. Uygulamanın Başlatılması

* `GirişEkranı.java` dosyasına sağ tıklayıp `Run As -> Java Application` seçeneği ile uygulamayı başlatın.

### 5. İlk Giriş ve Otomatik Yapılandırma

Uygulama ilk kez çalıştırıldığında gerekli dosya dizinlerini otomatik olarak oluşturur. Sisteme giriş yapabilmek için tanımlanan varsayılan yönetici bilgileri şöyledir:

* **E-posta:** `admin@gym.com`
* **Şifre:** `123456`

> **Not:** Veri dosyası konumu işletim sistemine göre `~/.sporSalonu/kullanicilar.json` adresinde otomatik olarak yönetilir.
