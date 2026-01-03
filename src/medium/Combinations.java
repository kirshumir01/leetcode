package medium;

/*
#77 Combinations

Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
You may return the answer in any order.

Example 1:
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

Example 2:
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.
 */

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(new ArrayList<>(), 1, ans);
        return ans;
    }

    // 1 step: [[], [], [], [], [], []] | need = 2 | remain = 4 | available = 2 | curr[1]
    // num = 1, deep into 1-st backtrack:
    // 2 step: [[1,2], [], [], [], [], []] | need = 1 | remain = 3 | available = 2 | curr[1, 2]
    // 3 step: [[1,2], [1,3], [], [], [], []] | need = 1 | remain = 3 | available = 2 | curr[1, 3]
    // 4 step: [[1,2], [1,3], [1,4], [], [], []] | need = 1 | remain = 3 | available = 2 | curr[1, 4]
    // num = 2, deep into 2-nd backtrack:
    // 5 step: [[1,2], [1,3], [1,4], [2,3], [], []] | need = 1 | remain = 2 | available = 1 | curr[2, 3]
    // 6 step: [[1,2], [1,3], [1,4], [2,3], [2,4], []] | need = 1 | remain = 2 | available = 1 | curr[2, 4]
    // num = 3, deep into 3-rd backtrack:
    // 7 step: [[1,2], [1,3], [1,4], [2,3], [2,4], []] | need = 1 | remain = 1 | available = 0 | curr[]

    public void backtrack(
            List<Integer> curr,
            int firstNum,
            List<List<Integer>> ans
    ) {
        if (curr.size() == k) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        int need = k - curr.size();
        int remain = n - firstNum + 1;
        int available = remain - need;

        for (int num = firstNum; num <= firstNum + available; num++) {
            curr.add(num);
            backtrack(curr, num + 1, ans);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        int n = 4;
        int k = 2;

        List<List<Integer>> result = combinations.combine(n, k);
        System.out.println(result);
    }
}
