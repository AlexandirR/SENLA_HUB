package com.senla.service;

import annitations.Inject;
import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.enums.OrderStatus;
import com.senla.repository.interfacedata.OrderHistory;
import com.senla.service.comparators.MapOrderComparators;
import com.senla.service.comparators.ordercomp.OrderPriceComp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    @Inject
    private OrderHistory orderHistory;
    @Inject
    private MapOrderComparators mapOrderComparators;

    public List<Order> closeBook(Book book) throws IOException {
        List<Order> closeOrders = new ArrayList<>();
        for (Order order : orderHistory.getOrders()) {
            if (order.getStatus() == OrderStatus.NEW && order.getBooks().contains(book)) {
                order.addCountOfMissingBook();
                orderHistory.update(order);
                closeOrders.add(order);
            }
        }
        return closeOrders;
    }

    public void decCountOfMissing(Order order) throws IOException {
        Order oldOrder = orderHistory.getById(order.getId());
        oldOrder.decCountOfMissingBook();
        orderHistory.update(oldOrder);
    }

    public Order canselOrder(Order order) throws IOException {
        order.setStatus(OrderStatus.CANCEL);
        orderHistory.update(order);
        return order;
    }

    public Order getOrderByDataCustomer(String info) throws IOException {
        return orderHistory.getOrderByDataCustomer(info);
    }

    public Order getOrderByIndex(int index) throws IOException {
        return orderHistory.getById(index);
    }

    public Order createOrder(String dataCustomer) throws IOException {
        Order order = new Order(orderHistory.getOrderSize(), dataCustomer);
        orderHistory.create(order);
        return order;
    }

    public Order completeOrder(Order order) throws IOException {
        if (order.getCountOfMissingBook() == 0) {
            order.setStatus(OrderStatus.COMPLETE);
            order.setDateComplete(LocalDate.now());
            orderHistory.update(order);
            return order;
        }

        //log
        System.out.println("Ошибка, не все книги были получены");
        return order;
    }

    public Order addBookInOrder(Order order, Book book) throws IOException {
        order.addBook(book);
        orderHistory.update(order);
        return order;
    }

    public List<Order> getSortedOrderList(String param) throws IOException {
        return orderHistory.getSortedOrders(mapOrderComparators.getComparatorByParam(param));
    }

    public List<Order> getCompletedOrderList(String param, LocalDate begin, LocalDate end) throws IOException {
        return orderHistory.getOrdersListInTime(begin, end, mapOrderComparators.getComparatorByParam(param));
    }

    public int earningsByTime(LocalDate dateBegin, LocalDate dateEnd) throws IOException {
        return orderHistory.getOrdersListInTime(dateBegin, dateEnd, new OrderPriceComp()).stream()
                .map(order -> order.getPrice())
                .reduce((s1, s2) -> s1 + s2).get();
    }

    public int countsByTime(LocalDate dateBegin, LocalDate dateEnd) throws IOException {
        return orderHistory.getOrdersListInTime(dateBegin, dateEnd, new OrderPriceComp()).size();
    }

    public void updateOrder(Order order) throws IOException {
        orderHistory.update(order);
    }

    public void deleteOrder(Order order) throws IOException {
        orderHistory.delete(order);
    }
}
