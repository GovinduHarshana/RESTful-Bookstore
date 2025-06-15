/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.resource;

import com.govindu.w2053082bookstore.exception.*;
import com.govindu.w2053082bookstore.model.Order;
import com.govindu.w2053082bookstore.service.OrderService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/**
 *
 * @author Govin
 */
@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private OrderService orderService = new OrderService();

    @POST
    public Response placeOrder(@PathParam("customerId") int customerId) {
        try {
            Order order = orderService.placeOrder(customerId);
            return Response.status(Response.Status.CREATED).entity(order).build();
        } catch (CustomerNotFoundException | CartNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (BookNotFoundException | OutOfStockException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InvalidInputException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getCustomerOrders(@PathParam("customerId") int customerId) {
        try {
            List<Order> orders = orderService.getOrdersByCustomer(customerId);
            return Response.ok(orders).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("customerId") int customerId, 
                           @PathParam("orderId") int orderId) {
        try {
            Order order = orderService.getOrderById(customerId, orderId);
            return Response.ok(order).build();
        } catch (CustomerNotFoundException | OrderNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
