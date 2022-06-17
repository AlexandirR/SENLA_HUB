package com.senla.service.comparators.ordercomp;

import com.senla.model.Order;

import java.util.Comparator;

public class OrderPriceComp implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Integer.compare(o1.getPrice(), o2.getPrice());
    }
}
