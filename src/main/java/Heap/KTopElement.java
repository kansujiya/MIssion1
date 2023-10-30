package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KTopElement {

    public static void main(String[] args) {
        //Kth Top element
        System.out.println(topElement(new int[] {7,4,3,2,1}, 2));

        //Min Meeting room
        System.out.println(minMeetingRooms(new int[][]{ {0,30}, {5,10}, {15,20} }));
    }


    public static int[] topElement(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(); //put CRITERIA

        for(int num: arr) {
            heap.add(num);
            if(heap.size() > k) {
                heap.remove();
            }
        }

        int[] ans = new int[k];
        for(int i = 0; i < k; i++) {
            ans[i] = heap.remove();
        }

        return ans;
    }

    //Given an array of meeting time intervals, intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
    //Input: intervals = [[0,30],[5,10],[15,20]]
    //Output: 2
    //Input: intervals = [[7,10],[2,4]]
    //Output: 1
    public static int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0) {
            return 0;
        }


        PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(intervals.length,
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        return a-b;
                    }
                });


        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(final int[] a, final int[] b) {
                return a[0] - b[0];
            }
        });

        System.out.print(intervals[0][1]);

        allocator.add(intervals[0][1]);

        for(int i = 1; i < intervals.length; i++) {

            System.out.print(intervals[i][0]);
            System.out.print("\n");
            System.out.print(allocator.peek());
            System.out.print("\n");

            if(intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            System.out.print(intervals[i][1]);
            allocator.add(intervals[i][1]);
        }

        return allocator.size();
    }

    //Kth Largest Element in an Array
    //Given an integer array nums and an integer k, return the kth largest element in the array.
    //
    //Note that it is the kth largest element in the sorted order, not the kth distinct element.
    //
    //You must solve it in O(n) time complexity.
    //Input: nums = [3,2,1,5,6,4], k = 2
    //Output: 5
    //Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    //Output: 4

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

        for(int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);

            if(heap.size() > k) {
                heap.poll();
            }
        }

        return heap.poll();
    }
}
