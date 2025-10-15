package com.lld.stockexchange;

import com.lld.stockexchange.enums.OrderType;
import com.lld.stockexchange.model.Stock;
import com.lld.stockexchange.model.User;
import com.lld.stockexchange.model.order.Order;
import com.lld.stockexchange.model.order.OrderFactory;

import java.time.Instant;

public class StockExchangeApp {
    public static void main(String[] args) {
        System.out.println("=== Stock Exchange Simulation Started ===\n");

        StockExchange exchange = StockExchange.getInstance();

        // Create stocks and users
        Stock aapl = new Stock("AAPL");
        Stock googl = new Stock("GOOGL");
        User alice = new User(1, "Alice");
        User bob = new User(2, "Bob");
        User charlie = new User(3, "Charlie");

        // Give users initial holdings
        setupPortfolios(alice, bob, aapl, googl);
        displayPortfolios("INITIAL", alice, bob, charlie);

        // Scenario 1: Limit Orders
        limitOrderScenario(exchange, alice, bob, charlie, aapl);

        // Scenario 2: Market Orders
        marketOrderScenario(exchange, alice, bob, charlie, googl);

        displayPortfolios("FINAL", alice, bob, charlie);
        System.out.println("=== Simulation Complete ===");
    }

    private static void setupPortfolios(User alice, User bob, Stock aapl, Stock googl) {
        System.out.println("=== Setting Up Portfolios ===");

        // Alice gets AAPL shares
        alice.addStock(aapl, 100, 150.0, Instant.now());
        System.out.println(" Alice: 100 AAPL shares at $150");

        // Bob gets GOOGL shares
        bob.addStock(googl, 50, 2500.0, Instant.now());
        System.out.println(" Bob: 50 GOOGL shares at $2500");

        System.out.println();
    }

    private static void limitOrderScenario(StockExchange exchange, User alice, User bob, User charlie, Stock aapl) {
        System.out.println("=== SCENARIO 1: LIMIT ORDERS ===");

        // Alice sells AAPL
        Order aliceSell = OrderFactory.createOrder("L001", alice, aapl, 30, false, OrderType.LIMIT, 155.0);
        exchange.placeOrder(aliceSell);

        // Bob buys AAPL
        Order bobBuy = OrderFactory.createOrder("L002", bob, aapl, 20, true, OrderType.LIMIT, 156.0);
        exchange.placeOrder(bobBuy);

        // Charlie buys remaining AAPL
        Order charlieBuy = OrderFactory.createOrder("L003", charlie, aapl, 15, true, OrderType.LIMIT, 155.0);
        exchange.placeOrder(charlieBuy);

        System.out.println(" Limit order scenario complete\n");
    }

    private static void marketOrderScenario(StockExchange exchange, User alice, User bob, User charlie, Stock googl) {
        System.out.println("=== SCENARIO 2: MARKET ORDERS ===");

        // Setup liquidity first - Bob places sell orders
        System.out.println("Setting up liquidity:");
        Order bobSell1 = OrderFactory.createOrder("L004", bob, googl, 20, false, OrderType.LIMIT, 2520.0);
        Order bobSell2 = OrderFactory.createOrder("L005", bob, googl, 15, false, OrderType.LIMIT, 2525.0);
        exchange.placeOrder(bobSell1);
        exchange.placeOrder(bobSell2);

        // Charlie places market buy order
        System.out.println("\nCharlie places MARKET BUY:");
        Order charlieMarketBuy = OrderFactory.createOrder("M001", charlie, googl, 25, true, OrderType.MARKET, null);
        exchange.placeOrder(charlieMarketBuy);

        // Alice places market sell order (but needs GOOGL shares first)
        System.out.println("\nAlice gets some GOOGL shares and places MARKET SELL:");
        alice.addStock(googl, 30, 2480.0, Instant.now());
        Order aliceMarketSell = OrderFactory.createOrder("M002", alice, googl, 10, false, OrderType.MARKET, null);
        exchange.placeOrder(aliceMarketSell);

        System.out.println(" Market order scenario complete\n");
    }

    private static void displayPortfolios(String title, User... users) {
        System.out.println("=== " + title + " PORTFOLIOS ===");
        for (User user : users) {
            System.out.println(user.getName() + "'s Portfolio:");
            if (user.getPortfolio().isEmpty()) {
                System.out.println("  Empty");
            } else {
                user.getPortfolio().forEach((symbol, lots) -> {
                    int total = lots.stream().mapToInt(lot -> lot.getQuantity()).sum();
                    if (total > 0) {
                        System.out.println("  " + symbol + ": " + total + " shares");
                    }
                });
            }
        }
        System.out.println();
    }
}
