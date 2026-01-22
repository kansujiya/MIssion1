package HelloInterview.DFS;
//Given a binary tree, use Depth-First Search to find the sum of all nodes in the tree.
//Input
//4
//2
//1
//3
//7
//6
//9
//Output
//4 + 2 + 1 + 3 + 7 + 6 + 9 = 32
public class SumOfNodes {
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        System.out.println("sum of all nodes " + allSumDFS(root));
    }

    public static int allSumDFS(Node node) {
        // base case: empty subtree
        if (node == null) {
            return 0;
        }

        // base case: leaf node
//        if (node.left == null && node.right == null) {
//            return node.value;
//        }

        int left = allSumDFS(node.left);
        int right = allSumDFS(node.right);
        return left + right + node.value;
    }
}
