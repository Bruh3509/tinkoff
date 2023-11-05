package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Task1 Test")
    void task1Test() {
        // arrange
        String[] sessions = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };

        // act
        var result = Task1.getAverageSessionTime(sessions);

        // assert
        assertThat(result).isEqualTo("3h 40m 0s\n");
    }
}
