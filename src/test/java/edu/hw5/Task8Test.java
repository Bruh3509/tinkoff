package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    static String[] task1BadStrings() {
        return new String[] {
            "",
            "10",
            "00",
            "11",
            "01",
            "1010",
            "0100",
            "01011101"
        };
    }

    static String[] task1GoodStrings() {
        return new String[] {
            "1",
            "0",
            "101",
            "010",
            "110",
            "001",
            "100",
            "1010001"
        };
    }

    static String[] task2BadStrings() {
        return new String[] {
            "",
            "1",
            "0",
            "1100",
            "1001011100010",
            "10",
            "01"
        };
    }

    static String[] task2GoodStrings() {
        return new String[] {
            "000",
            "101010",
            "000110100",
            "10101000010100"
        };
    }

    static String[] task3GoodStrings() {
        return new String[] {
            "1",
            "011",
            "1110",
            "11010",
            "11111"
        };
    }

    static String[] task4BadStrings() {
        return new String[] {
            "0",
            "01101",
            "11110"
        };
    }

    static String[] task4GoodStrings() {
        return new String[] {
            "1",
            "11",
            "101",
            "1011111",
            "101011101"
        };
    }

    static String[] task5BadStrings() {
        return new String[] {
            "1011",
            "11",
            "01001101"
        };
    }

    static String[] task5GoodStrings() {
        return new String[] {
            "010010",
            "1",
            "0",
            ""
        };
    }
    @ParameterizedTest
    @DisplayName("Task1 Test Not Valid")
    @MethodSource("task1BadStrings")
    void testTask1NotValid(String badString) {
        // act
        var result = Task8.task1(badString);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Task1 Test Valid")
    @MethodSource("task1GoodStrings")
    void testTask1Valid(String goodString) {
        // act
        var result = Task8.task1(goodString);

        // assert
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Task2 Test Not Valid")
    @MethodSource("task2BadStrings")
    void testTask2NotValid(String badString) {
        // act
        var result = Task8.task2(badString);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Task2 Test Valid")
    @MethodSource("task2GoodStrings")
    void testTask2Valid(String goodString) {
        // act
        var result = Task8.task2(goodString);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Task3 Test Not Valid")
    void testTask3() {
        // arrange
        var string1 = "111";
        var string2 = "11";

        // act
        var result1 = Task8.task3(string1);
        var result2 = Task8.task3(string2);

        // assert
        assertFalse(result1);
        assertFalse(result2);
    }

    @ParameterizedTest
    @DisplayName("Task3 Test Valid")
    @MethodSource("task3GoodStrings")
    void testTask3Valid(String goodString) {
        // act
        var result = Task8.task3(goodString);

        // assert
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Task4 Test Not Valid")
    @MethodSource("task4BadStrings")
    void testTask4NotValid(String badString) {
        // act
        var result = Task8.task4(badString);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Task4 Test Valid")
    @MethodSource("task4GoodStrings")
    void testTask4Valid(String goodString) {
        // act
        var result = Task8.task4(goodString);

        // assert
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Task5 Test Not Valid")
    @MethodSource("task5BadStrings")
    void testTask5NotValid(String badString) {
        // act
        var result = Task8.task5(badString);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Task5 Test Valid")
    @MethodSource("task5GoodStrings")
    void testTask5Valid(String goodString) {
        // act
        var result = Task8.task5(goodString);

        // assert
        assertTrue(result);
    }
}
