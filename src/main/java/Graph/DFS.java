package Graph;

public class DFS {


    static int[][] direction = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    static int[][] direction4 = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {1, 0, 0}
        };

        DFS(arr);
    }

    public static void DFS(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;

        // ❌ Wrong before: AND (&&)
        // ✅ Correct: OR (||)
        if (arr[0][0] == 1 || arr[m - 1][n - 1] == 1) {
            System.out.println("No Path: " + -1);
            return;
        }

        minDist = Integer.MAX_VALUE; // reset for safety
        edgeCount(arr, 0, 0, 1);
        System.out.println("Min Path is " + minDist);
    }

    public static void edgeCount(int[][] arr, int row, int col, int dist) {
        int m = arr.length;
        int n = arr[0].length;

        // Out of bounds or blocked
        if (row < 0 || col < 0 || row >= m || col >= n || arr[row][col] != 0) {
            return;
        }

        // ✅ Destination
        if (row == m - 1 && col == n - 1) {
            minDist = Math.min(minDist, dist);
            return;
        }

        arr[row][col] = 1; // mark visited

        // Explore all 8 directions
        for (int[] dir : direction4) {
            edgeCount(arr, row + dir[0], col + dir[1], dist + 1);
        }

        arr[row][col] = 0; // backtrack
    }
}
