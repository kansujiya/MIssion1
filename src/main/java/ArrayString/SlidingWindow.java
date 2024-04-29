package ArrayString;

// Identify //Needs to take care in case of sub Array or substring with largest or shortest or maximum or minimum
// Fix size window -> Min/Max array of size k
// Variable size window -> largest/smallest sub Array of sum k


//To get sub array/sub string
//1. get window size (How to get window size = end - start + 1) till it matched
//2. Maintain window size when k == end - start + 1
//3. Increase start & end & update k if required


import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    public  static void main(String[] args){


        //1. Average of k size arr
        double[] result = subArrayAverage(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5);
        for (int i = 0; i< result.length; i++) {
            System.out.println(result[i]);
        }
        //2.Smallest Sub array with a given sum
        System.out.println(maxSum(new int[] {2, 1, 5, 1, 3, 2}, 3));
        //3.Smallest Sub array with a given sum (easy)
        System.out.println(smallestArr(7, new int[] {2, 1, 5, 2, 3, 2}));
        //4.Longest Substring with K Distinct Characters
        System.out.println(longestSubStringWithKDistinct(2, "araaci"));
        //5.Fruits into Baskets (medium)
        System.out.println(maxFruitInTwoBasket(new char[]{'A', 'B', 'C', 'B', 'B', 'C'}));

        //6. No-repeat Substring (hard)
        System.out.println(noRepeatedSubstring("aabccbb"));

        //7.
        System.out.println(characterReplacement("AABABBA", 1));
    }

    //You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
    //
    //Return the length of the longest substring containing the same letter you can get after performing the above operations.
    //
    //
    //
    //Example 1:
    //
    //Input: s = "ABAB", k = 2
    //Output: 4
    //Explanation: Replace the two 'A's with two 'B's or vice versa.
    //Example 2:
    //
    //Input: s = "AABABBA", k = 1
    //Output: 4
    //Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
    //The substring "BBBB" has the longest repeating letters, which is 4.
    //There may exists other ways to achieve this answer too.

    public static int characterReplacement(String s, int k) {
        // Initialising an empty array to store the count of the
        // characters in the given string s
        int[] arr = new int[26];
        int res = 0;
        int max = 0;

        // The left pointer for the sliding window is l AND r is the
        // right pointer
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            // Counting the number of each character in the string s
            arr[s.charAt(r) - 'A']++;

            // Checking the character with max number of occurrence
            max = Math.max(max, arr[s.charAt(r) - 'A']);

            // Now we check if our current window is valid or not
            if (r - l + 1 - max > k) {
                // this means the no. of replacements is more than
                // allowed (k)
                // Decrementing the count of the character which was
                // at l because it is no longer in the window
                arr[s.charAt(l) - 'A']--;
                l++;
            }

            // The max our window can be
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    //Sliding window
    public int slidingWindow(int[] arr, int k) {
        int ans = 0;
        int start = 0;
        int end = 0;

        while(end < arr.length) {
            ans += arr[end];
            if(arr[end-start+1] < k) {
                end++;
            } else if(arr[end-start+1] == k) {
                ans = Math.max(ans, arr[end-start+1]);
                ans -= arr[start];
                start++;
                end++;
            }
        }

        return ans;
    }


    //To get variable window size
    //Input: s = "abcabcbb"
    //Output: 3
    //Explanation: The answer is "abc", with the length of 3.
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int start = 0;
        int end = 0;
        int result = 0;

        Map<Character, Integer> map = new HashMap<>();

        while(end < len) {
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while(map.size() < (end-start+1)) {
                char cc = s.charAt(start);
                map.put(cc, map.get(cc)-1);
                if(map.get(cc) == 0) {
                    map.remove(cc);
                }
                start++;
            }

            result = Math.max(result, end-start+1);
            end++;
        }
        return result;
    }

    //Find average of given subArray of length k
    //Array: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5
    // Output: [2.2, 2.8, 2.4, 3.6, 2.8]

    public static double[] subArrayAverage(int[] arr, int k) {
        double[] result = new double[arr.length - k + 1];

        int start = 0;
        double sum = 0;
        for(int end = 0; end < arr.length; end++) {
            sum += arr[end];
            if(end >= k-1) {
                result[start] = sum/k;
                sum -= arr[start];
                start++;
            }
        }
        return result;
    }

    //Maximum Sum Subarray of Size K (easy)
    //Input: [2, 1, 5, 1, 3, 2], k=3
    //Output: 9
    //Explanation: Subarray with maximum sum is [5, 1, 3].

    public static int maxSum(int[] arr, int k) {
        int start = 0;
        int max = 0;
        int sum = 0;

        for(int end = 0; end < arr.length; end++) {
            sum += arr[end];

            if(end >= k-1) {
                max = Math.max(sum, max);
                sum -= arr[start];
                start++;
            }
        }

        return max;
    }

    //Smallest Sub array with a given sum (easy)
    //Input: [2, 1, 5, 2, 3, 2], S=7
    //Output: 2
    //Explanation: The smallest sub array with a sum greater than or equal to '7' is [5, 2].
    public static int smallestArr(int k, int[] arr) {
        int minWindowSize = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;

        for(int end = 0; end < arr.length; end++) {
            sum += arr[end];

            while(sum >= k) {
                minWindowSize = Math.min(minWindowSize, end-start+1);
                    sum -= arr[start];
                    start++;
            }
        }
        return minWindowSize;
    }

    //Longest Substring with K Distinct Characters
    //Input: String="araaci", K=2
    //Output: 4
    //Explanation: The longest substring with no more than '2' distinct characters is "araa".

    public static int longestSubStringWithKDistinct(int k, String str) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxLength = Integer.MIN_VALUE;


        for(int end = 0; end < str.length(); end++) {
            char c = str.charAt(end);

            map.put(c, map.getOrDefault(str.charAt(end), 0) + 1);
            while(map.size() > k) {
                char shrinkChar = str.charAt(start);
                map.put(shrinkChar, map.getOrDefault(str.charAt(start), 0) - 1);
                if(map.get(shrinkChar) == 0) {
                    map.remove(shrinkChar);
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start +1);
        }

        return maxLength;
    }


    //Fruits into Baskets (medium)
    //Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.
    //
    //You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
    //Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
    //Output: 5
    //Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
    //This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']

    //Similar Problem
    //The Longest Substring with at most 2 distinct characters
    public static int maxFruitInTwoBasket(char[] arr) {
         int maxFruit = Integer.MIN_VALUE;
         int start = 0;
         Map<Character, Integer> basket = new HashMap<>();
         for(int end = 0; end < arr.length; end++) {
             char fruit = arr[end];
             basket.put(fruit, basket.getOrDefault(fruit, 0) + 1);

             while(basket.size() > 2) {
                  char removingFruit = arr[start];
                  basket.put(removingFruit, basket.getOrDefault(removingFruit, 0) - 1);
                  if(basket.get(removingFruit) == 0) {
                      basket.remove(removingFruit);
                  }
                  start++;
             }
             maxFruit = Math.max(maxFruit, end-start+1);
         }

         return maxFruit;
    }

    //No-repeat Substring (hard)
    //Given a string, find the length of the longest substring which has no repeating characters.
    //Input: String="aabccbb"
    //Output: 3
    //Explanation: The longest substring without any repeating characters is "abc".
    //Input: String="abccde"
    //Output: 3
    //Explanation: Longest substrings without any repeating characters are "abc" & "cde".

    public static int noRepeatedSubstring(String str) {
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;

        for(int end = 0; end < str.length(); end++) {
            char c = str.charAt(end);

            if(map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }

            map.put(c, end);
            maxLength = Math.max(maxLength, end - start + 1);

        }
        return maxLength;
    }


}
