package HelloInterview.DFS;

//Given the root node of a binary tree, write a function to find the number of "good nodes" in the tree. A node X in the tree is considered "good" if in the path from the root to the node X, there are no nodes with a value greater than X's value.
//Example
//Input:
//4
//2
//1
//3
//7
//6
//9
//Output:
//3 # The good nodes are highlighted in green (4, 7, 9)
//Node	Path	Is Good Node?	Explanation
//4	[4]	Yes	The root node is a "good node" since there are no nodes with a value greater than 4 in the path from the root to the node.
//2	[4, 2]	No	4 is greater than 2.
//1	[4, 2, 1]	No	Both 4 and 2 are greater than 1.
//3	[4, 2, 3]	No	4 is greater than 3.
//7	[4, 7]	Yes	There are no nodes with a value greater than 7, so it is a "good node".
//6	[4, 7, 6]	No	7 is greater than 6.
//9	[4, 7, 9]	Yes	There are no nodes with a value greater than 9, so it is a "good node".

public class GoodNode {

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);

        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        System.out.println("Good Node count is " + goodNodeDFS(root, Integer.MIN_VALUE));
    }

    static int goodNodeDFS(Node root, int maxVal) {
        // base case
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.value >= maxVal) {
            // good node found, update count and max_
            count += 1;
            maxVal = root.value;
        }
        // recurse and pass down updated max_
        // to the left and right children
        int left = goodNodeDFS(root.left, maxVal);
        int right = goodNodeDFS(root.right, maxVal);
        // return the number of good nodes in the
        // subtree rooted at the current node
        return count + left + right;
    }

}

//Recursion Thinking Pattern
//
//Base case: Stop if root == null.
//
//Process step:
//
//Check if root.value >= maxVal.
//
//If yes → add it and update maxVal.
//
//Recursive step:
//
//Visit left and right subtrees, passing the updated max.
//
//You’re carrying the “path maximum” along the recursive journey — like a backpack that tracks the biggest value seen so far.
