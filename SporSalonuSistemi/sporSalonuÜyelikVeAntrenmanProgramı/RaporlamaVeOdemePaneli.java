package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// RAPORLAMA VE ÖDEME PANELİ SINIFI
public class RaporlamaVeOdemePaneli extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tablo;
    private DefaultTableModel tabloModeli;

    public RaporlamaVeOdemePaneli() {
        setTitle("Raporlama ve Ödeme Yönetimi");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());
        
        JLabel baslik = new JLabel("Üye Finans ve Raporlama Tablosu", SwingConstants.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 18));
        baslik.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(baslik, BorderLayout.NORTH);

        String[] kolonlar = {"Üye ID", "Üye Adı Soyadı", "Paket Tipi"};
        tabloModeli = new DefaultTableModel(kolonlar, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tablo = new JTable(tabloModeli);
        add(new JScrollPane(tablo), BorderLayout.CENTER);

        verileriTabloyaYukle();


    }

    private void verileriTabloyaYukle() {
        tabloModeli.setRowCount(0); 
        
        List<Kullanici> tumKullanicilar = Admin.getKullanicilar(); 
        
        for (Kullanici k : tumKullanicilar) {
            if (k.getRole() == Role.UYE) {
                Uye uye = (Uye) k; 
                
                String kisaId = uye.getId().length() > 8 ? uye.getId().substring(0, 8) : uye.getId();

                String paketAdi = (uye.getPaket() != null) ? uye.getPaket().getPaketAdi() : "Paket Seçilmedi";
                tabloModeli.addRow(new Object[]{
                    kisaId, 
                    uye.getIsim() + " " + uye.getSoyisim(),
                    paketAdi, 
                });
            }
        }
    }
}