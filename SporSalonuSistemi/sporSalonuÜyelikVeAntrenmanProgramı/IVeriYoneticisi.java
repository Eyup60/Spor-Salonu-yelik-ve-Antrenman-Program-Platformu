package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.List;

// Tüm servis sınıfları için standart veri yönetim (CRUD) kurallarını belirleyen arayüz.
// OOP Prensibi: Generic (<T>) yapı kullanılarak, bu arayüzün her türlü veri modeli 
// (Üye, Antrenör, Paket vb.) ile tekrar yazılmadan kullanılabilmesi sağlanmıştır.
public interface IVeriYoneticisi<T> {
	
    // Sisteme (listeye veya veritabanına) yeni bir nesne kaydeder
    void ekle(T nesne);
	
    // Belirtilen index (sıra) numarasındaki kaydı sistemden siler
    void sil(int index);
	
    // Belirtilen indexteki mevcut kaydı, gönderilen yeni nesne verisiyle değiştirir
    void guncelle(int index, T nesne);
	
    // Sistemde kayıtlı olan tüm nesneleri bir liste (Collection) halinde geri döndürür
    List<T> listele();

}