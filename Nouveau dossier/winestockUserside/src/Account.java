import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Account extends JFrame {
    private JLabel Profil;
    private JButton changePasswordButton;
    private JButton logOutButton;
    private JLabel nametext;
    private JLabel nameLabel;
    private JLabel usernametxt;
    private JPanel panel;
    private JLabel usernameLabel;
    private JButton button1;

    public Account(String title ,String username){
        super(title);
        panel.setBackground(Color.GRAY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/Login/"+username);
        JSONArray array = new JSONArray(response);
        nametext.setText(array.getJSONObject(0).getString("name"));
        usernametxt.setText(username);
        setContentPane(panel);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new Homepage("homepage",username);
                ui.setVisible(true);
                ui.setSize(600,800);
                ui.setLocation(500,0);
                dispose();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int a = JOptionPane.showConfirmDialog(logOutButton, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Login lo = new Login("Login");
                    lo.setSize(600, 800);
                    lo.setLocation(500, 0);
                    lo.setVisible(true);
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                }
                else {
                    dispose();
                    Account ai = new Account("Account",username);
                    ai.setSize(600, 800);
                    ai.setLocation(500, 0);
                    ai.setVisible(true);
                }





            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame change = new ChangePass("Change-Password",username);
                change.setVisible(true);
                change.setSize(600, 800);
                change.setLocation(500,0);
                dispose();
            }
        });
    }


    public static void main(String[] args) {
        JFrame ui = new Account("Account","Stijn@hotmail.com");
        ui.setSize(600, 800);
        ui.setLocation(500, 0);
        ui.setVisible(true);
    }
}

