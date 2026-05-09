package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

// ATAMA MOTORU SINIFI
// YENİ ÜYELERİ EN UYGUN ANTRENÖRE ATAR
public class AtamaMotoru {

    // OTOMATİK ATAMA YAP METOTU
    // YENİ ÜYEYİ EN AZ ÜYESİ OLAN ANTRENÖRE ATAR
    public static void otomatikAtamaYap(Uye uye) {
        // ANTRENÖR LİSTESİNİ OLUŞTUR
        List<Antrenor> antrenorList = new ArrayList<>();
        for (Kullanici k : Admin.getKullanicilar()) {
            if (k instanceof Antrenor) antrenorList.add((Antrenor) k);
        }

        // ANTRENÖR YOKSA İŞLEMİ SONLANDIR
        if (antrenorList.isEmpty()) return;

        // EN AZ ÜYESİ OLAN ANTRENÖRÜ BUL
        Antrenor enUygunHoca = antrenorList.get(0);
        for (Antrenor k : antrenorList) {
            int kSize = (k.listele() == null) ? 0 : k.listele().size();
            int enSize = (enUygunHoca.listele() == null) ? 0 : enUygunHoca.listele().size();
            if (kSize < enSize) enUygunHoca = k;
        }

        try {
            // ÜYEYİ ANTRENÖRE EKLE VE VERİLERİ KAYDET
            enUygunHoca.ekle(uye);
            DosyaYoneticisi.verileriKaydet();
        } catch (Exception ex) {
            // HATA DURUMUNDA LOG YAZ
            ex.printStackTrace();
        }
    }
}