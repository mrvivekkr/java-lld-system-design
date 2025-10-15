package com.lld.stockexchange.model.order;

import com.lld.stockexchange.model.Stock;
import com.lld.stockexchange.model.User;

public class MarketOrder extends Order {
    public MarketOrder(String orderId, User user, Stock stock, int quantity, boolean isBuy) {
        super(orderId, user, stock, quantity, isBuy);
    }

    @Override
    public double getPrice() {
        throw new UnsupportedOperationException("Market orders do not have a fixed price");
    }
}
