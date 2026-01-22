package ArrayString;

import HelloInterview.DFS.Node;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ArrayQuestions {

    public static void main(String[] args) {
        // rotate an array with k time
        int[] nums = new int[] {1,2,3,4,5,6,7};
        int k = 3; // reverse entire array
        rotate(nums, k);
        System.out.println(" k time rotation is: " + Arrays.toString(nums));

        //reverse array without extra space
        int[] myArray = {10, 20, 30, 40, 50, 60};

        reverseArrayInPlace(myArray);
        System.out.println("Reversed array: " + Arrays.toString(myArray));

        //Find the second-largest element in an array

        int[] arr = new int[] {845,5,5,8,4,52};
         System.out.println("Second largest element: "+ kthLargestElement(arr));

         //Reverse a linked list
        Node root = new Node(2);
        root.next = new Node(3);
        root.next.next = new Node(0);
        root.next.next.next = new Node(6);
        root.next.next.next.next = new Node(6);
        //Node n = reverseLinkedList(root);
//        System.out.println("reverse linked list is" + n);
//        while(n != null) {
//            System.out.println(n.value);
//            n = n.next;
//        }

        //Find the middle node of a linked list
        int midElement = findMidElement(root);
        System.out.println("Mid node value is: " + midElement);

    }


        //k = 3
        //[1,2,3,4,5,6,7]
        //[7,6,5,4,3,2,1]
        //[5,6,7,4,3,2,1]
        //[5,6,7,1,2,3,4]
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;//All we have to do is to rotate all numbers the remainder times.
        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }

    public static void reverse(int[] nums, int i, int j) {
        while(j > i) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;

            i++;
            j--;
        }
    }

    public static void reverseArrayInPlace(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            // Swap the elements
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            // Move the pointers inward
            start++;
            end--;
        }
    }

    public static int kthLargestElement(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);

            if(queue.size() > 2) {
                queue.poll();
            }
        }

        return  queue.poll();
    }

    // 1->2->3->4 =>
    // Head prev curr
    public static Node reverseLinkedList(Node head) {
        Node curr = head;
        Node prev = null;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static int findMidElement(Node head) {

        Node curr = head;
        int len = 0;
        while(curr != null) {
            len++;
            curr = curr.next;
        }

        int mid = len/2;
        int i = 0;
        curr = head;
        while(i != mid) {
            i++;
            curr = curr.next;
        }

        return curr.value;
    }
}

