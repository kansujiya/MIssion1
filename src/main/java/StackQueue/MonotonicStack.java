package StackQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MonotonicStack {


    static Map<Integer, Integer> freqMap = new HashMap<>();

    // setMap is to map frequency to the
    // element list with this frequency
    static Map<Integer, Stack<Integer> > setMap = new HashMap<>();

    // Variable which stores maximum frequency
    // of the stack element
    static int maxfreq = 0;


    public static void main(String[] args)
    {

        //.1 Get most repeated item from stack
        // Push elements to the stack
        push(4);
        push(6);
        push(7);
        push(6);
        push(8);
        push(8);

        // Pop elements
        System.out.println(pop());
        System.out.println(pop());

        //2. Sort stack using another tempo stack
        Stack<Integer> input = new Stack<Integer>();
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);
        sortStack(input);
    }


    public static Stack<Integer> sortStack(Stack<Integer> input) {
        Stack<Integer> tmpStack = new Stack<Integer>();
        while(!input.isEmpty())
        {
            // pop out the first element
            int tmp = input.pop();

            // while temporary stack is not empty and
            // top of stack is lesser than temp
            while(!tmpStack.isEmpty() && tmpStack.peek()
                    < tmp)
            {
                // pop from temporary stack and
                // push it to the input stack
                input.push(tmpStack.pop());
            }

            // push temp in temporary of stack
            tmpStack.push(tmp);
        }
        return tmpStack;
    }

    // Function to insert x in the stack
    public static void push(int x)
    {
        // Frequency of x
        int freq = freqMap.getOrDefault(x, 0) + 1;

        // Mapping of x with its frequency
        freqMap.put(x, freq);

        // Update maxfreq variable
        if (freq > maxfreq)
            maxfreq = freq;

        // Map element to its frequency list
        // If given frequency list doesn't exist
        // make a new list of the required frequency
        //setMap.computeIfAbsent(freq, z -> new Stack()).push(x);
        if (!setMap.containsKey(freq)) {
            Stack stack = new Stack();
            stack.push(x);
            setMap.put(freq, stack);
        } else {
            setMap.get(freq).push(x);
        }

    }

    // Function to remove maximum frequency element
    public static int pop()
    {

        // Remove element from setMap
        // from maximum frequency list
        int top = setMap.get(maxfreq).pop();

        // Decrement the frequency of the popped element
        freqMap.put(top, freqMap.get(top) - 1);

        // If whole list is popped
        // then decrement the maxfreq
        if (setMap.get(maxfreq).size() == 0)
            maxfreq--;
        return top;
    }

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
