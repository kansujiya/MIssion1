package RandomAskedQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class Nayaka {
    public static void main(String[] args) {
        //0011011
        //1010111
        //1100000
        //1. Add two binary strings
        System.out.println(addBinary("011011", "1010111"));

        //2. Given no of friend with one position k . Find the winner after every kth position player out from circle
        System.out.println(winnerFromCircle(5,2));

        //3. Minimum no of platform
        int[] arrival = new int[]{900, 940, 950, 1100, 1500, 1800};
        int[] dep = new int[]{910, 1200, 1120, 1130, 1900, 2000};
        findPlatform(arrival, dep, arrival.length);
    }

    public static String addBinary(String x, String y) {
        StringBuilder result = new StringBuilder();

        int i = x.length() -1;
        int j = y.length() - 1;

        int carry = 0;

        while(i >= 0 || j >= 0) {
            int sum = carry;

            if(i>=0) {
                // - '0' way to convert char to int else Integer.parseInt(String.valueOf(x.charAt(0))) like this
                sum += x.charAt(i) - '0';
            }
            if(j>=0) {
                sum += y.charAt(j) - '0';
            }

            if(i == 0 || j == 1) {
                result.append(1);
                carry = 0;
            } else if(sum ==2) {
                result.append(0);
                carry = 1;
            } else {
                result.append(0);
                carry = 1;
            }

            i--;
            j--;

        }

        if(carry == 1) {
            result.append("1");
        }
        return result.reverse().toString();
    }

    public static int winnerFromCircle(int n, int k) {

        Queue<Integer> arr = new LinkedList<>();

        for(int i = 1; i<=n ; i++) {
            arr.add(i);
        }
        //[1,2,3,4,5] 2 => [2,3,4,5,1] => [3,4,5,1] => [4,5,1,3] => [5,1,3] => [1,3,5] => [3,5] => [5,3] => 3
        while(arr.size() != 1){
            for(int i = k-1; i > 0; i--) {
                arr.add(arr.poll());
            }
            arr.poll();
        }

        return arr.poll();
    }

    public static int findPlatform(int arr[], int dep[],
                                   int n)
    {

        // plat_needed indicates number of platforms
        // needed at a time
        int plat_needed = 1, result = 1;

        // run a nested  loop to find overlap
        for (int i = 0; i < n; i++) {
            // minimum platform
            plat_needed = 1;

            for (int j = 0; j < n; j++) {
                if (i != j)
                    // check for overlap
                    if (arr[i] >= arr[j]
                            && dep[j] >= arr[i])
                        plat_needed++;
            }

            // update result
            result = Math.max(result, plat_needed);
        }

        return result;
    }


}
