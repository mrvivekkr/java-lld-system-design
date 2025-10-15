package com.lld.stockexchange.model.order;

import com.lld.stockexchange.enums.OrderStatus;
import com.lld.stockexchange.model.Stock;
import com.lld.stockexchange.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Order {
    protected String orderId;
    protected User user;
    protected Stock stock;
    protected final int quantity;
    protected int remainingQty;
    protected boolean isBuy;
    protected OrderStatus status;
    protected final Long orderCreatedAt;

    public Order(String orderId, User user, Stock stock, int quantity, boolean isBuy) {
        this.orderId = orderId;
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
        this.remainingQty = quantity;
        this.isBuy = isBuy;
        this.status = OrderStatus.PENDING;
        this.orderCreatedAt = System.currentTimeMillis();
    }

    public abstract double getPrice();

    public long getCreatedTimestamp() {
        return orderCreatedAt;
    }

    public synchronized void reduceRemaining(int qty) {
        if (qty > remainingQty) {
            throw new IllegalArgumentException("Reducing more than remaining quantity");
        }
        remainingQty -= qty;
        if (remainingQty == 0) {
            status = OrderStatus.COMPLETED;
        }
    }
}
