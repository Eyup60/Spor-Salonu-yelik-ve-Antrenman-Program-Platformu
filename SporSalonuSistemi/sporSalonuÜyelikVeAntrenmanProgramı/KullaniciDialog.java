package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// KULLANICI DİALOG SINIFI
// KULLANICI EKLEME VE GÜNCELLEME İŞLEMLERİ İÇİN KULLANILIR
// SWING JDIALOG MİRAS ALIR
public class KullaniciDialog extends JDialog {
    // SERİALİZASYON VERSİYON NUMARASI
    private static final long serialVersionUID = 1L;
    
    // FORM DEĞİŞKENLERİ
    private JTextField txtIsim;
    private JTextField txtSoyisim;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JComboBox<Role> cbRole;
    private JTextField txtBoy, txtKilo, txtYas, txtYagOrani;
    private JTextField txtUzmanlik;
    private JPanel dynamicPanel;
    
    // SONUÇ VE GÜNCELLEME DEĞİŞKENLERİ
    private Kullanici sonucKullanici;
    private Kullanici guncellenecek;
    
    // ÜYE GÜNCELLEME KONTROLÜ
    private boolean isUyeUpdateOnly = false; 

    // KULLANICI DİALOG YAPICI METOT
    // YENİ KULLANICI EKLEME VEYA GÜNCELLEME İÇİN FORM OLUŞTURUR
    public KullaniciDialog(JFrame parent, Kullanici guncellenecek) {
        super(parent, guncellenecek == null ? "Yeni Kullanıcı Ekle" : "Kullanıcı Güncelle", true);
        this.guncellenecek = guncellenecek;
        
        // PENCERE AYARLARI
        setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        // ANA PANEL OLUŞTUR
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // TEMEL FORM ALANLARI
        mainPanel.add(new JLabel("Isim:"));
        txtIsim = new JTextField();
        mainPanel.add(txtIsim);
        
        mainPanel.add(new JLabel("Soyisim:"));
        txtSoyisim = new JTextField();
        mainPanel.add(txtSoyisim);

        mainPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        mainPanel.add(txtEmail);

        mainPanel.add(new JLabel("Şifre:"));
        txtPassword = new JPasswordField();
        mainPanel.add(txtPassword);

        mainPanel.add(new JLabel("Rol:"));
        cbRole = new JComboBox<>(Role.values());
        mainPanel.add(cbRole);

        // DİNAMİK PANEL KURULUMU
        dynamicPanel = new JPanel(new CardLayout());
        
        // ADMİN PANELİ (BOŞ)
        JPanel adminPanel = new JPanel();
        dynamicPanel.add(adminPanel, Role.ADMIN.name());
        
        // ANTRENÖR PANELİ
        JPanel antrenorPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        antrenorPanel.add(new JLabel("Uzmanlık Alanı:"));
        txtUzmanlik = new JTextField();
        antrenorPanel.add(txtUzmanlik);
        dynamicPanel.add(antrenorPanel, Role.ANTRENOR.name());
        
        // ÜYE PANELİ
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
        
        // ROL DEĞİŞİMİNDE PANEL GEÇİŞİ
        cbRole.addActionListener(e -> {
            Object selected = cbRole.getSelectedItem();
            if (selected != null) {
                CardLayout cl = (CardLayout) dynamicPanel.getLayout();
                cl.show(dynamicPanel, ((Role) selected).name());
            }
        });

        // PANEL EKLEME VE BUTONLAR
        add(mainPanel, BorderLayout.NORTH);
        add(dynamicPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnKaydet = new JButton("Kaydet");
        JButton btnIptal = new JButton("İptal");
        btnPanel.add(btnKaydet);
        btnPanel.add(btnIptal);
        add(btnPanel, BorderLayout.SOUTH);

        // BUTON AKSİYONLARI
        btnIptal.addActionListener(e -> dispose());
        btnKaydet.addActionListener(this::kaydet);
        
        // ENTER TUŞU İLE FORM DOLDURMA
        setupEnterKeyNavigationForForm();
        
        // VERİ DOLDURMA VEYA VARSAYILAN AYARLAR
        if (guncellenecek != null) {
            verileriDoldur(guncellenecek);
        } else {
            cbRole.setSelectedItem(Role.UYE); 
        }
    }
    
    // ÜYE GÜNCELLE SADECE METOTU
    // ANTRENÖRÜN SADECE BELİRLİ ALANLARI GÜNCELLEMESİ İÇİN KISITLAMA
    public void setIsUyeUpdateOnly(boolean isOnly) {
        this.isUyeUpdateOnly = isOnly;
        if(isOnly) {
            // Antrenörün değiştiremeyeceği alanları kapatıyoruz
            txtIsim.setEditable(false);
            txtSoyisim.setEditable(false);
            txtEmail.setEditable(false);
            txtPassword.setEnabled(false); // Şifre kutusunu tamamen devre dışı bırak
            cbRole.setEnabled(false);
            
            // Antrenörün odaklanması gereken alanları vurgula (isteğe bağlı renk değişimi)
            txtKilo.requestFocus();
        }
    }

    // ROL SEÇİMİ AYARLA METOTU
    // BELİRLİ BİR ROLÜ ÖNCEDEN SEÇER VE DEĞİŞTİRİLMESİNİ ENGELLER
    public void setRoleSelection(Role type) {
        cbRole.setSelectedItem(type);
        cbRole.setEnabled(false);
        CardLayout cl = (CardLayout) dynamicPanel.getLayout();
        cl.show(dynamicPanel, type.name());
    }
    
    // KISITLI ROLLER AYARLA METOTU
    // SADECE İZİN VERİLEN ROLLERİ SEÇİME SUNAR
    public void setRestrictedRoles(Role[] allowedRoles) {
        cbRole.removeAllItems(); // Mevcut tüm rolleri (Admin, Antrenör, Üye) temizle
        for (Role role : allowedRoles) {
            cbRole.addItem(role); // Sadece izin verilenleri ekle
        }
    }
    
    // VERİLERİ DOLDUR METOTU
    // GÜNCELLEME MODUNDA MEVCUT KULLANICI BİLGİLERİNİ FORMA YÜKLER
    private void verileriDoldur(Kullanici k) {
        txtIsim.setText(k.getIsim());
        txtSoyisim.setText(k.getSoyisim());
        txtEmail.setText(k.getEmail());
        txtPassword.setText(""); 
        cbRole.setSelectedItem(k.getRole());
        
        // Güncelleme modunda rol değişimi genellikle Admin panelinde de kısıtlanır
        if (guncellenecek != null && !isUyeUpdateOnly) {
             cbRole.setEnabled(false); 
        }
        
        // ROL'E GÖRE ÖZEL ALANLARI DOLDUR
        if (k instanceof Antrenor) {
            txtUzmanlik.setText(((Antrenor) k).getUzmanlıkAlanı());
        } else if (k instanceof Uye u) {
            txtBoy.setText(String.valueOf(u.getBoy()));
            txtKilo.setText(String.valueOf(u.getKilo()));
            txtYas.setText(String.valueOf(u.getYas()));
            txtYagOrani.setText(String.valueOf(u.getYağOrani()));
        }
    }

    // KAYDET METOTU
    // FORM VERİLERİNİ DOĞRULAR VE KULLANICI OLUŞTURUR/GÜNCELLER
    private void kaydet(ActionEvent e) {
        try {
            // Eğer Antrenör güncelliyorsa, sadece fiziksel verileri set etmeliyiz
            if (guncellenecek != null && isUyeUpdateOnly) {
                if (guncellenecek instanceof Uye u) {
                    u.setBoy(Double.parseDouble(txtBoy.getText()));
                    u.setKilo(Double.parseDouble(txtKilo.getText()));
                    u.setYas(Integer.parseInt(txtYas.getText()));
                    u.setYağOrani(Double.parseDouble(txtYagOrani.getText()));
                    
                    sonucKullanici = u;
                }
            } else {
                // NORMAL KAYIT/ADMİN GÜNCELLEME MANTIĞI
                String isim = txtIsim.getText();
                String soyisim = txtSoyisim.getText();
                String email = txtEmail.getText();
                String pwd = new String(txtPassword.getPassword());
                Role r = (Role) cbRole.getSelectedItem();

                if (guncellenecek != null) {
                    // MEVCUT KULLANICI GÜNCELLEME
                    guncellenecek.setEmail(email);
                    guncellenecek.setIsim(isim);
                    guncellenecek.setSoyisim(soyisim);
                    if (!pwd.isEmpty()) {
                        guncellenecek.setPassword(pwd);
                    }
                    
                    if (guncellenecek instanceof Uye u) {
                        u.setBoy(Double.parseDouble(txtBoy.getText()));
                        u.setKilo(Double.parseDouble(txtKilo.getText()));
                        u.setYas(Integer.parseInt(txtYas.getText()));
                        u.setYağOrani(Double.parseDouble(txtYagOrani.getText()));
                    } else if (guncellenecek instanceof Antrenor a) {
                        a.setUzmanlıkAlanı(txtUzmanlik.getText());
                    }
                    sonucKullanici = guncellenecek;
                } else {
                    // YENİ KAYIT
                    switch(r) {
                        case ADMIN -> sonucKullanici = new Admin(isim,soyisim,email, pwd);
                        case ANTRENOR -> sonucKullanici = new Antrenor(isim,soyisim,email, pwd, txtUzmanlik.getText());
                        case UYE -> sonucKullanici = new Uye(isim,soyisim,email, pwd, 
                                        Double.parseDouble(txtBoy.getText()), 
                                        Double.parseDouble(txtKilo.getText()), 
                                        Integer.parseInt(txtYas.getText()), 
                                        Double.parseDouble(txtYagOrani.getText()));
                    }
                    Admin.doğrudanEkle(sonucKullanici);
                }
            }
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hata: " + ex.getMessage(), "Girdi Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    // KULLANICI GETİR METOTU
    // OLUŞTURULAN VEYA GÜNCELLENEN KULLANICI NESNESİNİ DÖNDÜRÜR
    public Kullanici getKullanici() {
        return sonucKullanici;
    }
    
    // ENTER TUŞU İLE FORM ALANLARI ARASINDA GEZİNME METOTU
    // KULLANICI DENEYİMİNİ İYİLEŞTİRİR
    private void setupEnterKeyNavigationForForm() {
        // Form alanlarını bir listeye ekle
        java.util.List<JComponent> formComponents = new java.util.ArrayList<>();
        formComponents.add(txtIsim);
        formComponents.add(txtSoyisim);
        formComponents.add(txtEmail);
        formComponents.add(txtPassword);
        formComponents.add(cbRole);
        formComponents.add(txtBoy);
        formComponents.add(txtKilo);
        formComponents.add(txtYas);
        formComponents.add(txtYagOrani);
        formComponents.add(txtUzmanlik);
        
        // Butonları da ekle
        java.util.List<JButton> buttons = new java.util.ArrayList<>();
        // Butonları bulmak için panel içinde arama yap
        for (Component comp : ((JPanel) getContentPane().getComponent(2)).getComponents()) {
            if (comp instanceof JButton) {
                buttons.add((JButton) comp);
            }
        }
        
        // Form alanlarına Enter tuşu ekle
        for (int i = 0; i < formComponents.size(); i++) {
            final int currentIndex = i;
            final JComponent currentComponent = formComponents.get(i);
            
            currentComponent.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                        // Enter tuşuna basıldığında sonraki alana geç
                        int nextIndex = (currentIndex + 1) % formComponents.size();
                        formComponents.get(nextIndex).requestFocus();
                    }
                }
            });
        }
        
        // Butonlara Enter tuşu ekle
        for (int i = 0; i < buttons.size(); i++) {
            final int currentIndex = i;
            final JButton currentButton = buttons.get(i);
            
            currentButton.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                        // Enter tuşuna basıldığında butonun action'ını çalıştır
                        currentButton.doClick();
                    }
                }
            });
        }
        
        // İlk alana odaklan
        if (!formComponents.isEmpty()) {
            formComponents.get(0).requestFocus();
        }
    }
}