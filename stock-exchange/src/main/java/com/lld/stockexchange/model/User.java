package com.lld.stockexchange.model;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class User {

    private final int id;
    private String name;
    private final Map<String, List<StockLot>> portfolio;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.portfolio = new HashMap<>();
    }

    public void addStock(Stock stock, int quantity, double price, Instant purchaseDate) {
        StockLot lot = new StockLot(stock, quantity, price, purchaseDate);
        portfolio.computeIfAbsent(stock.getSymbol(), k -> new ArrayList<>()).add(lot);
    }

    public void removeStock(Stock stock, int tradeQuantity) {
        List<StockLot> lots = portfolio.get(stock.getSymbol());
        if (lots == null || lots.isEmpty())
            throw new IllegalArgumentException("No shares of " + stock.getSymbol() + " to sell");
        int remainingToSell = tradeQuantity;
        while (remainingToSell > 0 && !lots.isEmpty()) {
            StockLot lot = lots.get(0);
            if (lot.getQuantity() <= remainingToSell) {
                remainingToSell -= lot.getQuantity();
                lots.remove(0);
            } else {
                lot.setQuantity(lot.getQuantity() - remainingToSell);
                remainingToSell = 0;
            }
        }
        if (remainingToSell > 0) {
            throw new IllegalArgumentException("Insufficient shares to sell");
        }
    }
}
