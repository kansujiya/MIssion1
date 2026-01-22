package HelloInterview.DP;

//You can climb a staircase by taking either 1 or 2 steps at a time. If there are n steps in the staircase, how many distinct ways are there to climb to the top step?
//Example
//n = 3
//Output: 3
//1st way: 1 step + 1 step + 1 step
//2nd way: 1 step + 2 steps
//3rd way: 2 steps + 1 step

import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {

    public static void main(String[] args) {
        int n = 5;

        //1. Using recursion
        int result = climStairsRecursion(n);
        System.out.println("NO of ways to climb the stairs : " + result);

        //2. using memorization
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println("NO of ways to climb the stairs : " + climStairsRecursion(n, map));

        //3. using bottom Up --- dp(0) + dp(1)
        System.out.println("NO of ways to climb the stairs : " + climStairsRecursionDP(n));
    }


    static int climStairsRecursion(int n) {
        if(n <= 1) {
            return 1;
        }

        return climStairsRecursion(n-1) + climStairsRecursion(n-2);
    }

    static int climStairsRecursion(int n, Map<Integer, Integer> map) {
        if(n <= 1) {
            return 1;
        }
        if(map.containsKey(n)) {
            return map.get(n);
        }
        map.put(n, climStairsRecursion(n-1, map) + climStairsRecursion(n-2, map));
        return map.get(n);
    }


    static int climStairsRecursionDP(int n) {
        int dp[] = new int[n+1];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n ; i++) {
         dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}

