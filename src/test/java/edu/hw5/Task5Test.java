package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    static String[] validNumbers() {
        return new String[] {
            "А123ВЕ777",
            "О777ОО177",
            "У043НК997"
        };
    }

    static String[] notValidNumbers() {
        return new String[] {
            "123АВЕ777",
            "А123ВГ77",
            "А123ВЕ7777"
        };
    }

    @ParameterizedTest
    @DisplayName("Task5 Test Valid")
    @MethodSource("validNumbers")
    void testTask5Valid(String number) {
        // act
        var isValid = Task5.isValidNumber(number);

        // assert
        assertTrue(isValid);
    }

    @ParameterizedTest
    @DisplayName("Task5 Test Not Valid")
    @MethodSource("notValidNumbers")
    void testTask5NotValid(String number) {
        // act
        var isValid = Task5.isValidNumber(number);

        // assert
        assertFalse(isValid);
    }
}
