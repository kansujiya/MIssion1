package KWayMerge;

import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

class Node {
    int row;
    int col;
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class KWayMerge {

    //Whenever we are given ‘K’ sorted arrays, we can use a Heap to efficiently perform a sorted traversal of all the elements of all arrays. We can push the smallest (first) element of each sorted array in a Min Heap to get the overall minimum. While inserting elements to the Min Heap we keep track of which array the element came from. We can, then, remove the top element from the heap to get the smallest element and push the next element from the same array, to which this smallest element belonged, to the heap. We can repeat this process to make a sorted traversal of all elements.

    public static void main(String[] args) {
        //1. Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        ListNode result = mergeList(new ListNode[] {l1, l2, l3});

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

        //2.
        int[][] matrix = new int[][] {{2, 6, 8}, {3, 7, 10}, {5, 8, 11}};
        int k = 5;
        System.out.println("Smallest element at position "+  k + " is " + getNthSmallestNumberFromMatrix(matrix, k));
    }

    //Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.
    //Input: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4]
    //Output: [1, 2, 3, 3, 4, 6, 6, 7, 8]

    //Input: L1=[5, 8, 9], L2=[1, 7]
    //Output: [1, 5, 7, 8, 9]

    public static ListNode mergeList(ListNode[] nodes) {

        PriorityQueue<ListNode> pQueue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

//        PriorityQueue<ListNode> ppQueue = new PriorityQueue<ListNode>((n1, n2) -> n1.val - n2.val);

        //Now put the root of each element
        for(ListNode node : nodes) {
            if(node != null) {
                pQueue.add(node);
            }
        }

        //Take the smallest node from heap & add it into heap
        //if root node have next, needs to add into heap
        ListNode head = null;
        ListNode headNext = null;
        while (!pQueue.isEmpty()) {
            ListNode topNode = pQueue.poll();

            if(head == null) {
                head = headNext = topNode;
            } else {
                headNext.next = topNode;
                headNext = headNext.next;
            }

            if(headNext.next != null) {
                pQueue.add(headNext.next);
            }
        }

        return head;
    }

    //Kth Smallest Number in a Sorted Matrix (Hard)
    //Given an
    //N∗N matrix where each row and column is sorted in ascending order, find the Kth smallest element in the matrix.

    //Input: Matrix=[
    //    [2, 6, 8],
    //    [3, 7, 10],
    //    [5, 8, 11]
    //  ],
    //  K=5
    //Output: 7
    //Explanation: The 5th smallest number in the matrix is 7.

    public static int getNthSmallestNumberFromMatrix(int[][] matrix, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>((n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]);

        PriorityQueue<Node> minHeap1 = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return matrix[o1.row][o1.col] - matrix[o2.row][o2.col];
            }
        });

        //put first element fof row in min heap as to follow k-way merge algo
        //We don't need to push element in heap after k position
        for(int i = 0; i < matrix.length & i < k; i++) {
            minHeap.add(new Node(i, 0 ));
        }

        int numberCount  = 0;
        int result = 0;

        while(!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result = matrix[node.row][node.col];

            if(++numberCount == k) {
                break;
            }

            node.col++;
            if(matrix[0].length > node.col) {
                minHeap.add(node);
            }
        }

        return result;
    }

}
