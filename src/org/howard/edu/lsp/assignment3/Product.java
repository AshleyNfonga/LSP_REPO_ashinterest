package org.howard.edu.lsp.assignment3;

/**
 * Represents a product record.
 */
public class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    /** Convert to CSV row */
    public String toCsvRow() {
        return String.format("%s,%s,%.2f", id, name, price);
    }
}
