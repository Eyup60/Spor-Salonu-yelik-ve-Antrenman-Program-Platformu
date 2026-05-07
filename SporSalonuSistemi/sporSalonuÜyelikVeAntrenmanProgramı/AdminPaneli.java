package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// YÖNETİCİ PANELİ SINIFI
public class AdminPaneli extends JFrame {

    private static final long serialVersionUID = 1L;
	private Admin admin;
    private JTable table;
    private DefaultTableModel tableModel;

    // YÖNETİCİ PANELİ YAPICI METOT
    public AdminPaneli(Admin admin) {
        this.admin = admin;

        setTitle("Yönetici Paneli - " + admin.getEmail());
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Tüm Kullanıcılar", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        String[] cols = {"ID", "Email", "Rol"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnEkle = new JButton("Yeni Ekle(Admin / Antrenör)");
        JButton btnGuncelle = new JButton("Profilimi Güncelle");
        JButton btnSil = new JButton("Sil");
        JButton btnCikis = new JButton("Çıkış Yap");
        JButton btnRaporlar = new JButton("Raporlar ve Ödemeler");
        
        btnPanel.add(btnEkle);
        btnPanel.add(btnGuncelle);
        btnPanel.add(btnSil);
        btnPanel.add(btnRaporlar);
        btnPanel.add(btnCikis);
        add(btnPanel, BorderLayout.SOUTH);

        btnEkle.addActionListener(e -> ekleKullanici());
        btnGuncelle.addActionListener(e -> guncelleKullanici());
        btnSil.addActionListener(e -> silKullanici());
        btnCikis.addActionListener(e -> cikisYap());btnRaporlar.addActionListener(e -> {
            RaporlamaVeOdemePaneli raporPaneli = new RaporlamaVeOdemePaneli();
            raporPaneli.setVisible(true);
        });
        

        verileriYukle();
    }

    // VERİLERİ YÜKLE
    private void verileriYukle() {
        tableModel.setRowCount(0);
        for (Kullanici k : Admin.getKullanicilar()) {
            tableModel.addRow(new Object[]{k.getId(), k.getEmail(), k.getRole().name()});
        }
    }

    // KULLANICI EKLE
    private void ekleKullanici() {
    	KullaniciDialog dialog = new KullaniciDialog(this, null);
        
        dialog.setRestrictedRoles(new Role[]{Role.ADMIN, Role.ANTRENOR});
        
        dialog.setVisible(true);
        Kullanici yeni = dialog.getKullanici();
        
        if (yeni != null) {
            admin.ekle(yeni);
            verileriYukle();
        }
    }

    // KULLANICI GÜNCELLE
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

    // KULLANICI SİL
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
                JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Silme Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ÇIKIŞ YAP
    private void cikisYap() {
        this.dispose();
        new GirişEkranı().setVisible(true);
    }
}
