import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.*;

public class emplyee extends user {

    JButton make_order =new JButton("make order");
    JPanel panel_emplyee =new BackgroundPanel("item.jpg");
    JButton order_history=new JButton("history");
    JButton payment=new JButton("payment");
    public emplyee() {
        remove(panel);
        setTitle("emplyee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700,500));
        ImageIcon markertIcon=new ImageIcon("store.jpg");
        setIconImage(markertIcon.getImage());;
        add(panel_emplyee);
        panel_emplyee.add(make_order);
        panel_emplyee.add(order_history);
        panel_emplyee.add(payment);
        make_order.setBackground(Color.BLACK);
        make_order.setForeground(Color.WHITE);
        order_history.setBackground(Color.BLACK);
        order_history.setForeground(Color.WHITE);
        payment.setBackground(Color.BLACK);
        payment.setForeground(Color.WHITE);
        setVisible(true);
        make_order.addActionListener(this);
        order_history.addActionListener(this);
        payment.addActionListener(this);
    pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==make_order){
            OrderGUI gui;
            try {
                gui = new OrderGUI();
                gui.setVisible(true);
            } catch (Exception  ex) {
    
            }
                
        }
        if (e.getSource()==order_history){
            new history_of_order_gui().setVisible(true);;
        }
   if (e.getSource()==payment){
    new PaymentGUI().setVisible(true);;
   }
    }

public static void main(String[] args) {
    new emplyee();
}
}
