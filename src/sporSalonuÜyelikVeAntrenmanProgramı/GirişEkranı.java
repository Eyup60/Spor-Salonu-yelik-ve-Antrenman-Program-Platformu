package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GirişEkranı extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel adminBtnPanel;
    private Kullanici loggedInUser = null;
    

    private JButton btnLoginHeader;
    private JButton btnRegister;
    private JLabel lblUyeOlmadiniz;

    public static void main(String[] args) {
    	
    	DosyaYoneticisi.verileriYukle();

    	if (Admin.getKullanicilar().isEmpty()) {
    	    Admin.doğrudanEkle(new Admin("admin@gym.com", "123456"));
    	}

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                GirişEkranı frame = new GirişEkranı();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GirişEkranı() {
        setTitle("Spor Salonu Ana Giriş ve Kayıt Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);


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
        btnLoginHeader.setForeground(Color.BLACK);
        btnLoginHeader.setFocusPainted(false);
        btnLoginHeader.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLoginHeader.addActionListener(e -> performLogin());
        loginPanel.add(btnLoginHeader);

        topPanel.add(titlePanel, BorderLayout.WEST);
        topPanel.add(loginPanel, BorderLayout.EAST);
        getContentPane().add(topPanel, BorderLayout.NORTH);


        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBorder(new EmptyBorder(30, 20, 30, 20));
        cardsPanel.setBackground(Color.WHITE);
        
        JButton btnPaketler = createCardButton("Paketlerimiz");
        JButton btnAntrenorler = createCardButton("Antrenörlerimiz");
        JButton btnTesis = createCardButton("Tesisimiz");
        
        btnPaketler.addActionListener(e -> JOptionPane.showMessageDialog(this, "Aylık, 3 Aylık ve Yıllık paketlerimizle hizmetinizdeyiz. Özel ders imkanı bulunmaktadır."));
        btnAntrenorler.addActionListener(e -> JOptionPane.showMessageDialog(this, "Alanında uzman, sertifikalı antrenörlerimizle hedeflerinize daha hızlı ulaşın."));
        btnTesis.addActionListener(e -> JOptionPane.showMessageDialog(this, "En modern fitness aletleri, hijyenik soyunma odaları ve ferah çalışma alanları."));

        cardsPanel.add(Box.createVerticalStrut(20));
        cardsPanel.add(btnPaketler);
        cardsPanel.add(Box.createVerticalStrut(15));
        cardsPanel.add(btnAntrenorler);
        cardsPanel.add(Box.createVerticalStrut(15));
        cardsPanel.add(btnTesis);
        
        getContentPane().add(cardsPanel, BorderLayout.WEST);


        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(250, 250, 250));
        
        JLabel quoteLabel = new JLabel("<html><div style='text-align: center; width: 400px; font-size: 24px; font-style: italic; color: #333333; line-height: 1.5; font-family: Serif;'>"
                + "\"Ben sporcunun<br>zeki, çevik ve aynı zamanda ahlaklısını severim.\""
                + "<br><br><span style='font-size: 18px; font-weight: bold;'>- Mustafa Kemal Atatürk</span></div></html>");
        centerPanel.add(quoteLabel);
        
        getContentPane().add(centerPanel, BorderLayout.CENTER);


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
            } 
            else {

                openDashboard();
            }
        }
    }

    private void openDashboard() {
        if(loggedInUser == null) return;
        
        switch (loggedInUser.getRole()) {
        case ADMIN -> new AdminPaneli((Admin) loggedInUser).setVisible(true);
        case ANTRENOR -> { new AntrenorPaneli((Antrenor) loggedInUser).setVisible(true); this.dispose(); }
        case UYE -> { new UyePaneli((Uye) loggedInUser).setVisible(true); this.dispose(); }
        }

        if(loggedInUser.getRole() != Role.ADMIN) {
           this.dispose();
        }
    }

    private void performRegister() {
        KayıtDialog kd = new KayıtDialog(this);
        kd.setVisible(true);
    }
    

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
