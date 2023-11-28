package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class ThreadPoolTest {
    @Test
    @DisplayName("Fibonacci Numbers")
    void testThreadPool() throws InterruptedException {
        // arrange
        Runnable fib1 = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(fibonacciCount(1));
        };
        Runnable fib2 = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(fibonacciCount(2));
        };
        Runnable fib3 = () -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(fibonacciCount(3));
        };
        Runnable fib10 = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(fibonacciCount(10));
        };
        final int threads = 2;

        // act
        try (var threadPool = new FixedThreadPool()) {
            threadPool.create(threads);
            threadPool.start();
            threadPool.execute(fib1);
            threadPool.execute(fib2);
            threadPool.execute(fib3);
            threadPool.execute(fib10);
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
