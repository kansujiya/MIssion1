package BinarySearch;

public class BinarySearchDuplicateLeft {
    //Binary search: duplicate elements, left-most insertion point
    public int bSearchDuplicateLeft(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left<right) {
            int mid = left + (right-left)/2;

            if(arr[mid] >= target) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }

}

//left 0
//right 10
//target 6
//
//mid = 5
//
//left 6
//right 10
//mid = 6+2 = 8

