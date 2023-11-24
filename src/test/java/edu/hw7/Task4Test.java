package edu.hw7;

import edu.hw7.task4.MultiThreadCalc;
import edu.hw7.task4.SingleThreadCalc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("Test Pi Calculation MultiThread")
    void testMultiThread() {
        var multiThread = new MultiThreadCalc(1_000_000_000L);
        multiThread.calculatePi();
    }

    @Test
    @DisplayName("Test Pi Calculation SingleThread")
    void testSingleThread() {
        var singleThread = new SingleThreadCalc(1_000_000_000L);
        singleThread.calculatePi();
    }
}
