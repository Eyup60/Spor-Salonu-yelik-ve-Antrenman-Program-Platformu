package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.List;

// ATAMA MOTORU SINIFI
public class AtamaMotoru {

    // OTOMATİK ATAMA YAP
	public static void otomatikAtamaYap(Uye yeniUye) {
        // SİSTEMDEKİ TÜM ANTRENÖRLERİ BULUR
        List<Antrenor> antrenör = Admin.getKullanicilar().stream()
            .filter(k -> k instanceof Antrenor)
            .map(k -> (Antrenor) k)
            .toList();

        if (antrenör.isEmpty()) return;

        // EN AZ UYESİ OLAN ANTRENÖRE ATAMA YAPILIR
        Antrenor enUygunHoca = antrenör.get(0);
        for (Antrenor koç : antrenör) {
            if (koç.listele().size() < enUygunHoca.listele().size()) {
                enUygunHoca = koç;
            }
        }

        // ATAMAYI GERÇEKLEŞTİRİR
        enUygunHoca.ekle(yeniUye);
        System.out.println("SİSTEM: " + yeniUye.getIsim() + " -> " + enUygunHoca.getIsim() + " hocasına otomatik atandı.");
    }
	
}
