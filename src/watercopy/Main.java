//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
package watercopy;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Main extends JFrame implements ActionListener{
    JLabel userNameLabel,passwordLabel,signupLabel;
    JTextField username;
    JPasswordField password;
    JButton login,signup;
    Main() {
        super("Waterman Analysis");
        userNameLabel = new JLabel("Username: ");   userNameLabel.setBounds(100,100,100,30); add(userNameLabel);
        username = new JTextField();    username.setBounds(200,100,200,30); add(username);
        passwordLabel = new JLabel("Password: ");   passwordLabel.setBounds(100,200,100,30); add(passwordLabel);
        password = new JPasswordField();    password.setBounds(200,200,200,30); add(password);
        login = new JButton("Login"); login.setBounds(200,300,100,50);  add(login);
        login.addActionListener(this);
        signupLabel = new JLabel("New user: "); signupLabel.setBounds(100,400,150,30); add(signupLabel);
        signup = new JButton("Sign up"); signup.setBounds(250,400,100,50);  add(signup);
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SignUp();
            }
        });
        setLayout(null);
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception{
        new Main();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Login")){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jproject?useSSL=false", "root", "Abhi2209!!");
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery("select * from data where username=\""+username.getText()+"\";");
                if(!rs.next()){
                    JOptionPane.showMessageDialog(null,"No username matched","Message",JOptionPane.ERROR_MESSAGE);
                }
                else{

                    if(rs.getString("password").equals(new String(password.getPassword()))){
                        this.setVisible(false);
                        WaterContent wc = new WaterContent(rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("address"),rs.getString("phone"));
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid password","Message",JOptionPane.ERROR_MESSAGE);
                    }

                }

                rs.close();s.close();conn.close();

            }
            catch (SQLException se){
                System.out.println(se);
            }
            catch(Exception ex){

            }

        }}

}
class SignUp extends JFrame implements ActionListener{
    JLabel emailLabel,userNameLabel,passwordLabel,addressLabel,signupLabel,phoneNumberLabel;
    JTextField email,username,phoneNumber;
    JPasswordField password;
    JTextArea address;
    JButton signup;
    SignUp(){
        super("Sign up");
        userNameLabel = new JLabel("User name: ");   userNameLabel.setBounds(100,50,100,30); add(userNameLabel);
        username = new JTextField();    username.setBounds(200,50,200,30); add(username);
        emailLabel = new JLabel("Email: ");   emailLabel.setBounds(100,100,100,30); add(emailLabel);
        email = new JTextField();    email.setBounds(200,100,200,30); add(email);
        passwordLabel = new JLabel("Password: ");   passwordLabel.setBounds(100,150,100,30); add(passwordLabel);
        password = new JPasswordField();    password.setBounds(200,150,200,30); add(password);
        addressLabel = new JLabel("Address: ");  addressLabel.setBounds(100,200,100,30); add(addressLabel);
        address = new JTextArea(); address.setBounds(200,200,200,100); add(address);
        phoneNumberLabel = new JLabel("Phone number: "); phoneNumberLabel.setBounds(100,350,100,30); add(phoneNumberLabel);
        phoneNumber = new JTextField(); phoneNumber.setBounds(200,350,200,30); add(phoneNumber);
        //signupLabel = new JLabel("Already a user: "); signupLabel.setBounds(100,400,150,30); add(signupLabel);
        signup = new JButton("Sign up"); signup.setBounds(250,400,100,50);  add(signup);
        signup.addActionListener(this);
        setLayout(null);
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    void storedb(String username,String email, String password , String address, String phone) throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "Abhi2209!!");
        Statement s = conn.createStatement();
        try{
            s.execute("create database jproject;");
            s.execute("use jproject");
            s.execute("create table data(username varchar(20) primary key,email varchar(40),password varchar(10),address varchar(60),phone varchar(12))");
            //System.out.println("Database created successfully");
        }
        catch(SQLException e){}

        s.execute("use jproject;");
        s.execute("insert into data values(\""+username+"\",\"" + email + "\",\"" + password + "\",\"" + address + "\",\"" + phone + "\")");
        s.close();
        conn.close();


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            storedb(username.getText(),email.getText(), new String(password.getPassword()), address.getText(), phoneNumber.getText());
            JOptionPane.showMessageDialog(null,"Successfully signed up","Message",JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
            new Main();
        }

        catch(Exception r){
            System.out.println(r);
            JOptionPane.showMessageDialog(null,"Signing Failed","Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }}