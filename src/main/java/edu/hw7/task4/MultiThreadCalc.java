package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadCalc implements PiCalculation {
    private final long iterations;
    private static final long RADIUS = 100_000L;

    public MultiThreadCalc(long iterations) {
        this.iterations = iterations;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public double calculatePi() {
        int optimalThreads = Runtime.getRuntime().availableProcessors();
        List<Future<Long>> circlesPointsCount = new ArrayList<>();
        try (var executorService = Executors.newFixedThreadPool(optimalThreads)) {
            for (int i = 0; i < optimalThreads; ++i) {
                circlesPointsCount
                    .add(executorService
                        .submit(() -> calcPart(Math.floorDiv(iterations, optimalThreads))));
            }

            long resCircleCount = 0;
            for (var future : circlesPointsCount) {
                resCircleCount += future.get();
            }

            return 4 * ((double) resCircleCount / (double) iterations);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Long calcPart(long iterations) {
        long circleCount = 0;
        for (long i = 0; i < iterations; ++i) {
            long x = ThreadLocalRandom.current().nextInt() % RADIUS;
            long y = ThreadLocalRandom.current().nextInt() % RADIUS;

            if (x * x + y * y <= RADIUS * RADIUS) {
                ++circleCount;
            }
        }

        return circleCount;
    }
}
