package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;

public class FiyatGuncellemeDialog extends JDialog {
    private JTextField txtStandart, txtPremium, txtVip;
    private boolean onaylandi = false;

    public FiyatGuncellemeDialog(Frame parent) {
        super(parent, "Paket Fiyatlarını Güncelle", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);
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

        JButton btnOnay = new JButton("Güncelle");
        btnOnay.addActionListener(e -> {
            onaylandi = true;
            dispose();
        });
        add(btnOnay);

        JButton btnIptal = new JButton("İptal");
        btnIptal.addActionListener(e -> dispose());
        add(btnIptal);
    }

    public boolean isOnaylandi() { return onaylandi; }
    public String getStandartFiyat() { return txtStandart.getText(); }
    public String getPremiumFiyat() { return txtPremium.getText(); }
    public String getVipFiyat() { return txtVip.getText(); }
}