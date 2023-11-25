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
        var num3 = 0;
        var num4 = 1;
        var num5 = 2;

        // act
        var result1 = ParallelFactorial.parallelFactorial(num1);
        var result2 = ParallelFactorial.parallelFactorial(num2);
        var result3 = ParallelFactorial.parallelFactorial(num3);
        var result4 = ParallelFactorial.parallelFactorial(num4);
        var result5 = ParallelFactorial.parallelFactorial(num5);

        // assert
        assertThat(result1).isEqualTo(3_628_800);
        assertThat(result2).isEqualTo(2_432_902_008_176_640_000L);
        assertThat(result3).isEqualTo(1);
        assertThat(result4).isEqualTo(1);
        assertThat(result5).isEqualTo(2);
    }
}
