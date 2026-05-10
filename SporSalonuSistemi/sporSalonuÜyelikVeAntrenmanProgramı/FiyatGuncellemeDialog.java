package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;

// Fiyatları güncellemek için açılan küçük dialog penceresi
public class FiyatGuncellemeDialog extends JDialog {
    
    // Kullanıcının yeni fiyatları gireceği alanlar
    private JTextField txtStandart, txtPremium, txtVip;
    
    // İşlemin iptal mi yoksa onay mı edildiğini tutan kontrol değişkeni
    private boolean onaylandi = false;

    public FiyatGuncellemeDialog(Frame parent) {
        // 'true' parametresi ile pencereyi modal yaptık (kapanana kadar arkaya tıklanamaz)
        super(parent, "Paket Fiyatlarını Güncelle", true);
        setSize(300, 250);
        setLocationRelativeTo(parent); 
        
        // Elemanları düzenli dizmek için GridLayout (4 satır, 2 sütun) kullandım
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" Standart Paket:"));
        txtStandart = new JTextField();
        add(txtStandart);

        add(new JLabel(" Premium Paket:"));
        txtPremium = new JTextField();
        add(txtPremium);

        add(new JLabel(" VIP Paket:"));
        txtVip = new JTextField();
        add(txtVip);

        // Onaylandığında değişkeni true yapıp ekranı kapatıyoruz
        JButton btnOnay = new JButton("Güncelle");
        btnOnay.addActionListener(e -> {
            onaylandi = true; 
            dispose(); // Pencereyi kapatır
        });
        add(btnOnay);

        // İptale basılırsa sadece ekranı kapat (onaylandi false kalmaya devam eder)
        JButton btnIptal = new JButton("İptal");
        btnIptal.addActionListener(e -> dispose());
        add(btnIptal);
    }

    // --- Getter Metotları ---
    // Dialog kapandıktan sonra girilen verilere dışarıdan ulaşabilmek için
    
    public boolean isOnaylandi() { return onaylandi; }
    public String getStandartFiyat() { return txtStandart.getText(); }
    public String getPremiumFiyat() { return txtPremium.getText(); }
    public String getVipFiyat() { return txtVip.getText(); }
}