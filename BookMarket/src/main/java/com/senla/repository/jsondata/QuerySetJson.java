package com.senla.repository.jsondata;

import annitations.PostConstruct;
import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.repository.interfacedata.QuerySet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuerySetJson extends AbstractRepoData<Request> implements QuerySet {

    private static final String fileName = "QuerySet.json";

    @PostConstruct
    public void construct() throws IOException {
        super.nameOfFile = fileName;
        super.init();
    }

    @Override
    public List<Request> getRequests() {
        List<Request> results = new ArrayList<>(super.items);
        return results;
    }

    @Override
    public void deleteRequestByBook(Book book) {
        List<Request> connections = new ArrayList<>();
        List<Request> requests = new ArrayList<>(super.items);
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
}
