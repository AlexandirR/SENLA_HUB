package com.senla.controllers;

import annitations.Inject;
import com.senla.model.Order;
import com.senla.service.OrderService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class OrderHistoryController {

    private static final String EXCEPTION_ORDER_MESSAGE_IO = "Не найдена история заказов";

    @Inject
    private OrderService orderService;

    public List<Order> getSortedOrders(String param) {
        try {
            return orderService.getSortedOrderList(param);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public List<Order> getCompletedOrderList(String param, LocalDate begin, LocalDate end) {
        try {
            return orderService.getCompletedOrderList(param, begin, end);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public Order getOrderByDataCustomer(String info) {
        try {
            return orderService.getOrderByDataCustomer(info);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public Order getOrderByIndex(int index) {
        try {
            return orderService.getOrderByIndex(index);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public int earningsByTime(LocalDate dateBegin, LocalDate dateEnd) {
        try {
            return orderService.earningsByTime(dateBegin, dateEnd);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public int countsByTime(LocalDate dateBegin, LocalDate dateEnd) {
        try {
            return orderService.countsByTime(dateBegin, dateEnd);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }
}
