package hw1;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SampleTest {
    @Test
    @DisplayName("Task1")
    void testMinutesToSeconds() {
        // arrange
        String video1 = "01:00";
        String video2 = "13:56";
        String video3 = "10:60";
        String video4 = "-12:36";
        String video5 = "45:-8";
        String video6 = "00:13";

        // act
        int res1 = Task1.minutesToSeconds(video1);
        int res2 = Task1.minutesToSeconds(video2);
        int res3 = Task1.minutesToSeconds(video3);
        int res4 = Task1.minutesToSeconds(video4);
        int res5 = Task1.minutesToSeconds(video5);
        int res6 = Task1.minutesToSeconds(video6);

        // assert
        assertThat(res1).isEqualTo(60);
        assertThat(res2).isEqualTo(836);
        assertThat(res3).isEqualTo(-1);
        assertThat(res4).isEqualTo(-1);
        assertThat(res5).isEqualTo(-1);
        assertThat(res6).isEqualTo(13);
    }

    @Test
    @DisplayName("Task2")
    void testCountDigits() {
        // arrange
        int num1 = 4666;
        int num2 = 544;
        int num3 = 0;
        int num4 = -123552;
        int num5 = -1;

        // act
        int res1 = Task2.countDigits(num1);
        int res2 = Task2.countDigits(num2);
        int res3 = Task2.countDigits(num3);
        int res4 = Task2.countDigits(num4);
        int res5 = Task2.countDigits(num5);

        // assert
        assertThat(res1).isEqualTo(4);
        assertThat(res2).isEqualTo(3);
        assertThat(res3).isEqualTo(1);
        assertThat(res4).isEqualTo(6);
        assertThat(res5).isEqualTo(1);
    }

    @Test
    @DisplayName("Task3")
    void testIsNestable() {
        // arrange
        int[] arr1_1 = {1, 2, 3, 4};
        int[] arr1_2 = {0, 6};

        int[] arr2_1 = {3, 1};
        int[] arr2_2 = {4, 0};

        int[] arr3_1 = {9, 9, 8};
        int[] arr3_2 = {8, 9};

        int[] arr4_1 = {1, 2, 3, 4};
        int[] arr4_2 = {2, 3};

        // act
        boolean res1 = Task3.isNestable(arr1_1, arr1_2);
        boolean res2 = Task3.isNestable(arr2_1, arr2_2);
        boolean res3 = Task3.isNestable(arr3_1, arr3_2);
        boolean res4 = Task3.isNestable(arr4_1, arr4_2);

        // assert
        assertThat(res1).isTrue();
        assertThat(res2).isTrue();
        assertThat(res3).isFalse();
        assertThat(res4).isFalse();
    }

    @Test
    @DisplayName("Task4")
    void testFixString() {
        // arrange
        String brokenString1 = "123456";
        String brokenString2 = "hTsii  s aimex dpus rtni.g";
        String brokenString3 = "badce";
        String brokenString4 = "";

        // act
        String fixedString1 = Task4.fixString(brokenString1);
        String fixedString2 = Task4.fixString(brokenString2);
        String fixedString3 = Task4.fixString(brokenString3);
        String fixedString4 = Task4.fixString(brokenString4);

        // arrange
        assertThat(fixedString1).isEqualTo("214365");
        assertThat(fixedString2).isEqualTo("This is a mixed up string.");
        assertThat(fixedString3).isEqualTo("abcde");
        assertThat(fixedString4).isEqualTo("");
    }

    @Test
    @DisplayName("Task5")
    void testIsPalindromeDescendant() {
        // arrange
        int palindrome1 = 11211230;
        int palindrome2 = 13001120;
        int palindrome3 = 23336014;
        int palindrome4 = 11;

        // act
        boolean res1 = Task5.isPalindromeDescendant(palindrome1);
        boolean res2 = Task5.isPalindromeDescendant(palindrome2);
        boolean res3 = Task5.isPalindromeDescendant(palindrome3);
        boolean res4 = Task5.isPalindromeDescendant(palindrome4);

        // assert
        assertThat(res1).isTrue();
        assertThat(res2).isTrue();
        assertThat(res3).isTrue();
        assertThat(res4).isTrue();
    }
}
