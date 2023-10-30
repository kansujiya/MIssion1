package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSRecursive {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    //Binary tree: DFS (recursive)
    public int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int ans = 0;
        //do logic
        dfs(root.left);
        dfs(root.right);

        return ans;
    }


    // Recursive
    private List<Integer> answer = new ArrayList<>();

    private void dfsRecursive(TreeNode node) {
        if (node == null) {
            return;
        }
        // Visit the root first, then the left subtree, then the right subtree.
        answer.add(node.val);
        dfsRecursive(node.left);
        dfsRecursive(node.right);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        dfsRecursive(root);
        return answer;
    }

    //Given the root of a binary tree, return its maximum depth.
    //
    //A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    //Input: root = [3,9,20,null,null,15,7] = 3

    public int maxDepth(TreeNode root) {

        //     if (root == null) {
        //   return 0;
        // } else {
        //   int left_height = maxDepth(root.left);
        //   int right_height = maxDepth(root.right);
        //   return java.lang.Math.max(left_height, right_height) + 1;
        // }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();

        int finalDepth = 0;

        if(root == null) return finalDepth;

        //Init the Stack with root node with a depth of 1
        stack.push(root);
        depths.push(1);

        //Init the Stack with root node with a depth of 1
        finalDepth = 1;

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int currentDepth = depths.pop();

            if(node.left == null && node.right == null) {
                finalDepth = finalDepth > currentDepth ? finalDepth : currentDepth;
            }

            if(node.right != null) {
                stack.push(node.right);
                depths.push(currentDepth + 1);
            }
            if(node.left != null) {
                stack.push(node.left);
                depths.push(currentDepth + 1);
            }

        }

        return finalDepth;
    }
}
