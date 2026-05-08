package sporSalonuÜyelikVeAntrenmanProgramı;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// PROGRAM ATAMA YÖNETİCİSİ SINIFI
public class ProgramAtamaYöneticisi {
    
    private Map<String, List<Antrenman>> uyeProgramlari;
    private AntrenmanDepoYöneticisi depo;
    private final String DOSYA_YOLU = "uye_antrenmanlari.dat";

    public ProgramAtamaYöneticisi() {
        this.uyeProgramlari = new HashMap<>();
        this.depo = new AntrenmanDepoYöneticisi();
        verileriYukle();
    }

    public void programAta(Uye uye, String programTipi) {
        depo.hazirProgramYukle(programTipi);
        uyeProgramlari.put(uye.getEmail(), depo.listele());
        System.out.println(uye.getIsim() + " adlı üyeye '" + programTipi.toUpperCase() + "' programı başarıyla atandı!");
        verileriKaydet();
    }

    public List<Antrenman> programGetir(Uye uye) {
        return uyeProgramlari.get(uye.getEmail());
    }

    public double gunlukKaloriHesapla(Uye uye) {
        List<Antrenman> program = programGetir(uye);
        if (program == null || program.isEmpty()) {
            return 0.0;
        }

        double toplamKalori = 0;
        for (Antrenman hareket : program) {
            toplamKalori += hareket.kaloriHesapla(uye);
        }
        return toplamKalori;
    }

    public void uveninPrograminiEkranaBas(Uye uye) {
        List<Antrenman> program = programGetir(uye);
        
        System.out.println("=====================================");
        System.out.println(uye.getIsim().toUpperCase() + " " + uye.getSoyisim().toUpperCase() + " - ANTRENMAN PROGRAMI");
        System.out.println("Boy: " + uye.getBoy() + " | Kilo: " + uye.getKilo());
        System.out.println("=====================================");

        if (program == null || program.isEmpty()) {
            System.out.println("Bu üyeye henüz bir program atanmamış.");
            return;
        }

        for (int i = 0; i < program.size(); i++) {
            System.out.print((i + 1) + ". Hareket -> ");
            program.get(i).detayGoster();
        }
        
        System.out.println("-------------------------------------");
        System.out.printf("Tahmini Günlük Kalori Yakımı: %.2f kcal\n", gunlukKaloriHesapla(uye));
        System.out.println("=====================================");
    }

    public void verileriKaydet() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DOSYA_YOLU))) {
            oos.writeObject(uyeProgramlari);
        } catch (IOException e) {
            System.err.println("Antrenman verileri kaydedilirken hata: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void verileriYukle() {
        File dosya = new File(DOSYA_YOLU);
        if (!dosya.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DOSYA_YOLU))) {
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                this.uyeProgramlari = (Map<String, List<Antrenman>>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Antrenman verileri yüklenirken hata: " + e.getMessage());
        }
    }
}
