package BinaryTree;

import java.time.temporal.Temporal;
import java.util.*;

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

        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(5);
        //4. Math Path sum
        System.out.println("Total path sum : " + maxPathSum(treeNode));

        TreeNode treeNode1 = new TreeNode(4);
        treeNode1.left = new TreeNode(2);
        treeNode1.right = new TreeNode(7);
        treeNode1.left.left = new TreeNode(1);
        treeNode1.left.right = new TreeNode(3);
        treeNode1.right.left = new TreeNode(6);
        treeNode1.right.right = new TreeNode(9);

        //6. Max depth of tree
        System.out.println("Max depth of tress is : " + maxDepth(treeNode1));

        //7. diameterOfBinaryTree
        System.out.println("Max diameter of tress is : " + diameterOfBinaryTree(treeNode1));

        //Input: root = [1,2,2,3,3,null,null,4,4]
        TreeNode n = new TreeNode(1);
        n.left = new TreeNode(2);
        n.right = new TreeNode(2);
        n.left.left = new TreeNode(3);
        n.left.right = new TreeNode(3);
        n.left.left.left = new TreeNode(4);
        n.left.left.right = new TreeNode(4);

        //8. Balanced Binary Tree
        System.out.println("is tree balanced : " + isBalanced(n));

        TreeNode n1 = new TreeNode(1);
        n1.left = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        n2.left = new TreeNode(2);
        //9. Same Tree
        System.out.println("is tree same : " + isSameTree(n1, n2));

        TreeNode n3 = new TreeNode(1);
        n3.left = new TreeNode(4);
        n3.right = new TreeNode(5);
        n3.left.left = new TreeNode(1);
        n3.left.right = new TreeNode(2);
        n3.left.left = new TreeNode(0);

        TreeNode n4 = new TreeNode(4);
        n4.left = new TreeNode(1);
        n4.right = new TreeNode(2);
        //10. Subtree of Another Tree
        System.out.println("is subtree same : " + isSubtree(n3, n4));

        //11. Lowest Common Ancestor of a Binary Search Tree
        TreeNode n5 = new TreeNode(6);
        n5.left = new TreeNode(2);
        n5.right = new TreeNode(8);
        n5.left.left = new TreeNode(0);
        n5.left.right = new TreeNode(4);
        n5.left.right.left = new TreeNode(3);
        n5.left.right.right = new TreeNode(5);
        n5.right.left = new TreeNode(7);
        n5.right.right = new TreeNode(9);
        System.out.println("LCA of tree id : " + lowestCommonAncestor(n5, n5.left, n5.left.right));

        //12. Good nodes
        TreeNode n6 = new TreeNode(3);
        n6.left = new TreeNode(1);
        n6.right = new TreeNode(4);
        n6.left.left = new TreeNode(3);

        n6.right.left = new TreeNode(1);
        n6.right.right = new TreeNode(5);
        System.out.println("Good node count is : " + goodNodes(n6));

        //13.Kth Smallest Element in tree
        TreeNode n7 = new TreeNode(3);
        n7.left = new TreeNode(1);
        n7.right = new TreeNode(4);
        n6.left.right = new TreeNode(3);
        System.out.println("kth smallest element in tree is : " + kthSmallest(n7, 1));
    }

    //Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
    //Input: root = [3,1,4,null,2], k = 1
    //Output: 1
    //Input: root = [5,3,6,2,4,null,null,1], k = 3
    //Output: 3
    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        solution(root, list);
        return (list.get(k-1));
    }

    private static void solution(TreeNode root, List<Integer> list){
        if(root == null ) return;

        solution(root.left,list);
        list.add(root.value);
        solution(root.right, list);
    }

    //Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
    //
    //Return the number of good nodes in the binary tree.
    //Input: root = [3,1,4,3,null,1,5]
    //Output: 4
    //Explanation: Nodes in blue are good.
    //Root Node (3) is always a good node.
    //Node 4 -> (3,4) is the maximum value in the path starting from the root.
    //Node 5 -> (3,4,5) is the maximum value in the path
    //Node 3 -> (3,1,3) is the maximum value in the path.
    //Input: root = [3,3,null,4,2]
    //Output: 3
    //Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
    public static int goodNodes(TreeNode root) {
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> max = new Stack<>();
        stack.push(root);
        max.push(root.value);

        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            int currMax = Math.max(curr.value, max.pop());


            if(currMax == root.value) {
                count++;
            }

            if(curr.left != null) {
                stack.push(curr.left);
                max.push(curr.left.value);
            }

            if(curr.right != null) {
                stack.push(curr.right);
                max.push(curr.right.value);
            }

        }
        return count;
    }

    //Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
    //
    //According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
    //Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
    //Output: 6
    //Explanation: The LCA of nodes 2 and 8 is 6.
    //Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
    //Output: 2
    //Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.value;
        int qVal = q.value;

        TreeNode node  = root;

        while(node != null) {
            int nVal = node.value;

            if(pVal > nVal && qVal > nVal) {
                node = node.right;
            } else if(pVal < nVal && qVal < nVal) {
                node = node.left;
            } else {
                return node;
            }
        }

        return null;

//        if(root == null) return null;

//        int pVal = p.value;
//        int qVal = q.value;
//        int nVal = root.value;
//
//        if(pVal > nVal && qVal > nVal) {
//            return lowestCommonAncestor(root.right, p, q);
//        } else if(pVal < nVal && qVal < nVal) {
//            return lowestCommonAncestor(root.left, p, q);
//        } else {
//            return root;
//        }
    }

    //Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
    //
    //A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
    //Input: root = [3,4,5,1,2], subRoot = [4,1,2]
    //Output: true
    //Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
    //Output: false
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Stack<TreeNode> s1 = new Stack<>();

        s1.push(root);

        boolean isSame = false;

        while(!s1.isEmpty() && !isSame) {
            TreeNode curr1 = s1.pop();

            if(subRoot.value == curr1.value) {
                isSame = isSameTree(subRoot, curr1);
            }

            if(curr1.left != null) {
                s1.push(curr1.left);
            }
            if(curr1.right != null) {
                s1.push(curr1.right);
            }
        }

        return isSame;
    }

    //Given the roots of two binary trees p and q, write a function to check if they are the same or not.
    //
    //Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
    //Input: p = [1,2,3], q = [1,2,3]
    //Output: true
    //Input: p = [1,2], q = [1,null,2]
    //Output: false
    //Input: p = [1,2,1], q = [1,1,2]
    //Output: false
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(p);
        stack2.push(q);
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode n1 = stack1.pop();
            TreeNode n2 = stack2.pop();
            if(n1 == null && n2 == null) continue;
            if(n1 == null || n2 == null || n1.value != n2.value) return false;
            stack1.push(n1.right);
            stack2.push(n2.right);
            stack1.push(n1.left);
            stack2.push(n2.left);
        }
        return true;
    }

    //Given a binary tree, determine if it is
    //height-balanced
    //.A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
    //Input: root = [3,9,20,null,null,15,7]
    //Output: true
    //Input: root = [1,2,2,3,3,null,null,4,4]
    //Output: false
    public static boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode current = stack.pop();
                int leftHeight = getHeight(current.left);
                int rightHeight = getHeight(current.right);

                if (Math.abs(leftHeight - rightHeight) > 1) {
                    return false;
                }

                if (current.left != null) {
                    stack.push(current.left);
                }

                if (current.right != null) {
                    stack.push(current.right);
                }
            }
            return true;
    }

    private static int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    //Given the root of a binary tree, return the length of the diameter of the tree.
    //
    //The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
    //
    //The length of a path between two nodes is represented by the number of edges between them.
    //Input: root = [1,2,3,4,5]
    //Output: 3
    //Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
    public static int diameterOfBinaryTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        int diameter = Integer.MIN_VALUE;

        //Root’s children aren’t processed, so set value to 0.
        //Instead of node.val we’re storing node, as there can be multiple nodes with same values.
        map.put(root, 0);
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode currNode = stack.pop();

            if(map.get(currNode) != null && map.get(currNode) == 0) {
                //This is to indicate we will now process children for this node
                map.put(currNode, 1);
                stack.push(currNode);

                if(currNode.left != null) {
                    stack.push(currNode.left);
                    map.put(currNode.left, 0);
                }
                if(currNode.right != null) {
                    stack.push(currNode.right);
                    map.put(currNode.right, 0);
                }
            } else {
                //// We enter here when we know both children of the popped node have been processed already.
                int lMax = currNode.left == null ? 0 : map.get(currNode.left);
                int rMax = currNode.right == null ? 0 : map.get(currNode.right);

                //This updates the max variable in case present value is greater. This indicates the path length which includes the popped node.
                diameter = Math.max(diameter, (1+lMax+rMax));

                //Update popped node value in map so that it’s parent can use it.
                map.put(currNode, Math.max(lMax, rMax) + 1);
            }
        }
        //Subtract 1 since we need edge count, but we stored node count.
        return diameter -1 ;
    }



    //Given the root of a binary tree, return its maximum depth.
    //
    //A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    //Input: root = [3,9,20,null,null,15,7]
    //Output: 3
    public static int maxDepth(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> values = new Stack<>();

        int height = 0;
        stack.push(root);
        values.push(1);

        while(!stack.isEmpty()) {
            TreeNode currNode = stack.pop();
            int currHeight = values.pop();

            if(currNode.left == null || currNode.right == null) {
                height = Math.max(height, currHeight);
            }

            if(currNode.left != null) {
                stack.push(currNode.left);
                values.push(currHeight+1);
            } if(currNode.right != null) {
                stack.push(currNode.right);
                values.push(currHeight+1);
            }
        }

        return height;
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

    //Given the root of a binary tree, return the maximum path sum of any non-empty path.
    //Input: root = [1,2,3]
    //Output: 6
    //Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
    //Input: root = [-10,9,20,null,null,15,7]
    //Output: 42
    //Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

    // just returns the nodes in post-order
    public static Iterable<TreeNode> topSort(TreeNode root) {
        Deque<TreeNode> result = new LinkedList<>();
        if (root != null) {
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                result.push(curr);
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
        }
        return result;
    }

    public static int maxPathSum(TreeNode root) {
        int result = Integer.MIN_VALUE;
        Map<TreeNode, Integer> maxRootPath = new HashMap<>(); // cache
        maxRootPath.put(null, 0); // for simplicity we want to handle null nodes
        for (TreeNode node : topSort(root)) {
            // as we process nodes in post-order their children are already cached
            int left = Math.max(maxRootPath.get(node.left), 0);
            int right = Math.max(maxRootPath.get(node.right), 0);
            maxRootPath.put(node, Math.max(left, right) + node.value);
            result = Math.max(left + right + node.value, result);
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
