package com.senla.repository.interfacedata;

import com.senla.model.Order;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public interface OrderHistory extends RepositoryCrud<Order> {

    List<Order> getOrders();

    List<Order> getSortedOrders(Comparator<Order> comparator);

    List<Order> getOrdersListInTime(LocalDate dateBegin, LocalDate dateEnd, Comparator<Order> comparator);

    Order getOrderByDataCustomer(String info);

    Integer getOrderSize();
}
