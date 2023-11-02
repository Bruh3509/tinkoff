package edu.project2;

import java.util.Deque;

public final class SolvedMazeRender implements Render {
    private final Maze maze;
    private final Deque<Coordinate> route;
    private final String mazeWall;
    private final String mazeRouteCell;
    private static final String MAZE_CELL = "  ";

    public SolvedMazeRender(Maze maze, Deque<Coordinate> route, String mazeWall, String mazeRouteCell) {
        this.maze = maze;
        this.route = route;
        this.mazeWall = mazeWall.repeat(2);
        this.mazeRouteCell = mazeRouteCell.repeat(2);
    }

    @Override
    public String renderMaze() {
        var matrix = maze.matrix();
        StringBuilder resultString = new StringBuilder();
        for (var cells : matrix) {
            for (var cell : cells) {
                if (route.contains(cell.coordinate())) {
                    route.remove(cell.coordinate());
                    //System.out.print(mazeRouteCell);
                    resultString.append(mazeRouteCell);
                } else if (cell.type().equals(Cell.Type.WALL)) {
                    //System.out.print(mazeWall);
                    resultString.append(mazeWall);
                } else {
                    //System.out.print(MAZE_CELL);
                    resultString.append(MAZE_CELL);
                }
            }
            //System.out.print('\n');
            resultString.append('\n');
        }
        //System.out.print('\n');
        resultString.append('\n');
        return resultString.toString();
    }
}
