package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.List;

public interface IVeriYoneticisi<T> {
    void ekle(T veri);
    void sil(String id); // int yerine String
    void guncelle(String id, T yeniVeri); // int yerine String
    T ara(String id); // int yerine String
    List<T> tumunuListele();
}
