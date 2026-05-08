package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Yöneticilerin antrenör ve üyeleri eşleştirdiği arayüz sınıfı
public class AntrenorAtamaDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public AntrenorAtamaDialog(JFrame parent) {
        // Modal true ayarı ile bu pencere kapanmadan ana ekrana dönülmesi engellenir
        super(parent, "Antrenör ve Üye Eşleştirme Sistemi", true);
        setSize(380, 220);
        setLocationRelativeTo(parent);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        // 3 satır, 2 sütunlu düzenli ızgara yapısı
        contentPanel.setLayout(new GridLayout(3, 2, 10, 20));

        // TODO: JSON entegrasyonu tamamlandığında listeler veri tabanından çekilecek
        String[] antrenorler = {"101 - Ahmet Hoca", "102 - Mehmet Hoca"};
        String[] uyeler = {"201 - Ali", "202 - Alperen", "203 - Eyüp"};

        contentPanel.add(new JLabel("Antrenör Seçiniz:"));
        JComboBox<String> cbAntrenor = new JComboBox<>(antrenorler);
        contentPanel.add(cbAntrenor);

        contentPanel.add(new JLabel("Üye Seçiniz:"));
        JComboBox<String> cbUye = new JComboBox<>(uyeler);
        contentPanel.add(cbUye);

        contentPanel.add(new JLabel("")); // Düzeni korumak için boş etiket
        
        JButton btnAta = new JButton("Eşleştirmeyi Onayla");
        btnAta.setBackground(new Color(0, 102, 204));
        btnAta.setForeground(Color.WHITE);
        contentPanel.add(btnAta);

        // Atama butonu aksiyonu ve hata yönetimi
        btnAta.addActionListener(e -> {
            try {
                int antrenorIndex = cbAntrenor.getSelectedIndex();
                int uyeIndex = cbUye.getSelectedIndex();
                
                // Servis sınıfı çağrılarak mantıksal eşleştirme yapılır
                AntrenorUyeService service = new AntrenorUyeService();
                service.ata(antrenorIndex, uyeIndex);
                
                JOptionPane.showMessageDialog(this, "Eşleştirme Başarıyla Kaydedildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // İşlem bitince pencereyi kapat
                
            } catch (Exception ex) {
                // Herhangi bir null veya index hatasına karşı koruma
                JOptionPane.showMessageDialog(this, "Eşleştirme sırasında bir hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}