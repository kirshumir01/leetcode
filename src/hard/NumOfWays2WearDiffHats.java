package hard;

/*
#1434. Number of Ways to Wear Different Hats to Each Other

There are n people and 40 types of hats labeled from 1 to 40.
Given a 2D integer array hats, where hats[i] is a list of all hats preferred by the ith person.
Return the number of ways that n people can wear different hats from each other.
Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:
Input: hats = [[3,4],[4,5],[5]]
Output: 1
Explanation: There is only one way to choose hats given the conditions.
First person choose hat 3, Second person choose hat 4 and last one hat 5.

Example 2:
Input: hats = [[3,5,1],[3,5]]
Output: 4
Explanation: There are 4 ways to choose hats:
(3,5), (5,3), (1,3) and (1,5)

Example 3:
Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
Output: 24
Explanation: Each person can choose hats labeled from 1 to 4.
Number of Permutations of (1,2,3,4) = 24.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumOfWays2WearDiffHats {
    int n;
    int done;
    int MOD = 1000000007;
    Map<Integer, ArrayList<Integer>> hatsToPeople;
    int[][] memo;

    public int numberWays(List<List<Integer>> hats) {
        n = hats.size();
        done = (int) Math.pow(2, n) - 1;
        memo = new int[41][done];
        hatsToPeople = new HashMap<>();

        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }

        for (int i = 0; i < n; i++) {
            for (int hat : hats.get(i)) {
                if (!hatsToPeople.containsKey(hat)) {
                    hatsToPeople.put(hat, new ArrayList<>());
                }

                hatsToPeople.get(hat).add(i);
            }
        }

        return dp(1, 0);
    }

    private int dp(int hat, int mask) {
        if (mask == done) {
            return 1;
        }

        if (hat > 40) {
            return 0;
        }

        if (memo[hat][mask] != -1) {
            return memo[hat][mask];
        }

        int ans = dp(hat + 1, mask);

        if (hatsToPeople.containsKey(hat)) {
            for (int person : hatsToPeople.get(hat)) {
                if ((mask & (1 << person)) == 0) {
                    ans = (ans + dp(hat + 1, mask | (1 << person))) % MOD;
                }
            }
        }

        memo[hat][mask] = ans;
        return ans;
    }

    public static void main(String[] args) {
        NumOfWays2WearDiffHats solution = new NumOfWays2WearDiffHats();

        List<List<Integer>> hats = Arrays.asList(
                Arrays.asList(3,4),
                Arrays.asList(4,5),
                Arrays.asList(5)
        );

        int result = solution.numberWays(hats);
        System.out.println(result);
    }
}
