package com.lld.stockexchange.trade;

import com.lld.stockexchange.model.order.Order;

public interface TradeCommand {
    void execute(Order buyOrder, Order sellOrder, int tradeQuantity, double tradePrice);
}
