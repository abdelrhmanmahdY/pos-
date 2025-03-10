


import java.util.ArrayList;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String customer;
    private String date;
    private String employee;
    private ArrayList<orderItem> orderItems;
    private double totalAmount;

    public Order(String orderId, String customer, String date, String employee, ArrayList<orderItem> orderItems) {
        this.orderId = orderId;
        this.customer = customer;
        this.date = date;
        this.employee = employee;
        this.orderItems = orderItems;
     
    }

    public Order(String orderId, String customer, String date, String employee) {
        this.orderId = orderId;
        this.customer = customer;
        this.date = date;
        this.employee = employee;
        this.orderItems = new ArrayList<orderItem>();
        this.totalAmount = 0.0;
    }

    public void setOrderItems(ArrayList<orderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void calculateTotal() {
        totalAmount = 0.0;
        for (orderItem item : orderItems) {
            totalAmount += item.getTotalPrice();
        }
    }

    public void printOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer);
        System.out.println("Date: " + date);
        System.out.println("Employee: " + employee);
        System.out.println("Order Items:");
        for (orderItem item : orderItems) {
            System.out.println("  - " + item.getOrderItemId() + " | Quantity: " + item.getQuantity() + " | Total Price: " + item.getTotalPrice());
        }
        System.out.println("Total Amount: " + totalAmount);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public ArrayList<orderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
