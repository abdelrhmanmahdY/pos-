

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerForm extends JFrame {
    private JTextField nameField;
    private JTextField idField;
    private JTextField phoneField;
    private JButton saveButton;
    private JTextArea displayArea;

    private Customer currentCustomer;

    public CustomerForm() {
        super("Customer Management");

        setLayout(new BorderLayout());

        nameField = new JTextField(20);
        idField = new JTextField(20);
        phoneField = new JTextField(20);
        saveButton = new JButton("Save");
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(phoneField);

        add(inputPanel, BorderLayout.NORTH);
        add(saveButton, BorderLayout.CENTER);
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveCustomer();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveCustomer() {
        String name = nameField.getText();
        String id = idField.getText();
        String phone = phoneField.getText();

        if (!name.isEmpty() && !id.isEmpty() && !phone.isEmpty()) {
            currentCustomer = new Customer(name, id,phone);
            currentCustomer.setPhoneNumber(phone);
            displayCustomerInfo();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter name, ID, and phone number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayCustomerInfo() {
        if (currentCustomer != null) {
            displayArea.setText("Name: " + currentCustomer.getName() + "\n" +
                                "ID: " + currentCustomer.getId() + "\n" +
                                "Phone Number: " + currentCustomer.getPhoneNumber()); 
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustomerForm();
            }
        });
    }
}
