import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonorList extends JFrame implements ActionListener {
    public Object k;
    public String b;
    JLabel header;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scPane;
    JButton button;
    static String dotNo;
    ImageIcon icon = new ImageIcon("icon.png");

    DonorList(Object k, String b) {
        this.k = k;
        this.b = b;
        this.setTitle("Blood Bank Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.red);
        this.setIconImage(icon.getImage());

        header = new JLabel("Blood Bank Management System");
        header.setPreferredSize(new Dimension(500, 50));
        header.setFont(new Font("Lato", Font.BOLD, 27));
        header.setForeground(Color.WHITE);

        panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        panel1.setBounds(0, 0, 700, 60);
        panel1.add(header);
        this.add(panel1);

        panel2 = new JPanel();
        panel2.setBackground(Color.RED);
        panel2.setBounds(0, 60, 700, 275);
        this.add(panel2);

        panel3 = new JPanel();
        panel3.setBackground(Color.BLUE);
        panel3.setBounds(0, 335, 700, 45);
        this.add(panel3);

        table = new JTable();
        dotNo = k.toString();
        table.setEnabled(false);
        table.setBackground(Color.RED);
        table.setFont(new Font(null, Font.BOLD, 13));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIU_Blood_Bank", "dipto",
                    "secure");
            Statement st = con.createStatement();
            String query = "SELECT * FROM BloodBankDetails WHERE city='" + b + "' AND bloodGrp = '" + dotNo + "'";
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);
            String id, fn, pn, g, c, bg;
            while (rs.next()) {
                id = rs.getString(1);
                fn = rs.getString(2);
                pn = rs.getString(3);
                g = rs.getString(4);
                c = rs.getString(5);
                bg = rs.getString(6);
                String[] row = { id, fn, pn, g, c, bg };
                model.addRow(row);
            }
            scPane = new JScrollPane(table);
            scPane.setPreferredSize(new Dimension(700, 275));
            panel2.add(scPane);
            System.out.println("database connected");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("database not connected");
        }

        button = new JButton("Back");
        button.setPreferredSize(new Dimension(100, 27));
        button.setFocusable(false);
        panel3.add(button);
        button.addActionListener(this);

        this.setLayout(null);
        this.setSize(700, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == button) {
            this.dispose();
            new HomeScreen();
        }
    }

}
