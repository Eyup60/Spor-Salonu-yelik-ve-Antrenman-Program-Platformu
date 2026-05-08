package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// PAKET SEÇİMİ DİALOG SINIFI
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

        // Eğer üyenin zaten bir paketi varsa, combo'yu onunla başlat
        if (uye.getPaket() != null) {
            try {
                cbPaketler.setSelectedItem(uye.getPaket().getAd());
            } catch (Exception ignored) {}
        }

        contentPanel.add(new JLabel("Ek Özel Ders (Adet):"));
        txtOzelDers = new JTextField("0");
        contentPanel.add(txtOzelDers);

        lblSonuc = new JLabel("Tutar: 0.0 TL");
        lblSonuc.setFont(new Font("Tahoma", Font.BOLD, 13));
        contentPanel.add(lblSonuc);

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

        // Ödeme butonunun olayı
        btnOdeme.addActionListener(e -> {
            try {
                int dersSayisi = Integer.parseInt(txtOzelDers.getText());
                String secilen = (String) cbPaketler.getSelectedItem();

                // Eğer kullanıcı zaten aynı pakete sahipse, yeniden seçmesini engelle
                if (uye.getPaket() != null) {
                    String mevcut = uye.getPaket().getAd();
                    if (secilen.equals(mevcut)) {
                        JOptionPane.showMessageDialog(this, "Zaten bu pakete sahipsiniz. Lütfen farklı bir paket seçin.", "Uyarı", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }

                UyelikPaketi paket;
                if (secilen.equals("VIP Paket")) paket = new VIPPaket();
                else if (secilen.equals("Premium Paket")) paket = new PremiumPaket();
                else paket = new StandartPaket();

                double toplamTutar = UcretHesaplayici.hesapla(paket, dersSayisi, 0.1);

                int confirm = JOptionPane.showConfirmDialog(this, "Ödenecek tutar: " + toplamTutar + " TL\nÖdemeye devam edilsin mi?", "Ödeme Onayı", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;

                // Ödeme yöntemi yalnızca kredi kartı olarak sınırlı
                OdemeYontemi odeme = null;
                // Kullanıcıdan ödeme tutarını al (kullanıcının ödeyeceği miktar)
                String girilenStr = JOptionPane.showInputDialog(this, "Lütfen ödeyeceğiniz tutarı giriniz (TL):", String.valueOf(toplamTutar));
                if (girilenStr == null) {
                    // İptal edildi
                    return;
                }
                double girilenTutar;
                try {
                    girilenTutar = Double.parseDouble(girilenStr);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Lütfen geçerli bir sayı giriniz!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Girilen tutarın ödenmesi gereken tutardan az olmaması gerekiyor
                if (girilenTutar < toplamTutar) {
                    JOptionPane.showMessageDialog(this, "Girilen tutar ödenecek tutardan az. Lütfen yeterli bir miktar giriniz.", "Eksik Ödeme", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Kredi kartı ile ödeme adımı
                String kartInput = JOptionPane.showInputDialog(this, "Kart numarasını giriniz (16 hane, sadece rakam):", "Kart Bilgisi", JOptionPane.QUESTION_MESSAGE);
                if (kartInput == null) {
                    // İptal edildi
                    return;
                }
                String kart = kartInput.replaceAll("\\s+", "");
                // Kart numarasını sadece rakamlar olarak ve tam 16 hane kontrol et
                if (!kart.matches("\\d{16}")) {
                    JOptionPane.showMessageDialog(this, "Kart numarası 16 haneli olmalı ve sadece rakamlardan oluşmalıdır!", "Geçersiz Kart", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                odeme = new KrediKartiOdeme(toplamTutar, kart);

                String sonuc = odeme.odemeAl();
                uye.setPaket(paket);
                // Paket seçiminden sonra kalıcı olarak kullanıcı verilerini kaydet
                DosyaYoneticisi.verileriKaydet();

                // Eğer kullanıcı fazla ödediyse, farkı göster
                if (girilenTutar > toplamTutar) {
                    double fark = girilenTutar - toplamTutar;
                    JOptionPane.showMessageDialog(this, sonuc + "\nLütfen değişim için " + fark + " TL alın.", "Ödeme Başarılı", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, sonuc, "Ödeme Başarılı", JOptionPane.INFORMATION_MESSAGE);
                }
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
            lblSonuc.setText(String.format("Tutar: %.2f TL", toplam));
        } catch (NumberFormatException ex) {
            lblSonuc.setText("Tutar: - (Geçersiz sayı)");
        } catch (IllegalArgumentException ex) {
            lblSonuc.setText("Tutar: - (" + ex.getMessage() + ")");
        }
    }
}