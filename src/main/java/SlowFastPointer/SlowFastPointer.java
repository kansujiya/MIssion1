package SlowFastPointer;

public class SlowFastPointer {


    public static void main(String[] args) {
        //1.Happy Number (medium)
        System.out.println("Given number 23 is " + happyNumber(23));
        System.out.println("Given number 23 is " + happyNumber(12));
    }

    //Happy Number (medium)
    //Any number will be called a happy number if, after repeatedly replacing it with a number equal to the sum of the square of all of its digits, leads us to number ‘1’. All other (not-happy) numbers will never reach ‘1’. Instead, they will be stuck in a cycle of numbers which does not include ‘1’.
    //Input: 23
    //Output: true (23 is a happy number)
    //Explanations: Here are the steps to find out that 23 is a happy number: 2^2 + 3^2 = 13 => 1^2 + 3^2 = 10 => 1^2 + o^2 = 1

    public static boolean happyNumber(int num) {
        int slow = num;
        int fast = num;
        do{
            slow = squareSum(slow);
            fast = squareSum(squareSum((fast)));
        } while(slow != fast);
        return slow == 1;
    }

    public static int squareSum(int num) {
        int sum = 0;
        int digit = 0;
        while(num > 0) {
            digit = num%10;
            sum += digit*digit;
            num /= 10;
        }
        return sum;
    }

}
