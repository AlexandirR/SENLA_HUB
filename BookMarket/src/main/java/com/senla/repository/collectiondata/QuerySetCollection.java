package com.senla.repository.collectiondata;

import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.repository.interfacedata.QuerySet;

import java.util.ArrayList;
import java.util.List;

public class QuerySetCollection implements QuerySet {

    private List<Request> requests;

    public QuerySetCollection() {
        this.requests = new ArrayList<>();
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public Request create(Request item) {
        requests.add(item);
        return item;
    }

    @Override
    public void deleteRequestByBook(Book book) {
        List<Request> connections = new ArrayList<>();
        for (Request request :requests) {
            if (book.equals(request.getBook())) {
                connections.add(request);
            }
        }
        for (Request connection : connections) {
            connection.getOrder().decCountOfMissingBook();
        }
        requests.removeAll(connections);
    }

    @Override
    public Request delete(Request item) {
        this.requests.remove(item);
        return item;
    }

    @Override
    public Request update(Request item) {
        this.requests.set(item.getId(), item);
        return item;
    }

    @Override
    public Request getById(Integer id) {
        return this.requests.get(id);
    }
}
