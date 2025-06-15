/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.service;

import com.govindu.w2053082bookstore.exception.AuthorNotFoundException;
import com.govindu.w2053082bookstore.model.Author;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Govin
 */
public class AuthorService {
    private static Map<Integer, Author> authors = new HashMap<>();
    private static int idCounter = 1;

    // Add a new author
    public Author addAuthor(Author author) {
        author.setId(idCounter++);
        authors.put(author.getId(), author);
        return author;
    }

    // Get author by ID
    public Author getAuthorById(int id) throws AuthorNotFoundException {
        Author author = authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
        return author;
    }

    // Get all authors
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }

    // Update author
    public Author updateAuthor(int id, Author author) throws AuthorNotFoundException {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
        author.setId(id);
        authors.put(id, author);
        return author;
    }

    // Delete author
    public void deleteAuthor(int id) throws AuthorNotFoundException {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
        authors.remove(id);
    }
}
