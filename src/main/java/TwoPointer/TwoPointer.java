package TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class TwoPointer {
    public static void main(String[] args) {
        //1. target sum pair
        int[] result = targetSum(new int[]{1,2,3,4,6}, 6);
        System.out.println("Target sum is " + "{" + result[0] + " " + result[1] + "}");

        //2. Triplet sum
        System.out.println(searchAllTriplet(new int[]{-3, 0, 1, 2, -1, 1, -2}));

        //3. Closest TargetSum
        System.out.println(closetTargetSum(new int[]{-2, 0, 1, 2}, 2));
        System.out.println(closetTargetSum(new int[]{-3, -1, 1, 2}, 1));

        //4. Longest Palindromic Substring
        System.out.println(longestPalindrome("babad"));

        //5. Palindromic Substrings
        System.out.println(countSubstrings("aaa"));

        //6.Max Area
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));

        //7. Tap Water
        System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));

        //8.Sort colors
        sortColors(new int[] {2,0,2,1,1,0});
    }


    //Pair with Target Sum
    //Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
    //
    //Write a function to return the indices of the two numbers (i.e. the pair) such that they add up to the given target.
    //Input: [1, 2, 3, 4, 6], target=6
    //Output: [1, 3]
    //Explanation: The numbers at index 1 and 3 add up to 6: 2+4=6

    //Input: [2, 5, 9, 11], target=11
    //Output: [0, 2]
    //Explanation: The numbers at index 0 and 2 add up to 11: 2+9=11

    public static int[] targetSum(int[] arr, int targetSum) {

        int left = 0;
        int right = arr.length-1;
        while(left<right) {
            if(arr[left] + arr[right] == targetSum) {
                return new int[]{left, right};
            } else if(arr[left] + arr[right] > targetSum) {
                right--;
            } else {
                left++;
            }
        }

        return new int[]{-1, -1};
    }


    //Triplet Sum to Zero (medium)
    //Given an array of unsorted numbers, find all unique triplets in it that add up to zero.

    //Input: [-3, 0, 1, 2, -1, 1, -2]
    //Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
    //Explanation: There are four unique triplets whose sum is equal to zero.

    //Input: [-5, 2, -1, -2, 3]
    //Output: [[-5, 2, 3], [-2, -1, 3]]
    //Explanation: There are two unique triplets whose sum is equal to zero.


    //Approach
    //Input: [-5, 2, -1, -2, 3]
    //         |  |          |
    //        NO LEFT       RIGHT
    public static List<List<Integer>> searchAllTriplet(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);

        for(int i = 0; i < arr.length - 2; i++) {

            //if duplicate skip it
            if(i>0 && arr[i] == arr[i-1]) continue;

            searchPair(arr, -arr[i], i+1, result);
        }

        return result;
    }

    private static void searchPair(int[] arr, int targetSum, int left, List<List<Integer>> result) {
        int right = arr.length-1;

        while(left < right) {
            int currentSum = arr[left] + arr[right];
            if(currentSum == targetSum) {
                result.add(Arrays.asList(-targetSum, arr[left], arr[right]));
                left++;
                right--;
                //skip duplicate
                while(left < right && arr[left] == arr[left-1]) left++;
                while(left < right && arr[right] == arr[right+1]) right--;
            } else if(targetSum > currentSum) {
                left++;
            } else {
                right--;
            }
        }
    }
    
    //Triplet Sum Close to Target (medium)
    //Given an array of unsorted numbers and a target number, find a triplet in the array whose sum is as close to the target number as possible, return the sum of the triplet. If there are more than one such triplet, return the sum of the triplet with the smallest sum.

    //Input: [-2, 0, 1, 2], target=2
    //Output: 1
    //Explanation: The triplet [-2, 1, 2] has the closest sum to the target.

    //Input: [-3, -1, 1, 2], target=1
    //Output: 0
    //Explanation: The triplet [-3, 1, 2] has the closest sum to the target.

    public static int closetTargetSum(int[] arr, int target) {
        int result = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for(int i = 0; i < arr.length - 2; i++) {
            int right = arr.length-1;
            int left = i+1;

            while(left<right) {
                int targetDiff = target - arr[left] - arr[i] - arr[right];

                if(target == 0) {
                    return target - targetDiff;
                }

                result = Math.min(abs(target), abs(targetDiff));

                if(targetDiff > 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return target - result;
    }


    // Given a string s, return the longest palindromic
    //
    //Input: s = "babad"
    //Output: "bab"
    //Explanation: "aba" is also a valid answer.
    //Input: s = "cbbd"
    //Output: "bb"

    static int start = 0;
    static int maxLen = 0;
    public static String longestPalindrome(String s) {
        if(s.length()<2){
            return s;
        }

        for(int i=0;i<s.length()-1;i++){
            expand(s,i,i);
            expand(s,i,i+1);
        }

        return s.substring(start, start+ maxLen);
    }

    public static void expand(String str, int begin, int end)
    {
        while(begin>=0 && end<str.length() && str.charAt(begin)== str.charAt(end))
        {
            begin--;
            end++;
        }

        if(maxLen<(end-begin-1))
        {
            start = begin +1;
            maxLen = end-begin-1;
        }
    }

    //Palindromic Substrings
    //Given a string s, return the number of palindromic substrings in it.
    //
    //A string is a palindrome when it reads the same backward as forward.
    //
    //A substring is a contiguous sequence of characters within the string.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "abc"
    //Output: 3
    //Explanation: Three palindromic strings: "a", "b", "c".
    //Example 2:
    //
    //Input: s = "aaa"
    //Output: 6
    //Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
    static int count  = 0;
    public static int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }

        return count;
    }

    private static void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }

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
    public static int trap(int[] height) {

        //int l = 0, r = h.length - 1, lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE, ans = 0;
        //        while (l < r) {
        //        lmax = Math.max(lmax, h[l]);
        //        rmax = Math.max(rmax, h[r]);
        //        ans += (lmax < rmax) ? lmax - h[l++] : rmax - h[r--];
        //        }
        //        return ans;


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

    //You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
    //
    //Find two lines that together with the x-axis form a container, such that the container contains the most water.
    //
    //Return the maximum amount of water a container can store.

    //Input: height = [1,8,6,2,5,4,8,3,7]
    //Output: 49
    //Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
    //Example 2:
    //
    //Input: height = [1,1]
    //Output: 1

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, currentArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    //sort color 0 = red, 1= yellow, 2 = blue
    //Input: nums = [2,0,2,1,1,0]
    //Output: [0,0,1,1,2,2]
    //here 3 pointer used, one to track left for 0 , curr for 1 & right for 2
    public static void sortColors(int[] nums) {
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
}
