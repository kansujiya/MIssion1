package HelloInterview.SlidingWIndow;

//Write a function to return the length of the longest substring in a provided string s where all characters in the substring are distinct.
//
//Example 1: Input:
//
//s = "eghghhgg"
//Output:
//
//3
//The longest substring without repeating characters is "egh" with length of 3.
//
//Example 2: Input:
//
//s = "substring"
//Output:
//
//8
//The answer is "ubstring" with length of 8.

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    public static void main(String[] args) {
           String str = "eghghhgg";
            int maxLen = Integer.MIN_VALUE;
           int start = 0;
           Map<Character, Integer> map = new HashMap<>();

           for(int end = 0; end < str.length(); end++) {
//               char c = str.charAt(end);
//
//               if(map.containsKey(c)) {
//                   start = end;
//                   map = new HashMap<>();
//               }
//               map.put(c, 1);
//               maxLen = Math.max(maxLen, end - start + 1);

               char c = str.charAt(end);

               map.put(c, map.getOrDefault(c, 0) + 1);

               while(map.get(c) > 1) {
                   char r = str.charAt(start);
                   map.put(r, map.get(r) - 1);
                   start++;
               }
               maxLen = Math.max(maxLen, end - start + 1);
           }
        System.out.println("max len " + maxLen);
    }
}
