package sporSalonuÜyelikVeAntrenmanProgramı;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Kullanıcının atanan programını GUI üzerinde gösterir
public class AntrenmanProgramiBilgileriDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public AntrenmanProgramiBilgileriDialog(JFrame parent, Uye uye) {
        super(parent, "Atanan Antrenman Programı", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10,10));

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> lst = new JList<>(model);
        lst.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane sp = new JScrollPane(lst);
        add(sp, BorderLayout.CENTER);

        ProgramAtamaYöneticisi yonetici = new ProgramAtamaYöneticisi();
        List<Antrenman> program = yonetici.programGetir(uye);

        if (program == null || program.isEmpty()) {
            model.addElement("Bu üyeye henüz bir program atanmamış.");
        } else {
            double toplam = 0.0;
            for (int i = 0; i < program.size(); i++) {
                Antrenman a = program.get(i);
                double k = a.kaloriHesapla(uye);
                toplam += k;
                model.addElement(String.format("%d. %s - %.2f kcal", i+1, a.toString(), k));
            }
            model.addElement("----------------------------------------");
            model.addElement(String.format("Tahmini Günlük Toplam Kalori: %.2f kcal", toplam));
        }

        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnKapat = new JButton("Kapat");
        btnKapat.addActionListener(e -> dispose());
        pnl.add(btnKapat);
        add(pnl, BorderLayout.SOUTH);
    }
}
