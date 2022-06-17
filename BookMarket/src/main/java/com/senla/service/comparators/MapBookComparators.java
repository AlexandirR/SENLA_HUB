package com.senla.service.comparators;

import com.senla.model.Book;
import com.senla.service.comparators.bookcomp.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapBookComparators {

    private Map<String, Comparator<Book>> comparatorMap;

    public MapBookComparators() {
        comparatorMap = new HashMap<>();
        comparatorMap.put("Name", new BookNameComp());
        comparatorMap.put("Price", new BookPriceComp());
        comparatorMap.put("Publication", new BookPublicationComp());
        comparatorMap.put("Date", new BookDateComp());
        comparatorMap.put("Status", new BookStatusComp());
    }

    public Comparator<Book> getComparatorByParam(String param) {
        return comparatorMap.get(param);
    }
}
