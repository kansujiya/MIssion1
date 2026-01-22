package HelloInterview.DFS;

//Given the root of a binary tree, write a recursive function to find its maximum depth, where maximum depth is defined as the number of nodes along the longest path from the root node down to a leaf node.
//
//Example 1:
//
//4
//2
//1
//8
//7
//6
//9
//Input:
//
//[4, 2, 7, 1, null, 6, 9, null, 8, null, null, null, null, null, null]
//Output: 4 (The 4 nodes along the longest path are shown in bold)

public class MaximumDepth {
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.left.right = new Node(8);

        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        System.out.println("max in all nodes " + maxDepthDFS(root));
    }

    static int maxDepthDFS(Node root) {
        if(root == null) {
            return 0;
        }

        int left = maxDepthDFS(root.left);
        int right = maxDepthDFS(root.right);
        return Math.max(left, right) + 1;    }
}
