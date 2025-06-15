/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.service;
import com.govindu.w2053082bookstore.model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Govin
 */
public class CustomerService {
    private static Map<Integer, Customer> customers = new HashMap<>();
    private static int idCounter = 1;

    public Customer addCustomer(Customer customer) {
        customer.setId(idCounter++);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer updateCustomer(int id, Customer customer) {
        if (customers.containsKey(id)) {
            customer.setId(id);
            customers.put(id, customer);
            return customer;
        }
        return null;
    }

    public boolean deleteCustomer(int id) {
        return customers.remove(id) != null;
    }
}
