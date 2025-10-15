# Stock Exchange Order Matching System

A simplified low-level design and implementation of a stock exchange order matching and trading system in Java. This project demonstrates core concepts of matching buy and sell orders using price-time priority with support for limit and market orders, including portfolio management and trade execution.

---

## Features

- Support for `LIMIT` and `MARKET` orders with price-time priority matching
- Thread-safe order state management with statuses: `PENDING`, `COMPLETED`, `CANCELLED`
- Separate order books for buy and sell orders using priority queues
- User portfolios with stock lot tracking (quantity, purchase price, purchase date)
- Trade execution using the Command pattern updating buyer/seller holdings
- Singleton StockExchange engine coordinating order processing
- Comprehensive simulation scenarios for limit and market order workflows
- Extensible design using Strategy and Factory patterns

---

## Project Structure

- `model`: Core domain classes including `Order`, `LimitOrder`, `MarketOrder`, `User`, `Stock`, `StockLot`, and `OrderBook`
- `enums`: Enumerations for order status and type
- `matching`: Interfaces and classes for order matching strategies implementing price-time priority matching
- `processor`: Processors for market and limit order handling
- `trade`: Command pattern implementation for trade execution
- Main simulation entry: `StockExchangeApp`

---

## Usage

1. Clone the repository:
   git clone https://github.com/yourusername/stock-exchange-matching.git
2. Build and run the `StockExchangeApp` main class using your favorite IDE or command line.
3. Observe console output showing initial portfolios, order placements, trades executed, and final portfolios.

---

## Key Design Patterns

- **Factory Pattern**: For creating different types of `Order` objects
- **Strategy Pattern**: For swapping out matching algorithms
- **Command Pattern**: For encapsulating trade execution logic
- **Singleton Pattern**: For a single instance of the stock exchange engine

---

## Extensibility Ideas

- Add support for order cancellations and modifications
- Implement order cancellation and modification functionality to allow users to update or withdraw orders before execution.
- Introduce stock inventory management to track available shares accurately and prevent overselling.
- Implement concurrency controls for real-time multi-threaded trading

---

Feel free to check out the project, try it out, and suggest any improvements or features you would like to see!