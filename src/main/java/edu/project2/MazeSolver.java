package edu.project2;

import java.util.Deque;

public sealed interface MazeSolver permits DfsSolver, DfsSolverMultiThread {
    Deque<Coordinate> solve(Maze maze, Coordinate begin, Coordinate end);
}
