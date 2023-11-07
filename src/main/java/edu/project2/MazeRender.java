package edu.project2;

public final class MazeRender implements Render {
    private final Maze maze;
    private final String mazeWall;
    private static final String MAZE_CELL = "  ";

    public MazeRender(Maze maze, String mazeWall) {
        this.maze = maze;
        this.mazeWall = mazeWall.repeat(2);
    }

    public String renderMaze() {
        var matrix = maze.matrix();
        StringBuilder resultString = new StringBuilder();
        for (var cells : matrix) {
            for (var cell : cells) {
                if (cell.type().equals(Cell.Type.WALL)) {
                    resultString.append(mazeWall);
                } else {
                    resultString.append(MAZE_CELL);
                }
            }
            resultString.append('\n');
        }
        resultString.append('\n');
        return resultString.toString();
    }
}
