package com.senla.service;

import annitations.Inject;
import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.model.Order;
import com.senla.repository.interfacedata.QuerySet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueryService {

    @Inject
    private QuerySet querySet;

    public List<Order> deleteQuery(Book book) throws IOException {
        List<Request> connections = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        for (Request connection : querySet.getRequests()) {
            if (book.equals(connection.getBook())) {
                connections.add(connection);
            }
        }
        for (Request connection : connections) {
            orders.add(connection.getOrder());
            querySet.delete(connection);
        }
        return orders;
    }

    public void addQuery(Order order, Book book) throws IOException {
        querySet.create(new Request(querySet.getRequests().size(), book, order));
    }

    public List<Request> getRequestByBook(Book book) throws IOException {
        List<Request> requests = new ArrayList<>();
        for (Request request : querySet.getRequests()) {
            if (book.equals(request.getBook())) {
                requests.add(request);
            }
        }
        return requests;
    }

    public Request getRequestByIndex(Integer id) throws IOException {
        return querySet.getById(id);
    }

    public void updateRequest(Request request) throws IOException {
        querySet.update(request);
    }
}
