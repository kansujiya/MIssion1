package DynamicProgramming;

//Recursion approach
//- Base condition : Think the smallest valid input
//- Choice Diagram :
//- Make input small :
/*

    returnType function(input) {
        base condition;
        choice diagram;
    }

* */

public class Knapsack {

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        //1. k 1/0 nap sack problem with Recursion
        int[] profit = new int[] {1, 6, 10, 16};
        int[] weight = new int[] {1,2,3,5};
        System.out.println(ks.solveKNapSack(weight, profit, 7));

        //2.  Longest Common Subsequence
        String str1 = "abcde";
        String str2 = "ace";
        int m = str1.length();
        int n = str2.length();

        System.out.println(longestSubSequenceRecursion(str1, str2, m, n));
        System.out.println(longestSubSequence(str1, str2));
    }

    //Items: { Apple, Orange, Banana, Melon }
    //Weights: { 2, 3, 1, 4 }
    //Profits: { 4, 5, 3, 7 }
    //Knapsack capacity: 5
    //Steps
    //1. for each item i
    //2. create a new set which INCLUDE item 'i' if the total weight doesn't exceed the capacity and recursively process the remaining capacity & items
    //3. create a new set WITHOUT item 'i' and recursively process remaining items
    //4. Return the set from above 2 sets with higher profit
    public int solveKNapSack(int[] weight, int[] profit, int capacity) {
        return recursiveNapSack(weight, profit, capacity, 0);
    }

    public int  recursiveNapSack(int[] weight, int[] profit, int capacity, int index) {
        if(index >= profit.length || capacity <= 0){
            return 0;
        }
        int profit1 = 0;
        if(weight[index] <= capacity) {
            profit1 = profit[index] + recursiveNapSack(weight, profit, capacity-weight[index], index+1);
        }

        int profit2 = recursiveNapSack(weight, profit, capacity, index+1);

        return Math.max(profit1, profit2);
    }

    // Longest Common Subsequence
    //Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
    //
    //A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
    //
    //For example, "ace" is a subsequence of "abcde".
    //A common subsequence of two strings is a subsequence that is common to both strings.

    //Input: text1 = "abcde", text2 = "ace"
    //Output: 3
    //Explanation: The longest common subsequence is "ace" and its length is 3.

    public static int longestSubSequenceRecursion(String str1, String str2, int m, int n) {

        if(m==0 || n == 0)
            return 0;

        if(str1.charAt(m-1) == str2.charAt(n-1)) {
            return 1 + longestSubSequenceRecursion(str1, str2, m-1, n-1);
        } else {
            return Math.max(longestSubSequenceRecursion(str1, str2, m-1, n), longestSubSequenceRecursion(str1, str2, m, n-1));
        }
    }
    // Longest Common Subsequence
    //Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
    //
    //A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
    //
    //For example, "ace" is a subsequence of "abcde".
    //A common subsequence of two strings is a subsequence that is common to both strings.

    //Input: text1 = "abcde", text2 = "ace"
    //Output: 3
    //Explanation: The longest common subsequence is "ace" and its length is 3.

    //Input: text1 = "abc", text2 = "def"
    //Output: 0
    //Explanation: There is no such common subsequence, so the result is 0.

    public static int longestSubSequence(String str1, String str2) {

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m+1][n+1];

        for(int i = 0; i < m+1; i++) {
            for(int j = 0; j < n+1; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < m+1; i++) {
            for(int j = 1; j < n+1; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    //
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }

}
