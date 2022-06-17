package com.senla.model;

import annitations.Prototype;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.senla.model.enums.BookStatus;
import lombok.ToString;

import java.time.LocalDate;

@JsonTypeName(value = Book.TYPE)
@Prototype
@ToString
public class Book extends IEntities {

    protected static final String TYPE = "book";
    private int id;
    private String name;
    private String description;
    private BookStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate publicationDate;
    private int price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate receiptOfBook;

    public Book(){};

    public Book(int id, String name, String description, LocalDate publicationDate, int price,
                BookStatus status, LocalDate receiptOfBook) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.price = price;
        this.publicationDate = publicationDate;
        this.receiptOfBook = receiptOfBook;
    }

    public Book(int id, String name, String description, LocalDate publicationDate, int price) {
        this(id, name, description, publicationDate, price, BookStatus.INSTOCK, LocalDate.now());
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Book) obj).name)
                && this.description.equals(((Book) obj).description)
                && this.status.equals(((Book) obj).status);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getReceiptOfBook() {
        return receiptOfBook;
    }

    public void setReceiptOfBook(LocalDate receiptOfBook) {
        this.receiptOfBook = receiptOfBook;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
