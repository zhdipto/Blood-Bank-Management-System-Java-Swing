import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLogin extends JFrame implements ActionListener {

    JLabel userName;
    JLabel password;
    JLabel header;
    JTextField userText;
    JPasswordField pass;
    JButton button;
    ImageIcon icon = new ImageIcon("icon.png");

    AdminLogin() {
        this.setTitle("Blood Bank Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.RED);
        this.setIconImage(icon.getImage());

        header = new JLabel("Blood Bank Management System");
        header.setBounds(190, 1, 370, 150);
        header.setFont(new Font("Lato", Font.PLAIN, 20));
        header.setForeground(Color.WHITE);
        this.add(header);

        userText = new JTextField();
        userText.setFont(new Font(null, Font.BOLD, 16));
        userText.setBounds(300, 125, 200, 30);
        this.add(userText);

        userName = new JLabel("Username");
        userName.setBounds(190, 110, 120, 50);
        userName.setFont(new Font(null, Font.PLAIN, 20));
        userName.setForeground(Color.BLACK);
        this.add(userName);

        button = new JButton("login");
        button.setBounds(400, 230, 100, 25);
        button.setFocusable(false);
        this.add(button);
        button.addActionListener(this);

        pass = new JPasswordField();
        pass.setFont(new Font(null, Font.BOLD, 16));
        this.add(pass);
        pass.setVisible(true);
        pass.setBounds(300, 175, 200, 30);

        password = new JLabel("Password");
        password.setBounds(190, 165, 100, 50);
        password.setFont(new Font(null, Font.PLAIN, 20));
        password.setForeground(Color.BLACK);
        this.add(password);

        password = new JLabel("Password");
        password.setBounds(190, 165, 100, 50);
        password.setFont(new Font(null, Font.PLAIN, 20));
        password.setForeground(Color.BLACK);
        password.setVisible(false);
        this.add(password);

        this.setVisible(true);
        this.pack();
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == button) {
            if (userText.getText().equals("admin") && pass.getText().equals("admin")) {
                this.dispose();
                new AddDonorDetails();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password", "title",
                        JOptionPane.WARNING_MESSAGE);
                this.dispose();
                new HomeScreen();
            }
        }
    }

}
