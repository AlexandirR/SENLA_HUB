package com.senla.controllers;

import annitations.Inject;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.Request;
import com.senla.service.QueryService;

import java.io.IOException;
import java.util.List;

public class RequestController {

    private static final String EXCEPTION_REQUEST_MESSAGE_IO = "Не найдена история запросов на книги";

    @Inject
    private QueryService queryService;

    public Request getRequestByIndex(Integer id) {
        try {
            return queryService.getRequestByIndex(id);
        } catch (IOException e) {
            System.out.println(EXCEPTION_REQUEST_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public List<Request> getRequestsByBook(Book book) {
        try {
            return queryService.getRequestByBook(book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_REQUEST_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void updateRequest(Request request) {
        try {
            queryService.updateRequest(request);
        } catch (IOException e) {
            System.out.println(EXCEPTION_REQUEST_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void deleteRequest(Book book) {
        try {
            queryService.deleteQuery(book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_REQUEST_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void createRequest(Book book, Order order) {
        try {
            queryService.addQuery(order, book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_REQUEST_MESSAGE_IO);
            throw new RuntimeException();
        }
    }
}
