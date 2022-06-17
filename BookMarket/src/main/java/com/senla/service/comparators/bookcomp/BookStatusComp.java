package com.senla.service.comparators.bookcomp;

import com.senla.model.Book;
import com.senla.model.enums.BookStatus;

import java.util.Comparator;

public class BookStatusComp implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getStatus() == BookStatus.INSTOCK) {
            return 1;
        } else {
            return -1;
        }
    }
}
