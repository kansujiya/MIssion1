package StackQueue;

import java.util.Stack;

public class MonotonicStack {

    //Monotonic increasing stack : Same logic can be applied to maintain monotonic queue
    public int monotonicQueue(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for(int num: arr) {
            //for monotonic decreasing, just flip the > to <
            while(!stack.empty() && stack.peek() > num) {
                //do logic
                stack.pop();
            }
            stack.push(num);
        }

        return ans;
    }

}
