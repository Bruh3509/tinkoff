package edu.project2;

public class MazeRender {
    private final Maze maze;
    private final String mazeWall;
    private static final String mazeCell = "  ";

    public MazeRender(Maze maze, String mazeWall) {
        this.maze = maze;
        this.mazeWall = mazeWall.repeat(2);
    }

    public void printMaze() {
        var matrix = maze.getMatrix();
        for (var cells : matrix) {
            for (var cell : cells) {
                if (cell.type().equals(Cell.Type.WALL)) {
                    System.out.print(mazeWall);
                } else {
                    System.out.print(mazeCell);
                }
            }
            System.out.print('\n');
        }
    }
}
