package HelloInterview.Backtracking;

//DESCRIPTION
//Given an integer n, write a function to return all well-formed (valid) expressions that can be made using n pairs of parentheses.
//
//Example 1:
//
//Input:
//
//n = 3
//Output:
//
//["((()))","(()())","(())()","()(())","()()()"]
//Example 2:
//
//Input:
//
//n = 2
//Output:
//
//["()()", "(())"]

import java.nio.file.LinkPermission;
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        int input = 3;
        List<String> result = new ArrayList<>();

        backtrack(input, result, "", 0,0);

        System.out.println(result);
    }

    static void backtrack(int input, List<String> result, String currentPath, int open , int close) {
        if(currentPath.length() == input*2) {
            result.add(currentPath);
            return;
        }

        if(open < input) {
            backtrack(input, result, currentPath + "(", open+1, close);
        }

        if(close < open) {
            backtrack(input, result, currentPath + ")", open, close+1);
        }
    }
}
