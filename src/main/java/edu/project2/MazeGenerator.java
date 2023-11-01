package edu.project2;

public sealed interface MazeGenerator permits DfsGenerator {
    Maze generate(int height, int width);
}
