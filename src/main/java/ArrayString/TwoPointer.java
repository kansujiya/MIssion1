package ArrayString;

import java.util.List;


// Identify // In case of sorted array
//Using left right 2 pointers
//creating 2 separate array
//using 3 pointers, where one pointer will hold initial & one keep moving to right side, one left side

public class TwoPointer {

    //Two Pointer
    public int TwoPointer(List<Integer> arr) {
        int left = 0;
        int right = arr.size() -1;
        int ans = 0;
        while(left < right) {
            if(true) { // CONDITION
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    //Two pointers: two inputs, exhaust both
    public int TwoPointerExhaust(int[] arr) {
        int left = 0;
        int right = 0;
        int ans = 0;
        int length = arr.length -1;

        while(left < length && right < length) {
            if(true) { //CONDITION
                left++;
            } else {
                right++;
            }
        }

        while(left < length) {
            left++;
        }

        while (right < length) {
            right++;
        }

        return ans;
    }

    //Examples




    //Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    //Output: [1,2,2,3,5,6]
    //Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
    //The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.


    //Here 2 & 3 both pointer technique used check carefully
    public void mergeThreePointer(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of the first m elements of nums1.
        int[] nums1Copy = new int[m];
        for (int i = 0; i < m; i++) {
            nums1Copy[i] = nums1[i];
        }

        // Read pointers for nums1Copy and nums2 respectively.
        int p1 = 0;
        int p2 = 0;

        // Compare elements from nums1Copy and nums2 and write the smallest to nums1.
        for (int p = 0; p < m + n; p++) {
            // We also need to ensure that p1 and p2 aren't over the boundaries
            // of their respective arrays.
            if (p2 >= n || (p1 < m && nums1Copy[p1] < nums2[p2])) {
                nums1[p] = nums1Copy[p1++];
            } else {
                nums1[p] = nums2[p2++];
            }
        }
    }

    public void mergeTwoPointer(int[] nums1, int m, int[] nums2, int n) {
        // Set p1 and p2 to point to the end of their respective arrays.
        int p1 = m - 1;
        int p2 = n - 1;

        // And move p backwards through the array, each time writing
        // the smallest value pointed at by p1 or p2.
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }
}
