/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.resource;

import com.govindu.w2053082bookstore.exception.CustomerNotFoundException;
import com.govindu.w2053082bookstore.model.Customer;
import com.govindu.w2053082bookstore.service.CustomerService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author Govin
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private CustomerService customerService = new CustomerService();

    @POST
    public Response addCustomer(Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(newCustomer).build();
    }

    @GET
    public Response getAllCustomers() {
        return Response.ok(customerService.getAllCustomers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                return Response.ok(customer).build();
            }
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            return Response.ok(updatedCustomer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        if (customerService.deleteCustomer(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
