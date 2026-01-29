package HelloInterview.DP;

//Write a function that, given an integer n, returns an array dp of size n + 1, where dp[i] stores the count of '1' bits in the binary form of i.
//
//Input:
//
//n = 6
//Output:
//
//[0,1,1,2,1,2,2]
//Explanation:
//
//0 --> 0
//1 --> 1
//2 --> 10
//3 --> 11
//4 --> 100
//5 --> 101
//6 --> 110

import java.util.Arrays;

public class CountingBits {
    public static void main(String[] args) {
        int input = 6;
        int[] result = new int[input+1];
        countBit(input, result);
        System.out.println(Arrays.toString(result));
    }

    static void countBit(int input, int[] result) {
        for(int i =1; i <= input; i++) {
            //The key to this problem lies in the fact that any binary number can be broken down into two parts: the least-significant (rightmost bit), and the rest of the bits. The rest of the bits can be expressed as the binary number divided by 2
            //For example:
            //4 in binary = 100
            //rightmost bit = 0
            //rest of bits = 10, which is also (4 // 2) = 2 in binary.
            //When the number is odd,
            //5 in binary = 101
            //rightmost bit = 1
            //rest of bits = 10, which is also (5 // 2) = 2 in binary.
            //0: 0000 => 0
            //1: 0001 => result[1/2] + (1%2) = 1
            //2: 0010 => result[2/2] + (2%2) =
            //3: 0011
            //4: 0100
            //5: 0101
            //6: 0110
            //7: 0111
            //8: 1000
            //9: 1001
            //10: 1010
            System.out.println(i/2);
            System.out.println(result[i/2]);
            System.out.println(i%2);
            result[i] = result[i/2] + (i%2); //Half part + right most bit will be either 1 or 0 hence to add 1/2
        }
    }
}
