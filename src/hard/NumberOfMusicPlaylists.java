package hard;

/*
#920. Number of Music Playlists

Your music player contains n different songs.
You want to listen to goal songs (not necessarily different) during your trip.
To avoid boredom, you will create a playlist so that:

Every song is played at least once.
A song can only be played again only if k other songs have been played.
Given n, goal, and k, return the number of possible playlists that you can create.
Since the answer can be very large, return it modulo 109 + 7.



Example 1:
Input: n = 3, goal = 3, k = 1
Output: 6
Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].

Example 2:
Input: n = 2, goal = 3, k = 0
Output: 6
Explanation: There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].

Example 3:
Input: n = 2, goal = 3, k = 1
Output: 2
Explanation: There are 2 possible playlists: [1, 2, 1] and [2, 1, 2].

Constraints:
0 <= k < n <= goal <= 100
 */

import java.util.Arrays;

public class NumberOfMusicPlaylists {
    public static class Solution1 {
        public int numMusicPlaylists(int n, int goal, int k) {
            int MOD = 1_000_000_007;

            long[][] dp = new long[goal + 1][n + 1];
            dp[0][0] = 1;

            for (int i = 1; i <= goal; i++) {
                for (int j = 1; j <= Math.min(i, n); j++) {
                    dp[i][j] = dp[i - 1][j - 1] * (n - j + 1) % MOD;

                    if (j > k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - k)) % MOD;
                    }
                }
            }
            return (int) dp[goal][n];
        }
    }

    public static class Solution2 {
        private final int MOD = 1_000_000_007;
        private Long[][] dp;

        public int numMusicPlaylists(int n, int goal, int k) {
            dp = new Long[goal + 1][n + 1];
            for (Long[] row : dp) {
                Arrays.fill(row, -1L);
            }
            return (int) numberOfPlaylists(goal, n, k, n);
        }

        private long numberOfPlaylists(int i, int j, int k, int n) {
            if (i == 0 && j == 0) return 1;

            if (i == 0 || j == 0) return 0;

            if (dp[i][j] != -1) return dp[i][j];

            dp[i][j] = (numberOfPlaylists(i - 1, j - 1, k, n) * (n - j + 1)) % MOD;

            if (j > k) {
                dp[i][j] += (numberOfPlaylists(i - 1, j, k, n) * (j - k)) % MOD;
                dp[i][j] %= MOD;
            }
            return dp[i][j];
        }
    }

    private static class Solution3 {
        private static final long MOD = 1_000_000_007L;

        private long[] factorial;
        private long[] invFactorial;

        public int numMusicPlaylists(int n, int goal, int k) {
            precalculataFactorials(n);

            int sign = 1;
            long answer = 0;

            for (int i = n; i >= k; i--) {
                long temp = power(i - k , goal - k);
                temp = (temp * invFactorial[n - i]) % MOD;
                temp = (temp * invFactorial[i - k]) % MOD;

                answer = (answer + sign * temp + MOD) % MOD;

                sign *= -1;
            }
            return (int) ((factorial[n] * answer) % MOD);
        }

        private void precalculataFactorials(int n) {
            factorial = new long[n + 1];
            invFactorial = new long[n + 1];
            factorial[0] = invFactorial[0] = 1;

            for (int i = 1; i <= n; i++) {
                factorial[i] = (factorial[i - 1] * i) % MOD;
                invFactorial[i] = power(factorial[i], (int) (MOD - 2));
            }

        }

        private long power(long base, int exponent) {
            long result = 1L;

            while(exponent > 0) {
                if ((exponent & 1) == 1) {
                    result = (result * base) % MOD;
                }

                exponent >>= 1;
                base = (base * base) % MOD;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        Solution3 solution3 = new Solution3();

        int n = 3, goal = 3, k = 1;

        int result1 = solution1.numMusicPlaylists(n, goal, k);
        int result2 = solution2.numMusicPlaylists(n, goal, k);
        int result3 = solution3.numMusicPlaylists(n, goal, k);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}

