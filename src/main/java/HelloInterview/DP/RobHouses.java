package HelloInterview.DP;

//You're a treasure hunter in a neighborhood where houses are arranged in a row, and each house contains a different amount of treasure. Your goal is to collect as much treasure as possible, but there's a catch: if you collect treasure from two adjacent houses, it triggers a neighborhood-wide alert, ending your hunt immediately.
//Given an array treasure of non-negative integers, where each integer represents the amount of treasure in a house, write a function to return the maximum amount of treasure you can collect without triggering any alarms.
//Example 1:
//Input: treasure = : [3, 1, 4, 1, 5]
//Best Haul: 12
//Explanation: Collect from houses 0, 2, and 4 for a total of 3 + 4 + 5 = 12.

import java.util.Map;

public class RobHouses {

    public static void main(String[] args) {
        int[] input = new int[] {3, 1, 4, 1, 5};

        System.out.println("Max profit is : " + maxTreasure(input, input.length));
    }

    static int maxTreasure(int[] arr, int i) {
        if(i == 0 ) {
            return 0;
        }

        if(i == 1) {
            return arr[0];
        }

        int skip = maxTreasure(arr, i -1);
        int take = maxTreasure(arr, i-2) + arr[i-1];

        return Math.max(skip, take);
        //rob(5)
        // ├─ skip = rob(4)
        // │   ├─ skip = rob(3)
        // │   │   ├─ skip = rob(2)
        // │   │   │   ├─ skip = rob(1) = 2
        // │   │   │   └─ take = rob(0) + 7 = 7
        // │   │   │   → max(2, 7) = 7
        // │   │   ├─ take = rob(1) + 9 = 2 + 9 = 11
        // │   │   → max(7, 11) = 11
        // │   ├─ take = rob(2) + 3 = 7 + 3 = 10
        // │   → max(11, 10) = 11
        // ├─ take = rob(3) + 1 = 11 + 1 = 12
        // → max(11, 12) = 12
    }

}
