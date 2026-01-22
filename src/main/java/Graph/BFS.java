package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    static int[][] direction = new int[][] {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},   {1, 0},  {1, 1}
    };

    static int[][] direction4 = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {0, 1, 0},
                {0, 0, 0},
                {1, 0, 0}};

       // BFS(arr);
        DFS(arr);
    }

    public static void DFS(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;

        if(arr[0][0] == 1 && arr[m-1][n-1] == 1) {System.out.println("No Path" + -1);}

        minDist = Integer.MAX_VALUE; // reset for safety

        edgeCount(arr, 0, 0 , 1);

        System.out.println("Min Path is" + minDist);
    }

    public static void edgeCount(int[][] arr, int row, int col, int dist) {
        int m = arr.length;
        int n = arr[0].length;

        if(row < 0 || col < 0 || row >= m || col >= n || arr[row][col] != 0) {
            return;
        }

        if(row == m-1 && col == n-1) {
            minDist = Math.min(minDist, dist);
            return;
        }

        arr[row][col] = 1; //visited

        for(int[] dir: direction4) {
           edgeCount(arr, row+dir[0], col+dir[1], dist+1);
        }

        arr[row][col] = 0; //backtrack
    }


    public static void BFS(int[][] arr) {

        int m = arr.length;
        int n = arr[0].length;

        if(arr[0][0] == 1 || arr[m-1][n-1] == 1) {System.out.println("No Path" + -1);}

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {0,0,1});

        arr[0][0] = 1;

        while(!queue.isEmpty()) {
            int[] node = queue.poll();

            int row = node[0];
            int col = node[1];
            int dist = node[2];

            if(row == m-1 && col == n-1) {System.out.println("Path length" + dist);return;}

            for(int[] dir : direction) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && arr[newRow][newCol] == 0){
                    queue.add(new int[] {newRow, newCol, dist+1});
                    arr[newRow][newCol] = 1;
                }
            }
        }}
}
