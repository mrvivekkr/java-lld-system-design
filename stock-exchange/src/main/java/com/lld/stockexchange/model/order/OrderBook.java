package com.lld.stockexchange.model.order;

import lombok.Getter;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderBook {
    @Getter
    private PriorityQueue<Order> buyOrders;
    @Getter
    private PriorityQueue<Order> sellOrders;

    public OrderBook() {
        buyOrders = new PriorityQueue<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                int priceCompare = Double.compare(o2.getPrice(), o1.getPrice());
                if (priceCompare != 0)
                    return priceCompare;
                return Long.compare(o1.getCreatedTimestamp(), o2.getCreatedTimestamp());
            }
        });

        sellOrders = new PriorityQueue<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                int priceCompare = Double.compare(o1.getPrice(), o2.getPrice());
                if (priceCompare != 0) {
                    return priceCompare;
                }
                return Long.compare(o1.getCreatedTimestamp(), o2.getCreatedTimestamp());
            }
        });
    }

    public void addOrder(Order order) {
        if (order.isBuy)
            buyOrders.add(order);
        else
            sellOrders.add(order);
    }
}
