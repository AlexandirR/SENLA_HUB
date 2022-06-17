package com.senla.repository.jsondata;

import annitations.InjectByProperty;
import annitations.PostConstruct;
import com.senla.model.Book;
import com.senla.repository.interfacedata.Bibliography;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BibliographyJson extends AbstractRepoData<Book> implements Bibliography {

    private static final String fileName = "Bibliography.json";
    @InjectByProperty(nameOfFile = "bookmarket.properties", nameOfProperty = "months.long.time")
    private String monthsLongTime;

    @PostConstruct
    public void construct() throws IOException {
        super.nameOfFile = fileName;
        super.init();
    }

    @Override
    public List<Book> getBooks() {
        List<Book> results = new ArrayList<>(super.items);
        return results;
    }

    @Override
    public List<Book> getSortedBooks(Comparator<Book> comparator) {
        List<Book> results = new ArrayList<>(super.items);
        results.sort(comparator);
        return results;
    }

    @Override
    public Book getBookByName(String name) {
        for(Book book: items) {
            if(name.equals(book.getName())) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> longTimeBook(Comparator<Book> comparator) {
        List<Book> answer = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Book book : super.items) {
            if (book.getReceiptOfBook() != null) {
                Period period = Period.between(now, book.getReceiptOfBook());
                if (period.getYears() > 0 || period.getMonths() > Integer.getInteger(monthsLongTime)) {
                    answer.add(book);
                }
            }
        }
        answer.sort(comparator);
        return answer;
    }
}
