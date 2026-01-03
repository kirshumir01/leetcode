package medium;

/*
#300. Longest Increasing Subsequence

Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1
 */

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static class Solution1 {
        public int lengthOfLTS(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);

            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            int longest = 0;
            for (int c : dp) {
                longest = Math.max(c, longest);
            }
            return longest;
        }
    }

    public static class Solution2 {
        public int lengthOfLTS(int[] nums) {
            ArrayList<Integer> sub = new ArrayList<>();
            sub.add(nums[0]);

            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                if (num > sub.get(sub.size() - 1)) {
                    sub.add(num);
                } else {
                    int j = 0;
                    while (num > sub.get(j)) {
                        j += 1;
                    }
                    sub.set(j, num);
                }
            }
            return sub.size();
        }
    }

    public static class Solution3 {
        public int lengthOfLTS(int[] nums) {
            ArrayList<Integer> sub = new ArrayList<>();
            sub.add(nums[0]);

            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                if (num > sub.get(sub.size() - 1)) {
                    sub.add(num);
                } else {
                    int j = binarySearch(sub, num);
                    sub.set(j, num);
                }
            }
            return sub.size();
        }

        private int binarySearch(ArrayList<Integer> sub, int num) {
            int left = 0;
            int right = sub.size() - 1;
            int mid = (left + right) / 2;

            while (left < right) {
                mid = (left + right) / 2;

                if (sub.get(mid) == num) {
                    return mid;
                }

                if (sub.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        Solution3 solution3 = new Solution3();

        int[] nums = new int[]{10,9,2,5,3,7,101,18};

        int result1 = solution1.lengthOfLTS(nums);
        int result2 = solution2.lengthOfLTS(nums);
        int result3 = solution3.lengthOfLTS(nums);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
