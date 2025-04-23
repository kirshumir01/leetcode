package medium;

/*
Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Example 2:
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 */

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[nums.length + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int minPrefixIndex = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int j = 1; j <= n; j++) {
            int subArraySum = prefixSum[j] - prefixSum[minPrefixIndex];
            if (subArraySum > maxSum) {
                maxSum = subArraySum;
            }

            if (prefixSum[j] < prefixSum[minPrefixIndex]) {
                minPrefixIndex = j;
            }
        }
        return maxSum;
    }
}