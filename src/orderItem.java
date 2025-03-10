

import java.io.Serializable;

public class orderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderItemId;
    private int quantity;
    private double price;
    private double totalPrice;

    public orderItem(String orderItemId, int quantity, double price, double totalPrice) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
