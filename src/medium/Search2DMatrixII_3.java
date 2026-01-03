package medium;

public class Search2DMatrixII_3 {
    private int[][] matrix;
    private int target;

    private boolean searchRec(int left, int up, int right, int down) {
        if (left > right || up > down) {
            return false;
        } else if (target < matrix[up][left] || target > matrix[down][right]) {
            return false;
        }

        int mid = left + (right - left) / 2;
        int row = up;

        while (row <= down && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }
        return searchRec(left, row, mid - 1, down) || searchRec(mid + 1, up, right, row - 1);
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        return false;
    }


    public static void main(String[] args) {
        Search2DMatrixII_3 search = new Search2DMatrixII_3();
        int target = 5;
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(search.searchMatrix(matrix, target));
    }
}
