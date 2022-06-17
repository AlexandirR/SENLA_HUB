package com.senla.service;

import annitations.Inject;
import com.senla.model.Book;
import com.senla.model.enums.BookStatus;
import com.senla.repository.interfacedata.Bibliography;
import com.senla.service.comparators.MapBookComparators;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BookService {

    @Inject
    private Bibliography bibliography;
    @Inject
    private MapBookComparators mapBookComparators;

    public void writeOfBook(Book book) throws IOException {
        book.setStatus(BookStatus.MISSING);
        book.setReceiptOfBook(null);
        bibliography.update(book);
    }

    public void addBook(Book book) throws IOException {
        book.setStatus(BookStatus.INSTOCK);
        book.setReceiptOfBook(LocalDate.now());
        bibliography.update(book);
    }

    public List<Book> getSortedBooks(String param) throws IOException {
       return bibliography.getSortedBooks(mapBookComparators.getComparatorByParam(param));
    }

    public Book getBookByName(String name) throws IOException {
        return bibliography.getBookByName(name);
    }

    public Book getBookById(Integer id) throws IOException {
        return bibliography.getById(id);
    }

    public void createBook(Book book) throws IOException {
        bibliography.create(book);
    }

    public void deleteBook(Book book) throws IOException {
        bibliography.delete(book);
    }

    public void updateBook(Book book) throws IOException {
        bibliography.delete(book);
    }
}
