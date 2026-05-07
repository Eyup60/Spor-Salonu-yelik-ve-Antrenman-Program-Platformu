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

    // RAPORLAMA VE ÖDEME PANELİ YAPICI METOT
    public RaporlamaVeOdemePaneli() {
        setTitle("Raporlama ve Ödeme Yönetimi");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Kapatılınca sadece bu pencere kapansın
        setLocationRelativeTo(null); // Ekranın ortasında açılsın
        setLayout(new BorderLayout());

        // Üst Kısım: Başlık
        JLabel baslik = new JLabel("Üye Finans ve Raporlama Tablosu", SwingConstants.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 18));
        baslik.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(baslik, BorderLayout.NORTH);

        // Orta Kısım: JTable Kurulumu
        String[] kolonlar = {"Üye ID", "Üye Adı Soyadı", "Paket Tipi", "Kalan Borç (TL)"};
        tabloModeli = new DefaultTableModel(kolonlar, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tablo hücreleri elle değiştirilemesin
            }
        };
        tablo = new JTable(tabloModeli);
        add(new JScrollPane(tablo), BorderLayout.CENTER);

        // --- ENTEGRASYON: Eyüp'ün listesinden verileri tabloya çekiyoruz ---
        verileriTabloyaYukle();

        // Alt Kısım: Ödeme Alma Paneli
        JPanel odemePaneli = new JPanel(new FlowLayout());
        odemePaneli.setBorder(BorderFactory.createTitledBorder("Yeni Ödeme Tahsilatı"));

        JComboBox<String> odemeTurleri = new JComboBox<>(new String[]{"Nakit", "Kredi Kartı"});
        JTextField txtTutar = new JTextField(10);
        JButton btnOde = new JButton("Ödemeyi Onayla");

        odemePaneli.add(new JLabel("Ödeme Yöntemi:"));
        odemePaneli.add(odemeTurleri);
        odemePaneli.add(new JLabel("Tutar (TL):"));
        odemePaneli.add(txtTutar);
        odemePaneli.add(btnOde);
        add(odemePaneli, BorderLayout.SOUTH);

        // Ödeme Butonu Dinleyicisi
        btnOde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double tutar = Double.parseDouble(txtTutar.getText());
                    String yontem = (String) odemeTurleri.getSelectedItem();
                    OdemeYontemi odemeIslemi;

                    // Polymorphism (Çok Biçimlilik) uygulanıyor
                    if (yontem.equals("Kredi Kartı")) {
                        odemeIslemi = new KrediKartiOdeme(tutar, "5432-XXXX-XXXX-1234");
                    } else {
                        odemeIslemi = new NakitOdeme(tutar);
                    }

                    // İşlemi gerçekleştir
                    String sonucMesaji = odemeIslemi.odemeAl();
                    JOptionPane.showMessageDialog(null, sonucMesaji, "İşlem Başarılı", JOptionPane.INFORMATION_MESSAGE);
                    txtTutar.setText(""); // Kutuyu temizle
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Lütfen geçerli sayısal bir tutar giriniz!", "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
                } catch (GecersizOdemeException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Ödeme Kuralı İhlali", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // VERİLERİ TABLOYA YÜKLE
    private void verileriTabloyaYukle() {
        tabloModeli.setRowCount(0); 
        
        // Eyüp'ün altyapısından listeyi çağırıyoruz
        List<Kullanici> tumKullanicilar = Admin.getKullanicilar(); 
        
        for (Kullanici k : tumKullanicilar) {
            // Sadece Rolü ÜYE olanları gösteriyoruz
            if (k.getRole() == Role.UYE) {
                
                // İd'nin sadece ilk 8 hanesini gösterelim ki tablo güzel dursun
                String kisaId = k.getId().length() > 8 ? k.getId().substring(0, 8) : k.getId();
                
                tabloModeli.addRow(new Object[]{
                    kisaId, 
                    k.getIsim() + " " + k.getSoyisim(),
                    "Atanmadı", // Ali'nin paket kodları eklenince burası değişecek
                    "Hesaplanmadı" // Ali'nin ücret kodları eklenince burası değişecek
                });
            }
        }
    }
}