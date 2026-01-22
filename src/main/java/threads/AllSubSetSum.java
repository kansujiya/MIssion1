package threads;

class AllSubSetSum {

    // Prints sums of all
    // subsets of arr[l..r]
    static void subsetSums(int[] arr, int l, int r, int sum)
    {
        // Print current subset
        if (l >= arr.length) {
            System.out.println("length: " + r + ":" + sum );
            return;
        }
        
        subsetSums(arr, l + 1, r+1, sum + arr[l]);
        subsetSums(arr, l + 1,    r, sum);
    }

    // Driver code
    public static void main(String[] args)
    {
        int[] arr = { 5, 4, 3,7 };
        subsetSums(arr, 0, 0, 0);
    }
}
