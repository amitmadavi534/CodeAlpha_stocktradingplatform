import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    public Portfolio(double balance) {
        this.balance = balance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = quantity * price;
        if (cost <= balance) {
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
            balance -= cost;
            System.out.println("Bought " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (holdings.getOrDefault(symbol, 0) >= quantity) {
            holdings.put(symbol, holdings.get(symbol) - quantity);
            balance += quantity * price;
            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Not enough shares to sell!");
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        holdings.forEach((stock, qty) -> System.out.println(stock + ": " + qty + " shares"));
        System.out.println("Balance: " + balance);
    }
}

public class StockTradingSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Portfolio portfolio = new Portfolio(10000.0);
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150.0));
        market.put("GOOGL", new Stock("GOOGL", 2800.0));
        market.put("TSLA", new Stock("TSLA", 700.0));
        
        while (true) {
            System.out.println("1. Buy Stock\n2. Sell Stock\n3. View Portfolio\n4. Exit");
            int choice = scanner.nextInt();
            if (choice == 4) break;

            System.out.println("Enter stock symbol:");
            String symbol = scanner.next();
            Stock stock = market.get(symbol);
            if (stock == null) {
                System.out.println("Invalid stock symbol!");
                continue;
            }

            stock.price += random.nextDouble() * 10 - 5; // Simulating market changes

            if (choice == 1) {
                System.out.println("Enter quantity to buy:");
                int qty = scanner.nextInt();
                portfolio.buyStock(symbol, qty, stock.price);
            } else if (choice == 2) {
                System.out.println("Enter quantity to sell:");
                int qty = scanner.nextInt();
                portfolio.sellStock(symbol, qty, stock.price);
            } else if (choice == 3) {
                portfolio.displayPortfolio();
            }
        }
        scanner.close();
    }
}
