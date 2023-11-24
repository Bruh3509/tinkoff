package edu.hw7.task4;

import java.util.Random;

public class SingleThreadCalc implements PiCalculation {
    private final long iterations;
    private static final long RADIUS = 100_000L;

    public SingleThreadCalc(long iterations) {
        this.iterations = iterations;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public double calculatePi() {
        Random random = new Random();
        long circleCount = 0;
        for (long i = 0; i < iterations; ++i) {
            long x = random.nextInt() % RADIUS;
            long y = random.nextInt() % RADIUS;

            if (x * x + y * y <= RADIUS * RADIUS) {
                ++circleCount;
            }
        }

        return 4 * ((double) circleCount / (double) iterations);
    }
}
