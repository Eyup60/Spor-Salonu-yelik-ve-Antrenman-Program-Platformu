package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;

// ÜYELERE HAZIR ANTRENMAN PAKETLERİNDEN BİRİNİ SEÇME VE ATAMA İMKANI SUNAN ARA YÜZ SINIFI
public class AntrenmanProgramiDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    // PENCERE TASARIMINI VE BUTONLARIN İŞLEVLERİNİ YAPILANDIRAN YAPICI METOT
    public AntrenmanProgramiDialog(JFrame parent, Uye uye) {
        super(parent, "Antrenman Programı", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        // ANA PANEL DÜZENİNİ ÜST, ORTA VE ALT OLARAK ÜÇE BÖLEN BORDERLAYOUT YAPISI
        getContentPane().setLayout(new BorderLayout(10,10));

        // --- ÜST KISIM: PROGRAM TİPİ SEÇİM ALANI ---
        JPanel pnlUst = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlUst.add(new JLabel("Hazır Program Tipi:"));

        // DEPO YÖNETİCİSİNDEKİ MANTIĞA UYGUN TÜM BRANŞLARIN LİSTESİ
        String[] programTipleri = new String[] {
            "kilo verme","kas kütlesi","boksör","powerlifter","maratoncu",
            "yüzücü","basketbolcu","cimnastikçi","futbolcu","tenisçi",
            "güreşçi","bisikletçi","voleybolcu","crossfitçi","bilek güreşçisi"
        };

        // KULLANICININ BRANŞ SEÇEBİLECEĞİ AÇILIR MENÜ (COMBOBOX)
        JComboBox<String> cmbProgram = new JComboBox<>(programTipleri);
        pnlUst.add(cmbProgram);

        JButton btnYukle = new JButton("Programı Görüntüle");
        pnlUst.add(btnYukle);

        getContentPane().add(pnlUst, BorderLayout.NORTH);

        // --- ORTA KISIM: ANTRENMAN İÇERİĞİ LİSTELEME ALANI ---
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> lstHareketler = new JList<>(listModel);
        // MONOSPACED FONT KULLANILARAK VERİLERİN DAHA DÜZGÜN HİZALANMASI SAĞLANIR
        lstHareketler.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp = new JScrollPane(lstHareketler);
        getContentPane().add(sp, BorderLayout.CENTER);

        // --- ALT KISIM: ÖZET BİLGİ VE İŞLEM BUTONLARI ---
        JPanel pnlAlt = new JPanel(new BorderLayout(10,10));

        JLabel lblToplam = new JLabel("Tahmini Toplam Kalori: 0.00 kcal");
        pnlAlt.add(lblToplam, BorderLayout.NORTH);

        JPanel pnlButonlar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAta = new JButton("Programa Ata");
        JButton btnKapat = new JButton("Kapat");
        pnlButonlar.add(btnAta);
        pnlButonlar.add(btnKapat);

        pnlAlt.add(pnlButonlar, BorderLayout.SOUTH);
        getContentPane().add(pnlAlt, BorderLayout.SOUTH);

        // VERİ VE ATAMA İŞLEMLERİNİ YÜRÜTECEK YÖNETİCİ NESNELERİ
        AntrenmanDepoYöneticisi depo = new AntrenmanDepoYöneticisi();
        ProgramAtamaYöneticisi atamaYonetici = new ProgramAtamaYöneticisi();

        // [GÖRÜNTÜLE] BUTONU: SEÇİLEN PROGRAMI DEPOYA YÜKLER VE LİSTEYİ GÜNCELLER
        btnYukle.addActionListener(e -> {
            String secim = ((String)cmbProgram.getSelectedItem()).trim();
            depo.hazirProgramYukle(secim); // DEPOYU İLGİLİ PROGRAMLA DOLDURUR
            listModel.clear();
            double toplam = 0.0;
            
            // YÜKLENEN HER HAREKET İÇİN ÜYENİN KİLOSUNA GÖRE KALORİ HESAPLANIR
            for (Antrenman a : depo.listele()) {
                double k = a.kaloriHesapla(uye);
                toplam += k;
                listModel.addElement(String.format("%s  -  %.2f kcal", a.toString(), k));
            }
            lblToplam.setText(String.format("Tahmini Toplam Kalori: %.2f kcal", toplam));
        });

        // [PROGRAMA ATA] BUTONU: SEÇİLEN PAKETİ KALICI OLARAK ÜYEYE TANIMLAR
        btnAta.addActionListener(e -> {
            String secim = ((String)cmbProgram.getSelectedItem()).trim();
            // KULLANICIDAN SON BİR ONAY ALIR
            int cevap = JOptionPane.showConfirmDialog(this, "\"" + secim + "\" programını üyeye atamak istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (cevap == JOptionPane.YES_OPTION) {
                atamaYonetici.programAta(uye, secim); // ATAMA İŞLEMİNİ GERÇEKLEŞTİRİR
                JOptionPane.showMessageDialog(this, "Program başarıyla atandı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // [KAPAT] BUTONU: DİALOG PENCERESİNİ BELLEKTEN TEMİZLER VE KAPATIR
        btnKapat.addActionListener(e -> dispose());
    }
}