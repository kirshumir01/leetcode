package hard;

/*
#84. Largest Rectangle in Histogram

Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
return the area of the largest rectangle in the histogram.

Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Input: heights = [2,4]
Output: 4
 */

import org.w3c.dom.ls.LSException;

class SegmentTree {
    int start;
    int end;
    int min;
    SegmentTree left;
    SegmentTree right;

    public SegmentTree(int start, int end) {
        this.start = start;
        this.end = end;
        left = null;
        right = null;
    }
}

public class LargestRectangleInHistogram {
    private int calculateMax(int[] heights, SegmentTree root, int start, int end) {
        if (start > end) return -1;

        if (start == end) return heights[start];

        int minIndex = query(heights, root, start, end);
        int leftMax = calculateMax(heights, root, start, minIndex - 1);
        int rightMax = calculateMax(heights, root, minIndex + 1, end);
        int minMax = heights[minIndex] * (end - start + 1);
        return Math.max(Math.max(leftMax, rightMax), minMax);
    }

    private SegmentTree buildTree(int[] heights, int start, int end) {
        if (start > end) return null;
        SegmentTree root = new SegmentTree(start, end);
        if (start == end) {
            root.min = start;
            return root;
        } else {
            int middle = (start + end) / 2;
            root.left = buildTree(heights, start, middle);
            root.right = buildTree(heights, middle + 1, end);
            root.min = heights[root.left.min] < heights[root.right.min] ?
                    root.left.min : root.right.min;
            return root;
        }
    }

    private int query(int[] heights, SegmentTree root, int start, int end) {
        if (root == null || end < root.start || start > root.end) return -1;

        if (start <= root.start && end >= root.end) return root.min;

        int leftMin = query(heights, root.left, start, end);

        int rightMin = query(heights, root.right, start, end);

        if (leftMin == -1) return rightMin;

        if (rightMin == -1) return leftMin;

        return heights[leftMin] < heights[rightMin] ? leftMin : rightMin;
    }

    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;

        SegmentTree root = buildTree(heights, 0, heights.length - 1);

        return calculateMax(heights, root, 0, heights.length - 1);
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();

        int[] heights = new int[]{2,1,5,6,2,3};
        int result = largestRectangleInHistogram.largestRectangleArea(heights);

        System.out.println(result);
    }
}
