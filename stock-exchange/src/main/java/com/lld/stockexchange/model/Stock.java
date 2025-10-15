package com.lld.stockexchange.model;

import lombok.Getter;

@Getter
public class Stock {
    private final String symbol;

    public Stock(String symbol) {
        this.symbol = symbol;
    }
}
