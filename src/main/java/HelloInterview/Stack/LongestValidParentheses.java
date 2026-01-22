package HelloInterview.Stack;

//Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring. A well-formed parentheses string is one that follows these rules:
//
//Open brackets must be closed by a matching pair in the correct order.
//For example, given the string "(()", the longest valid parentheses substring is "()", which has a length of 2. Another example is the string ")()())", where the longest valid parentheses substring is "()()", which has a length of 4.
//
//Example 1:
//Inputs:
//
//s = "())))"
//Output:
//
//2
//(Explanation: The longest valid parentheses substring is "()")
//
//Example 2:
//Inputs:
//
//s = "((()()())"
//Output:
//
//8
//(Explanation: The longest valid parentheses substring is "(()()())" with a length of 8)

import java.util.Stack;

public class LongestValidParentheses {

    public static void main(String[] args) {
        String input = "((()()())";

        Stack<Integer> indexStack = new Stack<>();
        int maxValid = 0;
        indexStack.push(-1);

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '(') {
                indexStack.push(i);
            } else {
                indexStack.pop();
                if (indexStack.isEmpty()) { //if first is closing bracket condition
                    indexStack.push(i);
                } else {
                    maxValid = Math.max(maxValid, i - indexStack.peek());
                }
            }
        }

        System.out.println("valid size is " + maxValid);
    }

}
