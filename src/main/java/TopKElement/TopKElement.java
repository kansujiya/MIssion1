package TopKElement;

import java.util.*;

public class TopKElement {

    public static void main(String[] args) {
        //Tok K numbers
        System.out.println(findKTopElement(new int[] {3, 1, 5, 12, 2, 11}, 3));

        //Connect Ropes (easy)
        System.out.println(minimumCostToConnectRope(new int[] {1, 3, 11, 5}));

        //Top 'K' Frequent Numbers (medium)
        System.out.println(findTopKFrequentElement(new int[] {5, 12, 11, 3, 11}, 2));

        //Frequency Sort (medium)
        System.out.println(sortCharByFrequency("bbbaac"));

    }

    //Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
    //Input: [3, 1, 5, 12, 2, 11], K = 3
    //Output: [5, 12, 11]

    //Input: [5, 12, 11, -1, 12], K = 3
    //Output: [12, 11, 12]

    public static List<Integer> findKTopElement(int[] arr, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int n = arr.length-1;;

        while(n >= 0) {
            queue.add(arr[n]);

            if(queue.size() > k) {
                queue.remove();
            }
            n--;
        }

        return new ArrayList<>(queue);
    }

    //Connect Ropes (easy)
    //Given ‘N’ ropes with different lengths, we need to connect these ropes into one big rope with minimum cost. The cost of connecting two ropes is equal to the sum of their lengths.
    //Input: [1, 3, 11, 5]
    //Output: 33
    //Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20). So the total cost is 33 (4+9+20)

    //Input: [1, 3, 11, 5]
    //Output: 33
    //Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20). So the total cost is 33 (4+9+20)

    //Input: [1, 3, 11, 5]
    //Output: 33
    //Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20). So the total cost is 33 (4+9+20)

    public static int minimumCostToConnectRope(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i =0; i< arr.length; i++) {
            queue.add(arr[i]);
        }
        int temp = 0;
        int result = 0;

        while(queue.size() > 1) {
            temp = queue.poll() + queue.poll();
            result += temp;

            //Keep adding 2 pole value again to min heap
            queue.add(temp);
        }

        return result;
    }

    //Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.
    //Input: [1, 3, 5, 12, 11, 12, 11], K = 2
    //Output: [12, 11]
    //Explanation: Both '11' and '12' appeared twice.

    //Input: [5, 12, 11, 3, 11], K = 2
    //Output: [11, 5] or [11, 12] or [11, 3]
    //Explanation: Only '11' appeared twice, all other numbers appeared once.

    public static List<Integer> findTopKFrequentElement(int[] arr, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for(int n : arr) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<Map.Entry<Integer, Integer>>( new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> v1, Map.Entry<Integer, Integer> v2) {
                return v1.getValue() - v2.getValue();
            }
        });

        for(Map.Entry<Integer, Integer> keySet : frequencyMap.entrySet()) {

            heap.add(keySet);

            if(heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();

        while(!heap.isEmpty()) {
            result.add(heap.poll().getKey());
        }

        return result;
    }

    //Given a string, sort it based on the decreasing frequency of its characters.
    //Input: "Programming"
    //Output: "rrggmmPiano"
    //Explanation: 'r', 'g', and 'm' appeared twice, so they need to appear before any other character.

    //Input: "abcbab"
    //Output: "bbbaac"
    //Explanation: 'b' appeared three times, 'a' appeared twice, and 'c' appeared only once.

    public static String sortCharByFrequency(String str) {
        Map<Character, String> map = new HashMap<>();

        for(Character c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, "") + c);
        }

        PriorityQueue<Map.Entry<Character, String>> minHeap = new PriorityQueue<Map.Entry<Character, String>>( new Comparator<Map.Entry<Character, String>>() {
            @Override
            public int compare(Map.Entry<Character, String> e1, Map.Entry<Character, String> e2) {
                return e2.getValue().length() - e1.getValue().length();
            }
        });

        for(Map.Entry<Character, String> entrySet : map.entrySet()) {
            minHeap.add(entrySet);
        }

        String result = "";

        while (!minHeap.isEmpty()) {
            result += minHeap.poll().getValue();
        }

        return result;
    }
}
