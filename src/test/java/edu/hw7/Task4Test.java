package edu.hw7;

import edu.hw7.task4.MultiThreadCalc;
import edu.hw7.task4.SingleThreadCalc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;

/*
Среднее время ускорения:
Threads count == 1 -> x
Threads count == 2 -> 4 times faster
Threads count == 3 -> almost 7 times faster
Threads count == available cores count or more -> 7 times faster

Погрешность:
10_000_000L iterations -> 3.1410072
100_000_000L iterations -> 3.14169532
1_000_000_000L iterations -> 3.141537224
 */
public class Task4Test {
    @Test
    @DisplayName("Test Pi Calculation MultiThread")
    void testMultiThread() {
        var start = System.nanoTime();
        var multiThread = new MultiThreadCalc(1_000_000_000L);
        var pi = multiThread.calculatePi();
        var end = System.nanoTime();
        assertThat(pi).isGreaterThan(3);
        assertThat(pi).isLessThanOrEqualTo(3.2);
        System.out.println(Duration.ofNanos(end - start));
    }

    @Test
    @DisplayName("Test Pi Calculation SingleThread")
    void testSingleThread() {
        var start = System.nanoTime();
        var singleThread = new SingleThreadCalc(1_000_000_000L);
        var pi = singleThread.calculatePi();
        var end = System.nanoTime();
        assertThat(pi).isGreaterThan(3);
        assertThat(pi).isLessThanOrEqualTo(3.2);
        System.out.println(Duration.ofNanos(end - start));
    }
}
