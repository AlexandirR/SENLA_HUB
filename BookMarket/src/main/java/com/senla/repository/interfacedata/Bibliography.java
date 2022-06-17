package com.senla.repository.interfacedata;

import com.senla.model.Book;

import java.util.Comparator;
import java.util.List;

public interface Bibliography extends RepositoryCrud<Book> {

    List<Book> getBooks();

    List<Book> getSortedBooks(Comparator<Book> comparator);

    Book getBookByName(String name);

    List<Book> longTimeBook(Comparator<Book> comparator);
}
