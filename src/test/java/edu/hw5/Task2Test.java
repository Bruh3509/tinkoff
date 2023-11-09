package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Test Task2 All Fridays Thirteen")
    void testTask2getAll() {
        // arrange
        int year1 = 1925;
        int year2 = 2024;

        // act
        var result1 = Task2.getAllFridayThirteen(year1);
        var result2 = Task2.getAllFridayThirteen(year2);

        // assert
        assertThat(result1).containsExactlyInAnyOrder(
            LocalDate.of(1925, Month.FEBRUARY, 13),
            LocalDate.of(1925, Month.MARCH, 13),
            LocalDate.of(1925, Month.NOVEMBER, 13)
        );

        assertThat(result2).containsExactlyInAnyOrder(
            LocalDate.of(2024, Month.SEPTEMBER, 13),
            LocalDate.of(2024, Month.DECEMBER, 13)
        );
    }

    @Test
    @DisplayName("Test Task2 Nearest Friday Thirteen")
    void testTask2getTheNearest() {
        // arrange
        var localDate1 = LocalDate.of(1925, 3, 13);
        var localDate2 = LocalDate.of(2024, 9, 13);
        var localDate3 = LocalDate.of(2024, 12, 13);

        // act
        var nearestFridayThirteen1 = Task2.getNearestFridayThirteen(localDate1);
        var nearestFridayThirteen2 = Task2.getNearestFridayThirteen(localDate2);
        var nearestFridayThirteen3 = Task2.getNearestFridayThirteen(localDate3);

        // assert
        assertThat(nearestFridayThirteen1).isEqualTo(LocalDate.of(1925, 11, 13));
        assertThat(nearestFridayThirteen2).isEqualTo(LocalDate.of(2024, 12, 13));
        assertThat(nearestFridayThirteen3).isEqualTo(LocalDate.of(2025, 6, 13));
    }
}
