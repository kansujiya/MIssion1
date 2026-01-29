package HelloInterview.Backtracking;

//The solution-space tree will look different for each backtracking problem, but one common type is a binary tree, where each node in the tree represents a "choose" or "don't choose" decision at that level.
//This tree can be used to generate all possible subsets of a list of integers, which we breakdown below:
//DESCRIPTION
//Given a set of distinct integers, nums, return all possible subsets (the power set), without duplicates.
//Example:
//Input: nums = [1,2,3]
//Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//The solution-space tree for this problem is a binary tree. Each node in the binary tree represents a different subset of the input.


import java.util.ArrayList;
import java.util.List;

public class AllSubSets {

    public static void main(String[] args) {
        int[] num = new int[] {1,2,3};
        List<List<Integer>> result = new ArrayList<>();
        backtrack(num, result, 0, new ArrayList<>());
        System.out.println(result);
    }

    static void backtrack(int[] input, List<List<Integer>> result, int index, List<Integer> path) {
        if(index == input.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        //include num[index]
        path.add(input[index]);
        backtrack(input, result, index+1, path);

        //exclude num[index]
        path.remove(path.size()-1);
        backtrack(input, result, index+1, path);
    }

    //[]
    //----------------1
    //[1]
    //[]
    //----------------2
    //[1,2]
    //[1]
    //[2]
    //[]
    //----------------------3
    //[1,2,3]
    //[1,2]
    //[1,3]
    //[1]
    //[2, 3]
    //[2]
    //[3]
    //[]
    //-------------------4
}
