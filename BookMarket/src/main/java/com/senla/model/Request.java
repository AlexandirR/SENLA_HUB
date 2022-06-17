package com.senla.model;

import annitations.Prototype;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.ToString;

@JsonTypeName(value = Request.TYPE)
@Prototype
@ToString
public class Request extends IEntities {

    protected static final String TYPE = "request";
    private int id;
    private Book book;
    private Order order;

    public Request(){}

    public Request(int id, Book book, Order order) {
        this.id = id;
        this.book = book;
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public boolean equals(Object obj) {
        return this.book.equals(((Request) obj).book)
                && this.order.equals(((Request) obj).order);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
