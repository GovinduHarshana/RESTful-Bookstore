/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.resource;

import com.govindu.w2053082bookstore.exception.AuthorNotFoundException;
import com.govindu.w2053082bookstore.service.AuthorService;
import com.govindu.w2053082bookstore.model.Author;
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
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private AuthorService authorService = new AuthorService();

    @POST
    public Response addAuthor(Author author) {
        Author newAuthor = authorService.addAuthor(author);
        return Response.status(Response.Status.CREATED).entity(newAuthor).build();
    }

    @GET
    public Response getAllAuthors() {
        return Response.ok(authorService.getAllAuthors()).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") int id) {
        try {
            Author author = authorService.getAuthorById(id);
            return Response.ok(author).build();
        } catch (AuthorNotFoundException e) {
            throw e;
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author author) {
        try {
            Author updatedAuthor = authorService.updateAuthor(id, author);
            return Response.ok(updatedAuthor).build();
        } catch (AuthorNotFoundException e) {
            throw e;
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        try {
            authorService.deleteAuthor(id);
            return Response.noContent().build();
        } catch (AuthorNotFoundException e) {
            throw e;
        }
    }
}
