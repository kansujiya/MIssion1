package HelloInterview.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//Given an input string s consisting solely of the characters '(', ')', '{', '}', '[' and ']', determine whether s is a valid string. A string is considered valid if every opening bracket is closed by a matching type of bracket and in the correct order, and every closing bracket has a corresponding opening bracket of the same type.
//
//Example 1:
//
//Inputs:
//
//s = "(){({})}"
//Output:
//
//True
//Example 2:
//
//Inputs:
//
//s = "(){({}})"
//Output:
//
//False
public class ValidParentheses {

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', ']');

        String input = "(){({})}";//"(){({}})";


        for(char c : input.toCharArray()) {
            if(map.containsKey(c)) {
                if(stack.isEmpty() || stack.peek() != map.get(c)) {
                    System.out.println("False due to mismatch");
                    return;
                }
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }

        System.out.print(stack.isEmpty());
    }

}
