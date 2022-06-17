package com.senla.service.comparators.ordercomp;

import com.senla.model.Order;
import com.senla.model.enums.OrderStatus;

import java.util.Comparator;

public class OrderStatusComp implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getStatus() == OrderStatus.COMPLETE) {
            return 1;
        } else if (o2.getStatus() == OrderStatus.COMPLETE) {
            return -1;
        } else if (o1.getStatus() == OrderStatus.NEW) {
            return 1;
        } else {
            return -1;
        }
    }
}
