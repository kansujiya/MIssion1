package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

public class Backtrack {

    public static void main(String[] args) {
        //1. latter combinations
        System.out.println("letter for combination " +  letterCombinations("23"));
    }

    //Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
    //
    //A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
    //Input: digits = "23"
    //Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    //Input: digits = "2"
    //Output: ["a","b","c"]
    static List<String> result = new ArrayList<>();

    static Map<Character, String> letters = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public static List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return result;

        backtrack(0, new StringBuilder(), digits);
        return result;
    }

    private static void backtrack(int index, StringBuilder path, String digits) {
        if(path.length() == digits.length()) {
            result.add(path.toString());
            return;
        }

        String possibleLetteres = letters.get(digits.charAt(index));
        for(char letter: possibleLetteres.toCharArray()) {
            path.append(letter);
            backtrack(index + 1, path, digits);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public int combination(int state) {
        return backtrack(state);
    }

    public int backtrack(int state) {
        if(true) { //base condition
            //modify answer
            return 0;
        }
        int ans = 0;
        for(int i =0; i < state; i++) { //iterate till input
            //modify current state
            ans += backtrack(state);
            //undo the modification of the current state
        }

        return 0; //ignore later with some real example
    }
}
