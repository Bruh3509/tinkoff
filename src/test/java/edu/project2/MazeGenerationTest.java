package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.InputMismatchException;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazeGenerationTest {
    private static Arguments[] badSizes() {
        return new Arguments[] {
            Arguments.of(Map.entry(1, 10)),
            Arguments.of(Map.entry(2, 2)),
            Arguments.of(Map.entry(3, 5)),
            Arguments.of(Map.entry(0, 0))
        };
    }

    private static Arguments[] goodSizes() {
        return new Arguments[] {
            Arguments.of(Map.entry(5, 5)),
            Arguments.of(Map.entry(5, 10)),
            Arguments.of(Map.entry(20, 20)),
            Arguments.of(Map.entry(13, 21))
        };
    }

    @ParameterizedTest
    @DisplayName("Bad Maze Size")
    @MethodSource("badSizes")
    void wrongSizeTest(Map.Entry<Integer, Integer> entry) {
        // arrange
        var mazeGenerator = new DfsGenerator();

        // assert
        assertThrows(
            InputMismatchException.class,
            () -> mazeGenerator.generate(entry.getKey(), entry.getValue())
        );
    }

    @ParameterizedTest
    @DisplayName("Good Maze Size")
    @MethodSource("goodSizes")
    void goodSizeTest(Map.Entry<Integer, Integer> entry) {
        // arrange
        var mazeGenerator = new DfsGenerator();

        // assert
        mazeGenerator.generate(entry.getKey(), entry.getValue());
    }
}
