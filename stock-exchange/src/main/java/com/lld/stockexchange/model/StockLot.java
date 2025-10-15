package com.lld.stockexchange.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StockLot {

    private final Stock stock;
    private int quantity;
    private final double purchasePrice;
    private final Instant purchaseDate;

    public StockLot(Stock stock, int quantity, double purchasePrice, Instant purchaseDate) {
        this.stock = stock;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
    }
}
