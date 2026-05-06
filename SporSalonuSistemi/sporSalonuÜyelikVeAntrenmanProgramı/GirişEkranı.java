package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GirişEkranı extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel adminBtnPanel;
    private Kullanici loggedInUser = null; // OTURUM AÇAN KULLANICIYI TAKİP EDER

    private JButton btnLoginHeader;
    private JButton btnRegister;
    private JLabel lblUyeOlmadiniz;

    public static void main(String[] args) {
        
        // UYGULAMA BAŞLATILIRKEN KAYITLI VERİLERİ DOSYADAN YÜKLER
        DosyaYoneticisi.verileriYukle();

        // EĞER SİSTEMDE HİÇ KULLANICI YOKSA VARSAYILAN BİR ADMİN HESABI OLUŞTURUR
        if (Admin.getKullanicilar().isEmpty()) {
            Admin.doğrudanEkle(new Admin("ilk","Admin","admin@gym.com", "123456"));
        }

        // ARAYÜZÜN GÜVENLİ BİR ŞEKİLDE BAŞLATILMASINI SAĞLAR
        EventQueue.invokeLater(() -> {
            try {
                // SİSTEMİN VARSAYILAN GÖRÜNÜMÜNÜ (LOOK AND FEEL) UYGULAR
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                GirişEkranı frame = new GirişEkranı();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GirişEkranı() {
        // PENCERE GENEL AYARLARI
        setTitle("Spor Salonu Ana Giriş ve Kayıt Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // ÜST PANEL: BAŞLIK VE GİRİŞ BUTONUNU İÇERİR
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(245, 245, 245));
        JLabel mainTitle = new JLabel("Spor Salonu Üyelik ve Antrenman Programı");
        mainTitle.setFont(new Font("SansSerif", Font.BOLD, 26));
        mainTitle.setForeground(new Color(40, 40, 40));
        JLabel subTitle = new JLabel("Hoş geldiniz, fitness dünyanıza adım atın.");
        subTitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subTitle.setForeground(Color.GRAY);
        titlePanel.add(mainTitle);
        titlePanel.add(subTitle);
        
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginPanel.setBackground(new Color(245, 245, 245));
        btnLoginHeader = new JButton("Giriş Yap");
        btnLoginHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLoginHeader.setBackground(new Color(0, 102, 204));
        btnLoginHeader.setFocusPainted(false);
        btnLoginHeader.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLoginHeader.addActionListener(e -> performLogin());
        loginPanel.add(btnLoginHeader);

        topPanel.add(titlePanel, BorderLayout.WEST);
        topPanel.add(loginPanel, BorderLayout.EAST);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        // SOL PANEL: BİLGİ KARTLARI (PAKETLER, ANTRENÖRLER, TESİS)
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBorder(new EmptyBorder(30, 20, 30, 20));
        cardsPanel.setBackground(Color.WHITE);
        
        JButton btnPaketler = createCardButton("Paketlerimiz");
        JButton btnAntrenorler = createCardButton("Antrenörlerimiz");
        JButton btnTesis = createCardButton("Tesisimiz");
        
        btnPaketler.addActionListener(e -> JOptionPane.showMessageDialog(this, "Aylık, 3 Aylık ve Yıllık paketlerimiz mevcuttur."));
        btnAntrenorler.addActionListener(e -> JOptionPane.showMessageDialog(this, "Uzman kadromuzla hedeflerinize ulaşın."));
        btnTesis.addActionListener(e -> JOptionPane.showMessageDialog(this, "Modern aletler ve hijyenik çalışma ortamı."));

        cardsPanel.add(Box.createVerticalStrut(20));
        cardsPanel.add(btnPaketler);
        cardsPanel.add(Box.createVerticalStrut(15));
        cardsPanel.add(btnAntrenorler);
        cardsPanel.add(Box.createVerticalStrut(15));
        cardsPanel.add(btnTesis);
        
        getContentPane().add(cardsPanel, BorderLayout.WEST);

        // MERKEZ PANEL: ATATÜRK'ÜN SÖZÜNÜ HTML OLARAK TUTAR (RESİM DOSYASI GEREKTİRMEZ)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(250, 250, 250));
        
        JLabel quoteLabel = new JLabel("<html><div style='text-align: center; width: 450px;'>"
                + "<span style='font-size: 22px; font-style: italic; color: #2C3E50; font-family: Serif;'>"
                + "\"Ben sporcunun zeki, çevik ve aynı zamanda ahlaklısını severim.\""
                + "</span><br><br>"
                + "<span style='font-size: 18px; font-weight: bold; color: #7F8C8D;'>— Mustafa Kemal ATATÜRK</span>"
                + "</div></html>");
        GridBagConstraints gbc_quoteLabel = new GridBagConstraints();
        gbc_quoteLabel.anchor = GridBagConstraints.EAST;
        centerPanel.add(quoteLabel, gbc_quoteLabel);
        
        getContentPane().add(centerPanel, BorderLayout.CENTER);

        // ALT PANEL: KAYIT VE ADMİN ÖZEL İŞLEMLERİ
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        bottomPanel.setBackground(SystemColor.control);
        
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblUyeOlmadiniz = new JLabel("Üye olmadınız mı?");
        lblUyeOlmadiniz.setFont(new Font("SansSerif", Font.PLAIN, 15));
        
        btnRegister = new JButton("Kayıt Ol");
        btnRegister.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnRegister.setForeground(new Color(204, 0, 0));
        btnRegister.setContentAreaFilled(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.addActionListener(e -> performRegister());
        
        registerPanel.add(lblUyeOlmadiniz);
        registerPanel.add(btnRegister);

        // ADMİN PANELİ: SADECE YÖNETİCİ GİRİŞ YAPTIĞINDA GÖRÜNÜR OLUR
        adminBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAddAdmin = new JButton("+ Admin Ekle");
        JButton btnAddCoach = new JButton("+ Antrenör Ekle");
        JButton btnManageAll = new JButton("Tüm Kullanıcıları Yönet");
        
        adminBtnPanel.add(btnAddAdmin);
        adminBtnPanel.add(btnAddCoach);
        adminBtnPanel.add(btnManageAll);
        adminBtnPanel.setVisible(false); 

        btnAddAdmin.addActionListener(e -> createSpecificUser(Role.ADMIN));
        btnAddCoach.addActionListener(e -> createSpecificUser(Role.ANTRENOR));
        btnManageAll.addActionListener(e -> openDashboard()); 
        
        bottomPanel.add(adminBtnPanel, BorderLayout.WEST);
        bottomPanel.add(registerPanel, BorderLayout.EAST);
        
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    // ORTAK BUTON TASARIMI OLUŞTURUCU
    private JButton createCardButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(200, 60));
        btn.setMaximumSize(new Dimension(200, 60));
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setBackground(new Color(230, 230, 230));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 2));
        return btn;
    }

    // GİRİŞ VE ÇIKIŞ İŞLEMLERİNİ YÖNETEN METOT
    private void performLogin() {
        if (loggedInUser != null) { 
            loggedInUser = null;
            btnLoginHeader.setText("Giriş Yap");
            btnRegister.setVisible(true);
            lblUyeOlmadiniz.setVisible(true);
            adminBtnPanel.setVisible(false);
            JOptionPane.showMessageDialog(this, "Çıkış Yapıldı!");
            return;
        }

        LoginDialog ld = new LoginDialog(this);
        ld.setVisible(true);
        Kullanici k = ld.getLoggedInUser();
        
        if(k != null) {
            loggedInUser = k;
            btnLoginHeader.setText("Çıkış Yap ("+k.getEmail()+")");

            if(k.getRole() == Role.ADMIN) {
                btnRegister.setVisible(false);
                lblUyeOlmadiniz.setVisible(false);
                adminBtnPanel.setVisible(true);
            } else {
                openDashboard();
            }
        }
    }

    // KULLANICI ROLÜNE GÖRE İLGİLİ PANELİ AÇAR
    private void openDashboard() {
        if(loggedInUser == null) return;
        
        switch (loggedInUser.getRole()) {
            case ADMIN -> { new AdminPaneli((Admin) loggedInUser).setVisible(true); this.dispose(); }
            case ANTRENOR -> { new AntrenorPaneli((Antrenor) loggedInUser).setVisible(true); this.dispose(); }
            case UYE -> { new UyePaneli((Uye) loggedInUser).setVisible(true); this.dispose(); }
        }
    }

    private void performRegister() {
        KayıtDialog kd = new KayıtDialog(this);
        kd.setVisible(true);
    }

    // ADMİN TARAFINDAN YENİ ANTRENÖR VEYA ADMİN EKLENMESİNİ SAĞLAR
    private void createSpecificUser(Role type) {
        KullaniciDialog dialog = new KullaniciDialog(this, null);
        dialog.setIsUyeUpdateOnly(false);
        dialog.setRoleSelection(type);
        dialog.setVisible(true);
        
        Kullanici yeni = dialog.getKullanici();
        if (yeni != null && loggedInUser instanceof Admin a) {
            a.ekle(yeni);
        }
    }
}