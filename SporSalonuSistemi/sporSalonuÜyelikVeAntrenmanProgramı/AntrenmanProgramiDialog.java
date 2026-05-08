package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;

// ANTRENMAN PROGRAMİ DİALOG SINIFI
public class AntrenmanProgramiDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public AntrenmanProgramiDialog(JFrame parent, Uye uye) {
        super(parent, "Antrenman Programı", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        getContentPane().setLayout(new BorderLayout(10,10));

        // Üst kısım: program tipi seçimi
        JPanel pnlUst = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlUst.add(new JLabel("Hazır Program Tipi:"));

        // Program tipleri (AntrenmanDepoYöneticisi içindeki switch'e uygun isimler)
        String[] programTipleri = new String[] {
            "kilo verme","kas kütlesi","boksör","powerlifter","maratoncu",
            "yüzücü","basketbolcu","cimnastikçi","futbolcu","tenisçi",
            "güreşçi","bisikletçi","voleybolcu","crossfitçi","bilek güreşçisi"
        };

        JComboBox<String> cmbProgram = new JComboBox<>(programTipleri);
        pnlUst.add(cmbProgram);

        JButton btnYukle = new JButton("Programı Görüntüle");
        pnlUst.add(btnYukle);

        getContentPane().add(pnlUst, BorderLayout.NORTH);

        // Orta kısım: antrenman listesi ve detayları
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> lstHareketler = new JList<>(listModel);
        lstHareketler.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp = new JScrollPane(lstHareketler);
        getContentPane().add(sp, BorderLayout.CENTER);

        // Sağ alt: toplam kalori ve işlem butonları
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

        // Yöneticiler
        AntrenmanDepoYöneticisi depo = new AntrenmanDepoYöneticisi();
        ProgramAtamaYöneticisi atamaYonetici = new ProgramAtamaYöneticisi();

        // Yükle butonu davranışı: seçilen programı depoya yükle, listeyi doldur
        btnYukle.addActionListener(e -> {
            String secim = ((String)cmbProgram.getSelectedItem()).trim();
            depo.hazirProgramYukle(secim);
            listModel.clear();
            double toplam = 0.0;
            for (Antrenman a : depo.listele()) {
                double k = a.kaloriHesapla(uye);
                toplam += k;
                listModel.addElement(String.format("%s  -  %.2f kcal", a.toString(), k));
            }
            lblToplam.setText(String.format("Tahmini Toplam Kalori: %.2f kcal", toplam));
        });

        // Programa ata: atama yöneticisini kullanarak seçimi kaydet
        btnAta.addActionListener(e -> {
            String secim = ((String)cmbProgram.getSelectedItem()).trim();
            int cevap = JOptionPane.showConfirmDialog(this, "\"" + secim + "\" programını üyeye atamak istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (cevap == JOptionPane.YES_OPTION) {
                atamaYonetici.programAta(uye, secim);
                JOptionPane.showMessageDialog(this, "Program başarıyla atandı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnKapat.addActionListener(e -> dispose());
    }
}
