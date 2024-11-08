package com.example.washwashlaundry;

public class Service {

    private String name;
    private double price;

    public Service() {
        // No-argument constructor for Firebase
    }

    // Constructor
    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}