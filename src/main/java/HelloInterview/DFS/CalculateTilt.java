package HelloInterview.DFS;

//Given the root node of a binary tree, write a recursive function to return the sum of all node tilts in the tree.
//
//The tilt of a node is calculated as the absolute difference between the sum of all values in its left subtree and the sum of all values in its right subtree. For nodes that are missing a left child, right child, or both, treat the missing subtree as having a sum of 0.
//
//For example, a leaf node has a tilt of 0 because both its left and right subtrees are empty (sum = 0), so the absolute difference is |0 - 0| = 0.
//
//Example 1:
//
//5
//1
//3
//Input:
//
//[5, 1, 3]
//Output:
//
//2
//The leaf nodes 1, 3 have tilts of 0 (their left and right subtrees are empty)
//
//The root node 5 has a tilt of |1 - 3| = 2
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
//Output:
//
//21
//The leaf nodes 1, 3, 6, 9 have tilts of 0 (their left and right subtrees are empty)
//
//Node 2 has a tilt of |1 - 3| = 2 Node 7 has a tilt of |6 - 9| = 3 Node 4 has a tilt of |6 - 22] = 16
//
//2 + 3 + 16 = 21

public class CalculateTilt {
    static int tilt = 0;
    public static void main(String[] args) {
        Node root = new Node(4);
        //left
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        //right
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);


        calculateTiltDFS(root);

        System.out.println("Tilt of nodes is " + tilt);
    }

    static int calculateTiltDFS(Node root) {
        // base case
        if (root == null) {
            return 0;
        }

        int left = calculateTiltDFS(root.left);
        int right = calculateTiltDFS(root.right);

        tilt += Math.abs(left - right);

        return  left + right + root.value;
    }

}

//Recursion Thinking Framework
//
//Base case:
//If root == null, return 0.
//
//Recursive calls:
//Compute left and right subtree sums.
//
//Process current node:
//Add abs(left - right) to global tilt.
//
//Return upward:
//Return left + right + root.value (subtree sum).
//
//This is a postorder traversal problem (Left → Right → Node) because you need both subtrees’ data before processing the current node.