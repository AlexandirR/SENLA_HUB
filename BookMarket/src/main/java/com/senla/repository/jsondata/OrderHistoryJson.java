package com.senla.repository.jsondata;

import annitations.PostConstruct;
import com.senla.model.Order;
import com.senla.repository.interfacedata.OrderHistory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderHistoryJson extends AbstractRepoData<Order> implements OrderHistory {

    private static final String fileName = "OrderHistory.json";

    @PostConstruct
    public void construct() throws IOException {
        super.nameOfFile = fileName;
        super.init();
    }

    @Override
    public List<Order> getOrders() {
        List<Order> results = new ArrayList<>(super.items);
        return results;
    }

    @Override
    public List<Order> getSortedOrders(Comparator<Order> comparator) {
        List<Order> results = new ArrayList<>(super.items);
        results.sort(comparator);
        return results;
    }

    @Override
    public List<Order> getOrdersListInTime(LocalDate dateBegin, LocalDate dateEnd, Comparator<Order> comparator) {
        List<Order> answer = new ArrayList<>();
        for (Order order : super.items) {
            if (order.getDateComplete() != null) {
                if (dateBegin.isBefore(order.getDateComplete()) && dateEnd.isAfter(order.getDateComplete())) {
                    answer.add(order);
                }
            }
        }
        answer.sort(comparator);
        return answer;
    }

    @Override
    public Order getOrderByDataCustomer(String info) {
        for(Order order : items) {
            if(info.equals(order.getDataCustomer())) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Integer getOrderSize() {
        return super.items.size();
    }
}
