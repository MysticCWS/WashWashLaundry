package com.example.washwashlaundry;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    // Default constructor
    public Cart() {
        cartItems = new ArrayList<>();
    }

    // Add an item to the cart
    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    // Remove an item from the cart
    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }

    // Get all items in the cart
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Calculate the total price of the cart
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}