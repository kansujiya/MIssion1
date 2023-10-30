package Map;

import java.util.Map;

public class FindNumber {
    //Find number of sub arrays that fit an exact criteria
    public int findNumber(int[] arr, int k) {
        Map<Integer, Integer> counts = new java.util.HashMap<>();
        counts.put(0, 1);
        int ans = 0;
        int curr = 0;

        for(int num : arr) {
            // Do logic to change curr
            ans += counts.getOrDefault(curr - k, 0);
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
        }

        return ans;
    }
}
