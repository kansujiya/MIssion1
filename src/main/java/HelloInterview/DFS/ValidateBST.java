package HelloInterview.DFS;

//Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//
//A tree is a BST if the following conditions are met:
//
//Every node on the left subtree has a value less than the value of the current node.
//Every node on the right subtree has a value greater than the value of the current node.
//The left and right subtrees must also be valid BSTs.
//Example 1:
//
//2
//1
//4
//Input:
//
//[2,1,4]
//Output: true
//
//Example 2:
//
//4
//1
//5
//3
//6
//Input:
//
//[4,1,5,null,null,3,6]
//Output: false. 3 is the root node's right subtree, but it is less than the root node 4.

public class ValidateBST {
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        System.out.println("give tree is BST :  " + isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    static boolean isValidBST(Node root, int min, int max) {
        if(root == null) {
            return true;
        }

        if(root.value <= min || root.value >= max) {
            return false;
        }

        return isValidBST(root.left, min, root.value) && isValidBST(root.right, root.value, max);
    }
}

//Recursion Thinking Framework
//
//Base Case: If node is null → return true.
//
//Check condition: If node value violates (min, max) bounds → return false.
//
//Recursive Step:
//
//Left subtree → new range (min, root.value)
//
//Right subtree → new range (root.value, max)
//
//You’re essentially carrying min and max limits down the recursion — just like a “valid range guardrail” for every node.
