package HelloInterview.Backtracking;

//Given an m x n grid of characters board and a string word, return true if word exists in the grid.
//
//The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//Example 1:
//
//Input:
//
//board = [
//    ['B', 'L', 'C', 'H'],
//    ['D', 'E', 'L', 'T'],
//    ['D', 'A', 'K', 'A'],
//]
//word = "BLEAK"
//B
//L
//C
//H
//D
//E
//L
//T
//D
//A
//K
//A
//"BLEAK" can be formed using the tiles in dark blue.
//Output:
//
//True
//Example 2:
//
//Input:
//
//board = [
//    ['B', 'L', 'C', 'H'],
//    ['D', 'E', 'L', 'T'],
//    ['D', 'A', 'K', 'A'],
//]
//word = "BLEED"
//Output:
//
//False

public class WordSearch {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'B', 'L', 'C', 'H'},
                {'D', 'E', 'L', 'T'},
                {'D', 'A', 'K', 'A'},
        };

        String word = "BLEAK";

        System.out.println(findWord(board, word));


    }

    public static boolean findWord(char[][] grid, String word) {
        int r = grid.length;
        int c = grid[0].length;

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(grid[i][j] == word.charAt(0)) { //match first char
                    if(dfs(grid, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean dfs(char[][] grid, String word, int r, int c, int index) {
        if(index == word.length()) {
            return true;
        }

        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != word.charAt(index)) {
            return false;
        }

        char temp = grid[r][c];
        grid[r][c] = '#';

        boolean found  = dfs(grid, word, r+1, c, index+1) ||
                dfs(grid, word, r-1, c, index+1) ||
        dfs(grid, word, r, c-1, index+1) ||
                dfs(grid, word, r, c+1, index+1);

        grid[r][c] = temp;
        return found;
    }
}
