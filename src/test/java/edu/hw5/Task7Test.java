package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    static String[] task1BadStrings() {
        return new String[] {
            "11",
            "00",
            "120",
            "0010",
            "01013",
            "bad string"
        };
    }

    static String[] task1GoodStrings() {
        return new String[] {
            "110",
            "000",
            "110001",
            "110111"
        };
    }

    static String[] task2BadStrings() {
        return new String[] {
            "aba",
            "aboba",
            "0101",
            "1000",
            "10100"
        };
    }

    static String[] task2GoodStrings() {
        return new String[] {
            "101",
            "11",
            "00",
            "0",
            "1",
            "0100101110",
            "1011101"
        };
    }

    static String[] task3BadStrings() {
        return new String[] {
            "aba",
            "",
            "101010"
        };
    }

    static String[] task3GoodStrings() {
        return new String[] {
            "1",
            "0",
            "10",
            "01",
            "101",
            "000"
        };
    }

    @ParameterizedTest
    @DisplayName("Task1 Test Bad Strings")
    @MethodSource("task1BadStrings")
    void testTask1NotValid(String badString) {
        // act
        var result = Task7.task1(badString);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Task1 Test Good Strings")
    @MethodSource("task1GoodStrings")
    void testTask1Valid(String goodString) {
        // act
        var result = Task7.task1(goodString);

        // assert
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Task2 Test Bad Strings")
    @MethodSource("task2BadStrings")
    void testTask2NotValid(String badString) {
        // act
        var result = Task7.task2(badString);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Task2 Test Good Strings")
    @MethodSource("task2GoodStrings")
    void testTask2Valid(String goodString) {
        // act
        var result = Task7.task2(goodString);

        // assert
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Task3 Test Bad Strings")
    @MethodSource("task3BadStrings")
    void testTask3NotValid(String badString) {
        // act
        var result = Task7.task3(badString);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("Task3 Test Good Strings")
    @MethodSource("task3GoodStrings")
    void testTask3Valid(String goodString) {
        // act
        var result = Task7.task3(goodString);

        // assert
        assertTrue(result);
    }
}
