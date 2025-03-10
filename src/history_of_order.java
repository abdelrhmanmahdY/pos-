

import java.io.Serializable;
import java.util.ArrayList;

public class history_of_order implements Serializable {
    private ArrayList<Order> orders;

    public history_of_order() {
        this.orders = new ArrayList<Order>();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void add(Order order) {
        orders.add(order);
    }

    public void edit(Order oldOrder, Order newOrder) {
        int index = orders.indexOf(oldOrder);
        if (index != -1) {
            orders.set(index, newOrder);
        } else {
            System.out.println("Order not found.");
        }
    }

    public void remove(Order order) {
        orders.remove(order);
    }

    public Order findOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}
