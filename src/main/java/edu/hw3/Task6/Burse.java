package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Burse implements StockMarket {
    private final PriorityQueue<Stock> stocksBank;

    public Burse() {
        stocksBank = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public PriorityQueue<Stock> getStocksBank() {
        return stocksBank;
    }

    @Override
    public void add(Stock stock) {
        stocksBank.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocksBank.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocksBank.peek();
    }
}
