package edu.hw3;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("Task4 Test")
    void testTask4() {
        // arrange
        int two = 2;
        int twelve = 12;
        int sixteen = 16;

        // act
        String romanTwo = Task4.convertToRoman(two);
        String romanTwelve = Task4.convertToRoman(twelve);
        String romanSixteen = Task4.convertToRoman(sixteen);

        // assert
        assertThat(romanTwo).isEqualTo("II");
        assertThat(romanTwelve).isEqualTo("XII");
        assertThat(romanSixteen).isEqualTo("XVI");
    }
}
