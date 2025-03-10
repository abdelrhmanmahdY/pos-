import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class category extends JPanel implements ActionListener {
    private Image backgroundImage;
  
    private JFrame frame = new JFrame("category");



private String Name ;


private JLabel nameLabel = new JLabel("Name category:");
private JTextField nameField = new JTextField();
private JComboBox listcategory;

private JButton saveButtom=new JButton("save");
private JButton cancelButton =new JButton("cancel");


public category() {
    frame.setPreferredSize(new Dimension(1500,800));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon markertIcon=new ImageIcon("store.jpg");
    this.backgroundImage = new ImageIcon("category.jpg").getImage();
    cancelButton.addActionListener(this);
    saveButtom.addActionListener(this);
    frame.setIconImage(markertIcon.getImage());
    
}

public void degesin(){
    nameLabel.setBackground(Color.BLACK);
    nameLabel.setForeground(Color.WHITE);

    nameField.setBackground(Color.BLACK);
    nameField.setForeground(Color.WHITE);

    saveButtom.setBackground(Color.BLACK);
    saveButtom.setForeground(Color.WHITE);

    cancelButton.setBackground(Color.BLACK);
    cancelButton.setForeground(Color.WHITE);

}

public JFrame getFrame() {
    return frame;
}

public void setFrame(JFrame frame) {
    this.frame = frame;
}

public void remove_page(){  
    removeAll();

    degesin();
    try {
        listcategory =new JComboBox<>(get_ID_combobox());
    } catch (IOException e) {
listcategory=new JComboBox<>();
    }
    listcategory.addActionListener(this);
    listcategory.setBackground(Color.BLACK);
    listcategory.setForeground(Color.WHITE);
setLayout(null);
    add(listcategory);
    add(saveButtom);
    add(cancelButton);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    int x = (screenSize.width - 200) / 2; 
    int y = (screenSize.height - 150) / 2;

    listcategory.setBounds(x, y+30, 160, 30);
    saveButtom.setBounds(x, y + 80, 80, 30);
    cancelButton.setBounds(x + 90, y + 80, 80, 30);

    listcategory.setBackground(Color.BLACK);
    listcategory.setForeground(Color.WHITE);
    frame. add(this);
    ;
    frame.pack();
    saveButtom.addActionListener(this);

}



    public void add_page(){removeAll();

    degesin();
    try {
        listcategory =new JComboBox<>(get_ID_combobox());
    } catch (IOException e) {
listcategory=new JComboBox<>();
    }
    listcategory.addActionListener(this);
    listcategory.setBackground(Color.BLACK);
    listcategory.setForeground(Color.WHITE);

    setLayout(null);


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    add(saveButtom);
    add(cancelButton);
    add(nameField);
    add(nameLabel);
    add(listcategory);

    int x = (screenSize.width - 200) / 2; 
    int y = (screenSize.height - 150) / 2;
    nameLabel.setBounds(x, y-20, 100, 30);
    nameField.setBounds(x + 120, y-20, 100, 30);
    listcategory.setBounds(x, y+30, 160, 30);
    saveButtom.setBounds(x, y + 80, 80, 30);
    cancelButton.setBounds(x + 90, y + 80, 80, 30);

    
    frame. add(this);
     ;
     frame.pack();
     saveButtom.addActionListener(this);
 }
 

public void edit_page(){removeAll();

    degesin();
    try {
        listcategory =new JComboBox<>(get_ID_combobox());
    } catch (IOException e) {
listcategory=new JComboBox<>();
    }
    listcategory.addActionListener(this);
    listcategory.setBackground(Color.BLACK);
    listcategory.setForeground(Color.WHITE);

    setLayout(null);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
add(saveButtom);
add(cancelButton);
add(nameField);
add(nameLabel);
add(listcategory);

    
    int x = (screenSize.width - 200) / 2; 
    int y = (screenSize.height - 150) / 2;
    nameLabel.setBounds(x, y-20, 100, 30);
    nameField.setBounds(x + 120, y-20, 100, 30);
    listcategory.setBounds(x, y+30, 160, 30);
    saveButtom.setBounds(x, y + 80, 80, 30);
    cancelButton.setBounds(x + 90, y + 80, 80, 30);

    frame. add(this);
     ;
     frame.pack();
     saveButtom.addActionListener(this);  
 }

 public String[] get_ID_combobox() throws IOException {
  
    ArrayList<String> categorieList = FileManager.readcategory();
    String[] ids = new String[categorieList.size()];

    for (int i = 0; i < categorieList.size(); i++) {
        ids[i] = categorieList.get(i);
    }

    return ids;
}





@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (backgroundImage != null) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public void  view_page(){removeAll();
    ArrayList<String> categoryList=new ArrayList<>();
    try {degesin();
        categoryList = FileManager.readcategory();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Categories");

        for (String category : categoryList) {
            model.addRow(new Object[]{category});
        }

        JTable table = new JTable(model);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);


 
 
        scrollPane.setBounds(10, 10, 360, 200);
        cancelButton.setBounds(150, 220, 100, 30);

    add(scrollPane);
       add(cancelButton);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
     


      
    frame. add(this);

    frame.pack();
        frame.add(this);
frame.pack();
        frame.setVisible(true);

    } catch (IOException e) {
   
    }

}



public static void main(String[] args) {
    
category b=new category();

b.view_page();

}














public category(String name) {
 
   this.Name = name;
 
}

public String getName() {
    return Name;
}
public void setName(String name) {
    Name = name;
}


@Override
public void actionPerformed(ActionEvent e) {


}

public Image getBackgroundImage() {
    return backgroundImage;
}

public void setBackgroundImage(Image backgroundImage) {
    this.backgroundImage = backgroundImage;
}

public JLabel getNameLabel() {
    return nameLabel;
}

public void setNameLabel(JLabel nameLabel) {
    this.nameLabel = nameLabel;
}

public JTextField getNameField() {
    return nameField;
}

public void setNameField(JTextField nameField) {
    this.nameField = nameField;
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

public void setSaveButtom(JButton saveButtom) {
    this.saveButtom = saveButtom;
}

public JButton getCancelButton() {
    return cancelButton;
}

public void setCancelButton(JButton cancelButton) {
    this.cancelButton = cancelButton;
}



}
