package edu.project2;

public class MazeRender {
    public static void main(String[] args) {
        DfsGenerator generator = new DfsGenerator();
        Maze maze = generator.generate(50, 50);
        String string = "█";
        var matrix = maze.getMatrix();
        for (Cell[] cells : matrix) {
            for (Cell cell : cells) {
                if (cell.type().equals(Cell.Type.WALL)) {
                    System.out.print(string + string);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print('\n');
        }
    }
}
