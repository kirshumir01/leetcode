package medium;

/*
98. Validate Binary Search Tree

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys strictly less than the node's key.
The right subtree of a node contains only nodes with keys strictly greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/

import aux.TreeBuilder;
import aux.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ValidateBST {
    private Integer prev;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) return true;

        if (!inorder(root.left)) return false;

        if (prev != null && root.val <= prev) return false;

        prev = root.val;

        return inorder(root.right);
    }

    public static void main(String[] args) {
        TreeBuilder treeBuilder = new TreeBuilder();
        ValidateBST validateBST = new ValidateBST();

        Integer[] arr1 = {2, 1, 3};
        Integer[] arr2 = {5, 1, 4, null, null, 3, 6};
        Integer[] arr3 = {5, 4, 1, null, null, 3, 6};


        TreeNode root1 = treeBuilder.builder(arr1);
        TreeNode root2 = treeBuilder.builder(arr2);
        TreeNode root3 = treeBuilder.builder(arr3);

        boolean b1 = validateBST.isValidBST(root1);
        boolean b2 = validateBST.isValidBST(root2);
        boolean b3 = validateBST.isValidBST(root3);

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }
}