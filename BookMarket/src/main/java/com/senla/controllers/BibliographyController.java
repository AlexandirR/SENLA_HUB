package com.senla.controllers;

import annitations.Inject;
import com.senla.model.Book;
import com.senla.service.BookService;

import java.io.IOException;
import java.util.List;

public class BibliographyController {

    private static final String EXCEPTION_BOOK_MESSAGE_IO = "Не найдена библиотека";

    @Inject
    private BookService bookService;

    public List<Book> getSortedBooks(String param) {
        try {
            return bookService.getSortedBooks(param);
        } catch (IOException e) {
            System.out.println(EXCEPTION_BOOK_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public Book getBookByName(String name) {
        try {
            return bookService.getBookByName(name);
        } catch (IOException e) {
            System.out.println(EXCEPTION_BOOK_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public Book getBookById(Integer id) {
        try {
            return bookService.getBookById(id);
        } catch (IOException e) {
            System.out.println(EXCEPTION_BOOK_MESSAGE_IO);
            throw new RuntimeException();
        }
    }
}
