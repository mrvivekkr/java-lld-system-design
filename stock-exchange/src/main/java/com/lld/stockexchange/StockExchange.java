package com.lld.stockexchange;

import com.lld.stockexchange.matching.OrderMatchingStrategy;
import com.lld.stockexchange.matching.PriceTimePriorityMatchingStrategy;
import com.lld.stockexchange.model.order.LimitOrder;
import com.lld.stockexchange.model.order.MarketOrder;
import com.lld.stockexchange.model.order.Order;
import com.lld.stockexchange.model.order.OrderBook;
import com.lld.stockexchange.processor.LimitOrderProcessor;
import com.lld.stockexchange.processor.MarketOrderProcessor;
import com.lld.stockexchange.trade.ExecuteTradeCommand;
import com.lld.stockexchange.trade.TradeCommand;


public class StockExchange {
    private static StockExchange instance;
    private final OrderBook orderBook;
    private final MarketOrderProcessor marketOrderProcessor;
    private final LimitOrderProcessor limitOrderProcessor;


    private StockExchange() {
        this.orderBook = new OrderBook();

        TradeCommand command = new ExecuteTradeCommand();
        OrderMatchingStrategy strategy = new PriceTimePriorityMatchingStrategy(command);

        this.marketOrderProcessor = new MarketOrderProcessor(strategy);
        this.limitOrderProcessor = new LimitOrderProcessor(strategy);
    }

    // Thread-safe singleton access using Double-Checked Locking
    public static StockExchange getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (StockExchange.class) {
                if (instance == null) { // Second check (with locking)
                    instance = new StockExchange();
                }
            }
        }
        return instance;
    }

    public void placeOrder(Order order){
        System.out.printf("Received order: %s %s %d shares of %s%n",
                order.isBuy() ? "BUY" : "SELL",
                order.getClass().getSimpleName(),
                order.getQuantity(),
                order.getStock().getSymbol());

        if(order instanceof LimitOrder){
            limitOrderProcessor.processLimitOrder(order, orderBook);
        }else if (order instanceof MarketOrder){
            marketOrderProcessor.processMarketOrder(order, orderBook);
        }else{
            throw new IllegalArgumentException("Unknown Order Type");
        }
    }
}
