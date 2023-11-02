package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public final class DfsSolver implements MazeSolver {
    private final List<Cell> unvisitedCells;
    private Cell[][] matrix;

    public DfsSolver() {
        unvisitedCells = new ArrayList<>();
    }

    @Override
    public Deque<Coordinate> solve(Maze maze, Coordinate begin, Coordinate end) {
        Deque<Coordinate> resultRoute = new LinkedList<>();
        matrix = maze.matrix();
        fillUnvisitedCells(matrix);
        var currentCell = matrix[begin.row()][begin.column()];
        unvisitedCells.remove(currentCell);

        Random random = new Random();
        Stack<Cell> cellsStack = new Stack<>();
        resultRoute.addLast(currentCell.coordinate());
        while (!currentCell.coordinate().equals(end)) {
            var unvisitedNeighbours = getUnvisitedNeighbours(currentCell);
            if (!unvisitedNeighbours.isEmpty()) {
                cellsStack.push(currentCell);
                var randomNeighbourCell =
                    unvisitedNeighbours.get(Math.abs(random.nextInt()) % unvisitedNeighbours.size());
                unvisitedCells.remove(randomNeighbourCell);
                currentCell = randomNeighbourCell;
                resultRoute.addLast(currentCell.coordinate());
            } else if (!cellsStack.isEmpty()) {
                currentCell = cellsStack.pop();
                resultRoute.pollLast();
            } else {
                return new LinkedList<>();
            }
        }
        return resultRoute;
    }

    private void fillUnvisitedCells(Cell[][] matrix) {
        for (var cells : matrix) {
            for (var cell : cells) {
                if (cell.type().equals(Cell.Type.PASSAGE)) {
                    unvisitedCells.add(cell);
                }
            }
        }
    }

    private List<Cell> getUnvisitedNeighbours(Cell cell) {
        var cellsRow = cell.coordinate().row();
        var cellsColumn = cell.coordinate().column();

        // Define neighbour cells
        var up = new Cell(new Coordinate(cellsRow - 1, cellsColumn), Cell.Type.PASSAGE);
        var down = new Cell(new Coordinate(cellsRow + 1, cellsColumn), Cell.Type.PASSAGE);
        var left = new Cell(new Coordinate(cellsRow, cellsColumn - 1), Cell.Type.PASSAGE);
        var right = new Cell(new Coordinate(cellsRow, cellsColumn + 1), Cell.Type.PASSAGE);
        Cell[] neighbourCells = new Cell[] {up, down, left, right};

        return
            Arrays.stream(neighbourCells)
                .filter(unvisitedCells::contains)
                .toList();
    }
}
