package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// ÜYEYE ATANMIŞ OLAN ANTRENMAN LİSTESİNİ VE HESAPLANAN KALORİLERİ GÖSTEREN DİALOG PENCERESİ
public class AntrenmanProgramiBilgileriDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    // PENCERE YAPISINI VE VERİLERİN GÖSTERİLECEĞİ LİSTE BİLEŞENLERİNİ HAZIRLAYAN YAPICI METOT
    public AntrenmanProgramiBilgileriDialog(JFrame parent, Uye uye) {
        // MODAL ÖZELLİĞİ SAYESİNDE BU PENCERE KAPANMADAN ANA EKRANA DÖNÜLMESİNİ ENGELLER
        super(parent, "Atanan Antrenman Programı", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));

        // ANTRENMANLARIN SATIR SATIR GÖRÜNECEĞİ LİSTE MODELİ VE GÖRSEL LİSTE BİLEŞENİ
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> lst = new JList<>(model);
        lst.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // LİSTE İÇERİĞİ PENCEREYE SIĞMADIĞINDA KAYDIRMA ÇUBUĞU OLUŞMASINI SAĞLAR
        JScrollPane sp = new JScrollPane(lst);
        add(sp, BorderLayout.CENTER);

        // PROGRAM ATAMA YÖNETİCİSİ ÜZERİNDEN ÜYENİN GÜNCEL ANTRENMAN LİSTESİNİ ÇEKER
        ProgramAtamaYöneticisi yonetici = new ProgramAtamaYöneticisi();
        List<Antrenman> program = yonetici.programGetir(uye);

        // PROGRAMIN BOŞ OLUP OLMADIĞINI KONTROL EDER VE KULLANICIYA BİLGİ VERİR
        if (program == null || program.isEmpty()) {
            model.addElement("Bu üyeye henüz bir program atanmamış.");
        } else {
            double toplam = 0.0;
            // HER BİR ANTRENMANI DÖNGÜ İLE GEZEREK ÜYEYE ÖZEL KALORİ HESABI YAPAR
            for (int i = 0; i < program.size(); i++) {
                Antrenman a = program.get(i);
                // POLİMORFİZM KULLANILARAK HER ANTRENMAN TÜRÜ KENDİ KALORİSİNİ HESAPLAR
                double k = a.kaloriHesapla(uye);
                toplam += k;
                // ANTRENMAN ADINI VE YAKILACAK KALORİYİ LİSTEYE FORMATLI ŞEKİLDE EKLER
                model.addElement(String.format("%d. %s - %.2f kcal", i+1, a.toString(), k));
            }
            // TÜM ANTRENMANLARIN SONUNDA TOPLAM TAHMİNİ KALORİ TÜKETİMİNİ GÖSTERİR
            model.addElement("----------------------------------------");
            model.addElement(String.format("Tahmini Günlük Toplam Kalori: %.2f kcal", toplam));
        }

        // PENCERENİN ALT KISMINA KAPATMA BUTONU EKLEYEN PANEL YAPISI
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnKapat = new JButton("Kapat");
        // BUTONA TIKLANDIĞINDA SADECE BU DİALOG PENCERESİNİ KAPATIR
        btnKapat.addActionListener(e -> dispose());
        pnl.add(btnKapat);
        add(pnl, BorderLayout.SOUTH);
    }
}