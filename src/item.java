import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class item extends JPanel implements ActionListener {
private ArrayList<category> categories=new ArrayList<category>();
    private  String itemID ;
    private String Name;
    private Double Price;
    private  int quantity ;
    private  JComboBox  listIDS;
private JComboBox listcategory;
    private JFrame frame = new JFrame("item");
    private Image backgroundImage;
    private JTextField itemIDField;
    private JLabel itemIDLabel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JButton cancelButton =new JButton("cancel");
    private JButton saveButtom = new JButton("Save");;
    private JTextField priceField;
    private JLabel priceLabel;
    private JTextField quantityField;
    private JLabel quantityLabel;

    private JLabel nameview=new JLabel() ;
    public item(String itemID, String name, Double price, int quantity) {
        this.itemID = itemID;
        this.Name = name;
        this.Price = price;
        this.quantity = quantity;
    }

    public item() {
        ImageIcon markertIcon=new ImageIcon("store.jpg");
        backgroundImage = new ImageIcon("item.jpg").getImage();
  cancelButton.addActionListener(this);
frame.setIconImage(markertIcon.getImage());

saveButtom.addActionListener(this);
    }
    public String [] get_ID_combobox()throws IOException{

TreeMap <Integer,ArrayList<String>>        itemslists= FileManager.readitem();
String [] ids=new String [itemslists.keySet().size()];
int z=0;
for (int i : itemslists.keySet()){
ids[z]= ""+i;
System.out.println(ids[z]);
z++;
}
return ids;}
    
    public String getItemID() {
        return itemID;
    }


    public void setItemID(String itemID) {
        this.itemID = itemID;
    }


    public String getName() {
        return Name;
    }


    public void setName(String name) {
        Name = name;
    }


    public Double getPrice() {
        return Price;
    }


    public void setPrice(Double price) {
        Price = price;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
public void degesin(){ 
  
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1500, 750));
   
    frame.setContentPane(this);

    setLayout(null);
    setPreferredSize(new Dimension(700, 500));
    
    itemIDLabel = new JLabel("Item ID:");
itemIDLabel.setForeground(Color.WHITE);
itemIDField = new JTextField();




quantityField = new JTextField();
quantityLabel = new JLabel("Quantity:");
quantityLabel.setForeground(Color.WHITE);

itemIDField = new JTextField();
itemIDField.setForeground(Color.WHITE);
itemIDField.setBackground(Color.BLACK);

itemIDLabel = new JLabel("Item ID:");
itemIDLabel.setForeground(Color.WHITE);

nameField = new JTextField();
nameField.setForeground(Color.WHITE);

nameField.setBackground(Color.BLACK);
nameLabel = new JLabel("Name:");
nameLabel.setForeground(Color.WHITE);

priceField = new JTextField();
priceField.setForeground(Color.WHITE);
priceField.setBackground(Color.BLACK);

priceLabel = new JLabel("Price:");
priceLabel.setForeground(Color.WHITE);

quantityField = new JTextField();
quantityField.setForeground(Color.WHITE);
quantityField.setBackground(Color.BLACK);

quantityLabel = new JLabel("Quantity:");
quantityLabel.setForeground(Color.WHITE);

cancelButton.setBackground(Color.BLACK);
cancelButton.setForeground(Color.WHITE);

saveButtom.setBackground(Color.BLACK);
saveButtom.setForeground(Color.WHITE);
nameview.setForeground(Color.WHITE);}

    
public String[] get_category_array() throws IOException {
  
   
    String[] ids = new String[categories.size()];

    for (int i = 0; i < categories.size(); i++) {
        ids[i] = categories.get(i).getName();
    }

    return ids;
}






public void edit_page() {
    removeAll();

    try {
        listcategory = new JComboBox<>(get_category_array());
    } catch (IOException e) {
        listcategory = new JComboBox<>();
    }
    listcategory.setBackground(Color.BLACK);
    listcategory.setForeground(Color.WHITE);

    try {
        listIDS = new JComboBox<>(get_ID_combobox());
    } catch (IOException e) {
        listIDS = new JComboBox<>();
    }
    listIDS.setBackground(Color.BLACK);
    listIDS.setForeground(Color.WHITE);

    frame.setContentPane(this);
    setLayout(null);

    degesin();


    nameLabel.setBounds(150, 100, 100, 30);
    nameField.setBounds(260, 100, 200, 30);

    priceLabel.setBounds(150, 150, 100, 30);
    priceField.setBounds(260, 150, 200, 30);

    quantityLabel.setBounds(150, 200, 100, 30);
    quantityField.setBounds(260, 200, 200, 30);

    listIDS.setBounds(150, 250, 200, 30);

    listcategory.setBounds(150, 300, 310, 30); // Setting bounds for listcategory

    saveButtom.setBounds(150, 350, 100, 30);
    cancelButton.setBounds(270, 350, 100, 30);
    listcategory.addActionListener(this);
    add(nameLabel);
    add(nameField);
    add(priceLabel);
    add(priceField);
    add(quantityLabel);
    add(quantityField);
    add(listIDS);
    add(saveButtom);
    add(cancelButton);
    add(listcategory);

    frame.pack();

}


    public void add_page() {
        try {
            listcategory=new JComboBox<>(get_category_array());
        } catch (IOException e) {
            listcategory=new JComboBox<>();
        }
        listcategory.setBackground(Color.BLACK);
        listcategory.setForeground(Color.WHITE);
        removeAll();

        
     degesin();
        
        
       
        
        itemIDLabel.setBounds(150, 50, 100, 30);
        itemIDField.setBounds(260, 50, 200, 30);
        nameLabel.setBounds(150, 100, 100, 30);
        nameField.setBounds(260, 100, 200, 30);
        priceLabel.setBounds(150, 150, 100, 30);
        priceField.setBounds(260, 150, 200, 30);
        quantityLabel.setBounds(150, 200, 100, 30);
        quantityField.setBounds(260, 200, 200, 30);
        saveButtom.setBounds(150, 300, 100, 30);
        cancelButton.setBounds(270, 300, 100, 30);
        listcategory.setBounds(260, 250, 200, 30);
        add(itemIDLabel); add(itemIDField);add(nameLabel);add(nameField);add(priceLabel);
        add(priceField);add(quantityLabel);add(quantityField);add(saveButtom);add(cancelButton);
        add(listcategory);
        frame.pack();
      }


        
    public void remove_page() {
    

       
        

        removeAll();
    
   
     

        try {
            listIDS = new JComboBox<>(get_ID_combobox());
        } catch (IOException e) {
            listIDS = new JComboBox<>();
        }
        listIDS.setBackground(Color.BLACK);
        listIDS.setForeground(Color.WHITE);
   
       
        frame.setContentPane(this);
        setLayout(null);
      
        degesin();
        nameview.setBounds(580, 300, 100, 40);
        listIDS.setBounds(700, 300, 100, 40);
        saveButtom.setBounds(850, 250, 150, 40);
        cancelButton.setBounds(850, 300, 150, 40);

        add(saveButtom);
        add(nameview);
    add(cancelButton);
   add(listIDS);

       listIDS.addActionListener(this);
        frame.pack();
      

    }


    public static void main(String[] args) {
  item l= new item();
  
    
    
l.view_page();
}
    

    @Override
    public void actionPerformed(ActionEvent e) {
       
    }

    public JComboBox getListIDS() {
        return listIDS;
    }

    public void setListIDS(JComboBox listIDS) {
        this.listIDS = listIDS;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public JTextField getItemIDField() {
        return itemIDField;
    }

    public void setItemIDField(JTextField itemIDField) {
        this.itemIDField = itemIDField;
    }

    public JLabel getItemIDLabel() {
        return itemIDLabel;
    }

    public void setItemIDLabel(JLabel itemIDLabel) {
        this.itemIDLabel = itemIDLabel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JButton getsaveButtom() {
        return saveButtom;
    }

    public void setsaveButtom(JButton saveButtom) {
        this.saveButtom = saveButtom;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public void setPriceField(JTextField priceField) {
        this.priceField = priceField;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(JLabel priceLabel) {
        this.priceLabel = priceLabel;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(JTextField quantityField) {
        this.quantityField = quantityField;
    }

    public JLabel getQuantityLabel() {
        return quantityLabel;
    }

    public void setQuantityLabel(JLabel quantityLabel) {
        this.quantityLabel = quantityLabel;
    }

    public JLabel getNameview() {
        return nameview;
    }

    public void setNameview(JLabel nameview) {
        this.nameview = nameview;
    }

    public ArrayList<category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<category> categories) {
        this.categories = categories;
    }

    public JComboBox getListcategory() {
        return listcategory;
    }

    public void setListcategory(JComboBox listcategory) {
        this.listcategory = listcategory;
    }

    public JButton getSaveButtom() {
        return saveButtom;
    }
public void view_page(){removeAll();
    try {degesin();
        frame.setContentPane(this);
        setLayout(null);
        TreeMap<Integer,ArrayList  <String>> k=FileManager.readitem();
          DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Stock");
        model.addColumn("Category");

        for (Integer id :k.keySet()) {
            ArrayList<String> item = k.get(id);
            model.addRow(new Object[]{id, item.get(0), item.get(1), item.get(2), item.get(3)});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);



      


        scrollPane.setBounds(10, 10, 460, 200);
        cancelButton.setBounds(190, 220, 100, 30);

       
        add(scrollPane);
        add(cancelButton);

      
    

 
  

   

    frame.pack();
    } catch (Exception e) {

    }
}
    public void setSaveButtom(JButton saveButtom) {
        this.saveButtom = saveButtom;
    }
}
