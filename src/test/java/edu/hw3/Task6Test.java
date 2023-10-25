package edu.hw3;

import static org.assertj.core.api.Assertions.*;
import edu.hw3.Task6.Burse;
import edu.hw3.Task6.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Task6Test {
    private static final Stock STOCK_1 = new Stock(10);
    private static final Stock STOCK_2 = new Stock(13);
    private static final Stock STOCK_3 = new Stock(23);
    @Test
    @DisplayName("Task6 Add Test")
    void testTask6Add() {
        // arrange
        Burse market = new Burse();

        // act
        market.add(STOCK_1);
        market.add(STOCK_2);
        market.add(STOCK_3);

        // assert
        assertThat(market.getStocksBank()).containsAll(new ArrayList<>(List.of(STOCK_1, STOCK_2, STOCK_3)));
    }

    @Test
    @DisplayName("Task6 Remove Test")
    void testTask6Remove() {
        // arrange
        Burse market = new Burse();

        // act
        market.add(STOCK_1);
        market.add(STOCK_2);
        market.add(STOCK_3);
        market.remove(STOCK_2);

        // assert
        assertThat(market.getStocksBank()).containsAll(new ArrayList<>(List.of(STOCK_3, STOCK_1)));
    }

    @Test
    @DisplayName("Task6 MostValuableStock Test")
    void testMostValuableStock() {
        // arrange
        Burse market = new Burse();

        // act
        market.add(STOCK_1);
        market.add(STOCK_2);
        market.add(STOCK_3);

        // assert
        assertThat(market.mostValuableStock()).isEqualTo(STOCK_3);
    }
}
