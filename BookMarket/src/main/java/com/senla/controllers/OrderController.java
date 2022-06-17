package com.senla.controllers;

import annitations.Inject;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.enums.BookStatus;
import com.senla.service.OrderService;
import com.senla.service.QueryService;

import java.io.IOException;

public class OrderController {

    private static final String EXCEPTION_ORDER_MESSAGE_IO = "Не найдена история заказов";
    private static final String EXCEPTION_REQUEST_MESSAGE_IO = "Не найдена история заказов";

    @Inject
    private OrderService orderService;
    @Inject
    private QueryService queryService;

    public void cancelOrder(Order order) {
        try {
            orderService.canselOrder(order);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public Order createOrder(String customer) {
        try {
            return orderService.createOrder(customer);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void completeOrder(Order order) {
        try {
            orderService.completeOrder(order);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void addBookInOrder(Order order, Book book) {
        try {
            orderService.addBookInOrder(order, book);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
        if (book.getStatus() == BookStatus.MISSING) {
            try {
                queryService.addQuery(order, book);
            } catch (IOException e) {
                System.out.println(EXCEPTION_REQUEST_MESSAGE_IO);
                throw new RuntimeException();
            }
        }
    }

    public void updateOrder(Order order) {
        try {
            orderService.updateOrder(order);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }

    public void deleteOrder(Order order) {
        try {
            orderService.deleteOrder(order);
        } catch (IOException e) {
            System.out.println(EXCEPTION_ORDER_MESSAGE_IO);
            throw new RuntimeException();
        }
    }
}
