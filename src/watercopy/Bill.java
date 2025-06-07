package watercopy;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Bill extends JFrame implements ActionListener {
    int damount;
    String demail, duname, dpass, daddress, dph, dcanName, dcanCapacity, ddate, dmode;

    JLabel emaill, addressl, phonel, currDatel, canNamel, cancapacityl, amountl, modelabel;
    JTextField email, phone, currDate, canName, cancapacity, amount, mode;
    JTextArea address;
    JButton confirm, cancel;

    void setup() {
        emaill = new JLabel("Email"); emaill.setBounds(50, 100, 100, 30); add(emaill);
        email = new JTextField(); email.setBounds(150, 100, 300, 30); add(email); email.setEditable(false);

        phonel = new JLabel("Phone number"); phonel.setBounds(50, 150, 100, 30); add(phonel);
        phone = new JTextField(); phone.setBounds(150, 150, 200, 30); add(phone); phone.setEditable(false);

        addressl = new JLabel("Address"); addressl.setBounds(50, 200, 100, 30); add(addressl);
        address = new JTextArea(); address.setBounds(150, 200, 200, 100); add(address); address.setEditable(false);

        currDatel = new JLabel("Date"); currDatel.setBounds(50, 350, 100, 30); add(currDatel);
        currDate = new JTextField(); currDate.setBounds(150, 350, 200, 30); add(currDate); currDate.setEditable(false);

        canNamel = new JLabel("Can name"); canNamel.setBounds(50, 400, 100, 30); add(canNamel);
        canName = new JTextField(); canName.setBounds(150, 400, 200, 30); add(canName); canName.setEditable(false);

        cancapacityl = new JLabel("Can capacity"); cancapacityl.setBounds(50, 450, 100, 30); add(cancapacityl);
        cancapacity = new JTextField(); cancapacity.setBounds(150, 450, 200, 30); add(cancapacity); cancapacity.setEditable(false);

        modelabel = new JLabel("Payment mode"); modelabel.setBounds(50, 500, 100, 30); add(modelabel);
        mode = new JTextField(); mode.setBounds(150, 500, 200, 30); add(mode); mode.setEditable(false);

        amountl = new JLabel("Amount"); amountl.setBounds(50, 550, 100, 30); add(amountl);
        amount = new JTextField(); amount.setBounds(150, 550, 200, 30); add(amount); amount.setEditable(false);

        confirm = new JButton("Confirm"); confirm.setBounds(100, 600, 100, 50); add(confirm);
        confirm.addActionListener(this);

        cancel = new JButton("Cancel"); cancel.setBounds(300, 600, 100, 50); add(cancel);
        cancel.addActionListener(this);

        setLayout(null);
        setVisible(true);
        setSize(500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void updateData() {
        email.setText(demail);
        phone.setText(dph);
        address.setText(daddress);
        currDate.setText(ddate);
        canName.setText(dcanName);
        cancapacity.setText(dcanCapacity);
        mode.setText(dmode);
        damount = calculateAmount();
        amount.setText(Integer.toString(damount));
    }

    int calculateAmount() {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("Bisleri", 30);
        hm.put("Aqua Fina", 20);
        return hm.get(dcanName) * Integer.parseInt(dcanCapacity.substring(0, dcanCapacity.length() - 1)); // remove 'L'
    }

    Bill(String uname, String email, String pass, String address, String ph, String canName, String canCapacity, String date, String mode) {
        super("Bill");
        demail = email;
        duname = uname;
        dpass = pass;
        daddress = address;
        dph = ph;
        dcanCapacity = canCapacity;
        dcanName = canName;
        ddate = date;
        dmode = mode;

        setup();
        updateData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Confirm")) {
            try {
                List<String> command = new ArrayList<>();
                command.add("python");  // Or full path like "C:\\Python311\\python.exe"
                command.add("scripts/emailprog.py"); // Adjust this to your actual Python file location

                // Add arguments
                command.add(demail);
                command.add(duname);
                command.add(dph);
                command.add(daddress);
                command.add(ddate);
                command.add(dcanName);
                command.add(dcanCapacity);
                command.add(dmode);
                command.add(String.valueOf(damount));

                ProcessBuilder pb = new ProcessBuilder(command);
                Process process = pb.start();

                // Output stream (optional)
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Python output: " + line);
                }

                // Error stream (optional)
                BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while ((line = errReader.readLine()) != null) {
                    System.err.println("Python error: " + line);
                }

                process.waitFor();

                JOptionPane.showMessageDialog(null, "Successfully ordered", "Message", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
                new WaterContent(duname, demail, dpass, daddress, dph);

            } catch (Exception ec) {
                ec.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to run Python script", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Cancel")) {
            this.setVisible(false);
            new WaterContent(duname, demail, dpass, daddress, dph);
        }
    }

    public static void main(String[] args) {
        // test
        new Bill("JohnDoe", "john@example.com", "pass123", "123 Main St", "1234567890", "Bisleri", "10L", "2025-05-26", "Cash");
    }
}

















//package watercopy;
//import javax.swing.*;
//
//import java.awt.event.*;
//import java.util.HashMap;
//
//public class Bill extends JFrame implements ActionListener {
//    int damount;
//    String demail,duname,dpass,daddress,dph,dcanName,dcanCapacity,ddate,dmode;
//
//    JLabel emaill,addressl,phonel,currDatel,canNamel,cancapacityl,amountl,modelabel;
//    JTextField email,phone,currDate,canName,cancapacity,amount,mode;
//    JTextArea address;
//    JButton confirm,cancel;
//    void setup(){
//
//        emaill = new JLabel("Email"); emaill.setBounds(50,100,100,30); add(emaill);
//        email = new JTextField();        email.setBounds(150,100,300,30); add(email); email.setEditable(false);
//        phonel = new JLabel("Phone number");phonel.setBounds(50,150,100,30); add(phonel);
//        phone = new JTextField();       phone.setBounds(150,150,200,30);add(phone); phone.setEditable(false);
//        addressl = new JLabel("Address");addressl.setBounds(50,200,100,30); add(addressl);
//        address = new JTextArea();  address.setBounds(150,200,200,100); add(address); address.setEditable(false);
//        currDatel = new JLabel("Date");currDatel.setBounds(50,350,100,30); add(currDatel);
//        currDate = new JTextField(); currDate.setBounds(150,350,200,30); add(currDate);currDate.setEditable(false);
//        canNamel = new JLabel("Can name");canNamel.setBounds(50,400,100,30); add(canNamel);
//        canName = new JTextField(); canName.setBounds(150,400,200,30); add(canName);canName.setEditable(false);
//        cancapacityl = new JLabel("Can capacity");cancapacityl.setBounds(50,450,100,30); add(cancapacityl);
//        cancapacity = new JTextField();  cancapacity.setBounds(150,450,200,30); add(cancapacity);cancapacity.setEditable(false);
//        modelabel = new JLabel("Payment mode");modelabel.setBounds(50,500,100,30); add(modelabel);
//        mode = new JTextField(); mode.setBounds(150,500,200,30); add(mode); mode.setEditable(false);
//        amountl = new JLabel("Amount");amountl.setBounds(50,550,100,30); add(amountl);
//        amount = new JTextField(); amount.setBounds(150,550,200,30); add(amount);amount.setEditable(false);
//        confirm = new JButton("Confirm"); confirm.setBounds(100,600,100,50);add(confirm);
//        confirm.addActionListener(this);
//        cancel = new JButton("Cancel"); cancel.setBounds(300,600,100,50);add(cancel);
//        setLayout(null);
//        setVisible(true);
//        setSize(500,800);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//    void updateData(){
//        email.setText(demail);
//        phone.setText(dph);
//        address.setText(daddress);
//        currDate.setText(ddate);
//        canName.setText(dcanName);
//        cancapacity.setText(dcanCapacity);
//        mode.setText(dmode);
//        damount = calculateAmount();
//        amount.setText(Integer.toString(damount));
//    }
//    int calculateAmount(){
//        HashMap<String ,Integer> hm = new HashMap<>();
//        hm.put("Bisleri",30);hm.put("Aqua Fina",20);
//        return hm.get(dcanName)*Integer.parseInt(dcanCapacity.substring(0,dcanCapacity.length()-1)); //5L, 10L
//    }
//    Bill(String uname,String email, String pass, String address, String ph,String canName,String canCapacity,String date,String mode){
//        super("Bill");
//        demail = email;duname = uname;dpass = pass;daddress = address;dph=ph;dcanCapacity=canCapacity;dcanName=canName;ddate=date;dmode=mode;
//        setup();    updateData();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getActionCommand().equals("Confirm")){
//            try{
//                Runtime runtime = Runtime.getRuntime();
//                runtime.exec("cmd.exe /c python emailprog.py \""+demail+"\" \""+duname+"\" \""+dph+"\" \""+daddress+"\" \""+ddate+"\" \""+dcanName+"\" \""+dcanCapacity+"\" \""+dmode+"\" \""+damount);
//                JOptionPane.showMessageDialog(null,"Successfully ordered","Message",JOptionPane.INFORMATION_MESSAGE);
//                this.setVisible(false);
//                new WaterContent(duname,demail,dpass,daddress,dph);
//            }
//            catch (Exception ec){
//                System.out.println(ec);
//            }
//        }
//        else if (e.getActionCommand().equals("Cancel")) {
//            this.setVisible(false);
//            new WaterContent(duname,demail,dpass,daddress,dph);
//
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }
//}