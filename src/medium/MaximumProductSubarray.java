package medium;

/*
Given an integer array nums, find a subarray that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int left = 1;
        int right = 1;
        int result = nums[0];

        for (int i = 0; i < n; i++) {
            left = left == 0 ? 1 : left;
            right = right == 0 ? 1 : right;

            left *= nums[i];
            right *= nums[n - 1 - i];

            result = Math.max(result, Math.max(left, right));
        }
        return result;
    }
}