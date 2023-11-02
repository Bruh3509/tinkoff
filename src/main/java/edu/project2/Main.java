package edu.project2;

public class Main {
    private static final int HEIGHT = 5;
    private static final int WIDTH = 5;
    private static final String MAZE_WALL = "█";
    private static final String MAZE_ROUTE_CELL = "◆";

    public static void main(String[] args) {
        var mazeGenerator = new DfsGenerator();
        var maze = mazeGenerator.generate(HEIGHT, WIDTH);
        var solutionGenerator = new DfsSolver();
        var solvedMaze = solutionGenerator.solve(maze, new Coordinate(1, 1),
            new Coordinate(HEIGHT - 2, WIDTH - 2)
        );

        var mazeRender = new MazeRender(maze, MAZE_WALL);
        var solvedMazeRender = new SolvedMazeRender(maze, solvedMaze, MAZE_WALL, MAZE_ROUTE_CELL);
        var renderedMaze = mazeRender.renderMaze();
        var renderedSolvedMaze = solvedMazeRender.renderMaze();
        System.out.print(renderedMaze);
        System.out.print(renderedSolvedMaze);
    }
}
