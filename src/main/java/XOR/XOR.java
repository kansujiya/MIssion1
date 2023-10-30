package XOR;

//XOR have certain property like
// 1 ^ 1 = 0
// 1 ^ 0 = 1
// 0 ^ 1 = 1
// 0 ^ 0 = 0
//1 ^ 1 = 0
//29 ^ 29 = 0
//1 ^ 0 = 1
//31 ^ 0 = 31
//(a ^ b) ^ c = a ^ (b ^ c)
//a ^ b = b ^ a


public class XOR {

    public static void main(String[] args) {
        //1. Find Missing no
        System.out.println(findMissingNo(new int[] {1, 5, 2, 6, 4}));

        //2. Find non diplicate no
        System.out.println(findNonDuplicateNo(new int[] {1, 4, 2, 1, 3, 2, 3}));
    }

    //Find Missing no
    //Input: 1, 5, 2, 6, 4
    //Output: 3
    public static int findMissingNo(int[] arr) {
        int n = arr.length + 1;
        //find sum of all no
        int x1 = 1;
        for(int i = 2; i <= n; i++) {
            x1 = x1 ^ i;
            System.out.println(x1);
        }

//        System.out.println("\n\n");

        //XOR for all value in arr
        int x2 = arr[0];
        for(int i = 1; i < n-1; i++) {
            x2 = x2 ^ arr[i];
            System.out.println(x2);
        }

//        System.out.println("\n\n");
        System.out.println(x1);
        System.out.println(x2);

        return x1 ^ x2;
    }

    //Single Number (easy)
    //Input: 1, 4, 2, 1, 3, 2, 3
    //Output: 4
    public static int findNonDuplicateNo(int[] arr) {
        int num = 0;
        for(int i = 0; i < arr.length; i++) {
            num = num ^ arr[i];
            System.out.println(num);
        }

        return num;
    }
}
