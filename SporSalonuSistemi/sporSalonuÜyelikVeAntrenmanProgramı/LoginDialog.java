package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;

// GİRİŞ DİALOG SINIFI
public class LoginDialog extends JDialog {
    private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
    private JPasswordField txtPassword;
    private Kullanici loggedInUser;
    private YetkiYöneticisi yetkiYöneticisi = new YetkiYöneticisi();

    // GİRİŞ DİALOG YAPICI METOT
    public LoginDialog(JFrame parent) {
        super(parent, "Giriş Yap", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        centerPanel.add(new JLabel("Email:", SwingConstants.RIGHT));
        txtEmail = new JTextField();
        centerPanel.add(txtEmail);

        centerPanel.add(new JLabel("Şifre:", SwingConstants.RIGHT));
        txtPassword = new JPasswordField();
        centerPanel.add(txtPassword);

        add(centerPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnLogin = new JButton("Giriş Yap");
        JButton btnCancel = new JButton("İptal");
        btnPanel.add(btnLogin);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnLogin.addActionListener(e -> {
        	String email = txtEmail.getText().trim();
        	String pwd = new String(txtPassword.getPassword()).trim();

        	loggedInUser = yetkiYöneticisi.giriş(email, pwd);
            
            if(loggedInUser != null) {
                JOptionPane.showMessageDialog(this, "Giriş Başarılı! Rol: " + loggedInUser.getRole());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Hatalı Email veya Şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // GİRİŞ YAPAN KULLANICI GETİR
    public Kullanici getLoggedInUser() {
        return loggedInUser;
    }
}
