package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class epamTree {

    static class TreeNode {

        TreeNode left;
        TreeNode right;
        int data;

        TreeNode(int val){
            data = val;
        }
        void addLeft(int val){
            this.left = new TreeNode(val);
        }

        void addRight(int val){
            this.right = new TreeNode(val);
        }
    }

    public static void main (String[] args) {

//                   10
//                 /     \
//                11      12
//                / \      / \             <--------|||
//               13  14  15  16
//                       /
//                      17
// O/P: 10->12->16->17

        TreeNode root = new TreeNode(10);
        root.addLeft(11);
        root.addRight(12);

        root.left.addLeft(13);
        root.left.addRight(14);

        root.right.addLeft(15);
        root.right.addRight(16);

        root.right.left.addLeft(17);

        List<Integer> r = getLinkedListOfRightViewNodes(root);
        System.out.println(r);
    }


    private static List<Integer> getLinkedListOfRightViewNodes(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();

        // code here
        List<TreeNode> temp = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode curr = queue.peek();
                queue.remove();

                // print the last node of each level
                if (i == len - 1) {
                    result.add(curr.data);
                }

                // if left child is not null add it into
                // the
                // queue
                if (curr.left != null) {
                    queue.add(curr.left);
                }

                // if right child is not null add it into
                // the
                // queue
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
        }

        return result;
    }

}
