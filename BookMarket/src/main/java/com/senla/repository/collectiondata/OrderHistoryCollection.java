package com.senla.repository.collectiondata;

import com.senla.model.Order;
import com.senla.repository.interfacedata.OrderHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderHistoryCollection implements OrderHistory {

    private List<Order> orders;

    public OrderHistoryCollection() {
        this.orders = new ArrayList<>();
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public List<Order> getSortedOrders(Comparator<Order> comparator) {
        List<Order> sortedOrders = new ArrayList<>(orders);
        sortedOrders.sort(comparator);
        return sortedOrders;
    }

    @Override
    public List<Order> getOrdersListInTime(LocalDate dateBegin, LocalDate dateEnd, Comparator<Order> comparator) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getDateComplete() != null) {
                if (dateBegin.isBefore(order.getDateComplete()) && dateEnd.isAfter(order.getDateComplete())) {
                    result.add(order);
                }
            }
        }
        result.sort(comparator);
        return result;
    }

    @Override
    public Order getOrderByDataCustomer(String info) {
        for(Order order: orders) {
            if(info.equals(order.getDataCustomer())) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Integer getOrderSize() {
        return orders.size();
    }

    @Override
    public Order getById(Integer id) {
        for(Order order: orders) {
            if(id.equals(order.getId())) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Order create(Order item) {
        orders.add(item);
        return item;
    }

    @Override
    public Order delete(Order item) {
        this.orders.remove(item);
        return item;
    }

    @Override
    public Order update(Order item) {
        this.orders.set(item.getId(), item);
        return item;
    }
}
