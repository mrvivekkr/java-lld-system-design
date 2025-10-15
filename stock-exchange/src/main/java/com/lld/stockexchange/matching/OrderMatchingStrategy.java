package com.lld.stockexchange.matching;

import com.lld.stockexchange.model.order.Order;
import com.lld.stockexchange.model.order.OrderBook;

public interface OrderMatchingStrategy {
    void matchLimitOrders(OrderBook orderBook);

    void executeMarketBuyOrder(Order order, OrderBook orderBook);

    void executeMarketSellOrder(Order order, OrderBook orderBook);
}
