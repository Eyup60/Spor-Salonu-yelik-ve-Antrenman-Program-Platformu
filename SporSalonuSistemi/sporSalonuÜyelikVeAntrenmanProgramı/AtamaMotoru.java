package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.List;

// ATAMA MOTORU SINIFI
public class AtamaMotoru {

    // OTOMATİK ATAMA YAP
    /**
     * Sistemde bulunan antrenörler arasında en az üyesi olan antrenöre yeni bir üyenin atanmasını sağlar.
     * 
     * @param uye Atanacak yeni üye
     */
    public static void otomatikAtamaYap(Uye uye) {
        // SİSTEMDEKİ TÜM ANTRENÖRLERİ BULUR (güvenli şekilde)
        List<Antrenor> antrenorList = new java.util.ArrayList<>();
        for (Kullanici k : Admin.getKullanicilar()) {
            if (k instanceof Antrenor) antrenorList.add((Antrenor) k);
        }

        if (antrenorList.isEmpty()) {
            System.out.println("SİSTEM: Atama yapılamadı — sistemde antrenör yok.");
            return;
        }

        // EN AZ ÜYESİ OLAN ANTRENÖRÜ BUL
        Antrenor enUygunHoca = antrenorList.get(0);
        for (Antrenor k : antrenorList) {
            int kSize = (k.listele() == null) ? 0 : k.listele().size();
            int enSize = (enUygunHoca.listele() == null) ? 0 : enUygunHoca.listele().size();
            if (kSize < enSize) enUygunHoca = k;
        }

        // ATAMAYI GERÇEKLEŞTİR
        try {
            enUygunHoca.ekle(yeniUye);
            // Kalıcı kaydetme: antrenörün üyeye eklenmesi Antrenor.ekle içinde de kaydediyor,
            // ancak burada kesinlikle veriyi dosyaya yazmak için çağırıyoruz.
            DosyaYoneticisi.verileriKaydet();
            System.out.println("SİSTEM: " + yeniUye.getIsim() + " -> " + enUygunHoca.getIsim() + " hocasına otomatik atandı.");
        } catch (Exception ex) {
            System.err.println("Atama sırasında hata: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
	
}
