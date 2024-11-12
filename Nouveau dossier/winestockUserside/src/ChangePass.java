import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePass extends JFrame{
    private JButton enterButton;
    private JLabel newPasswordLabel;
    private JPanel panel;
    private JLabel usernameLabel;
    private JButton button1;
    private JPasswordField Enterpass;
    private JLabel Jlable1;

    public ChangePass(String title,String username){
        super(title);
        panel.setBackground(Color.GRAY);
        setContentPane(panel);
        Jlable1.setText(username);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new Account("Account",username);
                ui.setSize(600, 800);
                ui.setLocation(500, 0);
                ui.setVisible(true);
                dispose();
            }
        });
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase db = new DataBase();
                String newPass=String.valueOf(Enterpass.getPassword());
                String response1 = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/Login/"+username);
                JSONArray array = new JSONArray(response1);
                JSONObject obj1 = array.getJSONObject(0);
                if(username.equals(obj1.getString("username"))) {
                     db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/ChangePass/" + newPass + "/" + username);

                    JOptionPane.showMessageDialog(enterButton, "Password has been successfully changed");
                }
                else{

                    JOptionPane.showMessageDialog(enterButton, "Wrong username");
                }


            }
        });

    }
    public static void main(String[] args) {
        JFrame ui = new ChangePass("Password Change","Stijn@hotmail.com");
        ui.setSize(600, 800);
        ui.setLocation(500, 0);
        ui.setVisible(true);
    }
}
