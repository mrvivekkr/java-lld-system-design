package com.lld.stockexchange.processor;

import com.lld.stockexchange.matching.OrderMatchingStrategy;
import com.lld.stockexchange.model.order.Order;
import com.lld.stockexchange.model.order.OrderBook;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LimitOrderProcessor {
    private final OrderMatchingStrategy strategy;

    public void processLimitOrder(Order limitOrder, OrderBook orderBook) {
        System.out.println("Adding limit order to book and checking for matches...");
        orderBook.addOrder(limitOrder);

        strategy.matchLimitOrders(orderBook);
    }
}
