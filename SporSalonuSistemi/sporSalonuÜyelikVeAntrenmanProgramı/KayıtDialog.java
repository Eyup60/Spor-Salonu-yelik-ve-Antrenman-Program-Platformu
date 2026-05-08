package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KayıtDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField txtIsim, txtSoyIsim, txtEmail, txtBoy, txtKilo, txtYas, txtYagOrani;
    private JPasswordField txtPassword;
    private Kullanici yeniUye;

    public KayıtDialog(JFrame parent) {
        super(parent, "Yeni Üye Kaydı", true);
        setSize(350, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Isim:"));
        txtIsim = new JTextField();
        formPanel.add(txtIsim);

        formPanel.add(new JLabel("Soyisim:"));
        txtSoyIsim = new JTextField();
        formPanel.add(txtSoyIsim);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Şifre:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);

        formPanel.add(new JLabel("Boy (cm):"));
        txtBoy = new JTextField();
        formPanel.add(txtBoy);

        formPanel.add(new JLabel("Kilo (kg):"));
        txtKilo = new JTextField();
        formPanel.add(txtKilo);

        formPanel.add(new JLabel("Yaş:"));
        txtYas = new JTextField();
        formPanel.add(txtYas);

        formPanel.add(new JLabel("Yağ Oranı (%):"));
        txtYagOrani = new JTextField();
        formPanel.add(txtYagOrani);

        add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnKaydet = new JButton("Kayıt Ol");
        JButton btnIptal = new JButton("İptal");
        btnPanel.add(btnKaydet);
        btnPanel.add(btnIptal);
        add(btnPanel, BorderLayout.SOUTH);

        btnIptal.addActionListener(e -> dispose());
        btnKaydet.addActionListener(e -> kayitOl());

        KeyAdapter enterKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    kayitOl();
                }
            }
        };

        txtIsim.addKeyListener(enterKeyAdapter);
        txtSoyIsim.addKeyListener(enterKeyAdapter);
        txtEmail.addKeyListener(enterKeyAdapter);
        txtPassword.addKeyListener(enterKeyAdapter);
        txtBoy.addKeyListener(enterKeyAdapter);
        txtKilo.addKeyListener(enterKeyAdapter);
        txtYas.addKeyListener(enterKeyAdapter);
        txtYagOrani.addKeyListener(enterKeyAdapter);
        btnKaydet.addKeyListener(enterKeyAdapter);
    }

    private void kayitOl() {
        if (txtIsim.getText().isEmpty() || txtSoyIsim.getText().isEmpty() || 
            txtEmail.getText().isEmpty() || new String(txtPassword.getPassword()).isEmpty() ||
            txtBoy.getText().isEmpty() || txtKilo.getText().isEmpty() || 
            txtYas.getText().isEmpty() || txtYagOrani.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurunuz!", "Eksik Bilgi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String isim = txtIsim.getText();
            String soyIsim = txtSoyIsim.getText();
            String email = txtEmail.getText();
            String pwd = new String(txtPassword.getPassword());
            double boy = Double.parseDouble(txtBoy.getText());
            double kilo = Double.parseDouble(txtKilo.getText());
            int yas = Integer.parseInt(txtYas.getText());
            double yag = Double.parseDouble(txtYagOrani.getText());

            yeniUye = new Uye(isim, soyIsim, email, pwd, boy, kilo, yas, yag);
            Admin.doğrudanEkle(yeniUye);

            JOptionPane.showMessageDialog(this, "Kayıt Başarılı!", "BİLGİ", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Boy, Kilo, Yaş ve Yağ Oranı alanlarına geçerli sayısal değerler giriniz.", "Girdi Hatası", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Geçersiz Girdi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Beklenmedik bir hata oluştu: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Kullanici getYeniKullanici() {
        return yeniUye;
    }
}