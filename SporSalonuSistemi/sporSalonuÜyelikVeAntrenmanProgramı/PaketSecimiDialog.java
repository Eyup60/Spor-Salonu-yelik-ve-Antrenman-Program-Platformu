package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

        final JButton btnHesapla = new JButton("Onayla");
        btnHesapla.setBackground(new Color(50, 150, 50));
        btnHesapla.setForeground(Color.BLACK);
        contentPanel.add(btnHesapla);

        // Ödeme butonu: paket seçimi içinde ödeme yapma imkanı
        final JButton btnOdeme = new JButton("Ödeme Yap");
        btnOdeme.setBackground(new Color(0, 153, 204));
        btnOdeme.setForeground(Color.BLACK);
        contentPanel.add(btnOdeme);

        // Dinamik güncelleme: Paket veya özel ders sayısı değiştiğinde tutarı anında hesapla
        cbPaketler.addItemListener(e -> updateTutar());

        txtOzelDers.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateTutar(); }

            @Override
            public void removeUpdate(DocumentEvent e) { updateTutar(); }

            @Override
            public void changedUpdate(DocumentEvent e) { updateTutar(); }
        });

        // İlk hesaplama ve buton durumunu ayarla
        updateTutar();

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

        // Ödeme butonunun olayı
        btnOdeme.addActionListener(e -> {
            try {
                int dersSayisi = Integer.parseInt(txtOzelDers.getText());
                String secilen = (String) cbPaketler.getSelectedItem();

                UyelikPaketi paket;
                if (secilen.equals("VIP Paket")) paket = new VIPPaket();
                else if (secilen.equals("Premium Paket")) paket = new PremiumPaket();
                else paket = new StandartPaket();

                double toplamTutar = UcretHesaplayici.hesapla(paket, dersSayisi, 0.1);

                int confirm = JOptionPane.showConfirmDialog(this, "Ödenecek tutar: " + toplamTutar + " TL\nÖdemeye devam edilsin mi?", "Ödeme Onayı", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;

                String[] secenekler = {"Kredi Kartı", "Nakit"};
                int secim = JOptionPane.showOptionDialog(this, "Ödeme yöntemi seçiniz:", "Yöntem",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, secenekler, secenekler[0]);

                OdemeYontemi odeme;
                if (secim == 0) {
                    String kart = JOptionPane.showInputDialog(this, "Kart numarasını giriniz:", "Kart Bilgisi", JOptionPane.QUESTION_MESSAGE);
                    if (kart == null || kart.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Kart numarası girilmedi! İşlem iptal edildi.", "İptal", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    odeme = new KrediKartiOdeme(toplamTutar, kart);
                } else {
                    odeme = new NakitOdeme(toplamTutar);
                }

                String sonuc = odeme.odemeAl();
                uye.setPaket(paket);
                JOptionPane.showMessageDialog(this, sonuc, "Ödeme Başarılı", JOptionPane.INFORMATION_MESSAGE);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lütfen özel ders sayısını rakamla giriniz!", "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
            } catch (GecersizOdemeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Ödeme Reddedildi", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Kural Hatası", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    // Yardımcı metot: GUI'deki alanlar değiştiğinde tutarı günceller.
    private void updateTutar() {
        String secilen = (String) cbPaketler.getSelectedItem();
        UyelikPaketi paket;
        if ("VIP Paket".equals(secilen)) paket = new VIPPaket();
        else if ("Premium Paket".equals(secilen)) paket = new PremiumPaket();
        else paket = new StandartPaket();

        try {
            int dersSayisi = Integer.parseInt(txtOzelDers.getText());
            double toplam = UcretHesaplayici.hesapla(paket, dersSayisi, 0.1);
            lblSonuc.setText("Tutar: " + toplam + " TL");
        } catch (NumberFormatException ex) {
            lblSonuc.setText("Tutar: - (Geçersiz sayı)");
        } catch (IllegalArgumentException ex) {
            lblSonuc.setText("Tutar: - (" + ex.getMessage() + ")");
        }
    }
}