package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger counter;

    public Counter(int value) {
        counter = new AtomicInteger(value);
    }

    public Counter() {
        counter = new AtomicInteger(0);
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public AtomicInteger getCounter() {
        return counter;
    }
}
