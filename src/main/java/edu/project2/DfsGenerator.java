package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public final class DfsGenerator implements MazeGenerator {
    private final List<Cell> unvisitedCells;
    private static final int MINIMAL_MAZE_SIZE = 5;

    public DfsGenerator() {
        unvisitedCells = new ArrayList<>();
    }

    @Override
    public Maze generate(int height, int width) {
        if (height < MINIMAL_MAZE_SIZE || width < MINIMAL_MAZE_SIZE) {
            throw new InputMismatchException();
        }
        var matrix = generateRawMatrix(height, width);
        Stack<Cell> cellsStack = new Stack<>();

        Random random = new Random();
        var currentCell = matrix[1][1];
        unvisitedCells.remove(currentCell);
        while (!unvisitedCells.isEmpty()) {
            var unvisitedNeighbours = getUnvisitedNeighbours(currentCell);
            if (!unvisitedNeighbours.isEmpty()) {
                cellsStack.push(currentCell);
                var randomNeighbourCell =
                    unvisitedNeighbours.get(Math.abs(random.nextInt()) % unvisitedNeighbours.size());
                var newCell = getWallCell(currentCell, randomNeighbourCell);
                matrix[newCell.coordinate().row()][newCell.coordinate().column()] = newCell;
                unvisitedCells.remove(randomNeighbourCell);
                currentCell = randomNeighbourCell;
            } else if (!cellsStack.isEmpty()) {
                currentCell = cellsStack.pop();
            } else {
                currentCell = unvisitedCells.remove(Math.abs(random.nextInt()) % unvisitedCells.size());
            }
        }

        return new Maze(matrix);
    }

    private Cell[][] generateRawMatrix(int height, int width) {
        Cell[][] rawMatrix = new Cell[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                var currentCoordinate = new Coordinate(i, j);
                if ((i % 2 != 0 && j % 2 != 0)
                    && (i < height - 1 && j < width - 1)) {
                    rawMatrix[i][j] = new Cell(currentCoordinate, Cell.Type.PASSAGE);
                    unvisitedCells.add(rawMatrix[i][j]);
                } else {
                    rawMatrix[i][j] = new Cell(currentCoordinate, Cell.Type.WALL);
                }
            }
        }

        return rawMatrix;
    }

    private List<Cell> getUnvisitedNeighbours(Cell cell) {
        var cellsRow = cell.coordinate().row();
        var cellsColumn = cell.coordinate().column();

        var up = new Cell(new Coordinate(cellsRow - 2, cellsColumn), Cell.Type.PASSAGE);
        var down = new Cell(new Coordinate(cellsRow + 2, cellsColumn), Cell.Type.PASSAGE);
        var left = new Cell(new Coordinate(cellsRow, cellsColumn - 2), Cell.Type.PASSAGE);
        var right = new Cell(new Coordinate(cellsRow, cellsColumn + 2), Cell.Type.PASSAGE);
        Cell[] neighbourCells = new Cell[] {up, down, left, right};

        return
            Arrays.stream(neighbourCells)
                .filter(unvisitedCells::contains)
                .toList();
    }

    private Cell getWallCell(Cell currentCell, Cell randomNeighbourCell) {
        var currentCellCoordinate = currentCell.coordinate();
        var cellsCoordinate = randomNeighbourCell.coordinate();

        var newCellRow = currentCellCoordinate.row();
        if ((currentCellCoordinate.row() - cellsCoordinate.row()) > 0) {
            newCellRow -= 1;
        } else if ((currentCellCoordinate.row() - cellsCoordinate.row()) < 0) {
            newCellRow += 1;
        }

        var newCellCol = currentCellCoordinate.column();
        if ((currentCellCoordinate.column() - cellsCoordinate.column()) > 0) {
            newCellCol -= 1;
        } else if ((currentCellCoordinate.column() - cellsCoordinate.column()) < 0) {
            newCellCol += 1;
        }

        return new Cell(new Coordinate(newCellRow, newCellCol), Cell.Type.PASSAGE);
    }
}
