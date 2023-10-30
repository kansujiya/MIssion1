package BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Node {
    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
        left = right = null;
    }
}

public class DFSIterative {

    //Binary tree: DFS (iterative)

    public static void main(String[] args) {
        //1. Binary Tree Path Sum (easy)
        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right.left = new Node(2);

        System.out.println(hasPath(root, 23));
        System.out.println(hasPathRecursive(root, 23));


        Node root1 = new Node(12);
        root1.left = new Node(7);
        root1.right = new Node(1);
        root1.left.left = new Node(4);
        root1.right.left = new Node(10);
        root1.right.right = new Node(5);

        //2. All Paths for a Sum (medium)
        System.out.println(allPathSum(root1, 23));
        System.out.println(allPathSumRecursive(root1, 23));


        Node root2 = new Node(1);
        root2.left = new Node(0);
        root2.right = new Node(1);
        root2.left.left = new Node(1);
        root2.right.left = new Node(6);
        root2.right.right = new Node(5);

        //3. Sum of Path Numbers (medium)
        System.out.println("Total path sum : " + findSumOfPathNumberRecursive(root2, 0));
        System.out.println("Total path sum : " + findSumOfPathNumber(root2));

    }


    //Binary Tree Path Sum (easy)
    //Given a binary tree and a number ‘S’, find if the tree has a path from root-to-leaf such that the sum of all the node values of that path equals ‘S’.
    //1
    //  2
    //  3
    //  4
    //  5
    //  6
    //  7
    //S: 10 Output: true Explanation: The path with sum '10' is highlighted

    public static boolean hasPath(Node node, int sum) {

        Stack<Node> stack = new Stack<>();
        Stack<Integer> sums = new Stack<>();
        stack.push(node);
        sums.push(node.val);

        while(!stack.isEmpty()) {

            Node current = stack.pop();
            int target = sums.pop();

            if(current.left == null && current.right == null) {
                if(sum == target)
                    return true;
            }

            if(current.left != null) {
                stack.push(current.left);
                sums.push(target + current.left.val);
            }
            if(current.right != null) {
                stack.push(current.right);
                sums.push(target + current.right.val);
            }
        }

        return false;
    }

    public static boolean hasPathRecursive(Node node, int sum) {

        if(node == null) {
            return false;
        }

        if(node.val == sum && node.left == null && node.right == null) {
            return true;
        }

        return hasPathRecursive(node.left, sum - node.val) || hasPathRecursive(node.right, sum - node.val);
    }

    //Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.
    //  1
    //  7
    //  9
    //  4
    //  5
    //  2
    //  7
    //S: 12 Output: 2 Explanation: There are the two paths with sum '12':1 -> 7 -> 4 and 1 -> 9 -> 2

    public static List<List<Integer>> allPathSum(Node root, int sum) {
           List<List<Integer>> result = new ArrayList<>();
           Stack<List<Integer>> stack = new Stack<>();

           Stack<Node> nodeStack = new Stack<>();


           List<Integer> currentPathOfNode = new ArrayList();
           currentPathOfNode.add(root.val);

           stack.push(currentPathOfNode);
           nodeStack.push(root);

           while(!nodeStack.isEmpty()) {

               Node current = nodeStack.pop();
               List<Integer> currentPath = stack.pop();

               int total = currentPath.stream().reduce(0, (s, e) -> s+e);

               if(current.left == null && current.right == null &&  total == sum) {
                   result.add(currentPath);
               }

               if(current.left != null) {
                   nodeStack.push(current.left);
                   List<Integer> newPath = new ArrayList<>(currentPath);
                   newPath.add(current.left.val);
                   stack.push(newPath);
               }

               if(current.right != null) {
                   nodeStack.push(current.right);
                   List<Integer> newPath = new ArrayList<>(currentPath);
                   newPath.add(current.right.val);
                   stack.push(newPath);
               }
           }

           return result;
    }

    public static List<List<Integer>> allPathSumRecursive(Node root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        getAllPath(result, currentPath, sum, root);
        return result;
    }

    public static void getAllPath(List<List<Integer>> allPath, List<Integer> currentPath, int sum, Node root) {
        if(root == null) {
            return;
        }

        currentPath.add(root.val);

        if(root.left == null && root.right == null & root.val == sum) {
            allPath.add(new ArrayList<>(currentPath));
        }

        if(root.left != null) {
            getAllPath(allPath, currentPath, sum- root.val, root.left);
        }
        if(root.right != null) {
            getAllPath(allPath, currentPath, sum- root.val, root.right);
        }

        currentPath.remove(currentPath.size()-1);
    }

    //Sum of Path Numbers (medium)
    //Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will represent a number. Find the total sum of all the numbers represented by all paths.
    //  1
    //  7
    //  9
    //  2
    //  9
    // Example 1:
    // Output: 408 Explanation: The sum of all path numbers: 17 + 192 + 199
    public static int findSumOfPathNumber(Node root) {
        if (root == null) return 0;

        int totalSum = 0;
        Stack<Node> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();

        nodeStack.push(root);
        sumStack.push(0);

        while (!nodeStack.isEmpty()) {
            Node node = nodeStack.pop();
            int currentSum = sumStack.pop();

            int newSum = currentSum * 10 + node.val;

            if (node.left == null && node.right == null) {
                totalSum += newSum;
            } else {
                if (node.left != null) {
                    nodeStack.push(node.left);
                    sumStack.push(newSum);
                }

                if (node.right != null) {
                    nodeStack.push(node.right);
                    sumStack.push(newSum);
                }
            }
        }

        return totalSum;
    }

    public static int findSumOfPathNumberRecursive(Node node, int currentSum) {
        if (node == null) return 0;

        // Calculate the current path sum
        int newSum = currentSum * 10 + node.val;

        // If it's a leaf node, return the current path sum
        if (node.left == null && node.right == null) {
            return newSum;
        }

        // Otherwise, continue traversing the tree
        int leftSum = findSumOfPathNumberRecursive(node.left, newSum);
        int rightSum = findSumOfPathNumberRecursive(node.right, newSum);

        // Sum the paths from left and right subtrees
        return leftSum + rightSum;
    }


    public int dfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int ans = 0;

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            //do logic
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        return ans;
    }

    //Given the root of a binary tree, return the inorder traversal of its nodes' values. root-> left -> right
    //Input: root = [1,null,2,3]
    //Output: [1,3,2]

    public List<Integer> preorderTraversal(Node root) {
        List<Integer> result = new ArrayList<>();

        Stack<Node> stack = new Stack();

        stack.add(root);

        while(!stack.isEmpty()) {

            Node node = stack.peek();
            stack.pop();

            if(node != null) {
                result.add(node.val);
                stack.add(node.right);
                stack.add(node.left);
            }
        }

        return result;
    }

    // public List<Integer> preorderTraversal(TreeNode root) {

    //     private List<Integer> answer = new ArrayList<>();

    // private void dfs(TreeNode node) {
    //         if (node == null) {
    //             return;
    //         }
    //         // Visit the root first, then the left subtree, then the right subtree.
    //         answer.add(node.val);
    //         dfs(node.left);
    //         dfs(node.right);
    //     }

    //     public List<Integer> preorderTraversal(TreeNode root) {
    //         dfs(root);
    //         return answer;
    //     }
    // }


    //Given the root of a binary tree, return the postorder traversal of its nodes' values. Left -> Right -> Root
    //Input: root = [1,null,2,3]
    //Output: [3,2,1]
    List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(Node root) {
        if (root!=null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            list.add(root.val);
        }
        return list;
    }

}
