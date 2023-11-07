package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FindRouteTest {

    private static Coordinate[] goodPoints() {
        return new Coordinate[] {
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3)
        };
    }

    private static Coordinate[] badPoints() {
        return new Coordinate[] {
            new Coordinate(3, 1),
            new Coordinate(3, 2),
            new Coordinate(3, 3)
        };
    }

    @ParameterizedTest
    @DisplayName("Good Routes Test")
    @MethodSource("goodPoints")
    void goodRoutesTest(Coordinate goodPoint) {
        // arrange
        var mazesCells = createCellMatrix();
        var maze = new Maze(mazesCells);
        var solutionGenerator = new DfsSolver();

        // act
        var resultRoute = solutionGenerator.solve(maze, new Coordinate(1, 1), goodPoint);

        // assert
        assertThat(resultRoute).isNotEmpty();
    }

    @ParameterizedTest
    @DisplayName("Bad Routes Test")
    @MethodSource("badPoints")
    void badRoutesTest(Coordinate badPoint) {
        // arrange
        var mazesCells = createCellMatrix();
        var maze = new Maze(mazesCells);
        var solutionGenerator = new DfsSolver();

        // act
        var resultRoute = solutionGenerator.solve(maze, new Coordinate(1, 1), badPoint);

        // assert
        assertThat(resultRoute).isEmpty();
    }

    private Cell[][] createCellMatrix() {
        Cell[][] mazesCells = new Cell[5][5];
        for (int i = 0; i < 5; ++i) {
            mazesCells[0][i] = new Cell(new Coordinate(0, i), Cell.Type.WALL);
            mazesCells[4][i] = new Cell(new Coordinate(4, i), Cell.Type.WALL);
            mazesCells[i][0] = new Cell(new Coordinate(i, 0), Cell.Type.WALL);
            mazesCells[i][4] = new Cell(new Coordinate(i, 4), Cell.Type.WALL);
        }
        for (int i = 1; i < 5; i += 2) {
            for (int j = 1; j < 4; ++j) {
                mazesCells[i][j] = new Cell(new Coordinate(i, j), Cell.Type.PASSAGE);
            }
        }
        for (int i = 1; i < 4; ++i) {
            mazesCells[2][i] = new Cell(new Coordinate(2, i), Cell.Type.WALL);
        }
        return mazesCells;
    }
}
