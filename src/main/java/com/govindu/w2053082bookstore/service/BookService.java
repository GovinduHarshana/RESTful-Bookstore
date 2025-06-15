/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.govindu.w2053082bookstore.service;

import com.govindu.w2053082bookstore.exception.*;
import com.govindu.w2053082bookstore.model.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Govin
 */
public class BookService {
    private static Map<Integer, Book> books = new HashMap<>();
    private static int idCounter = 1;
    private AuthorService authorService = new AuthorService();

    // Add a new book with validation
    public Book addBook(Book book) throws AuthorNotFoundException, InvalidISBNException, 
                                         InvalidPublicationYearException {
        validateBook(book);
        book.setId(idCounter++);
        books.put(book.getId(), book);
        return book;
    }

    // Get book by ID
    public Book getBookById(int id) throws BookNotFoundException {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        return book;
    }

    // Get all books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    // Update book with validation
    public Book updateBook(int id, Book book) throws BookNotFoundException, 
                                                   AuthorNotFoundException,
                                                   InvalidISBNException,
                                                   InvalidPublicationYearException {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        validateBook(book);
        book.setId(id);
        books.put(id, book);
        return book;
    }
    
    public void updateBookStock(int bookId, int newStock) throws BookNotFoundException {
        Book book = getBookById(bookId);
        book.setStock(newStock);
}

    // Delete book
    public void deleteBook(int id) throws BookNotFoundException {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        books.remove(id);
    }

    // Validation logic
    private void validateBook(Book book) throws AuthorNotFoundException, 
                                              InvalidISBNException,
                                              InvalidPublicationYearException {
        // 1. Validate author exists
        if (authorService.getAuthorById(book.getAuthorId()) == null) {
            throw new AuthorNotFoundException("Author with ID " + book.getAuthorId() + " does not exist.");
        }

        // 2. Validate ISBN (13 digits)
        if (!book.getIsbn().matches("^\\d{13}$")) {
            throw new InvalidISBNException("An ISBN must be a 13-digit number");
        }

        // 3. Validate publication year
        int currentYear = java.time.Year.now().getValue();
        if (book.getPublicationYear() > currentYear) {
            throw new InvalidPublicationYearException("Publication year cannot be in the future.");
        }
    }
}
