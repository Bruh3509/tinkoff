package edu.hw1;

public class Task8 {
    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 1) {
                    if (isCurrentCanCapture(board, i, j)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean isCurrentCanCapture(int[][] board, int i, int j) {
        if (i - 2 >= 0 && j - 1 >= 0) {
            if (board[i - 2][j - 1] == 1) {
                return true;
            }
        }
        if (i - 2 >= 0 && j + 1 < board.length) {
            if (board[i - 2][j + 1] == 1) {
                return true;
            }
        }
        if (i - 1 >= 0 && j - 2 >= 0) {
            if (board[i - 1][j - 2] == 1) {
                return true;
            }
        }
        if (i - 1 >= 0 && j + 2 < board.length) {
            if (board[i - 1][j + 2] == 1) {
                return true;
            }
        }
        if (i + 1 < board.length && j - 2 >= 0) {
            if (board[i + 1][j - 2] == 1) {
                return true;
            }
        }
        if (i + 1 < board.length && j + 2 < board.length) {
            if (board[i + 1][j + 2] == 1) {
                return true;
            }
        }
        if (i + 2 < board.length && j - 1 >= 0) {
            if (board[i + 2][j - 1] == 1) {
                return true;
            }
        }
        if (i + 2 < board.length && j + 1 < board.length) {
            if (board[i + 2][j + 1] == 1) {
                return true;
            }
        }

        return false;
    }

    private Task8() {
    }
}
