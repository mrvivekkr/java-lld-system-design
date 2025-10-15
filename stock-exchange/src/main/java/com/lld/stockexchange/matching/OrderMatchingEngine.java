package com.lld.stockexchange.matching;

import com.lld.stockexchange.model.order.OrderBook;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class OrderMatchingEngine {
    @Setter
    private OrderMatchingStrategy matchingStrategy;

    public void execute(OrderBook orderBook) {
        matchingStrategy.matchLimitOrders(orderBook);
    }
}
