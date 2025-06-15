/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.service;


import com.govindu.w2053082bookstore.exception.BookNotFoundException;
import com.govindu.w2053082bookstore.exception.CartNotFoundException;
import com.govindu.w2053082bookstore.exception.CustomerNotFoundException;
import com.govindu.w2053082bookstore.exception.OutOfStockException;
import com.govindu.w2053082bookstore.model.Cart;
import com.govindu.w2053082bookstore.model.CartItem;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Govin
 */
public class CartService {
    private static Map<Integer, Cart> carts = new HashMap<>();
    private BookService bookService = new BookService();
    private CustomerService customerService = new CustomerService();

    public Cart getCartByCustomerId(int customerId) throws CustomerNotFoundException, CartNotFoundException {
        // Check if customer exists
        if (customerService.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        
        // Return cart if exists, or create new one
        if (!carts.containsKey(customerId)) {
            throw new CartNotFoundException("Cart for customer ID " + customerId + " not found");
        }
        return carts.get(customerId);
    }

    public Cart addItemToCart(int customerId, CartItem item) 
            throws CustomerNotFoundException, BookNotFoundException, OutOfStockException {
        // Validate customer exists
        if (customerService.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        
        // Validate book exists and has sufficient stock
        if (bookService.getBookById(item.getBookId()) == null) {
            throw new BookNotFoundException("Book with ID " + item.getBookId() + " not found");
        }
        
        // Get or create cart
        Cart cart = carts.computeIfAbsent(customerId, k -> new Cart(customerId));
        
        // Add item to cart
        cart.addItem(item);
        return cart;
    }
    
    public Cart updateCartItemQuantity(int customerId, CartItem updatedItem) 
        throws CustomerNotFoundException, CartNotFoundException, BookNotFoundException {
    
        // Validate customer exists
        if (customerService.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }

        Cart cart = getCartByCustomerId(customerId);
    
        // Find and update the item
        for (CartItem item : cart.getItems()) {
            if (item.getBookId() == updatedItem.getBookId()) {
                item.setQuantity(updatedItem.getQuantity());
                return cart;
            }
        }
    
        throw new BookNotFoundException("Book with ID " + updatedItem.getBookId() + " not found in cart");
    }

    public void clearCart(int customerId) throws CustomerNotFoundException {
        if (customerService.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
        carts.remove(customerId);
    }
}
