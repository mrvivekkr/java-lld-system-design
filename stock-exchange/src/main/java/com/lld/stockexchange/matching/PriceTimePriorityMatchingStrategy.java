package com.lld.stockexchange.matching;

import com.lld.stockexchange.model.order.Order;
import com.lld.stockexchange.model.order.OrderBook;
import com.lld.stockexchange.trade.TradeCommand;
import lombok.AllArgsConstructor;

import java.util.PriorityQueue;

@AllArgsConstructor
public class PriceTimePriorityMatchingStrategy implements OrderMatchingStrategy {

    private final TradeCommand tradeCommand;

    @Override
    public void matchLimitOrders(OrderBook orderBook) {
        PriorityQueue<Order> buyOrders = orderBook.getBuyOrders();
        PriorityQueue<Order> sellOrders = orderBook.getSellOrders();

        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            Order buy = buyOrders.peek();
            Order sell = sellOrders.peek();
            if (sell.getPrice() <= buy.getPrice()) {
                int tradeQty = Math.min(buy.getRemainingQty(), sell.getRemainingQty());
                double tradePrice = sell.getPrice();

                tradeCommand.execute(buy, sell, tradeQty, tradePrice);

                if (buy.getRemainingQty() == 0)
                    buyOrders.poll();
                if (sell.getRemainingQty() == 0)
                    sellOrders.poll();
            } else {
                break;
            }
        }

    }

    @Override
    public void executeMarketBuyOrder(Order buyOrder, OrderBook orderBook) {
        PriorityQueue<Order> sellOrders = orderBook.getSellOrders();
        while (buyOrder.getRemainingQty() > 0 && !sellOrders.isEmpty()) {
            Order sellOrder = sellOrders.peek();
            int tradeQty = Math.min(buyOrder.getRemainingQty(), sellOrder.getRemainingQty());
            double tradePrice = sellOrder.getPrice();
            tradeCommand.execute(buyOrder, sellOrder, tradeQty, tradePrice);
            if (sellOrder.getRemainingQty() == 0)
                sellOrders.poll();
        }

        if (buyOrder.getRemainingQty() > 0) {
            System.out.printf("Market buy order partially filled. %d shares cancelled.%n", buyOrder.getRemainingQty());
        }
    }

    @Override
    public void executeMarketSellOrder(Order sellOrder, OrderBook orderBook) {
        PriorityQueue<Order> buyOrders = orderBook.getBuyOrders();
        while (sellOrder.getRemainingQty() > 0 && !buyOrders.isEmpty()) {
            Order buyOrder = buyOrders.peek();
            int tradeQty = Math.min(sellOrder.getRemainingQty(), buyOrder.getRemainingQty());
            double tradePrice = buyOrder.getPrice();
            tradeCommand.execute(buyOrder, sellOrder, tradeQty, tradePrice);

            if (buyOrder.getRemainingQty() == 0) {
                buyOrders.poll();
            }

            if (sellOrder.getRemainingQty() > 0) {
                System.out.printf("Market sell order partially filled. %d shares cancelled.%n", sellOrder.getRemainingQty());
            }
        }
    }
}
