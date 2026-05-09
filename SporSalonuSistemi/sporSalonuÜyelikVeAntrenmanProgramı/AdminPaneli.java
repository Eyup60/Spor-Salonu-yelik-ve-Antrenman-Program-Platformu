package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// SİSTEM YÖNETİCİSİNİN KULLANICI LİSTESİNİ GÖRDÜĞÜ VE İDARİ İŞLEMLERİ YAPTIĞI ANA EKRAN SINIFI
public class AdminPaneli extends JFrame {

    private static final long serialVersionUID = 1L;
    private Admin admin;
    private JTable table;
    private DefaultTableModel tableModel;

    // YÖNETİCİ PANELİNİ BAŞLATAN VE GÖRSEL BİLEŞENLERİ OLUŞTURAN YAPICI METOT
    public AdminPaneli(Admin admin) {
        this.admin = admin;

        // PENCERE BAŞLIĞI VE TEMEL AYARLARIN YAPILMASI
        setTitle("Yönetici Paneli - " + admin.getIsim() + " " + admin.getSoyisim());
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setLayout(new BorderLayout());

        // ÜST KISIMDA TOPLAM KULLANICI SAYISINI GÖSTEREN BAŞLIK ETİKETİ
        JLabel lblTitle = new JLabel("Tüm Kullanıcılar(" + admin.getKullanicilar().size() + ")", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        getContentPane().add(lblTitle, BorderLayout.NORTH);

        // KULLANICI TABLOSUNUN SÜTUNLARININ VE DÜZENLENEMEZ MODELİNİN OLUŞTURULMASI
        String[] cols = {"ID", "Email", "Rol"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // TABLO HÜCRELERİNİN DOĞRUDAN DÜZENLENMESİNİ ENGELLER
                return false; 
            }
        };
        table = new JTable(tableModel);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        // ALT KISIMDAKİ İŞLEM BUTONLARININ TANIMLANMASI VE PANELE EKLENMESİ
        JPanel btnPanel = new JPanel();
        JButton btnEkle = new JButton("Yeni Ekle(Admin / Antrenör)");
        JButton btnGuncelle = new JButton("Profilimi Güncelle");
        JButton btnSil = new JButton("Sil");
        JButton btnCikis = new JButton("Çıkış Yap");
        JButton btnRaporlar = new JButton("Raporlar ve Ödemeler");
        JButton btnFiyatGüncelleme = new JButton("Fiyat Güncelleme");
        
        btnPanel.add(btnEkle);
        btnPanel.add(btnGuncelle);
        btnPanel.add(btnSil);
        btnPanel.add(btnFiyatGüncelleme);
        btnPanel.add(btnRaporlar);
        btnPanel.add(btnCikis);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

        // BUTONLARA TIKLANDIĞINDA ÇALIŞACAK OLAY DİNLEYİCİLERİNİN BAĞLANMASI
        btnEkle.addActionListener(e -> ekleKullanici());
        btnGuncelle.addActionListener(e -> guncelleKullanici());
        btnSil.addActionListener(e -> silKullanici());
        btnCikis.addActionListener(e -> cikisYap());
        btnFiyatGüncelleme.addActionListener(e -> fiyatlariGuncelle());
        btnRaporlar.addActionListener(e -> {
            RaporlamaVeOdemePaneli raporPaneli = new RaporlamaVeOdemePaneli();
            raporPaneli.setVisible(true);
        });
        
        // KLAVYE OK TUŞLARI VE ENTER İLE HIZLI MENÜ NAVİGASYONUNUN KURULMASI
        setupEnterKeyNavigation(btnEkle, btnGuncelle, btnSil, btnFiyatGüncelleme, btnRaporlar, btnCikis);
        
        // PANEL AÇILDIĞINDA OTOMATİK SİSTEM KONTROLÜ VE VERİLERİN TABLOYA YÜKLENMESİ
        admin.sistemBakimiYap();
        verileriYukle();
    }

    // GÜNCEL KULLANICI LİSTESİNİ RAM ÜZERİNDEN ÇEKİP TABLOYA YAZDIRAN METOT
    private void verileriYukle() {
        tableModel.setRowCount(0);
        for (Kullanici k : Admin.getKullanicilar()) {
            tableModel.addRow(new Object[]{k.getId(), k.getEmail(), k.getRole().name()});
        }
    }

    // YENİ YÖNETİCİ VEYA ANTRENÖR EKLEMEK İÇİN DİALOG PENCERESİNİ AÇAN METOT
    private void ekleKullanici() {
        KullaniciDialog dialog = new KullaniciDialog(this, null);
        
        // SADECE YETKİLİ ROLLERİN SEÇİLMESİNİ SAĞLAYAN SINIRLANDIRMA
        dialog.setRestrictedRoles(new Role[]{Role.ADMIN, Role.ANTRENOR});
        
        dialog.setVisible(true);
        Kullanici yeni = dialog.getKullanici();
        
        if (yeni != null) {
            admin.ekle(yeni);
            verileriYukle();
        }
    }

    // OTURUMU AÇIK OLAN YÖNETİCİNİN KENDİ BİLGİLERİNİ GÜNCELLEMESİNİ SAĞLAYAN METOT
    private void guncelleKullanici() {
        Kullanici guncellenecek = this.admin; 
        
        KullaniciDialog dialog = new KullaniciDialog(this, guncellenecek);
        dialog.setTitle("Profilimi Güncelle");
        dialog.setVisible(true);
        
        Kullanici sonuc = dialog.getKullanici();
        if(sonuc != null) {
            admin.guncelle(sonuc);
            setTitle("Yönetici Paneli - " + admin.getEmail());
            verileriYukle();
            JOptionPane.showMessageDialog(this, "Profil bilgileriniz başarıyla güncellendi.");
        }
    }

    // TABLODAN SEÇİLEN KULLANICIYI SİSTEMDEN KALDIRAN METOT
    private void silKullanici() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Silmek için bir kullanıcı seçin.");
            return;
        }
        
        int reply = JOptionPane.showConfirmDialog(this, "Bu kullanıcıyı silmek istediğinize emin misiniz?", "Sil", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String id = (String) tableModel.getValueAt(row, 0);
            try {
                admin.sil(id);
                verileriYukle();
            } catch (IllegalStateException ex) {
                // ÖRNEĞİN SON ADMİN SİLİNMEYE ÇALIŞILDIĞINDA HATA MESAJI GÖSTERİR
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Silme Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // MEVCUT PANELİ KAPATIP KULLANICIYI TEKRAR GİRİŞ EKRANINA YÖNLENDİREN METOT
    private void cikisYap() {
        this.dispose();
        new GirişEkranı().setVisible(true);
    }
    
    // FİYAT GÜNCELLEME EKRANINI AÇAN (GEÇİCİ) METOT - Gerçek panel eklenene kadar hata vermesin
    private void fiyatlariGuncelle() {
        // TODO: Fiyat güncelleme paneli eklenecek. Şimdilik bilgi mesajı göster.
        JOptionPane.showMessageDialog(this, "Fiyat güncelleme ekranı henüz eklenmedi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    }

    // BUTONLAR ARASINDA OK TUŞLARIYLA GEZİNMEYİ VE ENTER İLE TIKLAMAYI SAĞLAYAN YARDIMCI METOT
    private void setupEnterKeyNavigation(JButton... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            final int currentIndex = i;
            final JButton currentButton = buttons[i];
            
            currentButton.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    int code = evt.getKeyCode();
                    // SAĞ/AŞAĞI OKU -> SONRAKİ BUTONA ODAKLA
                    if (code == java.awt.event.KeyEvent.VK_RIGHT || code == java.awt.event.KeyEvent.VK_DOWN) {
                        int next = (currentIndex + 1) % buttons.length;
                        buttons[next].requestFocusInWindow();
                    // SOL/YUKARI OKU -> ÖNCEKİ BUTONA ODAKLA
                    } else if (code == java.awt.event.KeyEvent.VK_LEFT || code == java.awt.event.KeyEvent.VK_UP) {
                        int prev = (currentIndex - 1 + buttons.length) % buttons.length;
                        buttons[prev].requestFocusInWindow();
                    // ENTER -> SEÇİLİ BUTONU TIKLA
                    } else if (code == java.awt.event.KeyEvent.VK_ENTER) {
                        currentButton.doClick();
                    }
                }
            });
        }
    }
}