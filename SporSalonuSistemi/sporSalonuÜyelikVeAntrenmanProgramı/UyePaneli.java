package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Sisteme giriş yapan üyenin abonelik durumunu ve işlemlerini gördüğü ana panel.
public class UyePaneli extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Uye aktifUye; // Sisteme giriş yapmış olan kullanıcıyı (üyeyi) tutar

    // OOP Prensibi: Constructor Overloading (Yapıcı Metot Aşırı Yükleme) ve Chaining (Zincirleme)
    // Eğer bu ekran parametresiz çağrılırsa (örneğin WindowBuilder testlerinde), 
    // çökmemesi için varsayılan (dummy) bir Uye nesnesi oluşturarak asıl yapıcıyı (this) çağırır.
    public UyePaneli() {
        this(new Uye("test", "Test Üye", "test@gym.com", "123")); 
    }

    public UyePaneli(Uye uye) {
        this.aktifUye = uye;
        
        setTitle("Spor Salonu - Üye Paneli");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Çarpıya basınca programı tamamen kapatır
        setSize(450, 400);
        setLocationRelativeTo(null); // Pencerenin ekranın tam ortasında açılmasını sağlar
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 15));

        // --- Abonelik Durum Paneli ---
        JPanel panelDurum = new JPanel();
        panelDurum.setBorder(BorderFactory.createTitledBorder("Abonelik Bilgileriniz"));
        panelDurum.setLayout(new GridLayout(2, 1, 5, 5));
        
        // Dinamik Arayüz: Üyenin paketi varsa adını, yoksa "Seçilmedi" uyarısını yazar.
        String paketAdi = (aktifUye.getPaket() != null) ? aktifUye.getPaket().getAd() : "Paket Seçilmedi";
        JLabel lblPaket = new JLabel("Mevcut Paketiniz: " + paketAdi);
        lblPaket.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        JLabel lblAktiflik = new JLabel("Durum: " + (aktifUye.getPaket() != null ? "AKTİF" : "PASİF (Lütfen paket seçin)"));
        // Görsel Geri Bildirim: Paket varsa yazı yeşil, yoksa kırmızı olur
        lblAktiflik.setForeground(aktifUye.getPaket() != null ? new Color(0, 128, 0) : Color.RED);
        
        panelDurum.add(lblPaket);
        panelDurum.add(lblAktiflik);
        contentPane.add(panelDurum, BorderLayout.NORTH);

        // --- İşlem Butonları Paneli ---
        JPanel panelButonlar = new JPanel();
        contentPane.add(panelButonlar, BorderLayout.CENTER);
        panelButonlar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        JButton btnPaketSec = new JButton("Paket Seç / Güncelle");
        btnPaketSec.setPreferredSize(new Dimension(180, 40));
        
        JButton btnCikis = new JButton("Çıkış Yap");
        btnCikis.setPreferredSize(new Dimension(180, 40));
        // Yeni butonlar: Antrenman Programı ve Profil Bilgileri
        JButton btnAntrenman = new JButton("Antrenman Programı");
        btnAntrenman.setPreferredSize(new Dimension(180, 40));
        JButton btnProfil = new JButton("Profil Bilgileri");
        btnProfil.setPreferredSize(new Dimension(180, 40));

        panelButonlar.add(btnPaketSec);
        panelButonlar.add(btnAntrenman);
        panelButonlar.add(btnProfil);
        panelButonlar.add(btnCikis);

        // Antrenman programı dialogunu açar
        btnAntrenman.addActionListener(e -> {
            AntrenmanProgramiDialog dlg = new AntrenmanProgramiDialog(this, aktifUye);
            dlg.setVisible(true);
        });

        // Profil bilgileri dialogunu açar
        btnProfil.addActionListener(e -> {
            ProfilBilgileriDialog dlg = new ProfilBilgileriDialog(this, aktifUye);
            dlg.setVisible(true);
        });

        // Event Handling (Olay Yönetimi): Butona tıklandığında dialog penceresini açar
        btnPaketSec.addActionListener(e -> {
            // İlgili dialog ekranına "this" (bu pencere) ve o anki üye bilgisi gönderilir
            PaketSecimiDialog dialog = new PaketSecimiDialog(this, aktifUye);
            dialog.setVisible(true);
            
            // İşlem bittikten (Dialog kapandıktan) sonra ana ekranı yenilemek için 
            // mevcut pencereyi kapatır (dispose) ve güncel verilerle tekrar açar.
            this.dispose();
            new UyePaneli(aktifUye).setVisible(true);
        });
        
        btnCikis.addActionListener(e -> System.exit(0)); // Sistemi güvenlice sonlandırır
    }
}
