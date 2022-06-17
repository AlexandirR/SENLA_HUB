package com.senla.service.comparators;

import com.senla.model.Order;
import com.senla.service.comparators.ordercomp.OrderDateComp;
import com.senla.service.comparators.ordercomp.OrderPriceComp;
import com.senla.service.comparators.ordercomp.OrderStatusComp;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapOrderComparators {

    private Map<String, Comparator<Order>> comparatorMap;

    public MapOrderComparators() {
        comparatorMap = new HashMap<>();
        comparatorMap.put("Price", new OrderPriceComp());
        comparatorMap.put("Date", new OrderDateComp());
        comparatorMap.put("Status", new OrderStatusComp());
    }

    public Comparator<Order> getComparatorByParam(String param) {
        return comparatorMap.get(param);
    }
}

