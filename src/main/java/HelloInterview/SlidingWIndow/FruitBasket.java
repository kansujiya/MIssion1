package HelloInterview.SlidingWIndow;

import java.util.HashMap;
import java.util.Map;

//Write a function to calculate the maximum number of fruits you can collect from an integer array fruits, where each element represents a type of fruit. You can start collecting fruits from any position in the array, but you must stop once you encounter a third distinct type of fruit. The goal is to find the longest subarray where at most two different types of fruits are collected.
//Example:
//Input: fruits = [3, 3, 2, 1, 2, 1, 0]
//Output: 4
//Explanation: We can pick up 4 fruit from the subarray [2, 1, 2, 1]
public class FruitBasket {

    public static void main(String[] args) {

        int[] arr = new int[]{3,3,2,1,2,1,0};
        int start = 0;
        int maxLength = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();

        for(int end = 0; end < arr.length; end++) {

            map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);

            while(map.size() > 2) {
                map.put(arr[start], map.get(arr[start]) - 1);
                if(map.get(arr[start]) == 0) {
                    map.remove(arr[start]);
                }
                start++;
            }

            maxLength = Math.max(maxLength, end-start+1);

        }
        System.out.println("Max Fruit " + maxLength);
    }

}
