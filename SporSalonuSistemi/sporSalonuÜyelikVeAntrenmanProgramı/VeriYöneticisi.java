package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.List;

public interface VeriYöneticisi<T> {
    // TİPTEN BAĞIMSIZ OLARAK HERHANGİ BİR NESNEYİ SİSTEME EKLER
    void ekle(T nesne);
    
    // BENZERSİZ KİMLİK NUMARASI ÜZERİNDEN SİLME İŞLEMİ YAPAR
    void sil(String id);
    
    // MEVCUT VERİLERİ YENİ NESNE BİLGİLERİYLE GÜNCELLER
    void guncelle(T nesne);
    
    // TÜM KAYITLARI BİR LİSTE İÇERİSİNDE TOPLU OLARAK GETİRİR
    List<T> listele();
    
    // BELİRLİ BİR ID DEĞERİNE SAHİP NESNEYİ LİSTEDE BULUR
    T bul(String id);
}