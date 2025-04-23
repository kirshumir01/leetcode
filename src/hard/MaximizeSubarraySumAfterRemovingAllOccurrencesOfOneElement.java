package hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
You are given an integer array nums.
You can do the following operation on the array at most once:
Choose any integer x such that nums remains non-empty on removing all occurrences of x.
Remove all occurrences of x from the array.
Return the maximum subarray sum across all possible resulting arrays.

Example 1:
Input: nums = [-3,2,-2,-1,3,-2,3]
Output: 7

Explanation:
We can have the following arrays after at most one operation:
The original array is nums = [-3, 2, -2, -1, 3, -2, 3]. The maximum subarray sum is 3 + (-2) + 3 = 4.
Deleting all occurences of x = -3 results in nums = [2, -2, -1, 3, -2, 3]. The maximum subarray sum is 3 + (-2) + 3 = 4.
Deleting all occurences of x = -2 results in nums = [-3, 2, -1, 3, 3]. The maximum subarray sum is 2 + (-1) + 3 + 3 = 7.
Deleting all occurences of x = -1 results in nums = [-3, 2, -2, 3, -2, 3]. The maximum subarray sum is 3 + (-2) + 3 = 4.
Deleting all occurences of x = 3 results in nums = [-3, 2, -2, -1, -2]. The maximum subarray sum is 2.
The output is max(4, 4, 7, 4, 2) = 7.

Example 2:
Input: nums = [1,2,3,4]
Output: 10

Explanation:
It is optimal to not perform any operations.
 */
public class MaximizeSubarraySumAfterRemovingAllOccurrencesOfOneElement {
    class SegmentData {
        long totalSum;
        long prefixSum;
        long suffixSum;
        long maxSum;

        public SegmentData() {
            totalSum = 0;
            prefixSum = Integer.MIN_VALUE;
            suffixSum = Integer.MIN_VALUE;
            maxSum = Integer.MIN_VALUE;
        }
        public SegmentData(int value) {
            totalSum = value;
            prefixSum = value;
            suffixSum = value;
            maxSum = value;
        }
    }

    private List<SegmentData> segmentDataTree;

    private SegmentData merge(SegmentData left, SegmentData right) {
        SegmentData result = new SegmentData();
        result.totalSum = left.totalSum + right.totalSum;
        result.prefixSum = Math.max(left.prefixSum, left.totalSum + right.prefixSum);
        result.suffixSum = Math.max(right.suffixSum, right.totalSum + left.suffixSum);
        result.maxSum = Math.max(
                Math.max(left.maxSum, right.maxSum),
                Math.max(left.suffixSum + right.prefixSum, Math.max(result.prefixSum, result.suffixSum))
        );

        return result;
    }

    private void buildTree(int left, int right, int index, int[] arr) {
        if (left == right) {
            segmentDataTree.set(index, new SegmentData(arr[left]));
            return;
        }
        int mid = (left + right) / 2;
        buildTree(left, mid, 2 * index + 1, arr);
        buildTree(mid + 1, right, 2 * index + 2, arr);
        segmentDataTree.set(index, merge(segmentDataTree.get(2 * index + 1), segmentDataTree.get(2 * index + 2)));
    }

    private void updateTree(int left, int right, int index, int position, int value) {
        if (position < left || position > right) return;
        if (left == right && position == left) {
            segmentDataTree.get(index).totalSum = segmentDataTree.get(index).prefixSum =
                    segmentDataTree.get(index).suffixSum = segmentDataTree.get(index).maxSum = value;
            return;
        }
        int mid = (left + right) / 2;
        updateTree(left, mid, 2 * index + 1, position, value);
        updateTree(mid + 1, right, 2 * index + 2, position, value);
        segmentDataTree.set(index, merge(segmentDataTree.get(2 * index + 1), segmentDataTree.get(2 * index + 2)));
    }

    public long maxSubarraySum(int[] nums) {
        int n = nums.length;
        segmentDataTree = new ArrayList<>();

        for (int i = 0; i < 4 * n; i++) {
            segmentDataTree.add(new SegmentData());
        }

        TreeMap<Integer, List<Integer>> valueToIndexes = new TreeMap<>();
        buildTree(0, n - 1, 0, nums);
        long maxSubArraySum = segmentDataTree.get(0).maxSum;

        for (int i = 0; i < n; i++) {
            valueToIndexes.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        if (!valueToIndexes.isEmpty() && valueToIndexes.lastEntry().getKey() < 0) {
            return valueToIndexes.lastEntry().getKey();
        }

        for (Map.Entry<Integer, List<Integer>> entry : valueToIndexes.entrySet()) {
            int value = entry.getKey();
            List<Integer> indexes = entry.getValue();
            if (indexes.size() == n) continue;

            for (int i : indexes) updateTree(0, n - 1, 0, i, 0);
            maxSubArraySum = Math.max(maxSubArraySum, segmentDataTree.get(0).maxSum);

            for (int i : indexes) updateTree(0, n - 1, 0, i, value);
        }
        return maxSubArraySum;
    }
}
