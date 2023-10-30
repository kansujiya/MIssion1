package DynamicProgramming;

import java.util.Arrays;

public class StaircaseAirtel {
    public static void main(String[] args) {
        StaircaseAirtel staircaseAirtel = new StaircaseAirtel();
        //Stairs problem
        System.out.println(staircaseAirtel.climbStairs(5));
        System.out.println(staircaseAirtel.findNoOfWaysDP(5, 2));

        //Rob house
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(robIterate(new int[]{2, 1, 1, 2}));

        //Rob house circular
        System.out.println(robIterateCircular(new int[]{2, 1, 1, 2}));

        //Decode ways
        System.out.println(decodeWays("123"));

        //Longest increasing subsequence
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));

    }

    //Given an integer array nums, return the length of the longest strictly increasing subsequence
    //Example 1:
    //
    //Input: nums = [10,9,2,5,3,7,101,18]
    //Output: 4
    //Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
    //Example 2:
    //
    //Input: nums = [0,1,0,3,2,3]
    //Output: 4
    //Example 3:
    //
    //Input: nums = [7,7,7,7,7,7,7]
    //Output: 1
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);

        for(int i = 1; i<nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(nums[i]>nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int longest = 0;
        for(int c: dp) {
            longest = Math.max(longest, c);
        }
        return longest;
    }

    //A message containing letters from A-Z can be encoded into numbers using the following mapping:
    //
    //'A' -> "1"
    //'B' -> "2"
    //...
    //'Z' -> "26"
    //To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
    //
    //"AAJF" with the grouping (1 1 10 6)
    //"KJF" with the grouping (11 10 6)
    //Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
    //
    //Given a string s containing only digits, return the number of ways to decode it.
    //
    //The test cases are generated so that the answer fits in a 32-bit integer.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "12"
    //Output: 2
    //Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
    //Example 2:
    //
    //Input: s = "226"
    //Output: 3
    //Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
    //Example 3:
    //
    //Input: s = "06"
    //Output: 0
    //Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").

    //dp1 -> dp2 -> dp // 123
    // 1     0      0
    // 1     1      3
    // 2     2      2
    // 2     3      1
    public static int decodeWays(String s) {
        int dp1=1, dp2=0, n=s.length();
        for(int i=n-1;i>=0;i--) {
            int dp = s.charAt(i) == '0' ? 0 : dp1;
            if(i<n-1 && (s.charAt(i)=='1'|| s.charAt(i)=='2' && s.charAt(i+1)<'7'))
                dp+=dp2;
            dp2=dp1;
            dp1=dp;
        }
        return dp1;
    }



    //You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
    //
    //Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
    //Input: nums = [1,2,3,1]
    //Output: 4
    //Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    //Total amount you can rob = 1 + 3 = 4.

    //Input: nums = [2,7,9,3,1]
    //Output: 12
    //Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
    //Total amount you can rob = 2 + 9 + 1 = 12.

    //Input: nums = [2,1,1,2]
    //Output: 4
    // prev1 prev2 num
    // 0    0     2 =>


    public static int rob(int[] nums) {

        return rob(nums, nums.length - 1);

    }
    public static int rob(int[] nums, int index) {

        if(index < 0) {
            return 0;
        }
        return  Math.max( rob(nums, index - 2) + nums[index], rob(nums, index - 1));
    }

    /* the order is: prev2, prev1, num  */
    //Input: nums = [2,1,1,2]
    //Output: 4
    // prev1 prev2 num
    // 0    0      -
    // 2,   0      2
    // 2,   2      1
    // 3,   2      1
    // 4,   3      2
    public static int robIterate(int[] nums) {
        //prev2 -> prev1 -> num
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }

    //with Circular house
    public static int robIterateCircular(int[] nums) {
        //prev2 -> prev1 -> num
        int n = nums.length;
        if(n==1) return nums[0];

        int [] nums1 = new int[nums.length-1];
        int [] nums2 = new int[nums.length-1];
        for(int i=1; i<nums.length; i++){
            nums1[i-1] = nums[i];
        }
        for(int i=0; i<nums.length-1; i++){
            nums2[i] = nums[i];
        }

        return Math.max( robIterate(nums1), robIterate(nums2) );
    }

    //You are given a staircase with N steps to climb and a set of steps S you can take at a time.
    // Write a function to return the number of possible ways to climb the staircase.
    //Ex-1:
    //Inputs: 
    //N= 1
    //S=[1,2,3,6,9]
    //Output- 1
    //
    //Ex-2:
    //Inputs: 
    //N= 2
    //S=[1,2]
    //Output- 2
    //
    //Ex-3:
    //Inputs: 
    //N= 3
    //S=[1,2] 
    //Output- 3
    //
    //
    //Ex-3:
    //Inputs: 
    //N= 10
    //S=[1,2,3] 
    //Output- ?

    //S = 5 // N = 3
    //findNoOfWays(5) = findNoOfWays(4) + findNoOfWays(3) + findNoOfWays(2) = 7 + 4 + 2 = 13
    //findNoOfWays(4) = findNoOfWays(3) + findNoOfWays(2) + findNoOfWays(1) = 4 + 2 + 1 = 7
    //findNoOfWays(3) = findNoOfWays(2) + findNoOfWays(1) + findNoOfWays(0) = 2+1+1= 4
    //findNoOfWays(2) = findNoOfWays(1) + findNoOfWays(0) = 1 + 1 = 2
    //findNoOfWays(1) = 1
    //findNoOfWays(0) = 1

    public static int climbStairs(int n) {
        if(n == 0 || n == 1)
            return 1;

        return climbStairs(n-1) + climbStairs(n-2);
    }

    public int findNoOfWaysDP(int S, int N) {
        if (S == 0 || S == 1) {
            return 1;
        }

        int[] dp = new int[S+1];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = N; i <= S; i++) {
            int val = 0;
            int end = i-N;
            for(int j = i-1; j >= end; j--) {
                val += dp[j];
            }
            dp[i] = val;
        }
        return dp[S];
    }
}
