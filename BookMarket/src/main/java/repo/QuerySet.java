package repo;

import model.Request;

import java.util.ArrayList;
import java.util.List;

public class QuerySet {
    private List<Request> requests;

    public QuerySet() {
        this.requests = new ArrayList<>();
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request request) {
        requests.add(request);
    }
}
