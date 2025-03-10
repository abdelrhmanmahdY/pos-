import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class user extends JFrame implements ActionListener  {
   private String name;
    public user(String name, String password, String email, int id, String typeuser) {
    this.name = name;
    this.password = password;
    this.email = email;
    this.id = id;
    this.typeuser = typeuser;
}
protected final String [] emplyeeType={"Emplyee","admin"};
    private String  password;
    private String email;
    private int id;
    private String typeuser;
    protected JPanel panel =new BackgroundPanel("item.jpg");
   private JTextField emailfField =new JTextField();
   private JLabel emaillaJLabel=new JLabel("email") ;
   private  JPasswordField passwordField=new JPasswordField();  ;
   private JLabel passwordLabel=new JLabel("password ") ;
private JButton loginButton = new JButton("Login");
    public String getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }

    

    public user() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
       setLocationRelativeTo(null);

        panel.setLayout(null);

    
        emaillaJLabel.setBounds(50, 80, 80, 25); 
        emailfField.setBounds(150, 80, 200, 25);
        passwordLabel.setBounds(50, 120, 80, 25);
        passwordField.setBounds(150, 120, 200, 25);
        loginButton.setBounds(150, 160, 100, 30);
        emailfField.setBackground(Color.BLACK);
        emailfField.setForeground(Color.WHITE);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.WHITE);
emaillaJLabel.setForeground(Color.WHITE);
    passwordLabel.setForeground(Color.WHITE);
        panel.add(emaillaJLabel);
        panel.add(emailfField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        loginButton.addActionListener(this);
        add(panel);
        setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginButton){
            try {
                TreeMap<Integer,ArrayList  <String>>k=FileManager.readuser();
                for (ArrayList<String>  t: k.values()){
                    if (t.get(1).equalsIgnoreCase(new String(passwordField.getText()))&&t.get(2).equalsIgnoreCase(new String(emailfField.getText()))){
                        if(t.get(3).equalsIgnoreCase(emplyeeType[0])){dispose();
                            new emplyee();break;}
                        else{ System.out.println("rwer");
                            Admin o=new Admin();o.remove(panel);
                        o.setVisible(true);break;}

                    }
                    
                }
            } catch (IOException ex) {
          
            }
        }

    }



    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }


}