
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
    private JTextField textField1;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField1;
    private JPanel panel;
    private JLabel image;

    public Login(String title) {

        super(title);

        panel.setBackground(Color.GRAY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panel);



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  username=textField1.getText();
                String password= String.valueOf(passwordField1.getPassword());
                DataBase db = new DataBase();
                String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/Login/"+username);
                JSONArray array = new JSONArray(response);
                JSONObject obj1 = array.getJSONObject(0);
                JSONObject obj2 = array.getJSONObject(0);
                if (username.equals(obj2.getString("username")) &&password.equals(obj1.getString("password")) ){
                                dispose();
                                 Homepage home= new Homepage("homepage",username);
                                 home.setSize(600,800);
                                 home.setLocation(500,0);
                                home.setVisible(true);
                                JOptionPane.showMessageDialog(loginButton, "You have successfully logged in");
                } else {
                                JOptionPane.showMessageDialog(loginButton, "Wrong Username & Password");
                            }
            }
        });




    }

    public static void main(String[] args) {
        JFrame ui = new Login("Login");
        ui.setSize(600, 800);
        ui.setLocation(500, 0);
        ui.setVisible(true);
    }
}

