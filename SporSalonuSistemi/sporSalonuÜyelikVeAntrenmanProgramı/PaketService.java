package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

// PAKET SERVİS SINIFI
public class PaketService implements VeriYöneticisi<UyelikPaketi> {

    private List<UyelikPaketi> paketler = new ArrayList<>();

    @Override
    public void ekle(UyelikPaketi paket) {
        if (paket == null) throw new NullPointerException("Paket null olamaz!");
        paketler.add(paket);
    }

    @Override
    public void sil(String id) {
        // getAd() yerine getPaketAdi() kullanıldı
        paketler.removeIf(p -> p.getPaketAdi().equalsIgnoreCase(id));
    }

    @Override
    public void guncelle(UyelikPaketi yeniPaket) {
        for (int i = 0; i < paketler.size(); i++) {
            if (paketler.get(i).getPaketAdi().equalsIgnoreCase(yeniPaket.getPaketAdi())) {
                paketler.set(i, yeniPaket);
                return;
            }
        }
    }

    @Override
    public List<UyelikPaketi> listele() {
        return new ArrayList<>(paketler);
    }

    @Override
    public UyelikPaketi bul(String id) {
        for (UyelikPaketi p : paketler) {
            if (p.getPaketAdi().equalsIgnoreCase(id)) return p;
        }
        return null;
    }
}