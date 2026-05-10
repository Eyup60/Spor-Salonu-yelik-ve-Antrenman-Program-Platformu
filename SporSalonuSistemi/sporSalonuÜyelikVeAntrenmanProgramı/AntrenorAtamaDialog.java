package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// ANTRENÖR ATAMA DİALOG SINIFI
// SİSTEMDEKİ MEVCUT ANTRENÖRLER İLE ÜYELERİ BİRBİRİNE BAĞLAMAK İÇİN KULLANILIR
public class AntrenorAtamaDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    // ANTRENÖR ATAMA DİALOG YAPICI METOT
    public AntrenorAtamaDialog(JFrame parent) {
        // MODAL TRUE YAPILARAK BU PENCERE KAPANMADAN ANA EKRANA ERİŞİM ENGELLENİR
        super(parent, "Antrenör ve Üye Eşleştirme Sistemi", true);
        setSize(420, 240);
        setLocationRelativeTo(parent);

        // ANA PANEL OLUŞTURMA VE KENAR BOŞLUKLARINI AYARLAMA
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        // IZGARA DÜZENİ (GRIDLAYOUT): 3 SATIR, 2 SÜTUN
        contentPanel.setLayout(new GridLayout(3, 2, 10, 20));

        // SİSTEMDEKİ TÜM KULLANICILARI STATİK LİSTEDEN ÇEK
        List<Kullanici> tumKullanicilar = Admin.getKullanicilar();

        // POLİMORFİZM KULLANILARAK ANTRENÖRLERİ FİLTRELE VE LİSTELE
        List<Antrenor> antrenorListesi = tumKullanicilar.stream()
                .filter(k -> k instanceof Antrenor)
                .map(k -> (Antrenor) k)
                .collect(Collectors.toList());

        // POLİMORFİZM KULLANILARAK ÜYELERİ FİLTRELE VE LİSTELE
        List<Uye> uyeListesi = tumKullanicilar.stream()
                .filter(k -> k instanceof Uye)
                .map(k -> (Uye) k)
                .collect(Collectors.toList());

        // ANTRENÖR SEÇİM ALANI (COMBOBOX)
        contentPanel.add(new JLabel("Antrenör Seçiniz:"));
        String[] antrenorGorunum = antrenorListesi.stream()
                .map(a -> a.getId() + " - " + a.getIsim() + " " + a.getSoyisim())
                .toArray(String[]::new);
        JComboBox<String> cbAntrenor = new JComboBox<>(antrenorGorunum);
        contentPanel.add(cbAntrenor);

        // ÜYE SEÇİM ALANI (COMBOBOX)
        contentPanel.add(new JLabel("Üye Seçiniz:"));
        String[] uyeGorunum = uyeListesi.stream()
                .map(u -> u.getId() + " - " + u.getIsim() + " " + u.getSoyisim())
                .toArray(String[]::new);
        JComboBox<String> cbUye = new JComboBox<>(uyeGorunum);
        contentPanel.add(cbUye);

        // TASARIMI DENGELEMEK İÇİN BOŞ ETİKET
        contentPanel.add(new JLabel(""));

        // EŞLEŞTİRME ONAY BUTONU
        JButton btnAta = new JButton("Eşleştirmeyi Onayla");
        btnAta.setBackground(new Color(0, 102, 204));
        btnAta.setForeground(Color.WHITE);
        contentPanel.add(btnAta);

        // BUTON TIKLAMA OLAYI (ACTION LISTENER)
        btnAta.addActionListener(e -> {
            try {
                // SİSTEMDE VERİ OLUP OLMADIĞINI KONTROL ET
                if (antrenorListesi.isEmpty() || uyeListesi.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Sistemde antrenör veya üye bulunamadı!",
                            "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // SEÇİLEN İNDEKSLERE GÖRE LİSTEDEN GERÇEK NESNELERİ AL
                Antrenor secilenAntrenor = antrenorListesi.get(cbAntrenor.getSelectedIndex());
                Uye secilenUye = uyeListesi.get(cbUye.getSelectedIndex());

                // ANTRENÖRÜN LİSTESİNE ÜYEYİ EKLE (BU METOT DOSYAYA KAYDI DA TETİKLER)
                secilenAntrenor.ekle(secilenUye);

                // KULLANICIYA BAŞARI BİLGİSİ VER VE PENCEREYİ KAPAT
                JOptionPane.showMessageDialog(this,
                        secilenUye.getIsim() + " " + secilenUye.getSoyisim()
                        + " başarıyla "
                        + secilenAntrenor.getIsim() + " " + secilenAntrenor.getSoyisim()
                        + " antrenörüne atandı!",
                        "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                dispose();

            } catch (Exception ex) {
                // OLASI HATALARI YAKALA VE MESAJ OLARAK GÖSTER
                JOptionPane.showMessageDialog(this,
                        "Eşleştirme sırasında bir hata oluştu: " + ex.getMessage(),
                        "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}