package edu.project2;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] matrix;

    public Cell[][] getMatrix() {
        return matrix;
    }

    public Maze(int height, int width, Cell[][] matrix) {
        this.height = height;
        this.width = width;
        this.matrix = matrix;
    }
}
