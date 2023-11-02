package edu.project2;

public sealed interface Render permits MazeRender, SolvedMazeRender {
    String renderMaze();
}
