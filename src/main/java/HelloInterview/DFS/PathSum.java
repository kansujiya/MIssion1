package HelloInterview.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

//Given the root of a binary tree and an integer target, write a recursive function to determine if the tree has a root-to-leaf path where all the values along that path sum to the target.
//
//Example 1:
//
//4
//2
//1
//3
//7
//6
//9
//Input:
//
//[4, 2, 7, 1, 3, 6, 9]
//target = 17
//Output: true (the path is 4 -> 7 -> 6)
//
//Example 2:
//
//4
//2
//1
//3
//7
//6
//9
//Input:
//
//[4, 2, 7, 1, 3, 6, 9]
//target = 13
//Output: false
public class PathSum {
    static List<List<Integer>> result = new ArrayList<>();
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        int target = 17;
        hasPathDFS(root, target, new ArrayList<>());

        System.out.println("path exist " + result);
    }

    static void hasPathDFS(Node root, int target, List<Integer> path) {
        if(root == null) {
            return;
        }

        //add current node in path
        path.add(root.value);

        if(root.left == null && root.right == null ) {
            if(root.value == target) {
                result.add(new ArrayList<>(path));
            }
        }
        hasPathDFS(root.left, target - root.value, path);
        hasPathDFS(root.right, target - root.value, path);
        path.removeLast();
    }
}
