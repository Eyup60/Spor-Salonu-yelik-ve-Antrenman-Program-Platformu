# Spor Salonu Üyelik ve Antrenman Programı Platformu

## Proje Bilgileri

**Ders:** CENG106 - Object Oriented Programming   
**Proje Türü:** Java Masaüstü Uygulaması  
**Arayüz:** Java Swing / WindowBuilder  
**Veri Saklama:** JSON / CSV Dosya Sistemi  

---

## Proje Ekibi

| Ekip Üyesi | Öğrenci No | Şube |
|---|---:|---|
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

Sistem aşağıdaki ana modüllerden oluşmaktadır:

### 1. Akıllı Üye Kayıt ve Profilleme

Sistem yalnızca temel kullanıcı bilgilerini değil, aynı zamanda üyelerin fiziksel verilerini de kayıt altında tutmaktadır.

Tutulan bilgilerden bazıları:

- E-posta
- Şifre
- Boy
- Kilo
- Yaş
- Yağ oranı
- Vücut kitle endeksi

Bu veriler sayesinde üyelerin fiziksel durumu takip edilebilir ve kişiye özel değerlendirme yapılabilir.

---

### 2. Dinamik Antrenman Programlayıcı

Üyenin fiziksel bilgilerine ve hedeflerine göre farklı antrenman türleri oluşturulabilir. Kardiyo ve ağırlık gibi farklı antrenman türleri için ayrı hesaplama mantıkları kullanılabilir.

Bu modülde özellikle **polymorphism** yapısından yararlanılması hedeflenmiştir. Böylece her antrenman türü kendi kalori hesaplama veya program oluşturma mantığına sahip olabilir.

---

### 3. Antrenör - Üye İlişki Modülü

Sistemde antrenörler ve üyeler arasında ilişki kurulabilir. Antrenörler uzmanlık alanlarına göre üyelere atanabilir ve kendi sorumluluğundaki üyeleri listeleyebilir.

Bu modül sayesinde:

- Üye-antrenör eşleştirmesi yapılabilir.
- Antrenör kendi üye listesini görüntüleyebilir.
- Üye bilgileri antrenör panelinden takip edilebilir.
- Üye güncelleme ve silme işlemleri yapılabilir.

---

### 4. Üyelik Paketleri ve Dinamik Ücretlendirme

Sistemde farklı üyelik paketleri tanımlanması hedeflenmiştir:

- Standart Paket
- Premium Paket
- VIP Paket

Dinamik ücretlendirme motoru sayesinde seçilen paket türüne, özel ders durumuna veya kampanya koşullarına göre ücret hesaplanabilir. Böylece manuel hesaplama hatalarının önüne geçilmesi amaçlanmıştır.

---

### 5. Finansal Takip ve Raporlama

Sistem ödeme işlemlerini kayıt altına alabilecek şekilde tasarlanmıştır. Ödeme bilgileri ve üyelik durumları takip edilerek yöneticinin finansal süreçleri daha kolay yönetmesi hedeflenmiştir.

Bu kapsamda:

- Ödeme kayıtları tutulabilir.
- Üyelik paketi bilgileri takip edilebilir.
- Finansal veriler listelenebilir.
- CSV formatında raporlama yapılabilir.

---

## Kullanılan Teknolojiler

Projede kullanılan temel teknolojiler şunlardır:

- Java
- Java Swing
- AWT
- WindowBuilder
- JSON Dosya Sistemi
- CSV Dosya Sistemi
- Eclipse IDE

---

## Proje Tasarımı

Proje, Java Swing kütüphanesi kullanılarak masaüstü tabanlı bir uygulama olarak geliştirilmiştir. Swing tercih edilmesinin temel nedenleri şunlardır:

- Java ile doğrudan uyumlu olması
- Nesne yönelimli programlama yapısına uygun olması
- Harici web sunucusu veya veritabanı gerektirmemesi
- Yerel bilgisayarda hızlı çalışabilmesi
- Akademik proje kapsamına uygun olması
- WindowBuilder ile görsel arayüz tasarımına destek vermesi

---

## Veri Saklama Yapısı

Projede harici bir veritabanı sunucusu kullanılmamaktadır. Bunun yerine veriler yerel dosya sistemi üzerinde saklanmaktadır.

Ana veri saklama formatı:

JSON
