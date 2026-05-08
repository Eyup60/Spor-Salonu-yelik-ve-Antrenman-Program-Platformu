package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

public class AtamaMotoru {

    public static void otomatikAtamaYap(Uye uye) {
        List<Antrenor> antrenorList = new ArrayList<>();
        for (Kullanici k : Admin.getKullanicilar()) {
            if (k instanceof Antrenor) antrenorList.add((Antrenor) k);
        }

        if (antrenorList.isEmpty()) return;

        Antrenor enUygunHoca = antrenorList.get(0);
        for (Antrenor k : antrenorList) {
            int kSize = (k.listele() == null) ? 0 : k.listele().size();
            int enSize = (enUygunHoca.listele() == null) ? 0 : enUygunHoca.listele().size();
            if (kSize < enSize) enUygunHoca = k;
        }

        try {
            enUygunHoca.ekle(uye);
            DosyaYoneticisi.verileriKaydet();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}