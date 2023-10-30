package CyclicSort;

import java.util.ArrayList;
import java.util.List;

public class CyclicSort {
    public static void main(String[] args) {
        //1. Cyclic Sort
        int[] result = cyclicSort(new int[] {3,1,5,4,2});
        for(int i : result) {
            System.out.println(i + " ");
        }

        //2. Find missing number
        System.out.println("Missing number is " + findMissingNo(new int[] {4, 0 , 3 , 1}));

        //3. Find All missing number
        System.out.println(findAllMissingNumber(new int[] {2, 3, 1, 8, 2, 3, 5, 1}));
    }

    //We are given an array containing ‘n’ objects. Each object, when created, was assigned a unique number from 1 to ‘n’ based on their creation sequence. This means that the object with sequence number ‘3’ was created just before the object with sequence number ‘4’.
    //Input: [3, 1, 5, 4, 2]
    //Output: [1, 2, 3, 4, 5]
    //Input: [2, 6, 4, 3, 1, 5]
    //Output: [1, 2, 3, 4, 5, 6]
    public static int[] cyclicSort(int[] arr) {
        int n = arr.length;
        int i = 0;
        while(i<n) {
            int j = arr[i] - 1;

            if(arr[i] != arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                i++;
            }
        }

        return arr;
    }

    //Find the Missing Number (easy)
    //We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’. Since the array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing number.
    //Input: [4, 0, 3, 1]
    //Output: 2

    //Input: [8, 3, 5, 2, 4, 6, 0, 1]
    //Output: 7
    public static int findMissingNo(int[] arr) {

        int i = 0;
        while(i< arr.length) {
            int j = arr[i];

            if(arr[i] < arr.length && arr[i] != arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                i++;
            }
        }

        for(int k = 0; k < arr.length ; k++) {
            if(k != arr[k]) {
                return k;
            }
        }

        return -1;
    }

    //We are given an unsorted array containing numbers taken from the range 1 to ‘n’. The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.
    //Input: [2, 3, 1, 8, 2, 3, 5, 1]
    //Output: 4, 6, 7
    //Explanation: The array should have all numbers from 1 to 8, due to duplicates 4, 6, and 7 are missing.

    //Input: [2, 4, 1, 2]
    //Output: 3
    public static List<Integer> findAllMissingNumber(int[] arr) {
        int i = 0;

        while(i < arr.length) {
            int j = arr[i] - 1;
            if(arr[i] != arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                i++;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();

        for(int k = 0; k < arr.length; k++) {
            if(arr[k] != k+1) {
                missingNumbers.add(k+1);
            }
        }
        return missingNumbers;
    }
}
