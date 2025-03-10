

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentGUI extends JFrame {
    private JTextField paymentIDField;
    private JTextField amountField;
    private JTextField dateField;
    private JCheckBox cashCheckBox;
    private JCheckBox creditCardCheckBox;
    private JTextField customerNameField;
    private JTextField phoneNumberField;
    private JTextField IDField;
    private JButton checkButton;
    private JButton changeWayButton;
    private JTextArea outputArea;

    private payment currentPayment; // Update to use Payment class

    public PaymentGUI() {
        super("Payment Management");

        setLayout(new BorderLayout());

        paymentIDField = new JTextField(10);
        amountField = new JTextField(10);
        dateField = new JTextField(10);
        cashCheckBox = new JCheckBox("Cash");
        creditCardCheckBox = new JCheckBox("Credit Card");
        customerNameField = new JTextField(10);
        phoneNumberField = new JTextField(10);
        IDField = new JTextField(10);

        checkButton = new JButton("Check Payment");
        changeWayButton = new JButton("Change Payment Way");

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.add(new JLabel("Payment ID:"));
        inputPanel.add(paymentIDField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Date:"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Payment Way:"));
        JPanel paymentWayPanel = new JPanel();
        paymentWayPanel.add(cashCheckBox);
        paymentWayPanel.add(creditCardCheckBox);
        inputPanel.add(paymentWayPanel);
        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(customerNameField);
        inputPanel.add(new JLabel("Phone Number:")); 
        inputPanel.add(phoneNumberField); 
        inputPanel.add(new JLabel("Customer ID:")); 
        inputPanel.add(IDField);
        

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkButton);
        buttonPanel.add(changeWayButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createPayment();
            }
        });
        cashCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cashCheckBox.isSelected()) {
                    creditCardCheckBox.setSelected(false);
                }
            }
        });

        creditCardCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (creditCardCheckBox.isSelected()) {
                    cashCheckBox.setSelected(false);
                }
            }
        });

        changeWayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePaymentWay();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createPayment() {
        // Get input values
        String paymentID = paymentIDField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String date = dateField.getText();
        String paymentWay = "";
        if (cashCheckBox.isSelected()) {
            paymentWay = "Cash";
        } else if (creditCardCheckBox.isSelected()) {
            paymentWay = "Credit Card";
        }
        String customerName = customerNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String ID = IDField.getText();

        Customer customer = new Customer(customerName, phoneNumber,ID);

        payment newPayment = new payment(paymentID, amount, date, paymentWay, customer);
        setCurrentPayment(newPayment);
    }

    private void changePaymentWay() {
        if (currentPayment == null) {
            outputArea.setText("No payment information available.");
            return;
        }

        String newPaymentWay = "";
        if (cashCheckBox.isSelected()) {
            newPaymentWay = "Cash";
        } else if (creditCardCheckBox.isSelected()) {
            newPaymentWay = "Credit Card";
        }

        if (!newPaymentWay.isEmpty()) {
            currentPayment.chooseWay(newPaymentWay);
            displayPaymentInfo();
        } else {
            JOptionPane.showConfirmDialog(null, "Please select a payment way.","inf",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void displayPaymentInfo() {
        if (currentPayment != null) {
            outputArea.setText("Payment ID: " + currentPayment.getPaymentID() + "\n" +
                    "Amount: " + currentPayment.getAmount() + "\n" +
                    "Date: " + currentPayment.getDate() + "\n" +
                    "Payment Way: " + currentPayment.getPaymentWay() + "\n" +
                    "Customer: " + currentPayment.getCustomer().getName() + "\n" +
                    "Phone Number: " + currentPayment.getCustomer().getPhoneNumber() + "\n"+
                    "Customer ID: " + currentPayment.getCustomer().getId());
        }
    }

    public void setCurrentPayment(payment payment) {
        this.currentPayment = payment;
        paymentIDField.setText(payment.getPaymentID());
        amountField.setText(String.valueOf(payment.getAmount()));
        dateField.setText(payment.getDate());
        if (payment.getPaymentWay().equals("Cash")) {
            cashCheckBox.setSelected(true);
            creditCardCheckBox.setSelected(false);
        } else if (payment.getPaymentWay().equals("Credit Card")) {
            cashCheckBox.setSelected(false);
            creditCardCheckBox.setSelected(true);
        }
        customerNameField.setText(payment.getCustomer().getName());
        phoneNumberField.setText(payment.getCustomer().getPhoneNumber()); // Set phone number
        displayPaymentInfo();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaymentGUI();
            }
        });
    }

}
