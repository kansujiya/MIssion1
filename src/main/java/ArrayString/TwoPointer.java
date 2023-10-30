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

    //Input: nums = [3,2,2,3], val = 3
    //Output: 2, nums = [2,2,_,_]
    //Explanation: Your function should return k = 2, with the first two elements of nums being 2.
    //It does not matter what you leave beyond the returned k (hence they are underscores).
    //https://leetcode.com/problems/remove-element/
    class Solution {
        public int removeElement(int[] nums, int val) {
            int i = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != val) {
                    nums[i] = nums[j];
                    i++;
                }
            }
            return i;
        }
    }
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                // reduce array size by one
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    //Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    //Output: 6
    //Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
    //Here two pointer template used as two array left right
    //https://leetcode.com/problems/trapping-rain-water/description/
    public int trap(int[] height) {
        int size = height.length;

        int[] mxL = new int[size];
        int[] mxR = new int[size];

        mxL[0] = height[0];
        for(int i = 1; i < size; i++) {
            mxL[i] = Math.max(mxL[i-1], height[i]);
            System.out.println(mxL[i]);
        }

        mxR[size-1] = height[size-1];
        for(int i = size-2; i >= 0; i--) {
            mxR[i] = Math.max(mxR[i+1], height[i]);
            System.out.println(mxR[i]);
        }

        int[] water = new int[size];
        for(int i = 0; i < size; i++) {
            water[i] = Math.min(mxL[i], mxR[i]) - height[i];
            System.out.println(water[i]);
        }

        System.out.println(water);

        int sum = 0;
        for(int i = 0; i < size; i++) {
            sum = sum + water[i];
        }

        return sum;
    }

    //sort color 0 = red, 1= yellow, 2 = blue
    //Input: nums = [2,0,2,1,1,0]
    //Output: [0,0,1,1,2,2]
    //here 3 pointer used, one to track left for 0 , curr for 1 & right for 2
    public void sortColors(int[] nums) {
        int left = 0;
        int curr = left;
        int right = nums.length - 1;

        int temp;

        while(curr <= right) {
            if(nums[curr] == 0) {
                temp = nums[left];
                nums[left] = nums[curr];
                nums[curr] = temp;
                left++;
                curr++;
            } else if(nums[curr] == 2) {
                temp = nums[curr];
                nums[curr] = nums[right];
                nums[right] = temp;
                right--;
            } else {
                curr++;
            }

        }
    }


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
