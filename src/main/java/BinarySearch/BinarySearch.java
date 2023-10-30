package BinarySearch;

public class BinarySearch {

    public static void main(String[] args) {
        //1.Order-agnostic Binary Search (easy)
        System.out.println(orderBinarySearch(new int[] {4,6,10}, 10));
        System.out.println(orderBinarySearch(new int[] {1,2,3,4,5,6,7}, 5));

        //2. Ceiling of a Number (medium)
        System.out.println(searchCeilingOfANumber(new int[]{1,3,8,10,15}, 12));

        //3. Next letter
        System.out.println(searchNextLetter(new char[]{'a','c','f','h'}, 'b'));
        System.out.println(searchNextLetter(new char[]{'a','c','f','h'}, 'm'));

        //4. find Range
        int[] result = findRange(new int[]{4, 6, 6, 6, 9}, 6);
        System.out.println("[" + result[0] + "," + result[1] + "]");

        //5. Minimum Difference Element (medium)
        int result1 = findMinSearchElement(new int[]{1, 3, 8, 10, 15}, 12);
        System.out.println(result1);
    }


    //Order-agnostic Binary Search (easy)
    //Given a sorted array of numbers, find if a given number ‘key’ is present in the array. Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order. You should assume that the array can have duplicates.
    //
    //Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.

    //Input: [4, 6, 10], key = 10
    //Output: 2

    //Input: [1, 2, 3, 4, 5, 6, 7], key = 5
    //Output: 4

    //First, we will find the middle of start and end. An easy way to find the middle would be:
    //�
    //�
    //�
    //�
    //�
    //�
    //=
    //(
    //�
    //�
    //�
    //�
    //�
    //+
    //�
    //�
    //�
    //)
    ///
    //2
    //middle=(start+end)/2. For Java and C++, this equation will work for most cases, but when start or end is large, this equation will give us the wrong result due to integer overflow. Imagine that start is equal to the maximum range of an integer (e.g. for Java: int start = Integer.MAX_VALUE). Now adding anything to start will result in an integer overflow. Since we need to add both the numbers first to evaluate our equation, an overflow might occur.
    public static int orderBinarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length -1;
        boolean isAsc = arr[start] < arr[end];

        while(start <= end) {
            int mid = start + (end-start)/2;

            if(key == arr[mid]) {
                return mid;
            }

            if(isAsc) {
                if(key < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if(key > arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    //Ceiling of a Number (medium)
    //Given an array of numbers sorted in ascending order, find the ceiling of a given number ‘key’. The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key’.
    //
    //Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling return -1.
    //Input: [4, 6, 10], key = 6
    //Output: 1
    //Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.
    //Example 2:
    //Input: [1, 3, 8, 10, 15], key = 12
    //Output: 4
    //Explanation: The smallest number greater than or equal to '12' is '15' having index '4'.

    //Ceiling - grater value - Return start
    //floor - smaller value - Return end
    public static int searchCeilingOfANumber(int[] arr, int key) {
        //if key is greater than last element then return -1
        if(key > arr[arr.length-1]) return -1;

        int start = 0;
        int end = arr.length -1;

        while(start <= end) {
            int mid = start + (end-start)/2;

            if(arr[mid] < key) {
                start = mid+1;
            } else if(arr[mid] > key) {
                end = mid-1;
            } else {
                return mid;
            }
        }

        return start;
    }

    //Next Letter (medium)
    //Given an array of lowercase letters sorted in ascending order, find the smallest letter in the given array greater than a given ‘key’.
    //
    //Assume the given array is a circular list, which means that the last letter is assumed to be connected with the first letter. This also means that the smallest letter in the given array is greater than the last letter of the array and is also the first letter of the array.
    //Example 1:
    //
    //Example 2:
    //
    //Input: ['a', 'c', 'f', 'h'], key = 'b'
    //Output: 'c'
    //Explanation: The smallest letter greater than 'b' is 'c'.

    //Input: ['a', 'c', 'f', 'h'], key = 'm'
    //Output: 'a'
    //Explanation: As the array is assumed to be circular, the smallest letter greater than 'm' is 'a'.

    public static char searchNextLetter(char[] letter, char key) {
        int n = letter.length;
        if(key < letter[0] || key > letter[letter.length-1]) {
            return letter[0];
        }

        int start = 0;
        int end = letter.length-1;

        while(start<=end) {
            int mid = start + (end-start)/2;

            if(key < letter[end]) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        //In the end, instead of returning the element pointed out by start, we have to return the letter pointed out by start % array_length. This is needed because of point 2 discussed above. Imagine that the last letter of the array is equal to the ‘key’. In that case, we have to return the first letter of the input array.
        return letter[start%n];
    }

    //Number Range (medium)
    //Given an array of numbers sorted in ascending order, find the range of a given number ‘key’. The range of the ‘key’ will be the first and last position of the ‘key’ in the array.
    //Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1].

    //Input: [4, 6, 6, 6, 9], key = 6
    //Output: [1, 3]

    //Input: [1, 3, 8, 10, 15], key = 10
    //Output: [3, 3]
    public static int[] findRange(int[] arr, int key) {
        int[] result = new int[] {-1, -1};
        result[0] = findIndex(arr, key, false);
        if(result[0] != -1) {
            result[1] = findIndex(arr,key, true);
        }
        return result;
    }

    public static int findIndex(int[] arr, int key, boolean findMaxIndex) {
        int keyIndex = -1;
        int start = 0;
        int end = arr.length-1;

        while(start<=end) {
            int mid = start + (end-start)/2;
            if(key < arr[mid]) {
                end = mid-1;
            } else if(key > arr[mid]) {
                start = mid + 1;
            } else { //key = arr[mid]
                keyIndex = mid;
                if(findMaxIndex) {
                    start = start+1; //keep increasing start till matching key to get end index
                } else {
                    end = mid - 1; //keep decreasing end till matching key to get start index
                }
            }
        }
        return keyIndex;
    }

    public int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while(left <= right) {
            int mid = left + (right - left)/2;
            if(mid == target) {
                //do something
                return mid;
            }
            if(arr[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid+1;
            }
        }
        //left is the insertion point
        return left;
    }

    //Given an array of numbers sorted in ascending order, find the element in the array that has the minimum difference with the given ‘key’.
    //Input: [4, 6, 10], key = 7
    //Output: 6
    //Explanation: The difference between the key '7' and '6' is minimum than any other number in the array

    //Input: [1, 3, 8, 10, 15], key = 12
    //Output: 10
    public static int findMinSearchElement(int[] arr, int key) {

        if(key < arr[0])
            return arr[0];
        if(key > arr[arr.length - 1])
            return arr[arr.length - 1];

        int start = 0;
        int end = arr.length -1;

        while(start <= end) {
            int mid = start + (end - start)/2;

            if(key < arr[mid]) {
                end = mid-1;
            } else if(key > arr[mid]) {
                start = mid+1;
            } else {
                return arr[mid];
            }
        }

        if((arr[start] - key) < (arr[end] - key))
          return arr[start];

        return arr[end];
    }

}
