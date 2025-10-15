package com.lld.stockexchange.model.order;

import com.lld.stockexchange.enums.OrderType;
import com.lld.stockexchange.model.Stock;
import com.lld.stockexchange.model.User;

public class OrderFactory {

    public static Order createOrder(String orderId, User user, Stock stock, int quantity, boolean isBuy, OrderType type, Double price) {
        switch (type) {
            case MARKET:
                return new MarketOrder(orderId, user, stock, quantity, isBuy);
            case LIMIT:
                return new LimitOrder(orderId, user, stock, quantity, isBuy, price);
            default:
                throw new IllegalArgumentException("Unknown Order Type " + type);
        }
    }
}
