package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;

// ÜYENİN KİŞİSEL BİLGİLERİNİ VE GÜNCEL ANTRENÖRÜNÜ GÖSTEREN PENCERE
public class ProfilBilgileriDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public ProfilBilgileriDialog(JFrame parent, Uye uye) {
 
        super(parent, "Profil Bilgileri", true);
        setSize(350, 320);
        setLocationRelativeTo(parent);

        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        pnl.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Üyenin temel bilgilerini etiketlere aktarıyoruz
        pnl.add(new JLabel("İsim: " + uye.getIsim()));
        pnl.add(new JLabel("Soyisim: " + uye.getSoyisim()));
        pnl.add(new JLabel("Email: " + uye.getEmail()));
        pnl.add(new JLabel(String.format("Boy: %.1f cm", uye.getBoy())));
        pnl.add(new JLabel(String.format("Kilo: %.1f kg", uye.getKilo())));
        pnl.add(new JLabel("Yaş: " + uye.getYas()));
        pnl.add(new JLabel(String.format("Yağ Oranı: %.1f %%", uye.getYağOrani())));
        pnl.add(new JLabel("Kayıt Tarihi: " + uye.getKayitTarihi()));
        pnl.add(new JLabel("Paket: " + (uye.getPaket() != null ? uye.getPaket().getPaketAdi() : "Seçilmedi")));

        // ANTRENÖR BİLGİSİNİ SİSTEMDEN BULMA MANTIĞI
        String antrenorAdi = "Henüz Atanmadı";
        for (Kullanici k : Admin.getKullanicilar()) {
            if (k instanceof Antrenor antrenor) {
                // Eğer bu antrenörün listesi mevcut üyeyi içeriyorsa ismini alıyoruz
                if (antrenor.listele().contains(uye)) {
                    antrenorAdi = antrenor.getIsim() + " " + antrenor.getSoyisim();
                    break;
                }
            }
        }
        
        // Atanan koç bilgisini görsel olarak daha belirgin ekliyoruz
        JLabel lblAntrenor = new JLabel("Atanan Koç: " + antrenorAdi);
        lblAntrenor.setFont(new Font("Arial", Font.BOLD, 12));
        lblAntrenor.setForeground(new Color(0, 102, 204)); 
        pnl.add(lblAntrenor);

        // Kapat butonu ve sağa hizalı alt panel
        JButton btnKapat = new JButton("Kapat");
        btnKapat.addActionListener(e -> dispose());
        JPanel pnlAlt = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlAlt.add(btnKapat);

        getContentPane().add(pnl, BorderLayout.CENTER);
        getContentPane().add(pnlAlt, BorderLayout.SOUTH);
    }
}