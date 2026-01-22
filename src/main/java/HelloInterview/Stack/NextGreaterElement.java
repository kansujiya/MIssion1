package HelloInterview.Stack;

//DESCRIPTION
//Given an array of integers, find the next greater element for each element in the array. The next greater element of an element x is the first element to the right of x that is greater than x. If there is no such element, then the next greater element is -1.
//Example
//Input: [2, 1, 3, 2, 4, 3]
//Output: [3, 3, 4, 4, -1, -1]


import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {

    public static void main(String[] args) {

        int[] input = new int[]{2, 1, 3, 2, 4, 3};

        int[] result = new int[input.length];
        Arrays.fill(result, -1);

        Stack<Integer> increaseMonotonicStack = new Stack<>();

        for(int i = 0; i < input.length; i++) {
            while(!increaseMonotonicStack.isEmpty() && input[i] > input[increaseMonotonicStack.peek()]) {
                int index = increaseMonotonicStack.pop();
                result[index] = input[i];
            }
            increaseMonotonicStack.push(i);
        }

        System.out.println(Arrays.toString(result));

    }
}
