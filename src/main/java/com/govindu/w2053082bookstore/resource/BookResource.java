/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.resource;


import com.govindu.w2053082bookstore.exception.*;
import com.govindu.w2053082bookstore.service.BookService;
import com.govindu.w2053082bookstore.model.Book;
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
@Path("/books")      // Base path
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private BookService bookService = new BookService();

    @POST
    public Response addBook(Book book) throws AuthorNotFoundException, 
                                        InvalidISBNException, 
                                        InvalidPublicationYearException {
        Book newBook = bookService.addBook(book);
        return Response.status(Response.Status.CREATED).entity(newBook).build();
    }

    @GET
    public Response getAllBooks() {
        return Response.ok(bookService.getAllBooks()).build();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") int id) {
        try {
            Book book = bookService.getBookById(id);
            return Response.ok(book).build();
        } catch (BookNotFoundException e) {
            throw e;
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book book) throws BookNotFoundException,
                                                                    AuthorNotFoundException,
                                                                    InvalidISBNException,
                                                                    InvalidPublicationYearException {
        Book updatedBook = bookService.updateBook(id, book);
        return Response.ok(updatedBook).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        try {
            bookService.deleteBook(id);
            return Response.noContent().build();
        } catch (BookNotFoundException e) {
            throw e;
        }
    }
}
