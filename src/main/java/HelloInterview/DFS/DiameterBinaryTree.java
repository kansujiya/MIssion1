package HelloInterview.DFS;

//Given the root of a binary tree, write a recursive function to find the diameter of the tree. The diameter of a binary tree is the length of the longest path (# of edges) between any two nodes in a tree. This path may or may not pass through the root.
//
//Example 1:
//
//3
//9
//1
//5
//4
//2
//Input:
//
//[3, 9, 2, 1, 4, null, null, null, 5]
//Output: 4 (The longest path is 5 -> 1 -> 9 -> 3 -> 2) for a total of 4 edges
//
//Example 2:
//
//3
//9
//1
//2
//4
//3
//Input:
//
//[3, 9, null, 1, 4, null, null, 2, null, 3]
//Output: 4 (The longest path is 2 -> 1 -> 9 -> 4 -> 3) for a total of 4 edges

public class DiameterBinaryTree {

    static int maxDiameter = 0;

    public static void main(String[] args) {
        Node root = new Node(3);
        //left
        root.left = new Node(9);
        root.left.left = new Node(1);
        root.left.right = new Node(4);
        //right
        root.left.left.left = new Node(2);
        root.left.right.left = new Node(3);
        DiameterDFS(root);
        System.out.println("Max Diameter of Binary tree is " + maxDiameter);
    }

    static int DiameterDFS(Node root) {
        // base case
        if (root == null) {
            return 0;
        }

        int left = DiameterDFS(root.left);
        int right = DiameterDFS(root.right);

        maxDiameter = Math.max(maxDiameter, left + right);

        return  1 + Math.max(left, right);
    }
}

//Recursion Thinking Framework
//
//Base case: If node is null, height = 0.
//
//Recursive step:
//
//Compute left and right subtree heights.
//
//Update maxDiameter = max(maxDiameter, left + right).
//
//Return 1 + max(left, right) (height of this node).
//
//End result:
//maxDiameter holds the longest path between any two nodes.
//
//👉 You’re doing a postorder traversal — process left, then right, then use both to compute something for the current node.
