package HelloInterview.DP;

//Your are given a string s containing only digits. Write a function to return the number of ways to decode using the following mapping:
//
//'1' -> "A"
//'2' -> "B"
//'3' -> "C"
//...
//'26' -> "Z"
//There may be multiple ways to decode a string. For example, "14" can be decoded as "AD" or "N".
//
//Input:
//
//s = 101
//Output:
//
//1
//Explanation: The only way to decode it is "JA". "01" cannot be decoded into "A" as "1" and "01" are different.


//Input: s = "226"
//Output: 3
//Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

import LowLevelDesigns.MessageQueue.Publisher;

public class DecodeWays {
    public static void main(String[] args) {
        String input = "226";

        System.out.println(decodeWay(input));
    }

    static int decodeWay(String s) {

        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {

            int digit = Character.getNumericValue(s.charAt(i - 1));
            if (digit != 0) {
                dp[i] += dp[i - 1];
            }

            digit = Integer.parseInt(s.substring(i - 2, i));
            if (digit >= 10 && digit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
