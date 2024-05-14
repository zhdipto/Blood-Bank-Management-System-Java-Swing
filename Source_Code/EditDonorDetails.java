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

public class EditDonorDetails extends JFrame implements ActionListener {
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
    JButton submit;
    JButton close;
    int upId;
    String fName;
    int phNum;
    String gen;
    String cityUp;
    String blG;
    int i;
    ImageIcon icon = new ImageIcon("icon.png");

    EditDonorDetails(int upId) {
        this.upId = upId;
        this.setIconImage(icon.getImage());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIU_Blood_Bank", "dipto",
                    "secure");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BloodBankDetails WHERE donorId =" + upId + ";");
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
            new SearchForEdit();
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
            p3.add(phone);

            bloodGrp = new JLabel("Select Blood Group");
            bloodGrp.setFont(new Font(null, Font.BOLD, 16));
            bloodGrp.setForeground(Color.WHITE);
            p4.add(bloodGrp);

            String[] bloodG = { "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-" };
            blood = new JComboBox(bloodG);
            blood.setSelectedItem(blG);
            p4.add(blood);

            gender = new JLabel("  Gender  ");
            gender.setFont(new Font(null, Font.BOLD, 16));
            gender.setForeground(Color.WHITE);
            p5.add(gender);

            String[] sex = { "male", "female" };
            genderC = new JComboBox(sex);
            genderC.setPreferredSize(new Dimension(150, 25));
            genderC.setSelectedItem(gen);
            p5.add(genderC);

            city = new JLabel("City");
            city.setFont(new Font(null, Font.BOLD, 16));
            city.setForeground(Color.WHITE);
            p6.add(city);

            cityN = new JTextField();
            cityN.setPreferredSize(new Dimension(150, 25));
            cityN.setFont(new Font(null, Font.BOLD, 15));
            cityN.setText(cityUp);
            p6.add(cityN);

            panel2 = new JPanel();
            panel2.setBackground(Color.RED);
            panel2.setBounds(0, 290, 700, 60);
            this.add(panel2);

            String str = String.valueOf(upId);
            id = new JLabel(str);
            id.setFont(new Font(null, Font.BOLD, 18));
            id.setForeground(Color.BLACK);
            p2.add(id);

            submit = new JButton("Submit");
            submit.setPreferredSize(new Dimension(90, 30));
            submit.setFocusable(false);
            panel2.add(submit);
            submit.addActionListener(this);

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
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == submit) {
            String fn = fullN.getText();
            String ph = phone.getText();
            int p = Integer.parseInt(ph);
            String g = String.valueOf(genderC.getSelectedItem());
            String c = cityN.getText();
            String b = String.valueOf(blood.getSelectedItem());
            System.out.println("UPDATE BloodBankDetails SET fullName ='" + fn + "',phoneNum = " + p + ",gender = '" + g
                    + "',city = '" + c + "',bloodGrp = '" + b + "'WHERE donorId = " + upId + ";");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIU_Blood_Bank", "dipto",
                        "secure");
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE BloodBankDetails SET fullName ='" + fn + "',phoneNum = " + p + ",gender = '" + g
                                + "',city = '" + c + "',bloodGrp = '" + b + "'WHERE donorId = " + upId + ";");
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "updated Succesfully");
                con.close();
                ps.close();
                System.out.println("database connected");
                this.dispose();
                new SearchForEdit();
            } catch (Exception e) {
                System.out.println("database not connected");
            }
        }
        if (a.getSource() == close) {
            this.dispose();
            new HomeScreen();
        }
    }

}