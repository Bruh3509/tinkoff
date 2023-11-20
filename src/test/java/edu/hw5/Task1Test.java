package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Task1 Test")
    void testTask1() {
        // arrange
        var sessions1 = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };
        var sessions2 = new String[] {
            "2020-03-12, 19:00 - 2020-03-13, 01:20",
            "2020-05-09, 20:20 - 2020-05-10, 00:00",
            "2020-05-10, 02:00 - 2020-05-10, 03:00",
            "2020-05-10, 06:00 - 2020-05-10, 10:00"
        };

        // act
        var result1 = Task1.getAverageSessionTime(sessions1);
        var result2 = Task1.getAverageSessionTime(sessions2);

        // assert
        assertThat(result1).isEqualTo("3h 40m\n");
        assertThat(result2).isEqualTo("3h 45m\n");
    }
}
