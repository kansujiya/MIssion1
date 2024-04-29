package Matrix;

import java.util.*;
import java.util.stream.Collectors;

public class Matrix {

    public static void main(String[] args) {
        //1. Rotate Image
        int[][] matrix = new int[][] {{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}};
        System.out.println(rotateMatrix(matrix));

        //2. Spiral Matrix
        int[][] matrix1 = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(spiralMatrix(matrix1));

        //3. Set Matrix Zeroes
        int[][] matrix2 = new int[][] {{1,1,1}, {1,0,1}, {1,1,1}};
        int[][] matrix3 = new int[][] {{0,1,2,0}, {3,4,5,2}, {3,4,5,2}, {1,3,1,5}};
        System.out.println(setZeros(matrix2));
        System.out.println(setZeros(matrix3));

        //4. Search a 2D Matrix
        int[][] matrix4 = new int[][] {{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
        System.out.println(searchTargetIn2DMatrix(matrix4, 3));

        //5. zeros product
        int[][] A = {{6, 25, 4, 10}, {12, 25, 1, 15}, {7, 15, 15, 5}};
//        int[][] A = {{10, 100, 10}, {1, 10, 1}, {1, 10, 1}};
//        int[][] A = {{7500, 10, 11, 12}, {6250, 13, 14, 15}, {134, 17, 16, 1}, {5500, 2093, 5120, 238}};
        List<List<Integer>> listA = new ArrayList<>();

        for (int[] array : A) {
            List<Integer> tempList = new ArrayList<>();
            for (int value : array) {
                tempList.add(value);
            }
            listA.add(tempList);
        }
        int result = solve(listA);
        System.out.println("Maximum number of trailing zeros in some path: " + result);

        //6. Number of iceland
        char[][] arr = new char[][] {{'1','1','1','1','0'},
                                     {'1','1','0','1','0'},
                                     {'1','1','0','0', '0'},
                                     {'0','0','0','0','0'}};

        char[][] arr2 = new char[][]   {{'1','1','0','0','0'},
                                        {'0','1','0','0','1'},
                                        {'1','0','0','1', '1'},
                                        {'0','0','0','0','0'},
                                        {'1','0','1','0','0'}};

        System.out.println("Number of island " + numIslands(arr));
        System.out.println("Number of island " + numIslands(arr2));
    }

    //Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
    //
    //An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
    //Input: grid = [
    //  ["1","1","1","1","0"],
    //  ["1","1","0","1","0"],
    //  ["1","1","0","0","0"],
    //  ["0","0","0","0","0"]
    //]
    //Output: 1
    //Input: grid = [
    //  ["1","1","0","0","0"],
    //  ["1","1","0","0","0"],
    //  ["0","0","1","0","0"],
    //  ["0","0","0","1","1"]
    //]
    //Output: 3
    // /*
    //       y   y   y   y   y
    //    x["1","1","1","1","0"],
    //    x["1","1(x, y)","0","1","0"],
    //    x["1","1","0","0","0"],
    //    x["0","0","0","0","0"]
    //
    //
    //                        (r-1, c)
    //                          |
    //                          |
    //            (r, c-1)------(r,c)-----(r, c+1)
    //                          |
    //                          |
    //                      (r+1, c)
    //    */
    public static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        int islands = 0;
        int row = grid.length;
        int col = grid[0].length;

        int[][] directions = {{1,0}, {0,1}, {-1, 0}, {0,-1}};

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0; i< row; i++) {
            for(int j=0; j< col; j++) {
                if(grid[i][j] == '1') {

                    islands++;
                    queue.add(new int[] {i,j});

                    //Mark visited
                    grid[i][j] = '0';

                    while(!queue.isEmpty()) {
                        int[] curr = queue.poll();

                        for(int[] k : directions) {
                            int r = curr[0] + k[0];
                            int c = curr[1] + k[1];

                            if(r >= 0 && c >= 0 && r < row && c < col && grid[r][c] == '1') {
                                queue.add(new int[] {r,c});
                                grid[r][c] = '0';
                            }
                        }
                    }
                }
            }
        }

        return islands;
    }

    //You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
    //
    //You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
    //Input matrix = [[5,1,9,11],
    //               [2,4,8,10],
    //               [13,3,6,7],
    //               [15,14,12,16]]
    //Output: [[15,13,2,5],
    //        [14,3,4,1],
    //        [12,6,8,9],
    //        [16,7,10,11]]
    public static int[][] rotateMatrix(int[][] matrix) {

        //Matrix Transpose => Row will become colum and vice versa
        for(int i=0; i < matrix.length; i++) {
            for(int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i=0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }

        return matrix;
    }

    //Given an m x n matrix, return all elements of the matrix in spiral order.
    //Input: matrix = [[1,2,3],
    //                 [4,5,6],
    //                 [7,8,9]]
    //Output: [1,2,3,6,9,8,7,4,5]

    public static List<Integer> spiralMatrix(int[][] matrix) {
        int rows = matrix.length;
        int colums = matrix[0].length;

        List<Integer> result = new ArrayList<>();

        int up = 0;
        int left = 0;
        int right = colums - 1;
        int down = rows - 1;

        while(result.size() < rows*colums) {
            //Move left to right
            for(int col = left; col <= right ; col++) {
                result.add(matrix[up][col]);
            }

            //Move up to down
            for(int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }

            //Check if we are at diff row
            if(up != down) {
                //move right to left
                for(int col = right - 1; col >= left ; col--) {
                    result.add(matrix[down][col]);
                }
            }

            //Check if we are at diff column
            if(right != left) {
                for(int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }

            left++;
            right--;
            up++;
            down--;
        }

        return result;
    }

    //Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
    //Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
    //Output: [[1,0,1],[0,0,0],[1,0,1]]

    //Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
    //Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

    public static int[][] setZeros(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        Set<Integer> R = new HashSet();
        Set<Integer> C = new HashSet();

        //Store 0 position for row & col
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0) {
                    R.add(i);
                    C.add(j);
                }
            }
        }

        //now convert row or col to 0
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if(R.contains(r) || C.contains(c)) {
                    matrix[r][c] = 0;
                }
            }
        }
        return matrix;
    }

    //You are given an m x n integer matrix with the following two properties:
    //
    //Each row is sorted in non-decreasing order.
    //The first integer of each row is greater than the last integer of the previous row.
    //Given an integer target, return true if target is in matrix or false otherwise.
    //
    //You must write a solution in O(log(m * n)) time complexity.

    //Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    //Output: true
    //Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
    //Output: false
    public static boolean searchTargetIn2DMatrix(int[][] matrix, int target) {

        int row = matrix.length;
        int col = matrix[0].length;

        int start = 0;
        int end = row*col-1;

        int mid = 0;
        int midElement = 0;

        while(start<end) {
            mid = start +(end-start)/2;

            //mid = 0 + 11/2 = 5
            // r = mid/col = 5/4 = 0
            // c = mod%col = 5%4 = 0
            midElement = matrix[mid/col][mid%col];

            if(midElement == target) {
                return true;
            }
            else {
                if(target < midElement) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        return false;
    }

    public static  int solve(List<List<Integer>> input) {
        int m = input.size();
        int n = input.get(0).size();
        int result = 0;
        int[][] dp = new int[m][n];

        // Update the dp matrix for rows
        for (int i = 0; i < m; i++) {
            int a = 1;
            int count = 0;
            for (int j = 0; j < n; j++) {
                a *= input.get(i).get(j);
                while ((a % 10) == 0) {
                    count++;
                    a = a / 10;
                }
                a = a % 10;
                dp[i][j] = count;
            }
        }

        // Update the values for column matrix
        for (int i = 0; i < n; i++) {
            int a = 1;
            int count = 0;
            for (int j = 0; j < m; j++) {
                a *= input.get(j).get(i);
                while ((a % 10) == 0) {
                    count++;
                    a = a / 10;
                }
                a = a % 10;
                dp[j][i] += count;
                result = Math.max(dp[j][i], result);
            }
        }
        return result;
    }
}
