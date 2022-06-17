package com.senla.controllers;

import annitations.Inject;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.service.BookService;
import com.senla.service.OrderService;
import com.senla.service.QueryService;

import java.io.IOException;
import java.util.List;

public class BookController {

    private static final String EXCEPTION_BOOK_MESSAGE_IO = "Не найдена библиотека";
    private static final String EXCEPTION_ORDER_MESSAGE_IO = "Не найдена история заказов";
    private static final String EXCEPTION_REQUEST_MESSAGE_IO = "Не найдена история запросов на книги";

    @Inject
    private BookService bookService;
    @Inject
    private OrderService orderService;
    @Inject
    private QueryService queryService;

    public void writeOfBook(Book book) {
        try {
            bookService.writeOfBook(book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_BOOK_MESSAGE_IO);
            throw new RuntimeException();
        }
        try {
            List<Order> orders = orderService.closeBook(book);
            try {
                for (Order order : orders) {
                    queryService.addQuery(order, book);
                }
            } catch (IOException ex) {
                System.out.println(EXCEPTION_REQUEST_MESSAGE_IO);
                throw new RuntimeException();
            }
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void addBook(Book book) {
        try {
            bookService.addBook(book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_BOOK_MESSAGE_IO);
            throw new RuntimeException();
        }
        try {
            List<Order> orders = queryService.deleteQuery(book);
            for (Order order : orders) {
                try {
                    orderService.decCountOfMissing(order);
                } catch (IOException e) {
                    System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
                    throw new RuntimeException();
                }
            }
        } catch (IOException e) {
            System.out.println(EXCEPTION_REQUEST_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void createBook(Book book) {
        try {
            bookService.createBook(book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_BOOK_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void deleteBook(Book book) {
        try {
            bookService.deleteBook(book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_BOOK_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void updateBook(Book book) {
        try {
            bookService.updateBook(book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_BOOK_MESSAGE_IO);
            throw new RuntimeException();
        }
    }
}
