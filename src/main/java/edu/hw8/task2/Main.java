package edu.hw8.task2;

public class Main {
    public static void main(String[] args) throws Exception {
        try (FixedThreadPool threadPool = new FixedThreadPool()) {
            threadPool.create(3);
            threadPool.start();
            threadPool.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("12345");});
            threadPool.execute(() -> System.out.println("Hello"));
            threadPool.execute(() -> System.out.println("Bye"));
        }
    }
}
