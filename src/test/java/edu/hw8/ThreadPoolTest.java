package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class ThreadPoolTest {
    final Runnable fib1 = () -> {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(fibonacciCount(1));
    };
    final Runnable fib2 = () -> {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(fibonacciCount(2));
    };
    final Runnable fib3 = () -> {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(fibonacciCount(3));
    };
    final Runnable fib10 = () -> {
        System.out.println(fibonacciCount(10));
    };

    @Test
    @DisplayName("Fibonacci Numbers 2 Threads")
    void testThreadPool() throws InterruptedException {
        // arrange
        // threads count < tasks count
        final int threads = 2;

        // act
        try (var threadPool = new FixedThreadPool(threads)) {
            threadPool.start();
            threadPool.execute(fib1);
            threadPool.execute(fib2);
            threadPool.execute(fib3);
            threadPool.execute(fib10);
        }

        try (var threadPool1 = new FixedThreadPool(threads)) {
            threadPool1.execute(fib1);
            threadPool1.execute(fib2);
            threadPool1.execute(fib3);
            threadPool1.execute(fib10);
            threadPool1.start();
        }
    }

    @Test
    @DisplayName("Fibonacci Numbers 4 Threads")
    void testThreadPool1() throws InterruptedException {
        // arrange
        // threads count == tasks count
        final int threads = 4;

        // act
        try (var threadPool = new FixedThreadPool(threads)) {
            threadPool.start();
            threadPool.execute(fib1);
            threadPool.execute(fib2);
            threadPool.execute(fib3);
            threadPool.execute(fib10);
        }

        try (var threadPool1 = new FixedThreadPool(threads)) {
            threadPool1.execute(fib1);
            threadPool1.execute(fib2);
            threadPool1.execute(fib3);
            threadPool1.execute(fib10);
            threadPool1.start();
        }
    }

    @Test
    @DisplayName("Fibonacci Numbers 6 Threads")
    void testThreadPool2() throws InterruptedException {
        // arrange
        // threads count > tasks count
        final int threads = 6;

        // act
        try (var threadPool = new FixedThreadPool(threads)) {
            threadPool.start();
            threadPool.execute(fib1);
            threadPool.execute(fib2);
            threadPool.execute(fib3);
            threadPool.execute(fib10);
        }

        try (var threadPool1 = new FixedThreadPool(threads)) {
            threadPool1.execute(fib1);
            threadPool1.execute(fib2);
            threadPool1.execute(fib3);
            threadPool1.execute(fib10);
            threadPool1.start();
        }
    }

    private int fibonacciCount(int n) {
        if (n <= 2) {
            return 1;
        }
        int f = 1;
        int s = 1;
        for (int i = 3; i <= n; ++i) {
            int t = f;
            f = s;
            s = t + s;
        }

        return s;
    }
}
