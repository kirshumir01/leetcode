package aux;

import java.util.LinkedList;
import java.util.Queue;

public class TreeBuilder {
    public TreeNode builder (Integer[] array) {
        if (array==null || array.length==0) return null;

        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;

        while (!queue.isEmpty() && i < array.length) {
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
}
