import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen extends JFrame implements ActionListener {

    JLabel header;
    JButton updateDonor;
    JButton addDonor;
    JButton deleteDonor;
    JButton donorList;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    ImageIcon icon = new ImageIcon("icon.png");

    HomeScreen() {
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

        panel2 = new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setBounds(0, 125, 700, 130);
        this.add(panel2);

        panel3 = new JPanel();
        panel3.setBackground(Color.RED);
        panel3.setBounds(0, 250, 700, 125);
        this.add(panel3);

        donorList = new JButton("Donor List");
        donorList.setPreferredSize(new Dimension(160, 120));
        donorList.setFocusable(false);
        panel2.add(donorList);
        donorList.addActionListener(this);

        addDonor = new JButton("Add Donor");
        addDonor.setPreferredSize(new Dimension(160, 120));
        addDonor.setFocusable(false);
        panel2.add(addDonor);
        addDonor.addActionListener(this);

        updateDonor = new JButton("Edit Donor Info");
        updateDonor.setPreferredSize(new Dimension(160, 120));
        updateDonor.setFocusable(false);
        panel2.add(updateDonor);
        updateDonor.addActionListener(this);

        deleteDonor = new JButton("Delete Donor Info");
        deleteDonor.setPreferredSize(new Dimension(160, 120));
        deleteDonor.setFocusable(false);
        panel2.add(deleteDonor);
        deleteDonor.addActionListener(this);

        this.setLayout(null);
        this.setSize(700, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == donorList) {
            this.dispose();
            new SearchBloodGroup();
        }
        if (arg0.getSource() == addDonor) {
            this.dispose();
            new AdminLogin();
        }
        if (arg0.getSource() == updateDonor) {
            this.dispose();
            new AdminLogin1();
        }
        if (arg0.getSource() == deleteDonor) {
            this.dispose();
            new AdminLogin2();
        }
    }

}
