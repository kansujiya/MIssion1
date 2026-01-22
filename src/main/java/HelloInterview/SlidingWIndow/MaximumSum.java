package HelloInterview.SlidingWIndow;

//Given an array of integers nums and an integer k, find the maximum sum of any contiguous subarray of size k.
//Example:
//Input: nums = [2, 1, 5, 1, 3, 2], k = 3
//Output: 9
//Explanation: The subarray with the maximum sum is [5, 1, 3] with a sum of 9.

public class MaximumSum {
    public static void main(String[] args) {
        int[] num = new int[]{2, 1, 5, 1, 3, 2};
        int k = 3;

        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        int tempSum = 0;

        for(int end = 0; end<num.length; end++) {
            tempSum = tempSum+num[end];

            if(end - start + 1 == k) {
                maxSum = Math.max(tempSum, maxSum);
                tempSum = tempSum - num[start];
                start++;
            }


        }

        System.out.println("max sum " + maxSum);
    }
}
