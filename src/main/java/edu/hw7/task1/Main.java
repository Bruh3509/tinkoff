package edu.hw7.task1;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Counter");

    public static void main(String[] args) {
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

        incrementer1.start();
        incrementer2.start();

        try {
            incrementer1.join();
            incrementer2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info(counter.getCounter().toString());
    }
}
