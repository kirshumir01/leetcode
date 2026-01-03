package hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram2 {
    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;
        for (int i = 0; i < length; i++) {
            while (
                    (stack.peek() != -1) && (heights[stack.peek()] >= heights[i])
            ) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = new int[] {6,7,5,2,4,5,9,3};
        int result = largestRectangleArea(heights);
        System.out.println(result);
    }
}
