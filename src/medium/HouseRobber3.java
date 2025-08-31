package medium;

/*
#337 HOUSE ROBBER 3

The thief has found himself a new place for his thievery again.
There is only one entrance to this area, called root.

Besides the root, each house has one and only one parent house.
After a tour, the smart thief realized that all houses in this place form a binary tree.
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

Example 1:
Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
Input: root = [3,4,5,1,3,null,1]
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */

import java.util.LinkedList;
import java.util.Queue;

public class HouseRobber3 {
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }
    public int[] helper(TreeNode node) {
        // {rob this node, not rob this node}
        if (node == null) {
            return new int[] {0, 0};
        }

        int[] left = helper(node.left);
        int[] right = helper(node.right);

        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] {rob, notRob};
    }

    public int rob (TreeNode root) {
        int[] answer = helper(root);
        return Math.max(answer[0], answer[1]);
    }

    // build binary tree
    public TreeNode buildTree(Integer[] array) {
        if (array.length == 0 || array[0] == null) return null;

        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;

        while (i < array.length) {
            TreeNode current = queue.poll();

            if (i < array.length && array[i] != null) {
                current.left = new TreeNode(array[i]);
                queue.add(current.left);
            }
            i++;

            if (i < array.length && array[i] != null) {
                current.right = new TreeNode(array[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        HouseRobber3 solution = new HouseRobber3();
        Integer[] array = {3, 4, 5, 1, 3, null, 1};
        TreeNode root = solution.buildTree(array);
        int answer = solution.rob(root);
        System.out.println(answer); // 9
    }
}
