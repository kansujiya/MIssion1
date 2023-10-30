package BinarySearch;

public class GreedyBinarySearchMin {

    //Binary search: for greedy problems
    public class GreedyBinarySearchMax {
        public int greedyBinaryMin(int[] arr) {
            int left = 0; //MINIMUM_POSSIBLE_ANSWER
            int right = 0; //MAXIMUM_POSSIBLE_ANSWER
            while(left<=right){
                int mid = left + (right-left)/2;
                if(check(mid)) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            }
            return left;
        }

        public boolean check(int x) {
            // this function is implemented depending on the problem
            return true;
        }
    }

}
