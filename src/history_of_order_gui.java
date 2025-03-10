

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class history_of_order_gui extends JFrame {
    private history_of_order orderHistory;
    private JTextArea orderTextArea;
    private JButton viewOrderButton, addButton, editButton, removeButton, loadDataButton;
    private JTextField orderIdField;

    public history_of_order_gui() {
        super("History of Order Management");
        orderHistory = new history_of_order();

        orderTextArea = new JTextArea(20, 30);
        orderTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderTextArea);
        viewOrderButton = new JButton("View Order");
        addButton = new JButton("Add Order");
        editButton = new JButton("Edit Order");
        removeButton = new JButton("Remove Order");
        loadDataButton = new JButton("Load Data");
        orderIdField = new JTextField(10);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Order ID: "));
        inputPanel.add(orderIdField);
        inputPanel.add(viewOrderButton);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(loadDataButton);
        add(buttonPanel, BorderLayout.SOUTH);

        viewOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String orderId = orderIdField.getText();
                Order order = orderHistory.findOrderById(orderId);
                if (order != null) {
                    displayOrderDetails(order);
                } else {
                    JOptionPane.showMessageDialog(history_of_order_gui.this,
                                                  "Order with ID " + orderId + " not found!", "Order Not Found",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Order newOrder = getOrderDetailsFromUser();
                if (newOrder != null) {
                    orderHistory.add(newOrder);
                    JOptionPane.showMessageDialog(history_of_order_gui.this,
                                                  "Order added successfully!", "Add Order",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    displayOrderDetails(newOrder);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String orderId = orderIdField.getText();
                Order oldOrder = orderHistory.findOrderById(orderId);
                if (oldOrder != null) {
                    Order newOrder = getOrderDetailsFromUser();
                    if (newOrder != null) {
                        orderHistory.edit(oldOrder, newOrder);
                        JOptionPane.showMessageDialog(history_of_order_gui.this,
                                                      "Order with ID " + orderId + " edited successfully!", "Edit Order",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        displayOrderDetails(newOrder);
                    }
                } else {
                    JOptionPane.showMessageDialog(history_of_order_gui.this,
                                                  "Order with ID " + orderId + " not found!", "Order Not Found",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String orderId = orderIdField.getText();
                Order order = orderHistory.findOrderById(orderId);
                if (order != null) {
                    orderHistory.remove(order);
                    JOptionPane.showMessageDialog(history_of_order_gui.this,
                                                  "Order with ID " + orderId + " removed!", "Remove Order",
                                                  JOptionPane.INFORMATION_MESSAGE);
                    orderTextArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(history_of_order_gui.this,
                                                  "Order with ID " + orderId + " not found!", "Order Not Found",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loadDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void displayOrderDetails(Order order) {
        orderTextArea.setText("Order ID: " + order.getOrderId() + "\n" +
                "Customer: " + order.getCustomer() + "\n" +
                "Date: " + order.getDate() + "\n" +
                "Employee: " + order.getEmployee() + "\n" +
                "Items: \n");
        for (orderItem item : order.getOrderItems()) {
            orderTextArea.append("- " + item.getOrderItemId() + ": " + item.getQuantity() + "\n");
        }
    }

    private void loadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("orderData.bin"));
            orderHistory = (history_of_order) ois.readObject();
            ois.close();
            JOptionPane.showMessageDialog(this, "Data loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e){
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    private Order getOrderDetailsFromUser() {
        JTextField orderIdField = new JTextField(10);
        JTextField customerField = new JTextField(10);
        JTextField dateField = new JTextField(10);
        JTextField employeeField = new JTextField(10);
        JTextField itemsField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Order ID:"));
        panel.add(orderIdField);
        panel.add(new JLabel("Customer:"));
        panel.add(customerField);
        panel.add(new JLabel("Date:"));
        panel.add(dateField);
        panel.add(new JLabel("Employee:"));
        panel.add(employeeField);
        panel.add(new JLabel("Items (comma separated):"));
        panel.add(itemsField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Order Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String orderId = orderIdField.getText().trim();
            String customer = customerField.getText().trim();
            String date = dateField.getText().trim();
            String employee = employeeField.getText().trim();
            String[] itemArray = itemsField.getText().split(",");
            ArrayList<orderItem> orderItems = new ArrayList<orderItem>();
            for (String item : itemArray) {
                orderItems.add(new orderItem(item.trim(), 1, 0, 0)); 
            }
            return new Order(orderId, customer, date, employee, orderItems);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
      
                new history_of_order_gui();
     
    }
}
