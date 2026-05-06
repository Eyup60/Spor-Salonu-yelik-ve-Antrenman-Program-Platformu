package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AntrenorPaneli extends JFrame {

    private static final long serialVersionUID = 1L;
    private Antrenor antrenor;
    private JTable table;
    private DefaultTableModel tableModel;

    public AntrenorPaneli(Antrenor antrenor) {
        this.antrenor = antrenor;

        setTitle("Antrenör Paneli - " + antrenor.getEmail());
        setSize(800, 500); // Tablo genişlediği için biraz büyütüldü
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setLayout(new BorderLayout());

        // ÜST PANEL: HOCA BİLGİLERİ VE İSTATİSTİK
        JPanel baslikPaneli = new JPanel(new GridLayout(2, 1));
        JLabel lblTitle = new JLabel("Atanmış Öğrenciler (" + antrenor.listele().size() + ")", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lblAlt = new JLabel("Uzmanlık: " + antrenor.getUzmanlıkAlanı() + " | Sistem Tarafından Atanan Liste", SwingConstants.CENTER);
        
        baslikPaneli.add(lblTitle);
        baslikPaneli.add(lblAlt);
        baslikPaneli.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        getContentPane().add(baslikPaneli, BorderLayout.NORTH);

        // TABLO YAPISI: ID'Yİ GİZLİ TUTMAK VEYA GÖSTERMEK GEREKİR
        String[] cols = {"ID", "İsim", "Soyisim", "Email", "Boy", "Kilo", "Yaş", "VKE"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        // ALT PANEL: YETKİLER SINIRLANDIRILDI
        JPanel btnPanel = new JPanel();
        JButton btnGuncelle = new JButton("Gelişimi Güncelle"); // İsmi değiştirildi, daha profesyonel
        JButton btnSil = new JButton("Öğrenciyi Bırak/Sil");
        JButton btnCikis = new JButton("Çıkış");
        
        btnPanel.add(btnGuncelle);
        btnPanel.add(btnSil);
        btnPanel.add(btnCikis);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

        btnGuncelle.addActionListener(e -> guncelleUye());
        btnSil.addActionListener(e -> silUye());
        btnCikis.addActionListener(e -> cikisYap());

        verileriYukle();
    }

    private void verileriYukle() {
        tableModel.setRowCount(0);
        for (Uye u : antrenor.listele()) {
            tableModel.addRow(new Object[]{ 
                u.getId(), // ID artık 0. indekste
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

    private void guncelleUye() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Güncellemek için listeden bir öğrenci seçin.");
            return;
        }
        
        // ID ARTIK DOĞRU KOLONDAN ALINIYOR
        String id = (String) tableModel.getValueAt(row, 0);
        Uye u = antrenor.bul(id); // Antrenör sınıfındaki 'bul' metodunu kullanıyoruz
        
        if (u != null) {
            KullaniciDialog dialog = new KullaniciDialog(this, u);
            dialog.setIsUyeUpdateOnly(true);
            dialog.setVisible(true);
            
            Kullanici sonuc = dialog.getKullanici(); // getKullanici ismini kontrol et
            if(sonuc != null) {
                antrenor.guncelle((Uye)sonuc);
                verileriYukle();
            }
        }
    }

    private void silUye() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Listeden çıkarmak için bir öğrenci seçin.");
            return;
        }
        
        int reply = JOptionPane.showConfirmDialog(this, 
            "Bu öğrenciyi listenizden çıkarmak istediğinize emin misiniz?\n(Bu işlem kalıcıdır ve veriler güncellenir)", 
            "Öğrenci Silme Onayı", JOptionPane.YES_NO_OPTION);
            
        if (reply == JOptionPane.YES_OPTION) {
            String id = (String) tableModel.getValueAt(row, 0);
            antrenor.sil(id);
            verileriYukle();
            // Paneldeki başlığı da güncellemek için
            setTitle("Antrenör Paneli - " + antrenor.getEmail()); 
        }
    }

    private void cikisYap() {
        this.dispose();
        new GirişEkranı().setVisible(true);
    }
}