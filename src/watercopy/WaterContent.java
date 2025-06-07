package watercopy;
import javax.swing.*;
import java.awt.event.*;

public class WaterContent extends JFrame implements ActionListener {
    JLabel canNameLabel, capacityLabel, expectedDateLabel;
    JComboBox<String> canName, capacity;
    JTextField expectedDate;
    JButton confirm;
    // --------------- data ------------------
    String email,username , password, address, phone ;
    ButtonGroup bg = new ButtonGroup();
    JRadioButton gpay,cod;

    WaterContent(String uname, String email,String pass, String address, String ph){
        super("Order page");

        this.email = email;
        this.username = uname;
        this.password = pass;
        this.address = address;
        this.phone = ph;

        JLabel welcome = new JLabel("Welcome "+username);
        welcome.setBounds(50,0,300,30);
        add(welcome);

        canNameLabel = new JLabel("Choose can name: ");
        canNameLabel.setBounds(50,50,200,30);  add(canNameLabel);
        canName = new JComboBox<>();
        canName.addItem("Bisleri");
        canName.addItem("Aqua Fina");
        canName.setBounds(250,50,200,30);
        add(canName);

        capacityLabel = new JLabel("Choose litres: "); capacityLabel.setBounds(50,100,200,30);   add(capacityLabel);
        capacity = new JComboBox<>();
        capacity.addItem("5L");
        capacity.addItem("10L");
        capacity.addItem("20L");
        capacity.setBounds(250,100,200,30);   add(capacity);

        expectedDateLabel = new JLabel("Select Date: "); expectedDateLabel.setBounds(50,150,200,30); add(expectedDateLabel);
        expectedDate = new JTextField("DD/MM/YYYY");    expectedDate.setBounds(250,150,200,30);   add(expectedDate);

        gpay = new JRadioButton("GPay");
        gpay.setBounds(100,250,200,30);
        add(gpay);

        cod = new JRadioButton("COD");
        cod.setBounds(300,250,200,30);
        add(cod);
        bg.add(gpay);bg.add(cod);

        confirm = new JButton("Confirm");
        confirm.setBounds(250,350,100,30);
        add(confirm);confirm.addActionListener(this);
        setLayout(null);
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mode;
        if(gpay.isSelected())   mode = "GPay";
        else mode = "COD";
        this.setVisible(false);
        //System.out.println(username+password+address+phone+canName.getSelectedItem()+capacity.getSelectedItem()+expectedDate.getText()+mode);
        new Bill(username,email,password,address,phone,(String)canName.getSelectedItem(),(String)capacity.getSelectedItem(), expectedDate.getText(),mode);
    }


}