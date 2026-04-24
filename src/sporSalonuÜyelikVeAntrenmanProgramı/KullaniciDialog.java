package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KullaniciDialog extends JDialog {
    private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JComboBox<Role> cbRole;
    private JTextField txtBoy, txtKilo, txtYas, txtYagOrani;
    private JTextField txtUzmanlik;
    private JPanel dynamicPanel;
    
    private Kullanici sonucKullanici;
    private Kullanici guncellenecek;
    
    // For specific roles
    private boolean isUyeUpdateOnly = false; // from Antrenor panel

    public KullaniciDialog(JFrame parent, Kullanici guncellenecek) {
        super(parent, guncellenecek == null ? "Yeni Kullanıcı Ekle" : "Kullanıcı Güncelle", true);
        this.guncellenecek = guncellenecek;
        
        setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        mainPanel.add(txtEmail);

        mainPanel.add(new JLabel("Şifre:"));
        txtPassword = new JPasswordField();
        mainPanel.add(txtPassword);

        mainPanel.add(new JLabel("Rol:"));
        cbRole = new JComboBox<>(Role.values());
        mainPanel.add(cbRole);

        dynamicPanel = new JPanel(new CardLayout());
        
        // Blank panel for Admin
        JPanel adminPanel = new JPanel();
        dynamicPanel.add(adminPanel, Role.ADMIN.name());
        
        // Antrenor panel
        JPanel antrenorPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        antrenorPanel.add(new JLabel("Uzmanlık Alanı:"));
        txtUzmanlik = new JTextField();
        antrenorPanel.add(txtUzmanlik);
        dynamicPanel.add(antrenorPanel, Role.ANTRENOR.name());
        
        // Uye panel
        JPanel uyePanel = new JPanel(new GridLayout(0, 2, 5, 5));
        uyePanel.add(new JLabel("Boy (cm):"));
        txtBoy = new JTextField();
        uyePanel.add(txtBoy);
        uyePanel.add(new JLabel("Kilo (kg):"));
        txtKilo = new JTextField();
        uyePanel.add(txtKilo);
        uyePanel.add(new JLabel("Yaş:"));
        txtYas = new JTextField();
        uyePanel.add(txtYas);
        uyePanel.add(new JLabel("Yağ Oranı (%):"));
        txtYagOrani = new JTextField();
        uyePanel.add(txtYagOrani);
        dynamicPanel.add(uyePanel, Role.UYE.name());
        
        cbRole.addActionListener(e -> {
            CardLayout cl = (CardLayout) dynamicPanel.getLayout();
            cl.show(dynamicPanel, ((Role) cbRole.getSelectedItem()).name());
        });

        add(mainPanel, BorderLayout.NORTH);
        add(dynamicPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnKaydet = new JButton("Kaydet");
        JButton btnIptal = new JButton("İptal");
        btnPanel.add(btnKaydet);
        btnPanel.add(btnIptal);
        add(btnPanel, BorderLayout.SOUTH);

        btnIptal.addActionListener(e -> dispose());
        btnKaydet.addActionListener(this::kaydet);
        
        if (guncellenecek != null) {
            verileriDoldur(guncellenecek);
        } else {
            // tetiklemek için
            cbRole.setSelectedItem(Role.UYE); 
        }
    }
    
    public void setIsUyeUpdateOnly(boolean isOnly) {
        this.isUyeUpdateOnly = isOnly;
        if(isOnly) {
            cbRole.setSelectedItem(Role.UYE);
            cbRole.setEnabled(false);
            txtEmail.setEnabled(false);
            // Antrenor cannot change Uye's password either, supposedly? Wait, Uye password can be kept or requested blank.
            txtPassword.setEnabled(false);
        }
    }

    private void verileriDoldur(Kullanici k) {
        txtEmail.setText(k.getEmail());
        txtPassword.setText(k.getPassword());
        cbRole.setSelectedItem(k.getRole());
        cbRole.setEnabled(false); // Update sırasında rol değiştirilmesin.
        
        if (k instanceof Antrenor) {
            txtUzmanlik.setText(((Antrenor) k).getUzmanlıkAlanı());
            txtPassword.setText(""); // Update ederken password girmeyebilir
        } else if (k instanceof Uye) {
            Uye u = (Uye) k;
            txtBoy.setText(String.valueOf(u.getBoy()));
            txtKilo.setText(String.valueOf(u.getKilo()));
            txtYas.setText(String.valueOf(u.getYas()));
            txtYagOrani.setText(String.valueOf(u.getYağOrani()));
            txtPassword.setText("");
        }
    }

    private void kaydet(ActionEvent e) {
        try {
            String email = txtEmail.getText();
            String pwd = new String(txtPassword.getPassword());
            Role r = (Role) cbRole.getSelectedItem();
            
            if (guncellenecek != null) {
                // Sadece alanları güncelleyelim.
                // Exceptions might be thrown by setters
                if(!isUyeUpdateOnly) {
                   guncellenecek.setEmail(email);
                   if(!pwd.isEmpty()) {
                       guncellenecek.setPassword(pwd);
                   }
                }
                
                if (guncellenecek instanceof Antrenor) {
                    ((Antrenor) guncellenecek).setUzmanlıkAlanı(txtUzmanlik.getText());
                } else if (guncellenecek instanceof Uye) {
                    Uye u = (Uye) guncellenecek;
                    u.setBoy(Double.parseDouble(txtBoy.getText()));
                    u.setKilo(Double.parseDouble(txtKilo.getText()));
                    u.setYas(Integer.parseInt(txtYas.getText()));
                    u.setYağOrani(Double.parseDouble(txtYagOrani.getText()));
                }
                sonucKullanici = guncellenecek;
            } else {
                switch(r) {
                    case ADMIN -> sonucKullanici = new Admin(email, pwd);
                    case ANTRENOR -> sonucKullanici = new Antrenor(email, pwd, txtUzmanlik.getText());
                    case UYE -> sonucKullanici = new Uye(email, pwd, 
                                    Double.parseDouble(txtBoy.getText()), 
                                    Double.parseDouble(txtKilo.getText()), 
                                    Integer.parseInt(txtYas.getText()), 
                                    Double.parseDouble(txtYagOrani.getText()));
                }
            }
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Geçersiz Girdi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lütfen sayısal değerleri doğru formattata girin.", "Girdi Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Kullanici getKullanici() {
        return sonucKullanici;
    }
}
