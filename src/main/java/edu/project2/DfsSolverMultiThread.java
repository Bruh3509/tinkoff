package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public final class DfsSolverMultiThread extends RecursiveTask<Deque<Coordinate>> implements MazeSolver {
    private final Maze maze;
    private final Coordinate begin;
    private final Coordinate end;
    private final List<Cell> unvisitedCells;
    private final Deque<Coordinate> curRoute;
    private final Deque<Cell> cellsStack;

    public DfsSolverMultiThread(Maze maze, Coordinate begin, Coordinate end) {
        this.maze = maze;
        this.begin = begin;
        this.end = end;
        curRoute = new LinkedList<>();
        cellsStack = new LinkedList<>();
        unvisitedCells = new ArrayList<>();
        fillUnvisitedCells(maze.matrix());
        unvisitedCells.remove(maze.matrix()[begin.row()][begin.column()]);
        curRoute.addLast(begin);
    }

    private DfsSolverMultiThread(
        Maze maze, Coordinate begin, Coordinate end, List<Cell> unvisitedCells,
        Deque<Coordinate> curRoute,
        Deque<Cell> cellsStack
    ) {
        this.maze = maze;
        this.begin = begin;
        this.end = end;
        this.unvisitedCells = new ArrayList<>(unvisitedCells);
        this.curRoute = curRoute;
        this.cellsStack = cellsStack;
    }

    @Override
    public Deque<Coordinate> solve(Maze maze, Coordinate begin, Coordinate end) {
        try (var executorService = new ForkJoinPool(Runtime.getRuntime().availableProcessors())) {
            var task1 = executorService.submit(this);
            return task1.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Deque<Coordinate> compute() {
        var tasks = createSubtasks();
        if (!tasks.isEmpty()) {
            return ForkJoinTask.invokeAll(tasks).stream()
                .map(ForkJoinTask::join)
                .filter(route -> route != null && !route.isEmpty() && route.getLast().equals(end))
                .findAny().orElse(new LinkedList<>());
        } else {
            return curRoute;
        }
    }

    public Collection<DfsSolverMultiThread> createSubtasks() {
        List<DfsSolverMultiThread> result = new ArrayList<>();
        Cell[][] matrix = maze.matrix();
        var currentCell = matrix[begin.row()][begin.column()];

        var unvisitedNeighbours = getUnvisitedNeighbours(currentCell);
        if (begin.equals(end)) {
            return new LinkedList<>();
        }
        if (!unvisitedNeighbours.isEmpty()) {
            Deque<Cell> tempStack = new LinkedList<>(cellsStack);
            Deque<Coordinate> tempRoute;
            tempStack.push(currentCell);

            for (var cell : unvisitedNeighbours) {
                unvisitedCells.remove(cell);
                tempRoute = new LinkedList<>(curRoute);
                tempRoute.addLast(cell.coordinate());
                result.add(new DfsSolverMultiThread(maze, cell.coordinate(), end,
                    unvisitedCells,
                    tempRoute,
                    tempStack
                ));
            }

            return result;
        } else if (!cellsStack.isEmpty()) {
            cellsStack.pop();
            curRoute.pollLast();

            for (var cell : unvisitedNeighbours) {
                result.add(new DfsSolverMultiThread(maze, cell.coordinate(), end,
                    unvisitedCells,
                    curRoute,
                    cellsStack
                ));
            }

            return result;
        } else {
            return new LinkedList<>();
        }
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
