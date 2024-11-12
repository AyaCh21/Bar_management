import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame{
    private JButton settingButton;
    private JButton orderlist;
    private JButton storageList;
    private JButton warningButton;
    private JButton commentButton;
    private JPanel homepagePanel;
    private JLabel bar_image;
    private JButton profile;
    private JLabel BAR_MANAGER;
    private JLabel peopleLable;


    public Homepage(String title,String username)
    {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //homepagePanel.setBackground(Color.white);
        orderlist.setText("Order List");
        //bar. setIcon(new ImageIcon(Homepage.class.getResource("/com/Bar_image.jpg")));
        profile.setLocation(200,150);
        profile.setSize(100, 100);
        BAR_MANAGER.setLocation(120,50);
        BAR_MANAGER.setSize(300, 50);
        profile. setIcon(new ImageIcon(Homepage.class.getResource("/com/profile_icon.png")));

        warningButton. setIcon(new ImageIcon(Homepage.class.getResource("/com/notification_icon.png")));
        commentButton. setIcon(new ImageIcon(Homepage.class.getResource("/com/Notes_icon.jpg")));
        settingButton. setIcon(new ImageIcon(Homepage.class.getResource("/com/settings_icon.png")));
        settingButton.setBackground(Color.WHITE);
        warningButton.setBackground(Color.WHITE);
        commentButton.setBackground(Color.WHITE);
        storageList.setBackground(Color.WHITE);
        orderlist.setBackground(Color.WHITE);
        homepagePanel.setBackground(Color.GRAY);
        storageList.setIcon(new ImageIcon(Homepage.class.getResource("/com/list_icon.png")));
        orderlist.setIcon(new ImageIcon(Homepage.class.getResource("/com/list_icon.png")));
        peopleLable.setIcon(new ImageIcon(Homepage.class.getResource("/com/people_icon.png")));
        warningButton.setSize(80,80);
        storageList.setText("Storage List");

        bar_image.add(profile);
        bar_image.add(BAR_MANAGER);

        orderlist.setSize(400,400);
        storageList.setSize(400,400);
        setContentPane(homepagePanel);


        //check low stock
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);
        JSONObject obj = new JSONObject();

        commentButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                JFrame comment =  new Comment("comment",username);
                comment.setVisible(true);
                comment.setSize(600,800);
                comment.setLocation(500,0);
                dispose();
            }
        });
        warningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 3; i++) {
                    JFrame lowStock = new LowStock(username);
                    lowStock.setSize(400,200);
                    lowStock.setLocationRelativeTo(storageList);
                    lowStock.setVisible(true);
                    lowStock.setAlwaysOnTop(true);
                    break;
                }
            }
        });
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame setting =  new Set("setting",username);
                setting.setVisible(true);
                setting.setSize(600,800);
                setting.setLocation(500,0);
                dispose();
            }
        });
        //get number of people inside
        response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getPeople");
        JSONArray peoplearray = new JSONArray(response);
        JSONObject peopleobj = peoplearray.getJSONObject(0);
        int people = peopleobj.getInt("number");
        peopleLable.setText("there are currently "+people+" people inside ");

        orderlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame orderlist =  new orderList("orderlist",username);
                orderlist.setVisible(true);
                orderlist.setSize(600,800);
                orderlist.setLocation(500,0);
                dispose();
            }
        });
        storageList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame stock = new stock("stock",username);
                stock.setVisible(true);
                stock.setSize(600,800);
                stock.setLocation(500,0);
                dispose();
            }
        });
        profile.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                JFrame pro=  new Account("Account",username);
                pro.setVisible(true);
                pro.setSize(600,800);
                pro.setLocation(500,0);
                dispose();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        JFrame ui = new Homepage("homepage","Stijn@hotmail.com");
        ui.setSize(600,800);
        ui.setLocation(500,0);
        ui.setVisible(true);
    }

}
