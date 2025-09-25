package medium;

/*
#79 WORD SEARCH

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
or vertically neighboring.
The same letter cell may not be used more than once.

Constraints:
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
 */

public class WordSearch {
    private char[][] board;
    private int ROWS;
    private int COLS;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;

        for (int row = 0; row < this.ROWS; ++row) for (
            int col = 0;
            col < this.COLS;
            ++ col
        ) if (this.backtrack(row, col, word, 0)) return true;
        return false;
    }

    protected boolean backtrack(int row, int col, String word, int index) {
        // step 1
        if (index >= word.length()) return true;

        // step 2: check the boundaries
        if (
                row < 0 ||
                row==this.ROWS ||
                col < 0 ||
                col==this.COLS ||
                this.board[row][col]!= word.charAt(index)
        ) return false;

        // step 3: mark the path
        this.board[row][col] = '#';

        // up, right, down, left
        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};

        // sliding throw the board's cells
        for (int d = 0; d < 4; ++d) {
            if (
                    this.backtrack(
                    row + rowOffsets[d],
                    col + colOffsets[d],
                    word,
                    index + 1
                    )
            ) return true;
        }

        // step 4: clean up and return the result
        this.board[row][col] = word.charAt(index);
        return false;
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();

        char[][] board = {{'A', 'B', 'C', 'E'},{'S', 'F', 'C', 'S'},{'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        boolean result = wordSearch.exist(board, word);
        System.out.println(result);
    }
}