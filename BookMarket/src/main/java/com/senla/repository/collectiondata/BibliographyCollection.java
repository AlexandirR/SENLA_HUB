package com.senla.repository.collectiondata;

import com.senla.model.Book;
import com.senla.repository.interfacedata.Bibliography;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BibliographyCollection implements Bibliography {

    private List<Book> books;

    public BibliographyCollection() {
        this.books = new ArrayList<>();
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public List<Book> getSortedBooks(Comparator<Book> comparator) {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(comparator);
        return sortedBooks;
    }

    @Override
    public Book getBookByName(String name) {
        for (Book book: books) {
            if(book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book getById(Integer id) {
        for (Book book: books) {
            if(id == book.getId()) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> longTimeBook(Comparator<Book> comparator) {
        List<Book> result = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Book book : books) {
            if (book.getReceiptOfBook() != null) {
                Period period = Period.between(now, book.getReceiptOfBook());
                if (period.getYears() > 0 || period.getMonths() > 6) {
                    result.add(book);
                }
            }
        }
        result.sort(comparator);
        return result;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public Book create(Book item) {
        this.books.add(item);
        return item;
    }

    @Override
    public Book delete(Book item) {
        this.books.remove(item);
        return item;
    }

    @Override
    public Book update(Book item) {
        this.books.set(item.getId(), item);
        return item;
    }
}
