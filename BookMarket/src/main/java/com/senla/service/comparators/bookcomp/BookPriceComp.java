package com.senla.service.comparators.bookcomp;

import com.senla.model.Book;

import java.util.Comparator;

public class BookPriceComp implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return Integer.compare(o1.getPrice(), o2.getPrice());
    }
}
