package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// ANTRENÖR ATAMA DİALOG SINIFI
// ANTRENÖR VE ÜYE EŞLEŞTİRME İŞLEMLERİ İÇİN KULLANILIR
// SWING JDIALOG MİRAS ALIR
public class AntrenorAtamaDialog extends JDialog {

    // SERİALİZASYON VERSİYON NUMARASI
    private static final long serialVersionUID = 1L;

    // ANTRENÖR ATAMA DİALOG YAPICI METOT
    // MODAL PENCERE OLARAK ANA EKRANI ENGELLER
    public AntrenorAtamaDialog(JFrame parent) {
        // MODAL TRUE AYARI İLE BU PENCERE KAPANMADANA ANA EKRANA DÖNÜLMESİ ENGELLENİR
        super(parent, "Antrenör ve Üye Eşleştirme Sistemi", true);
        setSize(380, 220);
        setLocationRelativeTo(parent);
        
        // İÇERİK PANELİ OLUŞTUR
        // KENAR BOŞLUKLARI AYARLA
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        // IZGARA DÜZENİ
        // ÜÇ SATIR İKİ SÜTUNLU YAPI
        contentPanel.setLayout(new GridLayout(3, 2, 10, 20));

        // GEÇİCİ VERİ LİSTELERİ
        // TODO JSON ENTEGRASYONU SONRASI VERİ TABANINDAN GELECEK
        String[] antrenorler = {"101 - Ahmet Hoca", "102 - Mehmet Hoca"};
        String[] uyeler = {"201 - Ali", "202 - Alperen", "203 - Eyüp"};

        // ANTRENÖR SEÇİM BİLEŞENİ
        contentPanel.add(new JLabel("Antrenör Seçiniz:"));
        JComboBox<String> cbAntrenor = new JComboBox<>(antrenorler);
        contentPanel.add(cbAntrenor);

        // ÜYE SEÇİM BİLEŞENİ
        contentPanel.add(new JLabel("Üye Seçiniz:"));
        JComboBox<String> cbUye = new JComboBox<>(uyeler);
        contentPanel.add(cbUye);

        // DÜZENİ KORUMAK İÇİN BOŞ ETİKET
        contentPanel.add(new JLabel(""));
        
        // EŞLEŞTİRME BUTONU
        // MAVİ ARKA PLAN BEYAZ YAZI
        JButton btnAta = new JButton("Eşleştirmeyi Onayla");
        btnAta.setBackground(new Color(0, 102, 204));
        btnAta.setForeground(Color.WHITE);
        contentPanel.add(btnAta);

        // ATAMA BUTONU AKSİYONU
        // HATA YÖNETİMİ İLE İŞLEM YAP
        btnAta.addActionListener(e -> {
            try {
                // SEÇİLEN İNDEKSLERİ AL
                int antrenorIndex = cbAntrenor.getSelectedIndex();
                int uyeIndex = cbUye.getSelectedIndex();
                
                // SERVİS SINIFI İLE MANTIKSAL EŞLEŞTİRME
                AntrenorUyeService service = new AntrenorUyeService();
                service.ata(String.valueOf(antrenorIndex), String.valueOf(uyeIndex));
                
                // BAŞARILI MESAJI VE PENCEREYİ KAPAT
                JOptionPane.showMessageDialog(this, "Eşleştirme Başarıyla Kaydedildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                
            } catch (Exception ex) {
                // HATA DURUMUNDA KULLANICI BİLDİRİMİ
                JOptionPane.showMessageDialog(this, "Eşleştirme sırasında bir hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}