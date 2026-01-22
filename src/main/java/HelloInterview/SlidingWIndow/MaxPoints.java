package HelloInterview.SlidingWIndow;

//DESCRIPTION (inspired by Leetcode.com)
//Given an array of integers representing the value of cards, write a function to calculate the maximum score you can achieve by selecting exactly k cards from either the beginning or the end of the array.
//
//For example, if k = 3, then you have the option to select:
//
//the first three cards,
//the last three cards,
//the first card and the last two cards
//the first two cards and the last card
//Example 1: Input:
//
//cards = [2,11,4,5,3,9,2]
//k = 3
//Output:
//
//17
//Explanation:
//
//Selecting the first three cards from the beginning (2 + 11 + 4) gives a total of 17.
//Selecting the last three cards (3 + 9 + 2) gives a total of 14.
//Selecting the first card and the last two cards (2 + 9 + 2) gives a total of 13.
//Selecting the first two cards and the last card (2 + 11 + 2) gives a total of 15.
//So the maximum score is 17.

public class MaxPoints {

    public static void main(String[] args) {
        int[] cards = new int[]{2,11,4,5,3,9,2};

        int total = 0;
        int start = 0;
        int k = 3;
        while(start< cards.length) {
            total += cards[start];
            start++;
        }

        start = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int end = 0; end < cards.length; end++) {

            sum += cards[end];

            if(end-start+1 == cards.length-k) {
                max = Math.max(max, total-sum);
                sum -= cards[start];
                start++;
            }

        }

        System.out.println("total sum " + max);
    }

}
