package edu.hw3;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CopyFileTest {
    static Arguments[] badString() {
        return new Arguments[] {
            Arguments.of("()())"),
            Arguments.of("((ja1))"),
            Arguments.of(")()(")
        };
    }
    @Test
    @DisplayName("Task2 Test Correct String")
    void testCorrectInputTask2() {
        // arrange
        String str1 = "()()()";
        String str2 = "((()))";
        String str3 = "((()))(())()()(()())";
        String str4 = "((())())(()(()()))";

        // act
        ArrayList<String> resStr1 = Task2.clusterize(str1);
        ArrayList<String> resStr2 = Task2.clusterize(str2);
        ArrayList<String> resStr3 = Task2.clusterize(str3);
        ArrayList<String> resStr4 = Task2.clusterize(str4);

        // assert
        assertThat(resStr1).isEqualTo(new ArrayList<>(List.of("()", "()", "()")));
        assertThat(resStr2).isEqualTo(new ArrayList<>(List.of("((()))")));
        assertThat(resStr3).isEqualTo(new ArrayList<>(List.of("((()))", "(())", "()", "()", "(()())")));
        assertThat(resStr4).isEqualTo(new ArrayList<>(List.of("((())())", "(()(()()))")));
    }

    @ParameterizedTest
    @DisplayName("Task2 Test Incorrect String")
    @MethodSource("badString")
    void testIncorrectInputTask2(String string) {
        assertThrows(InputMismatchException.class, () -> Task2.clusterize(string));
    }
}
