package hard;

/*
#188. Best Time to Buy and Sell Stock IV

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve.
You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

Constraints:
1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
 */

public class BestTime2BuyAndSellStockIV {
    public static class Solution1 {
        private int[] prices;
        private int[][][] memo;

        private int dp(int i, int transactionsRemaining, int holding) {
            if (transactionsRemaining == 0 || i == prices.length) {
                return 0;
            }

            if (memo[i][transactionsRemaining][holding] == 0) {
                int doNothing = dp(i + 1, transactionsRemaining, holding);
                int doSomething;

                if (holding == 1) {
                    doSomething = prices[i] + dp(i + 1, transactionsRemaining - 1, 0);
                } else {
                    doSomething = -prices[i] + dp(i + 1, transactionsRemaining, 1);
                }

                memo[i][transactionsRemaining][holding] = Math.max(doSomething, doNothing);
            }
            return memo[i][transactionsRemaining][holding];
        }

        public int maxProfit(int k, int[] prices) {
            this.prices = prices;
            this.memo = new int[prices.length][k + 1][2];
            return dp(0, k, 0);
        }
    }

    public static class Solution2 {
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][][] dp = new int[n + 1][k + 1][2];

            for (int i = n - 1; i >= 0; i--) {
                for (int transactionsRemaining = 1; transactionsRemaining <= k; transactionsRemaining++) {
                    for (int holding = 0; holding < 2; holding++) {
                        int doNothing = dp[i + 1][transactionsRemaining][holding];
                        int doSomething;

                        if (holding == 1) {
                            doSomething = prices[i] + dp[i + 1][transactionsRemaining - 1][0];
                        } else {
                            doSomething = -prices[i] + dp[i + 1][transactionsRemaining][1];
                        }

                        dp[i][transactionsRemaining][holding] = Math.max(doSomething, doNothing);
                    }
                }
            }
            return dp[0][k][0];
        }
    }

    public static class Solution3 {
        public int maxProfit(int k, int[] prices) {
            if (prices.length == 1) return 0;
            int n = prices.length;
            int[] dp_prev = new int[n + 1];

            for (int i = 1; i <= k; i++) {
                int[] dp_curr = new int[n + 1];
                int pos = dp_prev[0] - prices[0];

                for (int j = 1; j <= n; j++) {
                    pos = Math.max(pos, dp_prev[j - 1] - prices[j - 1]);
                    dp_curr[j] = Math.max(dp_curr[j - 1], pos + prices[j - 1]);
                }
                dp_prev = dp_curr;
            }
            return dp_prev[n];
        }
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        Solution3 solution3 = new Solution3();

        int k = 2;
        int[] prices = new int[]{3,2,6,5,0,3};

        System.out.println(solution1.maxProfit(k, prices));
        System.out.println(solution2.maxProfit(k, prices));
        System.out.println(solution3.maxProfit(k, prices));
    }
}
