package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.ArrayList;
import java.util.List;

public class PaketService implements VeriYöneticisi<UyelikPaketi> {

    private List<UyelikPaketi> paketler = new ArrayList<>();

    @Override
    public void ekle(UyelikPaketi paket) {
        if (paket == null) {
            throw new NullPointerException("Eklenecek paket boş (null) olamaz!");
        }
        paketler.add(paket);
    }

    @Override
    public void sil(String id) {
        paketler.removeIf(p -> p.getAd().equalsIgnoreCase(id));
    }

    @Override
    public void guncelle(UyelikPaketi yeniPaket) {
        for (int i = 0; i < paketler.size(); i++) {
            if (paketler.get(i).getAd().equalsIgnoreCase(yeniPaket.getAd())) {
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
            if (p.getAd().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}