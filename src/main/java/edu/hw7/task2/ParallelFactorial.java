package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class ParallelFactorial {
    public static BigInteger parallelFactorial(int number) {
        if (number < 2) {
            return BigInteger.valueOf(1);
        }
        return IntStream.rangeClosed(2, number).parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger::multiply)
            .orElse(null);
    }

    private ParallelFactorial() {
    }
}
