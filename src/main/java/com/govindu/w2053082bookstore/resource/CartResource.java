/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.resource;

import com.govindu.w2053082bookstore.exception.BookNotFoundException;
import com.govindu.w2053082bookstore.exception.CartNotFoundException;
import com.govindu.w2053082bookstore.exception.CustomerNotFoundException;
import com.govindu.w2053082bookstore.exception.OutOfStockException;
import com.govindu.w2053082bookstore.model.Cart;
import com.govindu.w2053082bookstore.model.CartItem;
import com.govindu.w2053082bookstore.service.CartService;
import javax.ws.rs.BadRequestException;
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
@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private CartService cartService = new CartService();

    @POST
    @Path("/items")
    public Response addItemToCart(@PathParam("customerId") int customerId, CartItem item) {
        try {
            Cart cart = cartService.addItemToCart(customerId, item);
            return Response.status(Response.Status.CREATED).entity(cart).build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (OutOfStockException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response getCart(@PathParam("customerId") int customerId) {
        try {
            Cart cart = cartService.getCartByCustomerId(customerId);
            return Response.ok(cart).build();
        } catch (CustomerNotFoundException | CartNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    @PUT
    @Path("/items/{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCartItem(
        @PathParam("customerId") int customerId,
        @PathParam("bookId") int bookId,
        CartItem cartItem) 
        throws CustomerNotFoundException, CartNotFoundException, BookNotFoundException {
    
        // Verify path bookId matches body bookId
        if (cartItem.getBookId() != bookId) {
            throw new BadRequestException("Book ID in path does not match Book ID in body");
        }
    
    Cart updatedCart = cartService.updateCartItemQuantity(customerId, cartItem);
    return Response.ok(updatedCart).build();
}

    @DELETE
    @Path("/items/{bookId}")
    public Response removeItemFromCart(@PathParam("customerId") int customerId, 
                                     @PathParam("bookId") int bookId) {
        // Implementation would go here
        return Response.noContent().build();
    }

    @DELETE
    public Response clearCart(@PathParam("customerId") int customerId) {
        try {
            cartService.clearCart(customerId);
            return Response.noContent().build();
        } catch (CustomerNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
