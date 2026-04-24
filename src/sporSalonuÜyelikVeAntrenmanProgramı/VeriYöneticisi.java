package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.List;

public interface VeriYöneticisi<T> {
    void ekle(T nesne);

    void sil(String id);

    void guncelle(T nesne);

    List<T> listele();
}
