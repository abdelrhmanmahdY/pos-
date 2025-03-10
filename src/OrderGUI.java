import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.JTableHeader;

public class OrderGUI extends JFrame {

    private JTextField orderIdField, customerField, dateField, employeeField, quantityField, priceField, totalPriceField;
    private JComboBox<String> itemIdComboBox;
    private JButton addItemButton, calculateTotalButton, printOrderButton, saveButton, loadButton;
    private ArrayList<OrderItem> orderItemsList;
    private double totalAmount;
    private JTable orderItemsTable;
    private DefaultTableModel tableModel;
    private TreeMap<String, Double> itemPrices;

    public OrderGUI() {
        setTitle("Order Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.setBackground(new Color(50, 50, 50));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Initialize text fields
        orderIdField = createTextField();
        customerField = createTextField();
        dateField = createTextField();
        employeeField = createTextField();
        quantityField = createTextField();
        priceField = createTextField();
        priceField.setEditable(false); // Make priceField read-only
        totalPriceField = createTextField();
        totalPriceField.setEditable(false);

        // Initialize JComboBox for items
        itemIdComboBox = new JComboBox<>();
        populateItemComboBox(itemIdComboBox);

        // Initialize buttons
        addItemButton = createButton("Add Item");
        calculateTotalButton = createButton("Calculate Total");
        printOrderButton = createButton("Print Order");
        saveButton = createButton("Save Order");
        loadButton = createButton("Load Order");

        // Order items list
        orderItemsList = new ArrayList<>();

        // Table model and table
        tableModel = new DefaultTableModel(new String[]{"Item", "Quantity", "Price", "Total Price"}, 0);
        orderItemsTable = new JTable(tableModel);
        JTableHeader header = orderItemsTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        JScrollPane tableScrollPane = new JScrollPane(orderItemsTable);

        // Add components to input panel
        addInputComponents(inputPanel);

        // Add buttons to button panel
        buttonPanel.add(addItemButton);
        buttonPanel.add(calculateTotalButton);
        buttonPanel.add(printOrderButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        addActionListeners();

        // Add action listener to JComboBox to update price field when item is selected
        itemIdComboBox.addActionListener(e -> updatePriceField());
    }

    private void addInputComponents(JPanel panel) {
        panel.add(createStyledLabel("Order ID:"));
        panel.add(orderIdField);
        panel.add(createStyledLabel("Customer:"));
        panel.add(customerField);
        panel.add(createStyledLabel("Date:"));
        panel.add(dateField);
        panel.add(createStyledLabel("Employee:"));
        panel.add(employeeField);
        panel.add(createStyledLabel("Item:"));
        panel.add(itemIdComboBox);
        panel.add(createStyledLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(createStyledLabel("Price:"));
        panel.add(priceField);
        panel.add(createStyledLabel("Total Price:"));
        panel.add(totalPriceField);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        return button;
    }

    private void addActionListeners() {
        addItemButton.addActionListener(e -> addItem());
        calculateTotalButton.addActionListener(e -> calculateTotal());
        printOrderButton.addActionListener(e -> printOrder());
        saveButton.addActionListener(e -> saveOrder());
        loadButton.addActionListener(e -> loadOrder());
    }

    private void addItem() {
        try {
            String itemId = (String) itemIdComboBox.getSelectedItem();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = itemPrices.get(itemId);
            double totalPrice = quantity * price;

            OrderItem newItem = new OrderItem(itemId, quantity, price, totalPrice);
            orderItemsList.add(newItem);
            tableModel.addRow(new Object[]{itemId, quantity, price, totalPrice});
            clearItemFields();
            updateTotalPrice();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateTotal() {
        totalAmount = orderItemsList.stream().mapToDouble(OrderItem::getTotalPrice).sum();
        JOptionPane.showMessageDialog(this, "Total Amount: " + formatAmount(totalAmount));
    }

    private void printOrder() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("<html><body style='font-family: Arial, sans-serif;'>");
        invoice.append("<h2>INVOICE</h2><hr>");
        invoice.append("<p>Order ID: ").append(orderIdField.getText()).append("</p>");
        invoice.append("<p>Customer: ").append(customerField.getText()).append("</p>");
        invoice.append("<p>Date: ").append(dateField.getText()).append("</p>");
        invoice.append("<p>Employee: ").append(employeeField.getText()).append("</p><hr>");
        invoice.append("<table border='1' style='width:100%; text-align:left;'><tr><th>Item ID</th><th>Quantity</th><th>Price</th><th>Total Price</th></tr>");

        for (OrderItem item : orderItemsList) {
            invoice.append("<tr><td>").append(item.getItemId()).append("</td><td>").append(item.getQuantity()).append("</td><td>").append(item.getPrice()).append("</td><td>").append(item.getTotalPrice()).append("</td></tr>");
        }

        invoice.append("</table><hr>");
        invoice.append("<p>Total Amount: ").append(formatAmount(totalAmount)).append("</p>");
        invoice.append("</body></html>");

        JOptionPane.showMessageDialog(this, invoice.toString(), "Invoice", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveOrder() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("orderData.bin"))) {
            oos.writeObject(orderItemsList);
            JOptionPane.showMessageDialog(this, "Order saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadOrder() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("orderData.bin"))) {
            orderItemsList = (ArrayList<OrderItem>) ois.readObject();
            tableModel.setRowCount(0); // Clear existing rows
            for (OrderItem item : orderItemsList) {
                tableModel.addRow(new Object[]{item.getItemId(), item.getQuantity(), item.getPrice(), item.getTotalPrice()});
            }
            JOptionPane.showMessageDialog(this, "Order loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearItemFields() {
        itemIdComboBox.setSelectedIndex(0);
        quantityField.setText("");
        priceField.setText("");
        totalPriceField.setText("");
    }

    private void updatePriceField() {
        String selectedItemId = (String) itemIdComboBox.getSelectedItem();
        if (selectedItemId != null && itemPrices.containsKey(selectedItemId)) {
            priceField.setText(String.valueOf(itemPrices.get(selectedItemId)));
        }
    }

    private void updateTotalPrice() {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            double totalPrice = quantity * price;
            totalPriceField.setText(String.valueOf(totalPrice));
        } catch (NumberFormatException ex) {
            totalPriceField.setText("");
        }
    }

    private String formatAmount(double amount) {
        return new DecimalFormat("#.##").format(amount);
    }

    private void populateItemComboBox(JComboBox<String> comboBox) {
        try {
            TreeMap<Integer, ArrayList<String>> items = readItems();
            itemPrices = new TreeMap<>();
            for (Map.Entry<Integer, ArrayList<String>> entry : items.entrySet()) {
                String itemId = entry.getValue().get(0);
                double itemPrice = Double.parseDouble(entry.getValue().get(1));
                comboBox.addItem(itemId);
                itemPrices.put(itemId, itemPrice);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading items: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static TreeMap<Integer, ArrayList<String>> readItems() throws IOException {
        File file = new File("item.dat");
        try {
            if (file.exists()) {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                TreeMap<Integer, ArrayList<String>> itemList = (TreeMap<Integer, ArrayList<String>>) objectInputStream.readObject();
                objectInputStream.close();
                return itemList;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new TreeMap<>();
    }

    public static void main(String[] args) {
  new OrderGUI().setVisible(true);
    }

    static class OrderItem implements Serializable {
        private final String itemId;
        private final int quantity;
        private final double price;
        private final double totalPrice;

        public OrderItem(String itemId, int quantity, double price, double totalPrice) {
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
            this.totalPrice = totalPrice;
        }

        public String getItemId() {
            return itemId;
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
}
