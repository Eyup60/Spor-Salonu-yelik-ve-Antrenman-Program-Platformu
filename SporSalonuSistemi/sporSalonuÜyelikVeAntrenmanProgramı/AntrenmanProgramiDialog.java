package sporSalonuÜyelikVeAntrenmanProgramı;

import java.awt.*;
import javax.swing.*;

// Basit antrenman programı gösteren dialog
public class AntrenmanProgramiDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public AntrenmanProgramiDialog(JFrame parent, Uye uye) {
        super(parent, "Antrenman Programı", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Çok basit örnek program: kullanıcının yaş ve kilo bilgisine göre küçük farklılıklar
        StringBuilder sb = new StringBuilder();
        sb.append("--- Haftalık Antrenman Programı ---\n\n");
        sb.append("Pazartesi: Göğüs + Kardiyo\n");
        sb.append("Salı: Sırt + Core\n");
        sb.append("Çarşamba: Dinlenme veya Hafif Aktiviteler\n");
        sb.append("Perşembe: Bacaklar + Kardiyo\n");
        sb.append("Cuma: Omuz + Kol\n");
        sb.append("Cumartesi: Tam Vücut Hafif Ağırlıklar\n");
        sb.append("Pazar: Dinlenme\n\n");

        sb.append("Öneriler:\n");
        if (uye.getYas() > 50) {
            sb.append("- Yaşınıza uygun düşük yoğunluklu kardiyo tercih edin.\n");
        } else {
            sb.append("- Haftada en az 3 kez kuvvet antrenmanı yapın.\n");
        }

        sb.append("- Antrenman öncesi ısınma, sonrası esneme yapınız.\n");

        area.setText(sb.toString());

        getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnKapat = new JButton("Kapat");
        btnKapat.addActionListener(e -> dispose());
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnl.add(btnKapat);
        getContentPane().add(pnl, BorderLayout.SOUTH);
    }
}
