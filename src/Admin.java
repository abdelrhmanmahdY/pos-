
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class Admin  extends user  {
private int q;
private int end;
   private TreeMap<Integer,ArrayList  <String>>  admList; 
private String[] k = new String []{"id","name","password","email","type user"};
private JTable table=new JTable(view(), k);
private JScrollPane scrollPane = new JScrollPane(table);
private JLabel namLabel= new JLabel("name:");
private JTextField name = new JTextField(15);
private JLabel IDLabel= new JLabel("ID:");
private JTextField ID = new JTextField(15);
private JLabel emailLabel= new JLabel("email:");
private JTextField email = new JTextField(15);
private JLabel passwordLabel= new JLabel("password:");
private JPasswordField pasword = new JPasswordField(15);
private JButton save =new JButton("save") ;
private JFrame frame = new JFrame("add new user");
private JPanel panelHome=new  BackgroundPanel("front.jpg");
private JPanel panelback=new  BackgroundPanel("back.jpg");
private JButton add = new JButton("ADD");
private JButton edit = new JButton("edit");
private JButton remove = new JButton("remove");
private JButton view = new JButton("view");
private String [] ComboBox =get_ID_combobox();;
@SuppressWarnings({ "rawtypes", "unchecked" })
private JComboBox  list_IDs=new JComboBox(ComboBox);
@SuppressWarnings({ "rawtypes", "unchecked" })
private JComboBox type= new JComboBox(emplyeeType);
private JButton cancel=new JButton("cancel");
private int y=0;
private item items=new item();
private JLabel nameview=new JLabel();
private JLabel typeview=new JLabel();
private boolean isromave;
private JPanel itemJPanel=new  BackgroundPanel("front.jpg");;
private JButton additem = new JButton("ADD");
private JButton edititem = new JButton("edit");
private JButton removeitem = new JButton("remove");
private JButton viewitem = new JButton("view");
private JPanel panelcategory=new  BackgroundPanel("front.jpg");;
private JButton addcategory = new JButton("ADD");
private JButton editcategory = new JButton("edit");
private JButton removecategory = new JButton("remove");
private JButton viewcategory = new JButton("view");
private category categorycrud=new category();
public JButton getView() {
    return view;
}
public void setView(JButton view) {
    this.view = view;
}
private JTabbedPane tab=new JTabbedPane();
public JTabbedPane getTab() {
    return tab;
}
public void setTab(JTabbedPane tab) {
    this.tab = tab;
}
public JButton getAdd() {
    return add;
}
public void setAdd(JButton add) {
    this.add = add;
}

public String [] get_ID_combobox()throws IOException{
admList= FileManager.readuser();
String [] ids=new String [admList.keySet().size()];
int z=0;
for (int i : admList.keySet()){
ids[z]= ""+i;
System.out.println(ids[z]);
z++;
}
return  ids;
}

public Admin() throws IOException {
    remove(panel);
setTitle("Admin");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setPreferredSize(new Dimension(700,500));
ImageIcon markertIcon=new ImageIcon("store.jpg");
ImageIcon markertbottom=new ImageIcon("supermarket (1).png");
setIconImage(markertIcon.getImage());;
Image img = markertbottom.getImage() ;  
Image newimg = img.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
Icon markertIcon1=new ImageIcon(newimg);
additem.setForeground(Color.white);
additem.setBackground(Color.BLACK);
additem.setIcon(markertIcon1);
edititem.setBackground(Color.BLACK);
edititem.setForeground(Color.WHITE);
edititem .setIcon(markertIcon1);
removeitem.setBackground(Color.BLACK);
removeitem.setForeground(Color.WHITE);
removeitem.setIcon(markertIcon1);
viewitem.setBackground(Color.BLACK);
viewitem.setForeground(Color.WHITE);
viewitem.setIcon(markertIcon1);
add.setForeground(Color.WHITE);
 
addcategory.setForeground(Color.white);
addcategory.setBackground(Color.BLACK);
addcategory.setIcon(markertIcon1);
editcategory.setBackground(Color.BLACK);
editcategory.setForeground(Color.WHITE);
editcategory.setIcon(markertIcon1);
removecategory.setBackground(Color.BLACK);
removecategory.setForeground(Color.WHITE);
removecategory.setIcon(markertIcon1);
viewcategory.setBackground(Color.BLACK);
viewcategory.setForeground(Color.WHITE);
viewcategory.setIcon(markertIcon1);



 add.setBackground(Color.BLACK);
 add.setIcon(markertIcon1); 
 add.addActionListener(this);
 edit.setBackground(Color.BLACK);
 edit.setForeground(Color.WHITE);
 edit .setIcon(markertIcon1);
 remove.setBackground(Color.BLACK);
 remove.setForeground(Color.WHITE);
 remove.setIcon(markertIcon1);
 view.setBackground(Color.BLACK);
 view.setForeground(Color.WHITE);
view.setIcon(markertIcon1);
list_IDs.addActionListener(this);
items.getSaveButtom().addActionListener(this);
categorycrud.getSaveButtom().addActionListener(this);
itemJPanel.add(additem);
itemJPanel.add(edititem);
itemJPanel.add(removeitem);
itemJPanel.add(viewitem); 

panelcategory.add(addcategory);
panelcategory.add(editcategory);
panelcategory.add(removecategory);
panelcategory.add(viewcategory); 

panelHome.add(add);
panelHome.add(edit);
panelHome.add(remove);
panelHome.add(view);

save.addActionListener(this);
edit.addActionListener(this);
remove.addActionListener(this);
view.addActionListener(this);


additem.addActionListener(this);
edititem.addActionListener(this);
removeitem.addActionListener(this);
viewitem.addActionListener(this);

addcategory.addActionListener(this);
editcategory.addActionListener(this);
removecategory.addActionListener(this);
viewcategory.addActionListener(this);

items.getCancelButton().addActionListener(this);
categorycrud.getCancelButton().addActionListener(this);

tab.addTab("emplyee", panelHome);
tab.addTab("item", itemJPanel);
tab.addTab("category", panelcategory);
ArrayList<String> itemList=new ArrayList<>();
    try {
        itemList = FileManager.readcategory();
    } catch (IOException e) {
        //
    }

    // Create an ArrayList to hold the Item objects
    ArrayList<category> categories=new ArrayList<category>();

    // Create Item objects and add them to the list
    for (String itemName : itemList) {
        categories.add(new category(itemName));
    }
items.setCategories(categories);



tab.setBackground(Color.BLACK);
tab.setForeground(Color.WHITE);
add(tab);

pack();}
    public Boolean checkIDnotcontian(int ID) throws IOException{admList= FileManager.readuser();
    
    for (int i=0;i<admList.size();i++){if (admList.containsKey(ID)){
        return false;} }
return true;    
}

public void edit_emplyee (String name, String password, String email, int id, String typeuser) throws IOException{admList= FileManager.readuser();
  
    ArrayList<String> users=new ArrayList<String>();users.add(name);users.add(password);users.add(email);users.add(typeuser);
  
    admList.put(id, users);
    System.out.println(id);
    
  FileManager.wirtUsers(admList);
}
public String[][] view() {try {
    admList= FileManager.readuser();
} catch (IOException e) { 
}
    String[][] data = new String[admList.size()][5];

    int i = 0;
    for (Map.Entry<Integer, ArrayList<String>> entry : admList.entrySet()) {
        ArrayList<String> values = entry.getValue();

        for (int j = 0; j <= values.size(); j++) {
            if (j == 0) {
                data[i][j] = String.valueOf(entry.getKey());
                
            }
             else {
                
                data[i][j] = values.get(j-1);
            }
        }

        // Ensure the rest of the columns are not left null
      
        i++;
    }
    System.out.println(Arrays.deepToString(data));
    return data;
}


public void edit_page(){
    closeGUI();
    panelback.removeAll();
  

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon markertIcon=new ImageIcon("supermarket.png");
    frame.setIconImage(markertIcon.getImage());;
     
    panelback.setPreferredSize(new Dimension(800,300));
    panelback.setLayout(null);
    panelback.add(namLabel);  panelback.add(IDLabel);panelback.add(name);panelback.add(email);panelback.add(emailLabel);panelback.add(pasword);panelback.add(passwordLabel);
    panelback.add(save);
    panelback.add(type);panelback.add(cancel);panelback.add(list_IDs);
    
    namLabel.setBounds(600, 100,100, 30);
    
    name.setBounds(700, 100, 300,40 );

    emailLabel.setBounds(600, 150, 100, 30);
    email.setBounds(700, 150, 300, 40);
    passwordLabel.setBounds(600, 200, 100, 30);
    pasword.setBounds(700, 200,300 , 40);
    type.setBounds(700, 250, 100, 40);
    save.setBounds(850, 250, 150, 40);
    list_IDs.setBounds(700, 300, 100, 40);
    cancel.setBounds(850, 300, 150, 40);
    panelback.setPreferredSize(new Dimension(1500, 600));
    panelback.setBackground(Color.LIGHT_GRAY);
     
     
    frame.add(panelback);frame.setVisible(true);
    frame.pack();
   
    cancel.addActionListener(this);
    
}

public void remove_page() throws IOException{
    closeGUI();
    
    try {
admList= FileManager.readuser();
        nameview.setText(new String(admList.get(Integer.valueOf(get_ID_combobox()[list_IDs.getSelectedIndex()])).get(0)));
    } catch (IOException e1) {}
    panelback.removeAll();


    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon markertIcon=new ImageIcon("supermarket.png");
    frame.setIconImage(markertIcon.getImage());;
     
    panelback.setPreferredSize(new Dimension(800,300));
    panelback.setLayout(null);
  panelback.add(save);
  panelback.add(nameview);
    panelback.add(cancel);panelback.add(list_IDs);
    nameview.setBounds(580, 300, 100, 40);
       //type.setBounds(700, 250, 100, 40);
    
    save.setBounds(850, 250, 150, 40);
    list_IDs.setBounds(700, 300, 100, 40);
    cancel.setBounds(850, 300, 150, 40);
    panelback.setPreferredSize(new Dimension(1500, 600));
    panelback.setBackground(Color.LIGHT_GRAY);

     
    frame.add(panelback);frame.setVisible(true);
    frame.pack();
   
    
    cancel.addActionListener(this);

}////////////////////
   public void addemolyee(String name, String password, String email, int id, String typeuser) throws IOException{
    
    ArrayList<String> users=new ArrayList<String>();users.add(name);users.add(password);users.add(email);users.add(typeuser);
 
    admList= FileManager.readuser();
   admList.put(id, users);
  
    
    if (!checkIDnotcontian(id)){
    int x=JOptionPane.showConfirmDialog(null,"the id not correct do you want to close the window ", "error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
    if(x==0)
    {}
    else if(x==1|| x==2){frame.setVisible(false);;
        setVisible(true);}
    else 
    {
        frame.setVisible(false);;
        setVisible(true);
    }

}
else
{
    JOptionPane.showConfirmDialog(this,"DATA SAVED ", "INFO",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
    admList.put(id, users);
    FileManager.wirtUsers(admList);
}    
}
    
public void add_item(int id , String name, String price,String q, String categorytype){
try {ArrayList <String> k=new ArrayList<>();
    k.add(name);
    k.add(price);
    k.add(q);
    k.add(categorytype);
    TreeMap <Integer ,ArrayList<String>> data=FileManager.readitem();
    data.put(id,k);
    FileManager.wirtitem(data);
} catch (IOException e) {

   
}

}

public void remove_item(int id){
    try {
        TreeMap <Integer ,ArrayList<String>> data=FileManager.readitem();
        data.remove(id);
        FileManager.wirtitem(data);
    } catch (IOException e) {
   
       
    }
}

public void edit_item(int id , String name, String price,String q, String categorytype){

}
   


public void closeGUI(){
    setVisible(false);
}



public void addpage(){ 
    closeGUI();

    panelback.removeAll();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
ImageIcon markertIcon=new ImageIcon("supermarket.png");
frame.setIconImage(markertIcon.getImage());;
 
 
panelback.setPreferredSize(new Dimension(800,300));
panelback.setLayout(null);
panelback.add(namLabel); panelback.add(ID); panelback.add(IDLabel);panelback.add(name);panelback.add(email);panelback.add(emailLabel);panelback.add(pasword);panelback.add(passwordLabel);
panelback.add(save);
panelback.add(type);panelback.add(cancel);

namLabel.setBounds(600, 50,100, 30);
name.setBounds(700, 50, 300,40 );
IDLabel.setBounds(600, 100, 100, 30);
ID.setBounds(700, 100, 300, 40);
emailLabel.setBounds(600, 150, 100, 30);
email.setBounds(700, 150, 300, 40);
passwordLabel.setBounds(600, 200, 100, 30);
pasword.setBounds(700, 200,300 , 40);
type.setBounds(700, 250, 100, 40);
save.setBounds(850, 250, 150, 40);
cancel.setBounds(850, 300, 150, 40);
panelback.setPreferredSize(new Dimension(1500, 600));
panelback.setBackground(Color.LIGHT_GRAY);


frame.add(panelback);frame.setVisible(true);
frame.pack();
cancel.addActionListener(this);


}







public JButton getEdit() {
    return edit;
}
public void setEdit(JButton edit) {
    this.edit = edit;
}
public JButton getRemove() {
    return remove;
}
public void setRemove(JButton remove) {
    this.remove = remove;
}
public JLabel getNamLabel() {
    return namLabel;
}
public void setNamLabel(JLabel namLabel) {
    this.namLabel = namLabel;
}

public void setName(JTextField name) {
    this.name = name;
}
public JLabel getIDLabel() {
    return IDLabel;
}
public void setIDLabel(JLabel iDLabel) {
    IDLabel = iDLabel;
}
public JTextField getID() {
    return ID;
}
public void setID(JTextField iD) {
    ID = iD;
}
public JLabel getEmailLabel() {
    return emailLabel;
}
public void setEmailLabel(JLabel emailLabel) {
    this.emailLabel = emailLabel;
}

public void setEmail(JTextField email) {
    this.email = email;
}
public JLabel getPasswordLabel() {
    return passwordLabel;
}
public void setPasswordLabel(JLabel passwordLabel) {
    this.passwordLabel = passwordLabel;
}
public JPasswordField getPasword() {
    return pasword;
}
public void setPasword(JPasswordField pasword) {
    this.pasword = pasword;
}
public JButton getSave() {
    return save;
}
public void setSave(JButton save) {
    this.save = save;
}
public JFrame getFrame() {
    return frame;
}
public void setFrame(JFrame frame) {
    this.frame = frame;
}
public JPanel getPanelHome() {
    return panelHome;
}
public void setPanelHome(JPanel panelHome) {
    this.panelHome = panelHome;
}
public JPanel getPanelback() {
    return panelback;
}
public void setPanelback(JPanel panelback) {
    this.panelback = panelback;
}


public void setType(JComboBox type) {
    this.type = type;
}

public void add_c(String s){
    ArrayList<String> k=unque();
    k.add(s);
    FileManager.wirtcategory(unque(k));
}

public void edit_c(String y,String u){
    ArrayList<String> k=unque();
    for(int i=0;i<k.size();i++){
if (k.get(i)==y){k.remove(i);
    k.add(i, u);
    FileManager.wirtcategory(unque(k));
     break;}
    }
    
}

public void remove_c(String u){
    ArrayList<String> k=unque();
    for(int i=0;i<k.size();i++){
if (k.get(i)==u){k.remove(i);
    
    FileManager.wirtcategory(unque(k));
     break;}
    }
    
}


public void view_page(){

panelback.removeAll();
 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon markertIcon=new ImageIcon("supermarket.png");
    frame.setIconImage(markertIcon.getImage());;
    panelback.setPreferredSize(new Dimension(800,300));
    panelback.setLayout(null);
     
  
    panelback.add(cancel);
    cancel.setBounds(850, 300, 150, 40);
   
    scrollPane.setBounds(50, 50, 700, 400);   
    panelback.add(scrollPane,BorderLayout.CENTER); 


    frame.add(panelback);frame.setVisible(true);
    frame.pack();
    cancel.addActionListener(this);
    
}
public void remove_data(int id){

    try {System.out.println("make it");
        TreeMap <Integer,ArrayList<String>>     data=FileManager.readuser();
         data.remove(id);
         System.out.println(id);
FileManager.wirtUsers(data);
JOptionPane.showConfirmDialog(null,"done ", "Inf",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {JOptionPane.showInputDialog(null,"file not found",JOptionPane.CLOSED_OPTION);}

}
@Override
public void actionPerformed(ActionEvent e) {System.out.println("aasd");
System.out.println(11);
   
    if (add == e.getSource() && tab.getSelectedIndex() == 0 ) {
        panelback.remove(nameview);
        addpage();

     y=1;  
    }
     else if (e.getSource()==edit&& tab.getSelectedIndex() == 0 ) {
        panelback.remove(nameview);
        edit_page();
        y=2;
    }
    else if (e.getSource() == cancel) {
    isromave=false;
        frame.setVisible(false);;
        setVisible(true);
    }
    else if (e.getSource()==remove && tab.getSelectedIndex() == 0 ){
        try {isromave=true;
            if (!admList.isEmpty())  { remove_page();}
        } catch (IOException e1) {}
        y=3;
    }
    else if (e.getSource()==view&& tab.getSelectedIndex() == 0 ){
        frame.setVisible(false);
        System.out.println(true);
        view_page();
    
    } 
    else if(e.getSource()==save && y==1){
        
        try { System.out.println("SAVE");
        System.out.println(ID.getText());
            addemolyee(new String(name.getText()),new String(pasword.getText()),new String(email.getText()),Integer.valueOf(ID.getText()),emplyeeType[type.getSelectedIndex()]);
            frame.setVisible(false);;
            
            setVisible(true);
        } catch (Exception e2) {
            int x=JOptionPane.showConfirmDialog(null,"check the data please ", "error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
           
        }}
        else if (e.getSource() ==  save && y==2){
        
            try { 
                edit_emplyee(new String(name.getText()),new String(pasword.getText()),new String(email.getText()),Integer.valueOf(get_ID_combobox()[list_IDs.getSelectedIndex()]),emplyeeType[type.getSelectedIndex()]);
                frame.setVisible(false);;
                add.
                setVisible(true);
            } catch (Exception e2) {
                int x=JOptionPane.showConfirmDialog(null,"check the data please ", "error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
                if(x==0)
                {}
                else if(x==1|| x==2){frame.setVisible(false);;
                    setVisible(true);}
            
                else {
                    frame.setVisible(false);;
                    setVisible(true);
                }}
    }





    else if (e.getSource() ==  save && y==3){
     try {
        
        remove_data(Integer.valueOf(Integer.valueOf(get_ID_combobox()[list_IDs.getSelectedIndex()])));
    }  catch (IOException e1) {
        
    }   




    }
    else if (isromave==true){   
    try {
    
        nameview.setText(new String(admList.get(Integer.valueOf(get_ID_combobox()[list_IDs.getSelectedIndex()])).get(0)));
    } catch (IOException e1) {} }

    else if (e.getSource()==additem&& tab.getSelectedIndex() == 1){
        q=1;
        setVisible(false);
        items.getFrame().setVisible(true);
       items.add_page(); 
    }
    else if (e.getSource()==edititem&& tab.getSelectedIndex() == 1){setVisible(false);
    q=2;    items.getFrame().setVisible(true);
       items.edit_page();; 
       
    }

    else if (e.getSource()==removeitem&& tab.getSelectedIndex() == 1){setVisible(false);
        items.getFrame().setVisible(true);q=3;
       items.remove_page();

    }
    else if (e.getSource()==viewitem&& tab.getSelectedIndex() == 1){setVisible(false);
        items.getFrame().setVisible(true);
       items.view_page();

    }
    
    else if (e.getSource()==addcategory&&tab.getSelectedIndex() == 2){setVisible(false);
        categorycrud.getFrame().setVisible(true);end=1;
categorycrud.add_page();
    }
    else if (e.getSource()==editcategory&&tab.getSelectedIndex() == 2){setVisible(false);
        categorycrud.getFrame().setVisible(true);end=2;
categorycrud.edit_page();} 
else if (e.getSource()==removecategory&&tab.getSelectedIndex() == 2){setVisible(false);
    categorycrud.getFrame().setVisible(true);end=3;
categorycrud.remove_page();} 
else if (e.getSource()==viewcategory&&tab.getSelectedIndex() == 2){setVisible(false);
    categorycrud.getFrame().setVisible(true);
categorycrud.view_page();} 
    else if (e.getSource()==items.getCancelButton()){
        items.getFrame().dispose();
    setVisible(true);}

   else if (e.getSource()==items.getListIDS()){
        try {
            TreeMap <Integer,ArrayList<String>> admList = FileManager.readitem();
            nameview.setText(admList.get(Integer.valueOf(get_ID_combobox()[items.getListIDS().getSelectedIndex()])).get(0));
        } catch (IOException ex) {
 
        }
    }
   else if (e.getSource()==categorycrud.getCancelButton()){
        categorycrud.getFrame().dispose();
        setVisible(true);
    }
    else if (e.getSource()==items.getsaveButtom()&& q==1){items.getFrame().setVisible(false);setVisible(true);
        try {
            add_item(Integer.valueOf(items.getItemIDField().getText()),new String(items.getNameField().getText()),new String(items.getPriceField().getText()),new String(items.getQuantityField().getText()) ,new String (items.get_category_array()[items.getListcategory().getSelectedIndex()]));
        } catch (NumberFormatException | IOException e1) {
   
        }
    }
    
    else if (e.getSource()==items.getsaveButtom()&& q==2){items.getFrame().setVisible(false);setVisible(true);
        try {
            edit_item(Integer.valueOf(items.get_ID_combobox()[items.getListIDS().getSelectedIndex()]),new String(items.getNameField().getText()),new String(items.getPriceField().getText()),new String (items.getQuantityField().getText()),new String (items.get_category_array()[items.getListcategory().getSelectedIndex()]));
        } catch (NumberFormatException | IOException e1) {
        
        }
    }
    else if (e.getSource()==items.getsaveButtom()&& q==3 ){items.getFrame().setVisible(false);setVisible(true);
        try {System.out.println("qqqqqqqq");
            remove_item(Integer.valueOf(items.get_ID_combobox()[items.getListIDS().getSelectedIndex()]));
        } catch (NumberFormatException | IOException e1) {
         
        }
    }
    else if (e.getSource()==categorycrud.getSaveButtom()&& end==1 ){        categorycrud.getFrame().dispose();
        setVisible(true);
        add_c(new String(categorycrud.getNameField().getText()));

    }
    else if (e.getSource()==categorycrud.getSaveButtom()&& end==2 ){        categorycrud.getFrame().dispose();
        setVisible(true);
       try {
        edit_c( new String(categorycrud.get_ID_combobox()[categorycrud.getListcategory().getSelectedIndex()]),new String(categorycrud.getNameField().getText()));
    } catch (IOException e1) {
       
    
    }
    }else if (e.getSource()==categorycrud.getSaveButtom()&& end==3){        categorycrud.getFrame().dispose();
        setVisible(true);
        try {
            remove_c(new String(categorycrud.get_ID_combobox()[categorycrud.getListcategory().getSelectedIndex()]));
        } catch (IOException e1) {
      
        }
    }
}


public ArrayList<String> unque(){

ArrayList<String> categorieList=new ArrayList<>();
try {
    categorieList = FileManager.readcategory();
} catch (IOException e) {
  


}HashSet<String> uniqueCategories = new HashSet<>(categorieList);
return new ArrayList<>(uniqueCategories);}
public ArrayList<String> unque(ArrayList<String> categorieList){

HashSet<String> uniqueCategories = new HashSet<>(categorieList);


return new ArrayList<>(uniqueCategories);
}}
