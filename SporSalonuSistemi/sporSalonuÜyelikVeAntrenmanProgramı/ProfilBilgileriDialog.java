package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;

// PROFİL BİLGİLERİ DİALOG SINIFI
public class ProfilBilgileriDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public ProfilBilgileriDialog(JFrame parent, Uye uye) {
        super(parent, "Profil Bilgileri", true);
        setSize(350, 300);
        setLocationRelativeTo(parent);

        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        pnl.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        pnl.add(new JLabel("İsim: " + uye.getIsim()));
        pnl.add(new JLabel("Soyisim: " + uye.getSoyisim()));
        pnl.add(new JLabel("Email: " + uye.getEmail()));
        pnl.add(new JLabel(String.format("Boy: %.1f cm", uye.getBoy())));
        pnl.add(new JLabel(String.format("Kilo: %.1f kg", uye.getKilo())));
        pnl.add(new JLabel("Yaş: " + uye.getYas()));
        pnl.add(new JLabel(String.format("Yağ Oranı: %.1f %%", uye.getYağOrani())));
        pnl.add(new JLabel("Kayıt Tarihi: " + uye.getKayitTarihi()));
        pnl.add(new JLabel("Paket: " + (uye.getPaket() != null ? uye.getPaket().getAd() : "Seçilmedi")));
        // Atanan koçu göster
        String antrenor = "Henüz Atanmadı";
        try {
            antrenor = uye.antrenorum();
        } catch (Exception ex) {
            // hata olursa varsayılan kalır
        }
        pnl.add(new JLabel("Atanan Koç: " + antrenor));

        JButton btnKapat = new JButton("Kapat");
        btnKapat.addActionListener(e -> dispose());
        JPanel pnlAlt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlAlt.add(btnKapat);

        getContentPane().add(pnl, BorderLayout.CENTER);
        getContentPane().add(pnlAlt, BorderLayout.SOUTH);
    }
}
