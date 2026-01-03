package hard;

import java.util.HashSet;
import java.util.Set;

public class NQueens {
    private int size;

    public int totalNQueens(int n) {
        size = n;
        return backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private int backtrack(
            int row,
            Set<Integer> diagonals,
            Set<Integer> antidiagonals,
            Set<Integer> cols
    ) {
        if (row == size) {
            return 1;
        }

        int solutions = 0;

        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;

            if (
                cols.contains(col) ||
                diagonals.contains(currDiagonal) ||
                antidiagonals.contains(currAntiDiagonal))
            {
                continue;
            }

            cols.add(col);
            diagonals.add(currDiagonal);
            antidiagonals.add(currAntiDiagonal);

            solutions += backtrack(row + 1, diagonals, antidiagonals, cols);

            cols.remove(col);
            diagonals.remove(currDiagonal);
            antidiagonals.remove(currAntiDiagonal);
        }
        return solutions;
    }


    public static void main(String[] args) {
        NQueens nQueens = new NQueens();

        int result = nQueens.totalNQueens(4);
        System.out.println(result);
    }
}
