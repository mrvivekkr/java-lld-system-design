package com.lld.stockexchange.model.order;

import com.lld.stockexchange.model.Stock;
import com.lld.stockexchange.model.User;

public class LimitOrder extends Order {
    private double limitPrice;

    public LimitOrder(String orderId, User user, Stock stock, int quantity, boolean isBuy, double limitPrice) {
        super(orderId, user, stock, quantity, isBuy);
        this.limitPrice = limitPrice;
    }

    @Override
    public double getPrice() {
        return limitPrice;
    }
}
