package com.lld.stockexchange.processor;

import com.lld.stockexchange.matching.OrderMatchingStrategy;
import com.lld.stockexchange.model.order.Order;
import com.lld.stockexchange.model.order.OrderBook;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarketOrderProcessor {
    private final OrderMatchingStrategy strategy;

    public void processMarketOrder(Order marketOrder, OrderBook orderBook) {
        System.out.println("Processing market order immediately...");
        if (marketOrder.isBuy()) {
            strategy.executeMarketBuyOrder(marketOrder, orderBook);
        } else {
            strategy.executeMarketSellOrder(marketOrder, orderBook);
        }
    }
}
