package HelloInterview.Backtracking;

//Given an array of distinct integers candidates and a target integer target, generate all unique combinations of candidates which sum to target. The combinations may be returned in any order, and the same number may be chosen from candidates an unlimited number of times.
//
//Constraints:
//
//All values in candidates are positive integers.
//1 <= candidates.length <= 30
//2 <= candidates[i] <= 40
//All elements of candidates are distinct.
//1 <= target <= 40
//Input:
//
//candidates = [2,3,6,7]
//target = 7
//Output:
//
//[[2,2,3],[7]]
//Explanation:
//
//2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times. 7 is a candidate, and 7 = 7. These are the only two combinations.

import SubSets.SubSets;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        int[] input = new int[] {2,3,6,7};
        int target = 7;
        List<List<Integer>> result = new ArrayList<>();

        backtrack(input, target, 0, new ArrayList<>(), result);

        System.out.println(result);
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> combo, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combo));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int curr = candidates[i];
            if (candidates[i] > target) {
                return;
            }
            combo.add(curr);
            backtrack(candidates, target - curr, i, combo, result);
            combo.remove(combo.size() - 1);
        }
    }

}
