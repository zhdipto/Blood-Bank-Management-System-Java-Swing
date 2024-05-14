import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteDonorDetails extends JFrame implements ActionListener {
    JLabel header;
    JPanel panel1;
    JPanel panel2;
    JPanel p1;
    JPanel p2;
    JPanel p3;
    JPanel p4;
    JPanel p5;
    JPanel p6;
    JLabel fullName;
    JLabel donorId;
    JLabel phoneNum;
    JLabel bloodGrp;
    JLabel gender;
    JLabel city;
    JLabel id;
    JTextField fullN;
    JTextField phone;
    JTextField cityN;
    JComboBox genderC;
    JComboBox blood;
    JButton Delete;
    JButton Return;
    int dId;
    String fName;
    int phNum;
    String gen;
    String cityUp;
    String blG;
    int i;
    ImageIcon icon = new ImageIcon("icon.png");

    DeleteDonorDetails(int dId) {
        this.dId = dId;
        this.setIconImage(icon.getImage());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIU_Blood_Bank", "dipto",
                    "secure");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BloodBankDetails WHERE donorId =" + dId + ";");
            i = 0;
            while (rs.next()) {
                fName = rs.getString("fullName");
                phNum = rs.getInt("phoneNum");
                gen = rs.getString("gender");
                cityUp = rs.getString("city");
                blG = rs.getString("bloodGrp");
                i++;
            }
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "Sorry no data Available");
            }
            con.close();
            st.close();
            System.out.println("database connected");
        } catch (Exception e) {
            System.out.println("database not connected");
        }
        if (i == 0) {
            this.dispose();
            new SearchForDelete();
        } else {
            this.setTitle("Blood Bank Management System");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.getContentPane().setBackground(Color.red);

            header = new JLabel("Blood Bank Management System");
            header.setPreferredSize(new Dimension(500, 120));
            header.setFont(new Font("Lato", Font.BOLD, 27));
            header.setForeground(Color.WHITE);

            panel1 = new JPanel();
            panel1.setBackground(Color.RED);
            panel1.setBounds(0, 0, 700, 125);
            panel1.add(header);
            this.add(panel1);

            p1 = new JPanel();
            p1.setBackground(Color.RED);
            p1.setBounds(0, 125, 350, 40);
            this.add(p1);

            p2 = new JPanel();
            p2.setBackground(Color.RED);
            p2.setBounds(350, 125, 350, 40);
            this.add(p2);

            p3 = new JPanel();
            p3.setBackground(Color.RED);
            p3.setBounds(0, 165, 350, 40);
            this.add(p3);

            p4 = new JPanel();
            p4.setBackground(Color.RED);
            p4.setBounds(350, 165, 350, 40);
            this.add(p4);

            p5 = new JPanel();
            p5.setBackground(Color.RED);
            p5.setBounds(0, 205, 350, 40);
            this.add(p5);

            p6 = new JPanel();
            p6.setBackground(Color.RED);
            p6.setBounds(350, 205, 350, 40);
            this.add(p6);

            fullName = new JLabel(" Full name");
            fullName.setFont(new Font(null, Font.BOLD, 16));
            fullName.setForeground(Color.WHITE);
            p1.add(fullName);

            fullN = new JTextField();
            fullN.setPreferredSize(new Dimension(150, 25));
            fullN.setFont(new Font(null, Font.BOLD, 15));
            fullN.setText(fName);
            fullN.setEditable(false);
            p1.add(fullN);

            donorId = new JLabel("Donor ID: ");
            donorId.setFont(new Font(null, Font.BOLD, 18));
            donorId.setForeground(Color.WHITE);
            p2.add(donorId);

            phoneNum = new JLabel("Phone NO");
            phoneNum.setFont(new Font(null, Font.BOLD, 16));
            phoneNum.setForeground(Color.WHITE);
            p3.add(phoneNum);

            phone = new JTextField();
            phone.setPreferredSize(new Dimension(150, 25));
            phone.setFont(new Font(null, Font.BOLD, 15));
            String p = String.valueOf(phNum);
            phone.setText(p);
            phone.setEditable(false);
            p3.add(phone);

            bloodGrp = new JLabel("Select Blood Group");
            bloodGrp.setFont(new Font(null, Font.BOLD, 16));
            bloodGrp.setForeground(Color.WHITE);
            p4.add(bloodGrp);

            String[] bloodG = { blG };
            blood = new JComboBox(bloodG);
            blood.setEditable(false);
            p4.add(blood);

            gender = new JLabel("  Gender  ");
            gender.setFont(new Font(null, Font.BOLD, 16));
            gender.setForeground(Color.WHITE);
            p5.add(gender);

            String[] sex = { gen };
            genderC = new JComboBox(sex);
            genderC.setPreferredSize(new Dimension(150, 25));
            genderC.setEditable(false);
            p5.add(genderC);

            city = new JLabel("City");
            city.setFont(new Font(null, Font.BOLD, 16));
            city.setForeground(Color.WHITE);
            p6.add(city);

            cityN = new JTextField();
            cityN.setPreferredSize(new Dimension(150, 25));
            cityN.setFont(new Font(null, Font.BOLD, 15));
            cityN.setText(cityUp);
            cityN.setEditable(false);
            p6.add(cityN);

            panel2 = new JPanel();
            panel2.setBackground(Color.RED);
            panel2.setBounds(0, 290, 700, 60);
            this.add(panel2);

            String str = String.valueOf(dId);
            id = new JLabel(str);
            id.setFont(new Font(null, Font.BOLD, 18));
            id.setForeground(Color.BLACK);
            p2.add(id);

            Delete = new JButton("Delete");
            Delete.setPreferredSize(new Dimension(90, 30));
            Delete.setFocusable(false);
            panel2.add(Delete);
            Delete.addActionListener(this);

            Return = new JButton("Return");
            Return.setPreferredSize(new Dimension(90, 30));
            Return.setFocusable(false);
            panel2.add(Return);
            Return.addActionListener(this);

            this.setLayout(null);
            this.setSize(700, 400);
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == Delete) {
            System.out.println("DELETE FROM BloodBankDetails WHERE donorId = " + dId + ";");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIU_Blood_Bank", "dipto",
                        "secure");
                PreparedStatement ps = con
                        .prepareStatement("DELETE FROM BloodBankDetails WHERE donorId = " + dId + ";");
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Deleted Succesfully");
                con.close();
                ps.close();
                System.out.println("database connected");
                this.dispose();
                new SearchForDelete();
            } catch (Exception e) {
                System.out.println("database not connected");
            }
        }
        if (a.getSource() == Return) {
            this.dispose();
            new HomeScreen();
        }
    }

}