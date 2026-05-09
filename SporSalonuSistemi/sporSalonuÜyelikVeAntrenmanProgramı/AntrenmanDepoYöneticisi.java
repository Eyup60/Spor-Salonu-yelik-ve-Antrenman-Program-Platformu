package sporSalonuÜyelikVeAntrenmanProgramı;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// SİSTEMDEKİ TÜM ANTRENMAN ÇEŞİTLERİNİ LİSTELEYEN, YÖNETEN VE HAZIR PROGRAMLARI OLUŞTURAN DEPO SINIFI
public class AntrenmanDepoYöneticisi implements VeriYöneticisi<Antrenman>, Serializable {

    private static final long serialVersionUID = 1L;
    private List<Antrenman> antrenmanListesi;

    // ANTRENMAN LİSTESİNİ BELLEKTE TUTMAK İÇİN ARRAYLIST BAŞLATAN YAPICI METOT
    public AntrenmanDepoYöneticisi() {
        this.antrenmanListesi = new ArrayList<>();
    }

    // LİSTEYE YENİ BİR ANTRENMAN NESNESİ EKLEYEN METOT
    @Override
    public void ekle(Antrenman veri) {
        if (veri != null) antrenmanListesi.add(veri);
    }

    // BENZERSİZ ID NUMARASINA GÖRE ANTRENMANI LİSTEDEN KALDIRAN METOT
    @Override
    public void sil(String id) {
        antrenmanListesi.removeIf(a -> a.getId().equals(id));
    }

    // MEVCUT BİR ANTRENMANIN BİLGİLERİNİ ID ÜZERİNDEN BULUP GÜNCELLEYEN METOT
    @Override
    public void guncelle(Antrenman yeniVeri) {
        for (int i = 0; i < antrenmanListesi.size(); i++) {
            if (antrenmanListesi.get(i).getId().equals(yeniVeri.getId())) {
                antrenmanListesi.set(i, yeniVeri);
                return;
            }
        }
    }
    
    // VERİLEN ID İLE EŞLEŞEN ANTRENMAN NESNESİNİ LİSTE İÇİNDE ARAYIP DÖNDÜREN METOT
    @Override
	public Antrenman bul(String id) {
    	for (Antrenman a : antrenmanListesi) {
            if (a.getId().equals(id)) return a;
        }
        return null;
	}

    // MEVCUT ANTRENMAN LİSTESİNİN BİR KOPYASINI DÖNDÜREREK VERİ GÜVENLİĞİ SAĞLAYAN METOT
    @Override
    public List<Antrenman> listele() {
        return new ArrayList<>(antrenmanListesi);
    }

    // SEÇİLEN HEDEFE GÖRE (KİLO VERME, KAS KÜTLESİ VB.) ÖNCEDEN TANIMLANMIŞ ANTRENMANLARI YÜKLEYEN METOT
    public void hazirProgramYukle(String secim) {
        // YENİ PROGRAM YÜKLENMEDEN ÖNCE ESKİ LİSTEYİ TEMİZLER
        antrenmanListesi.clear();
        
        // KULLANICI SEÇİMİNE GÖRE FARKLI BRANŞLARDAKİ ANTRENMAN NESNELERİNİ LİSTEYE EKLER
        switch (secim.toLowerCase()) {
            case "kilo verme":
                // KARDİYO VE YAĞ YAKIMI ODAKLI PROGRAM İÇERİĞİ
                ekle(new Kardiyo("Hafif Yürüyüş", "Isınma", 10, "Kolay", 6.0, "Koşu Bandı", 1.0, 1));
                ekle(new Kardiyo("Yağ Yakım Koşusu", "Kardiyo", 25, "Zor", 9.5, "Koşu Bandı", 3.0, 1));
                ekle(new Kalisteniks("Burpees", "Yağ Yakımı", 15, "Zor", 4, 15, 1.3, 0.0, "Tüm Vücut"));
                // ... (Diğer eklemeler devam eder)
                break;

            case "kas kütlesi":
                // HİPERTROFİ VE GÜÇ ODAKLI AĞIRLIK ANTRENMANI PROGRAMI
                ekle(new Agirlik("Bench Press", "Göğüs", 20, "Zor", 4, 8, 85.0, "Göğüs", "Barbell", 120));
                ekle(new Agirlik("Barbell Squat", "Bacak", 25, "Zor", 4, 8, 100.0, "Bacak", "Barbell", 150));
                ekle(new Agirlik("Deadlift", "Sırt", 30, "Çok Zor", 3, 5, 130.0, "Sırt", "Barbell", 180));
                break;

            case "boksör":
                // KONDİSYON, HIZ VE PATLAYICI GÜÇ ODAKLI ÖZEL PROGRAM
                ekle(new Kardiyo("İp Atlama", "Kondisyon", 15, "Zor", 120.0, "Atlama İpi", 0.0, 1));
                ekle(new Kardiyo("Kum Torbası Interval", "Dayanıklılık", 20, "Çok Zor", 15.0, "Kum Torbası", 0.0, 1));
                ekle(new Kalisteniks("Patlayıcı Şınav", "Güç", 10, "Zor", 5, 20, 1.4, 0.0, "İtme"));
                break;

            case "powerlifter":
                // MAKSİMUM KUVVET VE TEMEL BİLEŞİK EGZERSİZ ODAKLI PROGRAM
                ekle(new Agirlik("Ağır Squat", "Bacak", 40, "Çok Zor", 5, 3, 160.0, "Alt Vücut", "Barbell", 300));
                ekle(new Agirlik("Ağır Deadlift", "Sırt", 40, "Çok Zor", 5, 1, 180.0, "Arka Zincir", "Barbell", 300));
                break;

            case "cimnastikçi":
                // ESNEKLİK, DENGE VE VÜCUT AĞIRLIĞI KONTROLÜ ODAKLI PROGRAM
                ekle(new Kalisteniks("Handstand Hold", "Denge", 15, "Zor", 5, 60, 1.2, 0.0, "Statik"));
                ekle(new Esneklik("Splits Çalışması", "Esneklik", 20, "Zor", "Jimnastik", 60));
                break;

            // DİĞER VAKA DURUMLARI (FUTBOLCU, TENİSÇİ, BİSİKLETÇİ VB.) İÇİN ÖZEL NESNELER OLUŞTURULUR
            default:
                // TANIMSIZ BİR SEÇİM YAPILDIĞINDA LİSTE BOŞ KALIR
                break;
        }
    }
}