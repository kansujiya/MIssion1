package HelloInterview.Backtracking;


//DESCRIPTION
//Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
//A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
//2: "abc"
//3: "def"
//4: "ghi"
//5: "jkl"
//6: "mno"
//7: "pqrs"
//8: "tuv"
//9: "wxyz"
//Example:
//Input:
//"23"
//Output:
//["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionSpaceTree {

    static Map<Character, String> map = Map.of('2', "abc", '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz");

    public static void main(String[] args) {
        String input = "23";
        List<String> result = new ArrayList<>();
        backtrack(input, result, "", 0);
        System.out.println(result);
    }

    static void backtrack(String input, List<String> result, String output, int index) {

        if(index == input.length()) {
            result.add(output);
            return;
        }

        for(char letter : map.get(input.charAt(index)).toCharArray()) {
            backtrack(input, result, output + letter, index+1);
        }
    }

                                                 //"", 0

                 //"a", 1                         //"b", 1                       //"c", 1

    //"ad", 2    //"ae", 2   //"af", 2  //"bd", 2  //"be", 2  //"bf", 2  //"cd", 2  //"ce", 2 //"cf", 2
}
