package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Dinamik ücret hesaplama ve paket atama arayüzü
public class PaketSecimiDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtOzelDers;
    private JComboBox<String> cbPaketler;
    private JLabel lblSonuc;

    public PaketSecimiDialog(JFrame parent, Uye uye) {
        super(parent, "Dinamik Ücret Hesaplama", true);
        setSize(350, 250);
        setLocationRelativeTo(parent);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(4, 2, 10, 15));

        contentPanel.add(new JLabel("Paket Tipi:"));
        cbPaketler = new JComboBox<>(new String[]{"Standart Paket", "Premium Paket", "VIP Paket"});
        contentPanel.add(cbPaketler);

        contentPanel.add(new JLabel("Ek Özel Ders (Adet):"));
        txtOzelDers = new JTextField("0");
        contentPanel.add(txtOzelDers);

        lblSonuc = new JLabel("Tutar: 0.0 TL");
        lblSonuc.setFont(new Font("Tahoma", Font.BOLD, 13));
        contentPanel.add(lblSonuc);

        JButton btnHesapla = new JButton("Hesapla ve Onayla");
        btnHesapla.setBackground(new Color(50, 150, 50));
        btnHesapla.setForeground(Color.WHITE);
        contentPanel.add(btnHesapla);

        // Ücret hesaplama ve paketi üyeye atama işlemi
        btnHesapla.addActionListener(e -> {
            try {
                int dersSayisi = Integer.parseInt(txtOzelDers.getText());
                String secilen = (String) cbPaketler.getSelectedItem();
                
                // OOP Prensibi: Polimorfizm (Çok Biçimlilik)
                // Üst sınıf referansına (UyelikPaketi), kullanıcının seçimine göre alt sınıf nesnesi atanır
                UyelikPaketi paket;
                if (secilen.equals("VIP Paket")) paket = new VIPPaket();
                else if (secilen.equals("Premium Paket")) paket = new PremiumPaket();
                else paket = new StandartPaket();

                // Logic katmanından hesaplama metodunun çağrılması
                double toplamTutar = UcretHesaplayici.hesapla(paket, dersSayisi, 0.1);
                
                lblSonuc.setText("Tutar: " + toplamTutar + " TL");
                uye.setPaket(paket); // Nesneler arası ilişki: Hesaplanan paket üyeye atanır
                
                JOptionPane.showMessageDialog(this, "İşlem Başarılı!\nPaket: " + secilen + "\nÖdenecek Tutar: " + toplamTutar + " TL");
                dispose(); 

            } catch (NumberFormatException ex) {
                // Exception Handling: Kullanıcı harf girerse programın çökmesini engeller
                JOptionPane.showMessageDialog(this, "Lütfen özel ders sayısını rakamla giriniz!", "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                // Exception Handling: İş mantığı kural hatalarını yakalar (Örn: negatif ders sayısı)
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Kural Hatası", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}