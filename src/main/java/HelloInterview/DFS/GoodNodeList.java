package HelloInterview.DFS;

import java.util.ArrayList;
import java.util.List;

public class GoodNodeList {
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);

        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        List<Node> nodes = new ArrayList<>();
        goodNodeDFS(root, Integer.MIN_VALUE, nodes);

        System.out.println("Good Node count list is " + nodes);
    }

    static void goodNodeDFS(Node root, int maxVal, List<Node> goodNodes) {
        // base case
        if (root == null) {
            return;
        }

        if (root.value >= maxVal) {
            maxVal = root.value;
            maxVal = root.value;
        }

        goodNodeDFS(root.left, maxVal, goodNodes);
        goodNodeDFS(root.right, maxVal, goodNodes);
    }

    //another way
    static List<Node> goodNodeListDFS(Node root, int maxVal) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Node> result = new ArrayList<>();
        if (root.value >= maxVal) {
            maxVal = root.value;
            result.add(root);
        }

        List<Node> left = goodNodeListDFS(root.left, maxVal);
        List<Node> right = goodNodeListDFS(root.right, maxVal);

        result.addAll(left);
        result.addAll(right);
        return result;
    }
}
