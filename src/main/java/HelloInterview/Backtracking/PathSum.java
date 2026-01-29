package HelloInterview.Backtracking;

import HelloInterview.DFS.Node;

import javax.xml.transform.stax.StAXResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a binary tree where all nodes have positive, integer values and a target sum, find all root-to-leaf paths where the sum of the values along the path equals the given sum.
//Example:
//Target: 7
//4
//7
//1
//3
//2
//6
//1
//Output:
//[[4, 2, 1]]

public class PathSum {
    public static void main(String[] args) {
        Node node = new Node(4);
        node.left = new Node(7);
        node.right = new Node(2);

        node.left.left = new Node(1);
        node.left.right = new Node(3);

        node.right.left = new Node(6);
        node.right.right = new Node(1);

        List<List<Integer>> result = new ArrayList<>();
        int target = 7;
        allPaths(node, 0, target, new ArrayList<>(), result);
        System.out.println((result));
    }

    static void allPaths(Node root, int total, int target, List<Integer> path, List<List<Integer>> result) {

        if(root == null) {
            return;
        }

        path.add(root.value);
        total += root.value;

        // KEY STEP 2
        // current sum exceeds target
        // so pop to remove the current node from the path
        // return to backtrack to previous node on the call stack
        if(total > target) {
            path.remove(path.size()-1);
            return;
        }

        if(root.left == null && root.right == null) {
            if(total == target) {
                result.add(new ArrayList<>(path));
            }
        } else {
            allPaths(root.left, total, target, path, result);
            allPaths(root.right, total, target, path, result);
        }

        // KEY STEP 1
        // we have finished exploring all paths containing the current node
        // so pop to remove the current node from the path
        // return to backtrack to previous node on the call stack.
        path.remove(path.size() - 1);
    }
}
