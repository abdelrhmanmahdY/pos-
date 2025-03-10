
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class payment {
    private String paymentID;
    private double amount;
    private String date;
    private String paymentWay;
    private Customer customer;

    public payment(String paymentID, double amount, String date, String paymentWay, Customer customer) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.date = date;
        this.paymentWay = paymentWay;
        this.customer = customer;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void chooseWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public void check() {
        System.out.println("Checking payment...");
        if (amount > 0 && customer != null && paymentID != null && !paymentID.isEmpty()) {
            System.out.println("Payment is valid.");
        } else {
            System.out.println("Payment is invalid.");
        }
    }

    public void displayPaymentInfo() {
        System.out.println("Payment ID: " + paymentID);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + date);
        System.out.println("Payment Way: " + paymentWay);
        System.out.println("Customer: " + customer.getName()); 
    }
    }
