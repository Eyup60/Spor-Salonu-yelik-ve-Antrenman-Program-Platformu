package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// ANTRENÖR PANELİ SINIFI
// ANTRENÖR KULLANICI ARAYÜZÜ
// JFRAME MİRAS ALIR
public class AntrenorPaneli extends JFrame {

    // SERİALİZASYON VERSİYON NUMARASI
    private static final long serialVersionUID = 1L;
    
    // PANEL ÖZELLİKLERİ
    private Antrenor antrenor;
    private JTable table;
    private DefaultTableModel tableModel;

    // ANTRENÖR PANELİ YAPICI METOT
    // PANELİ BAŞLAT VE ÖZELLİKLERİ AYARLA
    public AntrenorPaneli(Antrenor antrenor) {
        this.antrenor = antrenor;

        // PENCERE AYARLARI
        setTitle("Antrenör Paneli - " + antrenor.getIsim()+" "+antrenor.getSoyisim());
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setLayout(new BorderLayout());

        // ÜST PANEL BİLGİLERİ
        // ANTRENÖR BİLGİLERİ VE İSTATİSTİKLER
        JPanel baslikPaneli = new JPanel(new GridLayout(2, 1));
        JLabel lblTitle = new JLabel("Atanmış Öğrenciler (" + antrenor.listele().size() + ")", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lblAlt = new JLabel("Uzmanlık: " + antrenor.getUzmanlıkAlanı() + " | Sistem Tarafından Atanan Liste", SwingConstants.CENTER);
        
        baslikPaneli.add(lblTitle);
        baslikPaneli.add(lblAlt);
        baslikPaneli.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        getContentPane().add(baslikPaneli, BorderLayout.NORTH);

        // TABLO YAPISI
        // ID SÜTUNUNU GİZLE
        String[] cols = {"ID", "İsim", "Soyisim", "Email", "Boy", "Kilo", "Yaş", "VKE"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // HÜCRE DÜZENLEME İZİN VERME
                return false;
            }
        };
        table = new JTable(tableModel);
        // ID SÜTUNUNU GİZLE
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        // ALT PANEL BUTONLARI
        // YETKİLER SINIRLANDIRILMIŞ
        JPanel btnPanel = new JPanel();
        JButton btnProfilim = new JButton("Profilimi Güncelle");
        JButton btnSil = new JButton("Öğrenciyi Bırak/Sil");
        JButton btnCikis = new JButton("Çıkış");
       
        // BUTONLARI EKLE VE AKSİYONLARI BAĞLA
        btnPanel.add(btnProfilim);
        btnPanel.add(btnSil);
        btnPanel.add(btnCikis);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

        // BUTON AKSİYONLARI
        btnProfilim.addActionListener(e -> kendiProfiliniGuncelle());
        btnSil.addActionListener(e -> silUye());
        btnCikis.addActionListener(e -> cikisYap());
        
        // KLAVYE NAVİGASYONU
        setupEnterKeyNavigation(btnProfilim, btnSil, btnCikis);

        // VERİLERİ YÜKLE
        verileriYukle();
    }
    
    // KENDİ PROFİLİNİ GÜNCELLE METOTU
    // ANTRENÖR KENDİ BİLGİLERİNİ GÜNCELLER
    private void kendiProfiliniGuncelle() {
        KullaniciDialog dialog = new KullaniciDialog(this, this.antrenor);
        
        // DİALOG AYARLARI
        dialog.setIsUyeUpdateOnly(false); 
        dialog.setTitle("Profil Bilgilerimi Güncelle");
        dialog.setVisible(true);
        
        // SONUCU KONTROL ET VE GÜNCELLE
        Kullanici sonuc = dialog.getKullanici();
        if(sonuc != null && sonuc instanceof Antrenor) {
            Admin.doğrudanEkle(sonuc); 
            this.antrenor = (Antrenor) sonuc;
            
            // ARAYÜZÜ GÜNCELLE
            setTitle("Antrenör Paneli - " + antrenor.getEmail());
            verileriYukle();
            
            JOptionPane.showMessageDialog(this, "Profiliniz başarıyla güncellendi.");
        }
    }

    // VERİLERİ YÜKLE METOTU
    // TABLOYA ÜYE BİLGİLERİNİ DOLDUR
    private void verileriYukle() {
        tableModel.setRowCount(0);
        for (Uye u : antrenor.listele()) {
            // ÜYE BİLGİLERİNİ TABLOYA EKLE
            tableModel.addRow(new Object[]{ 
                u.getId(), 
                u.getIsim(),
                u.getSoyisim(),
                u.getEmail(), 
                u.getBoy(), 
                u.getKilo(), 
                u.getYas(), 
                String.format("%.2f", u.vucutKitleEndeksiHesapla())
            });
        }
    }

    // ÜYE GÜNCELLE METOTU
    // SEÇİLİ ÜYENİN BİLGİLERİNİ GÜNCELLER
    @SuppressWarnings("unused")
	private void guncelleUye() {
        // SEÇİLİ SATIRI KONTROL ET
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Güncellemek için listeden bir öğrenci seçin.");
            return;
        }
        
        // ÜYEYİ BUL
        String id = (String) tableModel.getValueAt(row, 0);
        Uye u = antrenor.bul(id);
        
        if (u != null) {
            // DİALOG AÇ VE GÜNCELLE
            KullaniciDialog dialog = new KullaniciDialog(this, u);
            dialog.setIsUyeUpdateOnly(true);
            dialog.setVisible(true);
            
            // SONUCU İŞLE
            Kullanici sonuc = dialog.getKullanici(); 
            if(sonuc != null) {
                antrenor.guncelle((Uye)sonuc);
                verileriYukle();
            }
        }
    }

    // ÜYE SİL METOTU
    // SEÇİLİ ÜYELERİ LİSTEDEN KALDIR
    private void silUye() {
        // SEÇİLİ SATIRI KONTROL ET
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Listeden çıkarmak için bir öğrenci seçin.");
            return;
        }
        
        // ONAY DİALOGU
        int reply = JOptionPane.showConfirmDialog(this, 
            "Bu öğrenciyi listenizden çıkarmak istediğinize emin misiniz?\n(Bu işlem kalıcıdır ve veriler güncellenir)", 
            "Öğrenci Silme Onayı", JOptionPane.YES_NO_OPTION);
            
        if (reply == JOptionPane.YES_OPTION) {
            // ÜYEYİ SİL VE VERİLERİ GÜNCELLE
            String id = (String) tableModel.getValueAt(row, 0);
            antrenor.sil(id);
            verileriYukle();
            setTitle("Antrenör Paneli - " + antrenor.getEmail()); 
        }
    }

    // ÇIKIŞ YAP METOTU
    // PENCEREYİ KAPAT GİRİŞ EKRANINA DÖN
    private void cikisYap() {
        this.dispose();
        new GirişEkranı().setVisible(true);
    }
    
    // ENTER TUŞU İLE BUTONLAR ARASI GEZİNME
    // KLAVYE KULLANIMINI KOLAYLAŞTIR
    private void setupEnterKeyNavigation(JButton... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            final int currentIndex = i;
            final JButton currentButton = buttons[i];
            
            // TUŞ DİNLEYİCİ EKLE
            currentButton.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                        // ENTER TUŞU İLE BUTONU TIKLA
                        currentButton.doClick();
                    } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
                        // AŞAĞI OK İLE SONRAKİ BUTONA GEÇ
                        int nextIndex = (currentIndex + 1) % buttons.length;
                        buttons[nextIndex].requestFocus();
                    } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
                        // YUKARI OK İLE ÖNCEKİ BUTONA GEÇ
                        int prevIndex = (currentIndex - 1 + buttons.length) % buttons.length;
                        buttons[prevIndex].requestFocus();
                    }
                }
            });
        }
        
        // İLK BUTONA ODAKLAN
        if (buttons.length > 0) {
            buttons[0].requestFocus();
        }
    }
}