package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.value = val;
    }
}

public class BFSIterative {

    public static void main(String[] args) {
        //1. Reversal level order traversal
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println(reversalLevelOrderTraversal(root));

        //2. ZigZag traverse
        TreeNode root1 = new TreeNode(12);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(9);
        root1.right.left = new TreeNode(10);
        root1.right.right = new TreeNode(5);
        root1.right.left.left = new TreeNode(20);
        root1.right.left.right = new TreeNode(17);
        System.out.println(zigZagTraverse(root1));

        //3. Level average
        TreeNode root2 = new TreeNode(12);
        root2.left = new TreeNode(7);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(9);
        root2.left.right = new TreeNode(2);
        root2.right.left = new TreeNode(10);
        root2.right.right = new TreeNode(5);
        System.out.println(findLevelAverage(root2));

        //4. Inverted tree
        TreeNode treeNode1 = new TreeNode(4);
        treeNode1.left = new TreeNode(2);
        treeNode1.right = new TreeNode(7);
        treeNode1.left.left = new TreeNode(1);
        treeNode1.left.right = new TreeNode(3);
        treeNode1.right.left = new TreeNode(6);
        treeNode1.right.right = new TreeNode(9);
        //5. Invert a binary tree
        System.out.println("inverted tress is : " + invertTree(treeNode1));
    }

    //Input: root = [4,2,7,1,3,6,9]
    //Output: [4,7,2,9,6,3,1]
    //Input: root = [2,1,3]
    //Output: [2,3,1]
    public static TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();

            //swap left right
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }

        return root;
    }

    //Given a binary tree, populate an array to represent its level-by-level traversal in reverse order, i.e., the lowest level comes first. You should populate the values of all nodes in each level from left to right in separate sub-arrays.
    //1
    //  2
    //  3
    //  4
    //  5
    //  6
    //  7
    //[[4,5,6,7],[2,3],[1]]
    public static List<List<Integer>> reversalLevelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        Queue<TreeNode> levelNodes = new LinkedList<>();
        levelNodes.add(root);

        while (!levelNodes.isEmpty()) {

            int size = levelNodes.size();

            List<Integer> subLevel = new ArrayList<>();

            for(int i = 0; i < size; i++) {
                TreeNode current = levelNodes.remove();
                subLevel.add(current.value);


                if(current.left != null) {
                    levelNodes.add(current.left);
                }
                if(current.right != null) {
                    levelNodes.add(current.right);
                }
            }
            result.add(0, subLevel);
        }

        return result;
    }

    //Given a binary tree, populate an array to represent its zigzag level order traversal. You should populate the values of all nodes of the first level from left to right, then right to left for the next level and keep alternating in the same manner for the following levels.
    //1
    //  2
    //  3
    //  4
    //  5
    //  6
    //  7
    //[[1],[3, 2],[4, 5, 6, 7]]


    //12
    //  7
    //  1
    //  9
    //  10
    //  5
    //  20
    //  17
    //[[12],[1,7],[9,10,5][17,20]]
    public static List<List<Integer>> zigZagTraverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        int level = 0;
        boolean isReverse = true;
        Queue<TreeNode> currentLevel = new LinkedList<>();
        currentLevel.add(root);

        while(!currentLevel.isEmpty()) {
            int size = currentLevel.size();

            List<Integer> newLevel = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode currentNode = currentLevel.remove();


                if(isReverse) {
                    newLevel.add(currentNode.value);
                } else {
                    newLevel.add(0, currentNode.value);
                }

                    if(currentNode.left != null) {
                        currentLevel.add(currentNode.left);
                    }

                    if(currentNode.right != null) {
                        currentLevel.add(currentNode.right);
                    }

            }
            isReverse = !isReverse;
            result.add(newLevel);
        }

        return result;
    }

    public int bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int ans = 0;
        queue.add(root);

        while(!queue.isEmpty()) {
            int currentLength = queue.size();

            //Do some logic on current level

            for (int i = 0; i < currentLength; i++) {
                TreeNode node = queue.remove();
                //do logic
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return ans;
    }

    //Given a binary tree, populate an array to represent the averages of all of its levels.
    //[1,2,3,4,,5,6,7]
    //[1, 2.5, 5.5]
    public static List<Double> findLevelAverage(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0.0;
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                sum += node.value;

                if(node.left != null) {
                    queue.add(node.left);
                }

                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(sum / size);
        }

        return result;
    }

    //Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
    //Input: root = [1,2,2,3,4,4,3] //true
    //[1,2,2,null,3,null,3] //false
        public boolean isSymmetric(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode t1 = q.poll();
                TreeNode t2 = q.poll();
                if (t1 == null && t2 == null) continue;
                if (t1 == null || t2 == null) return false;
                if (t1.value != t2.value) return false;
                q.add(t1.left);
                q.add(t2.right);
                q.add(t1.right);
                q.add(t2.left);
            }
            return true;

            // public boolean isMirror(TreeNode t1, TreeNode t2) {
            // if (t1 == null && t2 == null) return true;
            // if (t1 == null || t2 == null) return false;
            // return (t1.val == t2.val)
            //     && isMirror(t1.right, t2.left)
            //     && isMirror(t1.left, t2.right);
            // }
        }


}
