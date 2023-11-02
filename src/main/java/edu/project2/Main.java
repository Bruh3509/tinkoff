package edu.project2;

public class Main {
    private static final int HEIGHT = 20;
    private static final int WIDTH = 20;
    private static final String MAZE_WALL = "█";

    public static void main(String[] args) {
        var mazeGenerator = new DfsGenerator();
        var maze = mazeGenerator.generate(HEIGHT, WIDTH);
        var mazeRender = new MazeRender(maze, MAZE_WALL);
        mazeRender.printMaze();
    }
}
