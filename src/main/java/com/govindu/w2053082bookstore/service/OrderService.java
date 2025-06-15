/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.service;

import com.govindu.w2053082bookstore.exception.*;
import com.govindu.w2053082bookstore.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 *
 * @author Govin
 */
public class OrderService {
    private static Map<Integer, Order> orders = new HashMap<>();
    private static int idCounter = 1;
    private BookService bookService = new BookService();
    private CustomerService customerService = new CustomerService();
    private CartService cartService = new CartService();

    public Order placeOrder(int customerId) 
            throws CustomerNotFoundException, CartNotFoundException, 
                   BookNotFoundException, OutOfStockException, InvalidInputException {
        
        // Validate customer exists
        if (customerService.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }

        // Get customer's cart
        Cart cart = cartService.getCartByCustomerId(customerId);
        if (cart.getItems().isEmpty()) {
            throw new InvalidInputException("Cannot place order with empty cart");
        }

        // Convert cart items to order items and calculate total
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (CartItem cartItem : cart.getItems()) {
            Book book = bookService.getBookById(cartItem.getBookId());
            
            // Check stock availability
             if (book.getStock() < cartItem.getQuantity()) {
                throw new OutOfStockException(
                    "Not enough stock for book: " + book.getTitle() + "\n" +
                    "Only available copies: " + book.getStock()
                );
            }
            
            // Create order item
            OrderItem orderItem = new OrderItem(
                book.getId(),
                book.getTitle(),
                cartItem.getQuantity(),
                book.getPrice()
            );
            orderItems.add(orderItem);
            totalAmount += (book.getPrice() * cartItem.getQuantity());
            
            // Update book stock
            book.setStock(book.getStock() - cartItem.getQuantity());
            bookService.updateBookStock(
                book.getId(), 
                book.getStock() - cartItem.getQuantity()
            );
        }

        // Create and save order
        Order order = new Order(idCounter++, customerId, totalAmount, orderItems);
        orders.put(order.getId(), order);
        
        // Clear the cart
        cartService.clearCart(customerId);
        
        return order;
    }

    public List<Order> getOrdersByCustomer(int customerId) throws CustomerNotFoundException {
        if (customerService.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        
        return orders.values().stream()
            .filter(order -> order.getCustomerId() == customerId)
            .collect(Collectors.toList());
    }

    public Order getOrderById(int customerId, int orderId) 
            throws CustomerNotFoundException, OrderNotFoundException {
        
        if (customerService.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        
        Order order = orders.get(orderId);
        if (order == null || order.getCustomerId() != customerId) {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found for customer");
        }
        
        return order;
    }
}