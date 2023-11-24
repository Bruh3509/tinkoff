package edu.hw7;

import edu.hw7.task1.Counter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Test Counter")
    void testCounter() {
        // arrange
        var counter = new Counter(0);

        // act
        for (int i = 0; i < 10000; ++i) {
            counter.increment();
        }

        // assert
        assertThat(counter.getCounter().get()).isEqualTo(10000);
    }

    @Test
    @DisplayName("Test Counter Concurrency")
    void testCounterConcurrency() {
        // arrange
        var counter = new Counter(0);
        var incrementer1 = new Thread(() -> {
            for (int i = 0; i < 10000; ++i) {
                counter.increment();
            }
        });
        var incrementer2 = new Thread(() -> {
            for (int i = 0; i < 10000; ++i) {
                counter.increment();
            }
        });

        // act
        incrementer1.start();
        incrementer2.start();
        try {
            incrementer1.join();
            incrementer2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // assert
        assertThat(counter.getCounter().get()).isEqualTo(20000);
    }
}
