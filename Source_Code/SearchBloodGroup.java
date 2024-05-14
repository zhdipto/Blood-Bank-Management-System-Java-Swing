import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBloodGroup extends JFrame implements ActionListener {
    JLabel header;
    JPanel panel1;
    JPanel panel2;
    JPanel p3;
    JPanel p4;
    JLabel bloodGrp;
    JLabel city;
    JTextField cityN;
    JComboBox blood;
    JButton search;
    JButton close;
    ImageIcon icon = new ImageIcon("icon.png");

    SearchBloodGroup() {
        this.setTitle("Blood Bank Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.red);
        this.setIconImage(icon.getImage());

        header = new JLabel("Blood Bank Management System");
        header.setPreferredSize(new Dimension(500, 120));
        header.setFont(new Font("Lato", Font.BOLD, 27));
        header.setForeground(Color.WHITE);

        panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        panel1.setBounds(0, 0, 700, 125);
        panel1.add(header);
        this.add(panel1);

        p3 = new JPanel();
        p3.setBackground(Color.RED);
        p3.setBounds(0, 165, 350, 40);
        this.add(p3);

        p4 = new JPanel();
        p4.setBackground(Color.RED);
        p4.setBounds(350, 165, 350, 40);
        this.add(p4);

        bloodGrp = new JLabel("Select Blood Group");
        bloodGrp.setFont(new Font(null, Font.BOLD, 14));
        bloodGrp.setForeground(Color.BLACK);

        String[] bloodG = { "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-" };
        blood = new JComboBox(bloodG);
        blood.addActionListener(this);
        p4.add(blood);
        p4.add(bloodGrp);

        city = new JLabel("City");
        city.setFont(new Font(null, Font.BOLD, 14));
        city.setForeground(Color.BLACK);
        p3.add(city);

        cityN = new JTextField();
        cityN.setFont(new Font(null, Font.BOLD, 16));
        cityN.setPreferredSize(new Dimension(150, 25));
        p3.add(cityN);

        panel2 = new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setBounds(0, 290, 700, 60);
        this.add(panel2);

        search = new JButton("Search");
        search.setPreferredSize(new Dimension(90, 30));
        search.setFocusable(false);
        panel2.add(search);
        search.addActionListener(this);

        close = new JButton("Close");
        close.setPreferredSize(new Dimension(90, 30));
        close.setFocusable(false);
        panel2.add(close);
        close.addActionListener(this);

        this.setLayout(null);
        this.setSize(700, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == close) {
            this.dispose();
            new HomeScreen();
        }
        if (a.getSource() == search) {
            this.dispose();
            Object bo = blood.getSelectedItem();
            String s = cityN.getText();
            new DonorList(bo, s);
        }
    }

}
