package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;

// FİYAT GÜNCELLEME DİALOG SINIFI
// PAKET FİYATLARINI GÜNCELLEME İŞLEMLERİ İÇİN KULLANILIR
// SWING JDIALOG MİRAS ALIR
public class FiyatGuncellemeDialog extends JDialog {
    
    // SERİALİZASYON VERSİYON NUMARASI
    private static final long serialVersionUID = 1L;
    
    // FİYAT GİRİŞ ALANLARI
    private JTextField txtStandart, txtPremium, txtVip;
    
    // ONAY DURUMU TAKİP DEĞİŞKENİ
    private boolean onaylandi = false;

    // FİYAT GÜNCELLEME DİALOG YAPICI METOT
    // MODAL PENCERE OLARAK FİYAT GÜNCELLEME ARAYÜZÜ OLUŞTURUR
    public FiyatGuncellemeDialog(Frame parent) {
        super(parent, "Paket Fiyatlarını Güncelle", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);
        // IZGARA DÜZENİ AYARLA
        setLayout(new GridLayout(4, 2, 10, 10));

        // STANDART PAKET FİYAT ALANI
        add(new JLabel(" Standart Paket:"));
        txtStandart = new JTextField();
        add(txtStandart);

        // PREMIUM PAKET FİYAT ALANI
        add(new JLabel(" Premium Paket:"));
        txtPremium = new JTextField();
        add(txtPremium);

        // VIP PAKET FİYAT ALANI
        add(new JLabel(" VIP Paket:"));
        txtVip = new JTextField();
        add(txtVip);

        // GÜNCELLEME BUTONU
        // ONAY DURUMUNU AYARLA VE PENCEREYİ KAPAT
        JButton btnOnay = new JButton("Güncelle");
        btnOnay.addActionListener(e -> {
            onaylandi = true;
            dispose();
        });
        add(btnOnay);

        // İPTAL BUTONU
        // Sadece PENCEREYİ KAPAT
        JButton btnIptal = new JButton("İptal");
        btnIptal.addActionListener(e -> dispose());
        add(btnIptal);
    }

    // GETTER METOTLARI
    // KULLANICI GİRİŞLERİNİ VE ONAY DURUMUNU DÖNDÜRÜR
    
    // ONAYLANDI MI KONTROLÜ
    public boolean isOnaylandi() { return onaylandi; }
    
    // STANDART FİYAT GETİR
    public String getStandartFiyat() { return txtStandart.getText(); }
    
    // PREMIUM FİYAT GETİR
    public String getPremiumFiyat() { return txtPremium.getText(); }
    
    // VIP FİYAT GETİR
    public String getVipFiyat() { return txtVip.getText(); }
}