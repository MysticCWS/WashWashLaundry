package com.example.washwashlaundry;

public class Order {
    private String id;
    private String customerName;
    private String serviceType;
    private int weight;

    // Empty constructor required for Firebase
    public Order() {}

    public Order(String id, String customerName, String serviceType, int weight) {
        this.id = id;
        this.customerName = customerName;
        this.serviceType = serviceType;
        this.weight = weight;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
}