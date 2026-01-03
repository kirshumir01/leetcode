package hard;

public class SudokuSolver2 {
    int[] rows = new int[9];
    int[] cols = new int[9];
    int[] boxes = new int[9];
    char[][] board;
    int[][] empties;
    int emptyCount = 0;

    public void solveSudoku(char[][] board) {
        this.board = board;
        empties = new int[81][2];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.' || ch == '0') {
                    empties[emptyCount++] = new int[]{r, c};
                } else {
                    int d = ch - '0';
                    int mask = 1 << d;
                    rows[r] |= mask;
                    cols[c] |= mask;
                    boxes[boxIndex(r, c)] |= mask;
                }
            }
        }
        backtrack(0);
    }

    private boolean backtrack(int idx) {
        if (idx == emptyCount) return true;

        int bestIdx = -1;
        int bestOptions = 10;
        for (int k = idx; k < emptyCount; k++) {
            int r = empties[k][0]; // row of empty cell
            int c = empties[k][1]; // col of empty cell
            int used = rows[r] | cols[c] | boxes[boxIndex(r, c)];
            int options = 9 - Integer.bitCount(used & 0x3FE);
            if (options < bestOptions) {
                bestOptions = options;
                bestIdx = k;
                if (options == 1) break;
            }
        }

        if (bestIdx != idx) {
            int[] tmp = empties[idx];
            empties[idx] = empties[bestIdx];
            empties[bestIdx] = tmp;
        }

        int r = empties[idx][0];
        int c = empties[idx][1];
        int used = rows[r] | cols[c] | boxes[boxIndex(r, c)];
        for (int d = 1; d <= 9; d++) {
            int mask = 1 << d;
            if ((used & mask)==0) {
                rows[r] |= mask;
                cols[c] |= mask;
                boxes[boxIndex(r, c)] |= mask;
                board[r][c] = (char) ('0' + d);

                if (backtrack(idx + 1)) return true;

                rows[r] &= ~mask;
                cols[c] &= ~mask;
                boxes[boxIndex(r, c)] &= ~mask;
                board[r][c] = '.';
            }
        }

        return false;
    }

    private int boxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
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
