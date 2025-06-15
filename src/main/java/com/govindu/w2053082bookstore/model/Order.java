/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Govin
 */
public class Order {
    private int id;
    private int customerId;
    private Date orderDate;
    private double totalAmount;
    private List<OrderItem> items;
    
    // date formatter
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    

    // Constructors
    public Order() {
        this.orderDate = new Date();
    }

    public Order(int id, int customerId, double totalAmount, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = new Date();
        this.totalAmount = totalAmount;
        this.items = items;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    //getter for formatted date
    public String getFormattedOrderDate() {
        return DATE_FORMAT.format(orderDate);
    }

    // Change the existing getter to be ignored by JSON serialization
    @JsonIgnore
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
