package HelloInterview.BinarySearch;

//You are given a sorted array that has been rotated at an unknown pivot point, along with a target value. Develop an algorithm to locate the index of the target value in the array. If the target is not present, return -1. The algorithm should have a time complexity of O(log n).
//
//Note:
//
//The array was originally sorted in ascending order before being rotated.
//The rotation could be at any index, including 0 (no rotation).
//You may assume there are no duplicate elements in the array.
//Example 1:
//Input:
//
//nums = [4,5,6,7,0,1,2], target = 0
//Output: 4 (The index of 0 in the array)
//
//Example 2:
//
//Input:
//
//nums = [4,5,6,7,0,1,2], target = 3
//Output: -1 (3 is not in the array)

public class RotatedSortedArray {
    public static void main(String[] args) {
        int[] num = new int[] {4,5,6,7,0,1,2};
        int target = 0;

        int left = 0;
        int right = num.length;

        while(left <= right) {
            int mid = (right + left)/2;

            if(num[mid] == target) {
                System.out.println("pivot at " + mid);
                return;
            }

            if(num[left] <= num[mid]) {
                // left half is sorted
                if (num[left] <= target && target < num[mid]) {
                    // target is in the left half
                    right = mid - 1;
                } else {
                    // target is in the right half
                    left = mid + 1;
                }
            } else {
                // right half is sorted
                if (num[mid] < target && target <= num[right]) {
                    // target is in the right half
                    left = mid + 1;
                } else {
                    // target is in the left half
                    right = mid - 1;
                }
            }
        }
        System.out.println("pivot at " + -1);
    }
}
