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
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        JPanel baslikPaneli = new JPanel(new GridLayout(2, 1));
        JLabel lblTitle = new JLabel("Atanmış Öğrenciler", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel lblAlt = new JLabel("Uzmanlık: " + antrenor.getUzmanlıkAlanı(), SwingConstants.CENTER);
        
        baslikPaneli.add(lblTitle);
        baslikPaneli.add(lblAlt);
        baslikPaneli.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(baslikPaneli, BorderLayout.NORTH);

        String[] cols = {"Isim","Soyisim", "Email", "Boy", "Kilo", "Yaş", "VKE"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnAta = new JButton("Üye Ata");
        JButton btnGuncelle = new JButton("Üyeyi Güncelle");
        JButton btnSil = new JButton("Çıkar");
        JButton btnCikis = new JButton("Çıkış Yap");

        btnPanel.add(btnAta);
        btnPanel.add(btnGuncelle);
        btnPanel.add(btnSil);
        btnPanel.add(btnCikis);
        add(btnPanel, BorderLayout.SOUTH);

        btnAta.addActionListener(e -> uyeAta());
        btnGuncelle.addActionListener(e -> guncelleUye());
        btnSil.addActionListener(e -> silUye());
        btnCikis.addActionListener(e -> cikisYap());

        verileriYukle();
    }

    private void verileriYukle() {
        tableModel.setRowCount(0);
        for (Uye u : antrenor.listele()) {
            tableModel.addRow(new Object[]{ 
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

    private void uyeAta() {
        List<Uye> potansiyel = new ArrayList<>();
        for(Kullanici k : Admin.getKullanicilar()) {
            if(k instanceof Uye && !antrenor.listele().contains(k)) {
                potansiyel.add((Uye) k);
            }
        }
        
        if (potansiyel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Atanabilecek boşta veya listende olmayan üye yok.");
            return;
        }

        Uye[] uyeler = potansiyel.toArray(new Uye[0]);
        Uye secilen = (Uye) JOptionPane.showInputDialog(this, "Listene eklenecek üyeyi seç:", "Üye Ata", 
                                  JOptionPane.QUESTION_MESSAGE, null, uyeler, uyeler[0]);
                                  
        if (secilen != null) {
            antrenor.ekle(secilen);
            verileriYukle();
        }
    }

    private void guncelleUye() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Güncellemek için bir öğrenci seçin.");
            return;
        }
        
        String id = (String) tableModel.getValueAt(row, 0);
        Uye u = null;
        for(Uye uye : antrenor.listele()) {
            if (uye.getId().equals(id)) {
                 u = uye;
                 break;
            }
        }
        
        if (u != null) {
            KullaniciDialog dialog = new KullaniciDialog(this, u);
            dialog.setIsUyeUpdateOnly(true);
            dialog.setVisible(true);
            
            Kullanici sonuc = dialog.getKullanici();
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
        
        int reply = JOptionPane.showConfirmDialog(this, "Bu öğrenciyi antrenman programınızdan çıkarmak istiyor musunuz?", "Çıkar", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            String id = (String) tableModel.getValueAt(row, 0);
            antrenor.sil(id);
            verileriYukle();
        }
    }

    private void cikisYap() {
        this.dispose();
        new GirişEkranı().setVisible(true);
    }
}
