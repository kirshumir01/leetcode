package hard;

import java.util.Arrays;

public class SudokuSolver {
    int n = 3;
    int N = n * n;

    int[][] rows = new int[N][N + 1];
    int[][] columns = new int[N][N + 1];
    int[][] boxes = new int[N][N + 1];

    char[][] board;

    boolean sudokuSolved = false;

    public boolean couldPlace(int d, int row, int col) {
        int index = (row / n) * n + col / n;
        return rows[row][d] + columns[col][d] + boxes[index][d] == 0;
    }

    public void placeNumber(int d, int row, int col) {
        int index = (row / n) * n + col / n;

        rows[row][d]++;
        columns[col][d]++;
        boxes[index][d]++;
        board[row][col] = (char) (d + '0');
    }

    public void removeNumber(int d, int row, int col) {
        int index = (row / n) * n + col / n;

        rows[row][d]--;
        columns[col][d]--;
        boxes[index][d]--;
        board[row][col] = '.';
    }

    public void placeNextNumbers(int row, int col) {
        if ((col == N - 1) && (row == N - 1)) {
            sudokuSolved = true;
        } else {
            if (col == N - 1) backTrack(row + 1, 0);
            else backTrack(row, col + 1);
        }
    }

    public void backTrack(int row, int col) {
        if (board[row][col] == '.') {
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    if (!sudokuSolved) removeNumber(d, row, col);
                }
            }
        } else placeNextNumbers(row, col);
    }

    public void solveSudoku(char[][] board) {
        this.board = board;

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                char num = board[j][i];
                if (num != '.') {
                    int d = Character.getNumericValue(num);
                    placeNumber(d, j, i);
                }
            }
        }
        backTrack(0, 0);
    }

    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        sudokuSolver.solveSudoku(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    System.out.print("| " + Character.getNumericValue(board[i][j]) + " | ");
                } else if (j == 8) {
                    System.out.print(Character.getNumericValue(board[i][j]) + " |\n");
                } else {
                    System.out.print(Character.getNumericValue(board[i][j]) + " | ");
                }
            }
        }
    }
}
