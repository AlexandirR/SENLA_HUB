package com.senla.repository.interfacedata;

import com.senla.model.Book;
import com.senla.model.Request;

import java.util.List;

public interface QuerySet extends RepositoryCrud<Request> {

    List<Request> getRequests() ;

    void deleteRequestByBook(Book book);
}
