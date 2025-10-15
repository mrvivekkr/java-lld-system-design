package com.lld.stockexchange.trade;

import com.lld.stockexchange.model.Stock;
import com.lld.stockexchange.model.User;
import com.lld.stockexchange.model.order.Order;

import java.time.Instant;

public class ExecuteTradeCommand implements TradeCommand {
    @Override
    public void execute(Order buyOrder, Order sellOrder, int tradeQuantity, double tradePrice) {
        buyOrder.reduceRemaining(tradeQuantity);
        sellOrder.reduceRemaining(tradeQuantity);

        User buyer = buyOrder.getUser();
        User seller = sellOrder.getUser();
        Stock stock = buyOrder.getStock();
        buyer.addStock(stock, tradeQuantity, tradePrice, Instant.now());
        seller.removeStock(stock, tradeQuantity);

        // Log the trade execution
        System.out.printf("TRADE EXECUTED: %s bought %d shares of %s from %s at $%.2f per share%n",
                buyer.getName(), tradeQuantity, stock.getSymbol(),
                seller.getName(), tradePrice);

    }
}
