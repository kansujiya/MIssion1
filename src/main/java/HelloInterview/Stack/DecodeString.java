package HelloInterview.Stack;

//Given an encoded string, write a function to return its decoded string that follows a specific encoding rule: k[encoded_string], where the encoded_string within the brackets is repeated exactly k times. Note that k is always a positive integer. The input string is well-formed without any extra spaces, and square brackets are properly matched. Also, assume that the original data doesn't contain digits other than the ones that specify the number of times to repeat the following encoded_string.
//
//Inputs:
//
//s = "3[a2[c]]"
//Output:
//
//"accaccacc"


import java.util.Stack;

public class DecodeString {

    public static void main(String[] args) {
        String input = "3[a2[c]]";

        Stack<String> stringStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();

        int currentNum = 0;
        String currString = "";

        for(char c : input.toCharArray()) {
            if(c == '[') {
                stringStack.push(currString);
                numStack.push(currentNum);
                //reset values
                currString = "";
                currentNum = 0;
            } else if(c == ']') {
                int num = numStack.pop();
                String s = stringStack.pop();
                // repeat current string with no of time
                currString = s + currString.repeat(num);
            } else if (Character.isDigit(c)) {
                //due to double-digit needs to multiply 10 and then add converted char in it
                currentNum = currentNum * 10 + (c - '0');
            } else {
                currString += c;
            }
        }

        System.out.println(currString);
    }
}
