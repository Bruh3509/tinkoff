package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
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
    void goodRoutesTest(Coordinate goodPoint) throws ExecutionException, InterruptedException {
        // arrange
        var executor = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        var mazesCells = createCellMatrix();
        var maze = new Maze(mazesCells);
        var solutionGenerator = new DfsSolver();
        var threadSolutionGen = new DfsSolverMultiThread(maze, new Coordinate(1,1), goodPoint);

        // act
        var resultRoute = solutionGenerator.solve(maze, new Coordinate(1, 1), goodPoint);
        var resultRoute1 = executor.submit(threadSolutionGen).get();
        executor.close();

        // assert
        assertThat(resultRoute).isNotEmpty();
        assertThat(resultRoute1).isNotEmpty();
    }

    @ParameterizedTest
    @DisplayName("Bad Routes Test")
    @MethodSource("badPoints")
    void badRoutesTest(Coordinate badPoint) throws ExecutionException, InterruptedException {
        // arrange
        var executor = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        var mazesCells = createCellMatrix();
        var maze = new Maze(mazesCells);
        var solutionGenerator = new DfsSolver();
        var threadSolutionGen = new DfsSolverMultiThread(maze, new Coordinate(1, 1), badPoint);

        // act
        var resultRoute = solutionGenerator.solve(maze, new Coordinate(1, 1), badPoint);
        var resultRoute1 = executor.submit(threadSolutionGen).get();
        executor.close();

        // assert
        assertThat(resultRoute).isEmpty();
        assertThat(resultRoute1).isEmpty();
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
