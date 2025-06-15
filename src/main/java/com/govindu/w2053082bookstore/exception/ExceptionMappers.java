/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Govin
 */

public class ExceptionMappers {
    
    @Provider
    public static class BookNotFoundExceptionMapper implements ExceptionMapper<BookNotFoundException> {
        @Override
        public Response toResponse(BookNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Book Not Found\",\"message\":\"" + 
                           escapeJson(exception.getMessage()) + "\"}")
                    .type("application/json")
                    .build();
        }
    }

    @Provider
    public static class AuthorNotFoundExceptionMapper implements ExceptionMapper<AuthorNotFoundException> {
        @Override
        public Response toResponse(AuthorNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Author Not Found\",\"message\":\"" + 
                           escapeJson(exception.getMessage()) + "\"}")
                    .type("application/json")
                    .build();
        }
    }

    @Provider
    public static class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException> {
        @Override
        public Response toResponse(CustomerNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Customer Not Found\",\"message\":\"" + 
                           escapeJson(exception.getMessage()) + "\"}")
                    .type("application/json")
                    .build();
        }
    }

    @Provider
    public static class CartNotFoundExceptionMapper implements ExceptionMapper<CartNotFoundException> {
        @Override
        public Response toResponse(CartNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Cart Not Found\",\"message\":\"" + 
                           escapeJson(exception.getMessage()) + "\"}")
                    .type("application/json")
                    .build();
        }
    }

    @Provider
    public static class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {
        @Override
        public Response toResponse(OutOfStockException exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Out of Stock\",\"message\":\"" + 
                           escapeJson(exception.getMessage()) + "\"}")
                    .type("application/json")
                    .build();
        }
    }

    @Provider
    public static class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
        @Override
        public Response toResponse(InvalidInputException exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Invalid Input\",\"message\":\"" + 
                           escapeJson(exception.getMessage()) + "\"}")
                    .type("application/json")
                    .build();
        }
    }
    
    @Provider
    public static class OrderNotFoundExceptionMapper implements ExceptionMapper<OrderNotFoundException> {
        @Override
        public Response toResponse(OrderNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Order Not Found\",\"message\":\"" + 
                           escapeJson(exception.getMessage()) + "\"}")
                    .type("application/json")
                    .build();
        }
    }
    
    @Provider
    public static class InvalidISBNExceptionMapper implements ExceptionMapper<InvalidISBNException> {
        @Override
        public Response toResponse(InvalidISBNException exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Invalid ISBN\",\"message\":\"" + exception.getMessage() + "\"}")
                    .type("application/json")
                    .build();
        }
    }
    
    @Provider
    public static class InvalidPublicationYearExceptionMapper implements ExceptionMapper<InvalidPublicationYearException> {
        @Override
        public Response toResponse(InvalidPublicationYearException exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Invalid Publication Year\",\"message\":\"" + exception.getMessage() + "\"}")
                    .type("application/json")
                    .build();
        }
    }

    // Helper method to escape JSON strings
    private static String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}
