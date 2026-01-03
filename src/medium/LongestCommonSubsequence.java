package medium;

public class LongestCommonSubsequence {
    static class Solution1 {
        private int[][] memo;
        private String text1;
        private String text2;

        public int longestCommonSubsequence(String text1, String text2) {
            this.memo = new int[text1.length() + 1][text2.length() + 1];

            for (int i = 0; i < text1.length(); i++) {
                for (int j = 0; j < text2.length(); j++) {
                    this.memo[i][j] = -1;
                }
            }

            this.text1 = text1;
            this.text2 = text2;
            return memoSolve(0, 0);
        }

        private int memoSolve(int p1, int p2) {
            if (memo[p1][p2] != -1) {
                return memo[p1][p2];
            }

            int option1 = memoSolve(p1 + 1, p2);

            int firstOccurence = text2.indexOf(text1.charAt(p1), p2);
            int option2 = 0;

            if (firstOccurence != -1) {
                option2 = 1 + memoSolve(p1 + 1, firstOccurence + 1);
            }
            memo[p1][p2] = Math.max(option1, option2);
            return memo[p1][p2];
        }
    }

    static class Solution2 {
        private int[][] memo;
        private String text1;
        private String text2;

        public int longestCommonSubsequence(String text1, String text2) {
            this.memo = new int[text1.length() + 1][text2.length() + 1];

            for (int i = 0; i < text1.length(); i++) {
                for (int j = 0; j < text2.length(); j++) {
                    this.memo[i][j] = -1;
                }
            }

            this.text1 = text1;
            this.text2 = text2;
            return memoSolve(0, 0);
        }

        private int memoSolve(int p1, int p2) {
            if (memo[p1][p2] != -1) {
                return memo[p1][p2];
            }

            int answer = 0;

            if (text1.charAt(p1) == text2.charAt(p2)) {
                answer = 1 + memoSolve(p1 + 1, p2 + 1);
            } else {
                answer = Math.max(
                        memoSolve(p1, p2 + 1),
                        memoSolve(p1 + 1, p2));
            }
            memo[p1][p2] = answer;
            return memo[p1][p2];
        }
    }

    class Solution3 {
        public int longestCommonSubsequence(String text1, String text2) {
            int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];

            for (int col = text2.length() - 1; col >= 0; col--) {
                for (int row = text1.length() - 1; row >= 0; row--) {
                    if (text1.charAt(row) == text2.charAt(col)) {
                        dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
                    } else {
                        dpGrid[row][col] = Math.max(
                                dpGrid[row + 1][col], dpGrid[row][col + 1]
                        );
                    }
                }
            }
            return dpGrid[0][0];
        }
    }

}
