package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// ÜYE PANELİ SINIFI
public class UyePaneli extends JFrame {

    private static final long serialVersionUID = 1L;
    // ÜYE PANELİ YAPICI METOT
	public UyePaneli(Uye uye) {
        setTitle("Üye Paneli - " + uye.getEmail());
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Profilim", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(9, 2, 10, 15));
        centerPanel.setBorder(new EmptyBorder(10, 30, 20, 30));
        
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font valFont = new Font("Arial", Font.PLAIN, 14);

        String[] labels = {"İsim:","Soyisim:","Atanan Koç:","Email:", "Boy (cm):", "Kilo (kg):", "Yaş:", "Yağ Oranı (%):", "Vücut Kitle Endeksi (BMI):"};
        String[] values = {
        	uye.getIsim(),
        	uye.getSoyisim(),
        	uye.antrenorum(),
            uye.getEmail(),                                          
            String.valueOf(uye.getBoy()),
            String.valueOf(uye.getKilo()),
            String.valueOf(uye.getYas()),
            String.valueOf(uye.getYağOrani()),
            String.format("%.2f", uye.vucutKitleEndeksiHesapla())
        };

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i], SwingConstants.RIGHT);
            lbl.setFont(labelFont);
            centerPanel.add(lbl);

            JLabel val = new JLabel(values[i]);
            val.setFont(valFont);
            if (i == 4) {
                val.setForeground(new Color(0, 102, 204));
                val.setFont(new Font("Arial", Font.BOLD, 16));
            }
            centerPanel.add(val);
        }

        add(centerPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        
        JButton btnGuncelle = new JButton("Profilimi Güncelle");
        btnGuncelle.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuncelle.addActionListener(e -> {
            KullaniciDialog dialog = new KullaniciDialog(UyePaneli.this, uye);
            dialog.setIsUyeUpdateOnly(true);
            dialog.setVisible(true);
            if (dialog.getKullanici() != null) {
                dispose();
                new UyePaneli((Uye) dialog.getKullanici()).setVisible(true);
            }
        });
        btnPanel.add(btnGuncelle);
        
        JButton btnCikis = new JButton("Çıkış Yap");
        btnCikis.setFont(new Font("Arial", Font.BOLD, 14));
        btnCikis.addActionListener(e -> cikisYap());
        btnPanel.add(btnCikis);
        add(btnPanel, BorderLayout.SOUTH);
    }

    // ÇIKIŞ YAP
    private void cikisYap() {
        this.dispose();
        new GirişEkranı().setVisible(true);
    }
}
