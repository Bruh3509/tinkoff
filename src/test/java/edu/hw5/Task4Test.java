package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    static String[] securePasswords() {
        return new String[] {
            "12^7asd",
            "%asd#",
            "~!|",
            "a12&^@la12",
            "*ak12kal"
        };
    }

    static String[] notSecurePasswords() {
        return new String[] {
            "(123)fdla",
            "139ajvka942",
            "not secure password 123"
        };
    }
    @ParameterizedTest
    @DisplayName("Task4 Test Secure")
    @MethodSource("securePasswords")
    void testTas4Secure(String password) {
        // act
        var isSecure = Task4.isSecurePassword(password);

        // assert
        assertTrue(isSecure);
    }

    @ParameterizedTest
    @DisplayName("Task4 Test Not Secure")
    @MethodSource("notSecurePasswords")
    void testTask4NotSecure(String password) {
        // act
        var isSecure = Task4.isSecurePassword(password);

        // assert
        assertFalse(isSecure);
    }
}
