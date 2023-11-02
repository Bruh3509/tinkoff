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
        int fiftyNine = 59;
        int threeHundredsSixtyEight = 368;
        int sevenHundredsSeventyFive = 775;
        int threeThousandsEightHundredsNinetyNine = 3899;

        // act
        String romanTwo = Task4.convertToRoman(two);
        String romanTwelve = Task4.convertToRoman(twelve);
        String romanSixteen = Task4.convertToRoman(sixteen);
        String romanFiftyNine = Task4.convertToRoman(fiftyNine);
        String romanThreeHundredsSixtyEight = Task4.convertToRoman(threeHundredsSixtyEight);
        String romanSevenHundredsSeventyFive = Task4.convertToRoman(sevenHundredsSeventyFive);
        String romanThreeThousandsEightHundredsNinetyNine = Task4.convertToRoman(threeThousandsEightHundredsNinetyNine);

        // assert
        assertThat(romanTwo).isEqualTo("II");
        assertThat(romanTwelve).isEqualTo("XII");
        assertThat(romanSixteen).isEqualTo("XVI");
        assertThat(romanFiftyNine).isEqualTo("LVIIII");
        assertThat(romanThreeHundredsSixtyEight).isEqualTo("CCCLXVIII");
        assertThat(romanSevenHundredsSeventyFive).isEqualTo("DCCLXXV");
        assertThat(romanThreeThousandsEightHundredsNinetyNine).isEqualTo("MMMDCCCLXXXXVIIII");
    }
}
