import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForEdit extends JFrame implements ActionListener {
    JLabel header;
    JPanel panel1;
    JPanel panel2;
    JPanel p1;
    JPanel p2;
    JLabel info;
    JLabel up;
    JTextField update;
    JButton search;
    JButton close;
    ImageIcon icon = new ImageIcon("icon.png");

    SearchForEdit() {
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

        info = new JLabel("Update Donor Information");
        info.setPreferredSize(new Dimension(290, 35));
        info.setFont(new Font("Lato", Font.BOLD, 19));
        info.setForeground(Color.WHITE);

        p1 = new JPanel();
        p1.setBackground(Color.RED);
        p1.setBounds(200, 150, 300, 40);
        this.add(p1);
        p1.add(info);

        up = new JLabel("Donor ID:");
        up.setPreferredSize(new Dimension(100, 35));
        up.setFont(new Font(null, Font.BOLD, 15));
        up.setForeground(Color.WHITE);

        p2 = new JPanel();
        p2.setBackground(Color.RED);
        p2.setBounds(200, 200, 300, 40);
        this.add(p2);
        p2.add(up);

        panel2 = new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setBounds(0, 290, 700, 60);
        this.add(panel2);

        update = new JTextField();
        update.setPreferredSize(new Dimension(180, 30));
        update.setFont(new Font(null, Font.BOLD, 15));
        p2.add(update);

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

            int i = Integer.valueOf(update.getText());
            this.dispose();
            new EditDonorDetails(i);
        }
    }

}
