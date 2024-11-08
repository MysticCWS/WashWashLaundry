package com.example.washwashlaundry;

public class CartItem {
    private String serviceName;
    private int quantity;
    private double price;

    // Default constructor (required for Firebase)
    public CartItem() {
    }

    // Constructor with parameters
    public CartItem(String serviceName, int quantity, double price) {
        this.serviceName = serviceName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter and setter methods
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
