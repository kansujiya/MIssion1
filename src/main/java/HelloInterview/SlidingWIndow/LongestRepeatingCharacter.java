package HelloInterview.SlidingWIndow;

import java.util.HashMap;
import java.util.Map;

//DESCRIPTION (inspired by Leetcode.com)
//Write a function to find the length of the longest substring containing the same letter in a given string s, after performing at most k operations in which you can choose any character of the string and change it to any other uppercase English letter.
//
//Input:
//
//s = "BBABCCDD"
//k = 2
//Output:
//
//5
//Explanation: Replace the first 'A' and 'C' with 'B' to form "BBBBBCDD". The longest substring with identical letters is "BBBBB", which has a length of 5.
public class LongestRepeatingCharacter {
    public static void main(String[] args) {
        int maxLen = Integer.MIN_VALUE;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        String s = "BBABCCDD";
        int maxFreq = 0;

        for(int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            map.put(c, map.getOrDefault(c,0) + 1);

            maxFreq = Math.max(map.get(c), maxFreq);

            while((end - start +1)-maxFreq > 2) {
                char r = s.charAt(start);
                map.put(r, map.get(r) - 1);
                start++;
            }

            maxLen = Math.max(maxLen, end-start+1);
        }
        System.out.println("Max size is " + maxLen);

    }
}
