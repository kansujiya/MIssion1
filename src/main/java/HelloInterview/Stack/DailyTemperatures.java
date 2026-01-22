package HelloInterview.Stack;

//Given an integer array temps representing daily temperatures, write a function to calculate the number of days one has to wait for a warmer temperature after each given day. The function should return an array answer where answer[i] represents the wait time for a warmer day after the ith day. If no warmer day is expected in the future, set answer[i] to 0.
//
//Inputs:
//
//temps = [65, 70, 68, 60, 55, 75, 80, 74]
//Output:
//
//[1,4,3,2,1,1,0,0]


import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] input = new int[] {65, 70, 68, 60, 55, 75, 80, 74};
        Stack<Integer> decreseStack = new Stack<>();
        int[] result = new int[input.length];

        for(int i = 0; i < input.length; i++) {
            while(!decreseStack.isEmpty() && input[i] > input[decreseStack.peek()]) {
                int topStackIndex = decreseStack.pop();
                result[topStackIndex] = i - topStackIndex;
            }
            decreseStack.push(i);
        }

        System.out.println(Arrays.toString(result));
    }
}
