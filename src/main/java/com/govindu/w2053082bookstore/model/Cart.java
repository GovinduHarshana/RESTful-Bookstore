/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Govin
 */
public class Cart {
    private int customerId;
    private List<CartItem> items;

    // Constructors
    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(int customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }
        
}
