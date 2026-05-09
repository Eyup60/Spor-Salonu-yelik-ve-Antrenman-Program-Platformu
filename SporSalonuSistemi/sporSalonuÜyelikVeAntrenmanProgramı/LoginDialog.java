package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

// GİRİŞ DİALOG SINIFI
// KULLANICI GİRİŞ İŞLEMLERİ İÇİN KULLANILIR
// SWING JDIALOG MİRAS ALIR
public class LoginDialog extends JDialog {
    // SERİALİZASYON VERSİYON NUMARASI
    private static final long serialVersionUID = 1L;
    
    // FORM DEĞİŞKENLERİ
	private JTextField txtEmail;
    private JPasswordField txtPassword;
    private Kullanici loggedInUser;
    private YetkiYöneticisi yetkiYöneticisi = new YetkiYöneticisi();

    // GİRİŞ DİALOG YAPICI METOT
    // MODAL PENCERE OLARAK GİRİŞ FORMU OLUŞTURUR
    public LoginDialog(JFrame parent) {
        super(parent, "Giriş Yap", true);
        // PENCERE AYARLARI
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // MERKEZ PANEL FORM ALANLARI
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // EMAIL ALANI
        centerPanel.add(new JLabel("Email:", SwingConstants.RIGHT));
        txtEmail = new JTextField();
        centerPanel.add(txtEmail);

        // ŞİFRE ALANI
        centerPanel.add(new JLabel("Şifre:", SwingConstants.RIGHT));
        txtPassword = new JPasswordField();
        centerPanel.add(txtPassword);

        add(centerPanel, BorderLayout.CENTER);

        // BUTON PANELİ
        JPanel btnPanel = new JPanel();
        JButton btnLogin = new JButton("Giriş Yap");
        JButton btnCancel = new JButton("İptal");
        btnPanel.add(btnLogin);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);

        // BUTON AKSİYONLARI
        btnCancel.addActionListener(e -> dispose());
        btnLogin.addActionListener(e -> {
        	// FORM VERİLERİNİ AL VE GİRİŞ YAP
        	String email = txtEmail.getText().trim();
        	String pwd = new String(txtPassword.getPassword()).trim();

        	// YETKİ YÖNETİCİSİ İLE GİRİŞ KONTROLÜ
        	loggedInUser = yetkiYöneticisi.giriş(email, pwd);
            
            if(loggedInUser != null) {
                JOptionPane.showMessageDialog(this, "Giriş Başarılı! Rol: " + loggedInUser.getRole());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Hatalı Email veya Şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // ENTER TUŞU İLE FORM ALANLARI VE BUTONLAR ARASINDA GEZİNME
        setupEnterKeyNavigation();
    }

    // GİRİŞ YAPAN KULLANICI GETİR METOTU
    // BAŞARILI GİRİŞ SONRASI KULLANICI NESNESİNİ DÖNDÜRÜR
    public Kullanici getLoggedInUser() {
        return loggedInUser;
    }
    
    // ENTER TUŞU İLE FORM ALANLARI ARASINDA GEZİNME METOTU
    // KULLANICI DENEYİMİNİ İYİLEŞTİRİR
    private void setupEnterKeyNavigation() {
        // Email alanından şifre alanına geçiş
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    txtPassword.requestFocus();
                }
            }
        });
        
        // Şifre alanından giriş butonuna geçiş veya giriş yapma
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    // Giriş butonuna tıkla
                    JButton btnLogin = (JButton) ((JPanel) getContentPane().getComponent(1)).getComponent(0);
                    btnLogin.doClick();
                }
            }
        });
        
        // İlk alana odaklan
        txtEmail.requestFocus();
    }
}
