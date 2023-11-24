package edu.hw7;

import edu.hw7.task2.ParallelFactorial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Test Factorial")
    void testFactorial() {
        // arrange
        var num1 = 10;
        var num2 = 20;

        // act
        var result1 = ParallelFactorial.parallelFactorial(num1);
        var result2 = ParallelFactorial.parallelFactorial(num2);

        // assert
        assertThat(result1).isEqualTo(3_628_800);
        assertThat(result2).isEqualTo(2_432_902_008_176_640_000L);
    }
}
