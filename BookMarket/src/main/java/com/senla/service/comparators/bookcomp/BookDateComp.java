package com.senla.service.comparators.bookcomp;

import com.senla.model.Book;

import java.util.Comparator;

public class BookDateComp implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getReceiptOfBook().compareTo(o2.getReceiptOfBook());
    }
}
