package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UyePaneli extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Uye aktifUye;

    public UyePaneli() {
        this(new Uye("test", "Test Üye", "test@gym.com", "123")); 
    }

    public UyePaneli(Uye uye) {
        this.aktifUye = uye;
        
        setTitle("Spor Salonu - Üye Paneli");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 15));

        JPanel panelDurum = new JPanel();
        panelDurum.setBorder(BorderFactory.createTitledBorder("Abonelik Bilgileriniz"));
        panelDurum.setLayout(new GridLayout(2, 1, 5, 5));
        
        String paketAdi = (aktifUye.getPaket() != null) ? aktifUye.getPaket().getAd() : "Paket Seçilmedi";
        JLabel lblPaket = new JLabel("Mevcut Paketiniz: " + paketAdi);
        lblPaket.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        JLabel lblAktiflik = new JLabel("Durum: " + (aktifUye.getPaket() != null ? "AKTİF" : "PASİF (Lütfen paket seçin)"));
        lblAktiflik.setForeground(aktifUye.getPaket() != null ? new Color(0, 128, 0) : Color.RED);
        
        panelDurum.add(lblPaket);
        panelDurum.add(lblAktiflik);
        contentPane.add(panelDurum, BorderLayout.NORTH);

        JPanel panelButonlar = new JPanel();
        contentPane.add(panelButonlar, BorderLayout.CENTER);
        panelButonlar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        JButton btnPaketSec = new JButton("Paket Seç / Güncelle");
        btnPaketSec.setPreferredSize(new Dimension(180, 40));
        
        JButton btnCikis = new JButton("Çıkış Yap");
        btnCikis.setPreferredSize(new Dimension(180, 40));

        JButton btnAntrenman = new JButton("Antrenman Programı");
        btnAntrenman.setPreferredSize(new Dimension(180, 40));

        JButton btnProgramBilgi = new JButton("Antrenman Programı Bilgileri");
        btnProgramBilgi.setPreferredSize(new Dimension(180, 40));

        JButton btnProfil = new JButton("Profil Bilgileri");
        btnProfil.setPreferredSize(new Dimension(180, 40));

        panelButonlar.add(btnPaketSec);
        panelButonlar.add(btnAntrenman);
        panelButonlar.add(btnProgramBilgi);
        panelButonlar.add(btnProfil);
        panelButonlar.add(btnCikis);

        // Eğer üyenin paketi seçilmemişse antrenman ile ilgili işlemleri pasif yap
        boolean paketSecili = aktifUye.getPaket() != null;
        btnAntrenman.setEnabled(paketSecili);
        btnProgramBilgi.setEnabled(paketSecili);

        btnAntrenman.addActionListener(e -> {
            if (!btnAntrenman.isEnabled()) {
                JOptionPane.showMessageDialog(this, "Lütfen önce bir paket seçiniz.", "Paket Seçilmedi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            AntrenmanProgramiDialog dlg = new AntrenmanProgramiDialog(this, aktifUye);
            dlg.setVisible(true);
        });

        btnProgramBilgi.addActionListener(e -> {
            if (!btnProgramBilgi.isEnabled()) {
                JOptionPane.showMessageDialog(this, "Paket seçilmeden program bilgileri görüntülenemez.", "Paket Seçilmedi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            AntrenmanProgramiBilgileriDialog info = new AntrenmanProgramiBilgileriDialog(this, aktifUye);
            info.setVisible(true);
        });

        btnProfil.addActionListener(e -> {
            ProfilBilgileriDialog dlg = new ProfilBilgileriDialog(this, aktifUye);
            dlg.setVisible(true);
        });

        btnPaketSec.addActionListener(e -> {
            PaketSecimiDialog dialog = new PaketSecimiDialog(this, aktifUye);
            dialog.setVisible(true);
            this.dispose();
            new UyePaneli(aktifUye).setVisible(true);
        });
        
        btnCikis.addActionListener(e -> System.exit(0));
    }
}