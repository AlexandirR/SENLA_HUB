package com.senla.model;

import annitations.Prototype;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.senla.model.enums.BookStatus;
import com.senla.model.enums.OrderStatus;
import lombok.SneakyThrows;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName(value = Order.TYPE)
@Prototype
@ToString
public class Order extends IEntities {

    protected static final String TYPE = "order";
    private int id;
    private int countOfMissingBook;
    private int price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateComplete;
    private OrderStatus status = OrderStatus.NEW;
    private String dataCustomer;
    private List<Book> books;

    private void formatMissingBook() {
        for (Book book : this.books) {
            if (book.getStatus() == BookStatus.MISSING) {
                this.countOfMissingBook++;
            }
        }
    }

    private void formatFullPrice() {
        for (Book book : this.books) {
            this.price += book.getPrice();
        }
    }

    public Order() {}

    public Order(int id, List<Book> books, String dataCustomer) {
        this.id = id;
        this.dataCustomer = dataCustomer;
        this.countOfMissingBook = 0;
        this.books = new ArrayList<>(books);
        formatMissingBook();
        formatFullPrice();
    }

    public Order(int id) {
        this(id, new ArrayList<>(), "NAN");
        this.countOfMissingBook = 0;
    }

    public Order(int id, String dataCustomer) {
        this(id, new ArrayList<>(), dataCustomer);
        this.countOfMissingBook = 0;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addBook(Book book) {
        this.books.add(book);
        if (book.getStatus() == BookStatus.MISSING) {
            this.countOfMissingBook++;
        }
        this.price += book.getPrice();
    }

    public List<Book> getBooks() {
        return this.books;
    }

    @Override
    public boolean equals(Object obj) {
        return this.status == ((Order) obj).status && this.books.equals(((Order) obj).books);
    }

    public int getCountOfMissingBook() {
        return countOfMissingBook;
    }

    public void addCountOfMissingBook() {
        this.countOfMissingBook++;
    }

    public void decCountOfMissingBook() {
        this.countOfMissingBook--;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(LocalDate dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getDataCustomer() {
        return dataCustomer;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
