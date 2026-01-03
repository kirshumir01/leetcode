package easy;

import java.util.Arrays;

public class SortAnArray {
    public int[] sortArray(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }

        int mid = nums.length / 2;
        int[] left_list = sortArray(Arrays.copyOfRange(nums, 0, mid));
        int[] right_length = sortArray(Arrays.copyOfRange(nums, mid, nums.length));
        return merge(left_list, right_length);
    }

    public int[] merge(int[] left_list, int[] right_list) {
        int[] ret = new int[left_list.length + right_list.length];
        int left_cursor = 0;
        int ret_cursor = 0;
        int right_cursor = 0;

        while (left_cursor < left_list.length && right_cursor < right_list.length) {
            if (left_list[left_cursor] < right_list[right_cursor]) {
                ret[ret_cursor++] = left_list[left_cursor++];
            } else {
                ret[ret_cursor++] = right_list[right_cursor++];
            }
        }

        while (left_cursor < left_list.length) {
            ret[ret_cursor++] = left_list[left_cursor++];
        }

        while (right_cursor < right_list.length) {
            ret[ret_cursor++] = right_list[right_cursor++];
        }
        return ret;
    }


    public static void main(String[] args) {
        SortAnArray sortAnArray = new SortAnArray();

        int[] nums1 = {5,2,3,1};
        int[] nums2 = {5, 1, 1, 2, 0, 0};
        int[] nums3 = {-2, 3, -5};

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        for (int num : sortAnArray.sortArray(nums1)) {
            if (count1 < (sortAnArray.sortArray(nums1).length - 1)) {
                System.out.print(num + ", ");
                count1++;
            } else {
                System.out.print(num + ".\n");
                count1 = 0;
            }
        }

        System.out.println();

        for (int num : sortAnArray.sortArray(nums2)) {
            if (count2 < (sortAnArray.sortArray(nums2).length - 1)) {
                System.out.print(num + ", ");
                count2++;
            } else {
                System.out.print(num + ".");
                count2 = 0;
            }
        }

        System.out.println();

        for (int num : sortAnArray.sortArray(nums3)) {
            if (count3 < (sortAnArray.sortArray(nums3).length - 1)) {
                System.out.print(num + ", ");
                count3++;
            } else {
                System.out.print(num + ".");
                count3 = 0;
            }
        }
    }
}
