import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello from Mission1");
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(15);
        list.add(14);
        list.add(16);
        list.add(21);


        List<List<Integer>> list1 = new ArrayList<List<Integer>>();
        List<Integer> list2 = new ArrayList<>();
        list2.add(10);
        list2.add(17);

        List<Integer> list3 = new ArrayList<>();
        list3.add(13);
        list3.add(15);

        List<Integer> list4 = new ArrayList<>();
        list4.add(13);
        list4.add(17);

        list1.add(list2);
        list1.add(list3);
        list1.add(list4);


        //countSignals(list, list1);
        //firstUniqChar("dddccdbba");
        isAnagram("anagram", "nagaram");
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counter = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }

        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static  int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();

        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int count = 0;
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            if(entry.getValue() == 1) {
                return count++;
            }
            count++;
        }

        return -1;
    }

    public static int countSignals(List<Integer> frequencies, List<List<Integer>> filterRanges) {
        // Write your code here
        int count = 0;

        for(int i = 0; i < frequencies.size(); i++) {
            int frequency = frequencies.get(i);
            for(int j =0 ; j < filterRanges.size(); j++) {

                int minFilter = filterRanges.get(j).get(0);
                int maxFilter = filterRanges.get(j).get(1);
                if (frequency >= minFilter && frequency <= maxFilter) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
