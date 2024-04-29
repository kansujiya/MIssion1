package SubSets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SubSets {
    public static void main(String[] args){
        //.1 Subset
        int[] arr = new int[]{1,5,3};
        List<List<Integer>> result = findAllSubSet(arr);
        System.out.println(result);
        //.2 Subset without duplicate
        int[] arr1 = new int[]{1, 3, 3};
        List<List<Integer>> result1 = findAllDistinctSubSet(arr1);
        System.out.println(result1);
        //3. Permutations
        List<List<Integer>> result2 = permutations(new int[] {1,2,3});
        System.out.println(result2);
        List<List<Integer>> result3 = permutationsRecursive(new int[] {1,2,3});
        System.out.println(result3);

        //4. String Permutation
        List<String> result4 = stringPermutation("ad52");
        System.out.println(result4);
        List<String> result5 = stringPermutation("ab7c");
        System.out.println(result5);

        //5.generateValidateParenthesis
        SubSets s = new SubSets();
        System.out.println(s.generateValidateParenthesis(3));

    }

    //Given a set with distinct elements, find all of its distinct subsets.
    //Input: [1, 3]
    //Output: [], [1], [3], [1,3]
    public static List<List<Integer>> findAllSubSet(int[] arr) {
        List<List<Integer>> subsets = new ArrayList<>();
        //add empty set first
        subsets.add(new ArrayList<>());
        for(int num : arr) {
            // add current number to existing sets
            int n = subsets.size();
            for(int i = 0; i < n; i++) {
                //create a new sub set from existing subset and inset current element to it
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(num);
                subsets.add(set);
            }
        }
        return subsets;
    }

    //Given a set of numbers that might contain duplicates, find all of its distinct subsets.
    //Input: [1, 3, 3]
    //Output: [], [1], [3], [1,3], [3,3], [1,3,3]

    //Input: [1, 5, 3, 3]
    //Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3], [3,3], [1,3,3], [3,3,5], [1,5,3,3]
    public static List<List<Integer>> findAllDistinctSubSet(int[] arr) {
        List<List<Integer>> subsets = new ArrayList<>();

        //to maintain size of subset
        int end = 0;
        int start = 0;
        //add empty set first
        subsets.add(new ArrayList<>());
        for(int k = 0; k < arr.length; k++) {
            start = 0;
            // add current number to existing sets
            int n = subsets.size();
            //to increase iteration by 1 in case of duplicate

            if(k>0 && arr[k] == arr[k-1]) {
                start = end+1;
            }
            end = subsets.size()-1;
            for(int i = start; i <= end; i++) {
                //create a new sub set from existing subset and inset current element to it
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(arr[k]);
                subsets.add(set);
            }
        }
        return subsets;
    }

    //Permutation is defined as the re-arranging of the elements of the set. For example, {1, 2, 3} has the following six permutations:
    //
    //{1, 2, 3}
    //{1, 3, 2}
    //{2, 1, 3}
    //{2, 3, 1}
    //{3, 1, 2}
    //{3, 2, 1}
    public static List<List<Integer>> permutations(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());
        for(int currentNum: arr) {

            //Take all existing permutation & add new element at every positions
            int n = permutations.size();

            for(int i = 0; i < n; i++) {

                List<Integer> oldPermutation = permutations.poll();

                //create a new permutation to adding new element at each position

                for(int j= 0; j<= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);

                    newPermutation.add(j, currentNum);

                    if(newPermutation.size() == arr.length) {
                        result.add(newPermutation);
                    } else {
                        permutations.add(newPermutation);
                    }
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> permutationsRecursive(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        permutationsRecursiveCall(arr, 0, new ArrayList<>(), result);
        return result;
    }

    public static void permutationsRecursiveCall(int[] arr, int index, List<Integer> currentPermutation, List<List<Integer>> result) {
        if(currentPermutation.size() == arr.length) {
            result.add(currentPermutation);
        } else {
            for(int i = 0; i <= currentPermutation.size(); i++) {
                List<Integer> newPermutation = new ArrayList<>(currentPermutation);
                newPermutation.add(i, arr[index]);
                permutationsRecursiveCall(arr, index+1, newPermutation, result);
            }
        }
    }

    //String Permutations by changing case
    //Given a string, find all of its permutations preserving the character sequence but changing case.
    //Input: "ad52"
    //Output: "ad52", "Ad52", "aD52", "AD52"

    //Input: "ab7c"
    //Output: "ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"

    public static List<String> stringPermutation(String str) {
        List<String> result = new ArrayList<>();
        result.add(str);

        for(int i = 0; i<str.length(); i++) {
            if(Character.isLetter(str.charAt(i))) {
                int n = result.size();
                for(int j = 0; j < n; j++) {
                    char[] chs = result.get(j).toCharArray();
                    if(Character.isUpperCase(chs[i])) {
                        chs[i] = Character.toLowerCase(chs[i]);
                    } else {
                        chs[i] = Character.toUpperCase(chs[i]);
                    }
                    result.add(String.valueOf(chs));
                }
            }

        }
        return result;
    }

    //Balanced Parentheses (hard)
    //For a given number ‘N’, write a function to generate all combination of ‘N’ pairs of balanced parentheses.
    //Input: N=2
    //Output: (()), ()()

    //Input: N=3
    //Output: ((())), (()()), (())(), ()(()), ()()()

    class ParenthesisSting {
        int open;
        int close;
        String s;
        public ParenthesisSting (String s, int open, int close) {
            this.s = s;
            this.open = open;
            this.close = close;
        }
    }
    public List<String> generateValidateParenthesis(int num) {
        List<String> result = new ArrayList<>();
        Queue<ParenthesisSting> q = new LinkedList<>();
        q.add(new ParenthesisSting("", 0,0));

        while(!q.isEmpty()) {
            int size = q.size();

            for(int  i = 0; i < size; i++) {
                ParenthesisSting parenthesisSting = q.poll();
                if(parenthesisSting.close == 3) {
                    result.add(parenthesisSting.s);
                }

                if(parenthesisSting.open < num) {
                    q.add(new ParenthesisSting(parenthesisSting.s + "(", parenthesisSting.open+1, parenthesisSting.close));
                }
                if(parenthesisSting.open > parenthesisSting.close) {
                    q.add(new ParenthesisSting(parenthesisSting.s + ")", parenthesisSting.open, parenthesisSting.close+1));
                }
            }
        }
        return result;
    }
}
