package hard;

/*
#4 MEDIAN OF TWO SORTED ARRAYS

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 */

public class MedianOfTwoSortedArrays {
    private int p1 = 0, p2 = 0;

    private int getMin(int nums1[], int nums2[]) {
        if (p1 < nums1.length && p2 < nums2.length) {
            return nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        } else if (p1 < nums1.length) {
            return nums1[p1++];
        } else if (p2 < nums2.length) {
            return nums2[p2++];
        }
        return -1;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if ((m + n) % 2 == 0) {
            for (int i = 0; i < (m + n) / 2 - 1; ++i) {
                int tmp = getMin(nums1, nums2);
            }
            return (double) (getMin(nums1, nums2) + getMin(nums1, nums2)) / 2;
        } else {
            for (int i = 0; i < (m + n) / 2; i++) {
                int tmp = getMin(nums1, nums2);
            }
            return getMin(nums1, nums2);
        }
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();

        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double answer = solution.findMedianSortedArrays(nums1, nums2);

        System.out.println(answer); // 2.0
    }
}
