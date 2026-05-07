package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.List;

// VERİ YÖNETİCİSİ ARAYÜZÜ
public interface VeriYöneticisi<T> {
    // EKLE
    void ekle(T nesne);
    
    // SİL
    void sil(String id);
    
    // GÜNCELLE
    void guncelle(T nesne);
    
    // LİSTELE
    List<T> listele();
    
    // BUL
    T bul(String id);
}