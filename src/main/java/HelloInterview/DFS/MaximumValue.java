package HelloInterview.DFS;

public class MaximumValue {
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        System.out.println("max in all nodes " + allSumDFS(root));
    }

    public static int allSumDFS(Node node) {
        // base case: empty subtree
        if (node == null) {
            return 0;
        }

//        if(node.left == null && node.right == null) {
//            return node.value;
//        }

        int left = allSumDFS(node.left);
        int right = allSumDFS(node.right);
        return Math.max(left, Math.max(right, node.value));
        //return Math.max(left, right);
    }
}
