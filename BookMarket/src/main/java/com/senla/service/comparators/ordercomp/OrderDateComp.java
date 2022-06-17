package com.senla.service.comparators.ordercomp;

import com.senla.model.Order;

import java.util.Comparator;

public class OrderDateComp implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if(o1.getDateComplete() == null)
            return -1;
        if (o2.getDateComplete() == null)
            return 1;
        return o1.getDateComplete().compareTo(o2.getDateComplete());
    }
}
