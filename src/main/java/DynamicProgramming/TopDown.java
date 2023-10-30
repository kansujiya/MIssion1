package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopDown {

    Map<Integer, Integer> memoization = new HashMap<>();

    public int TopDown(int[] arr) {
        int state = 0;
        return dp(state, arr);
    }

    public int dp(int state, int[] arr) {
        if(true) { //base condition
            return 0;
        }

        if(memoization.containsKey(state)) {
            return memoization.get(state);
        }

        int ans = dp(state, arr); //Recurrence relation
        memoization.put(state, ans);
        return ans;
    }

    //Given an integer numRows, return the first numRows of Pascal's triangle.
    //Input: numRows = 5
    //Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();


        // Base case; first row is always [1].
        result.add(new ArrayList<>());
        result.get(0).add(1);

        int i = 1;

        while(i < numRows) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = result.get(i-1);

            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for(int j = 1; j < i ; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            row.add(1);

            result.add(row);

            i++;
        }

        return result;
    }
}

