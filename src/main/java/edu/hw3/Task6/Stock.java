package edu.hw3.Task6;

import java.util.Objects;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final UUID stockId;
    private final Integer stockValue;

    public Stock(Integer stockValue) {
        this.stockValue = stockValue;
        stockId = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return Objects.equals(stockId, stock.stockId) && Objects.equals(stockValue, stock.stockValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockId, stockValue);
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return this.stockValue.compareTo(o.stockValue);
    }
}
