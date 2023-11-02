package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MazeRenderTest {
    private static final String RENDERED_MAZE =
        """
            ██████████
            ██      ██
            ██████  ██
            ██      ██
            ██████████

            """;
    private static final String RENDERED_SOLVED_MAZE =
        """
            ██████████
            ██◆◆◆◆◆◆██
            ██████◆◆██
            ██    ◆◆██
            ██████████

            """;

    @Test
    @DisplayName("Maze Render Test")
    void mazeRenderTest() {
        // arrange
        var mazesCells = createCellMatrix();
        var maze = new Maze(mazesCells);
        var mazeRender = new MazeRender(maze, "█");

        // act
        var renderedMaze = mazeRender.renderMaze();

        // assert
        assertThat(renderedMaze).isEqualTo(RENDERED_MAZE);
    }

    @Test
    @DisplayName("Solved Maze Render Test")
    void solvedMazeRenderTest() {
        // arrange
        var mazesCells = createCellMatrix();
        var maze = new Maze(mazesCells);
        var solutionGenerator = new DfsSolver();
        var route = solutionGenerator.solve(maze, new Coordinate(1, 1), new Coordinate(3, 3));
        var solvedMazeRender = new SolvedMazeRender(maze, route, "█", "◆");

        // act
        var renderedSolvedMaze = solvedMazeRender.renderMaze();

        // assert
        assertThat(renderedSolvedMaze).isEqualTo(RENDERED_SOLVED_MAZE);
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
        mazesCells[2][3] = new Cell(new Coordinate(2, 3), Cell.Type.PASSAGE);
        mazesCells[2][1] = new Cell(new Coordinate(2, 1), Cell.Type.WALL);
        mazesCells[2][2] = new Cell(new Coordinate(2, 2), Cell.Type.WALL);

        return mazesCells;
    }
}
