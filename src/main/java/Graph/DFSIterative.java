package Graph;

import java.util.*;

public class DFSIterative {

    public static void main(String[] args) {
        //1. Pacific Atlantic Water Flow
        int[][] height = new int[][] {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(pacificAtlantic(height));

        //2. Course schedule
        int[][] prerequisites = new int[][] {{0,1}, {0,2}, {1,3}, {1,4}, {3,4}};
        System.out.println(canFinish(5, prerequisites));

        //3. Max area of island
        int[][] grid = new int[][] {{1,1}};
        System.out.println(maxAreaOfIsland(grid));

        //4.Surrounded Regions
        char[][] board = new char[][] {{'X','X','X','X'},
                                        {'X','O','O','X'},
                                        {'X','X','O','X'},
                                        {'X','O','X','X'}};
        surroundedRegions(board);
        System.out.println(board);

        //5.
        //[[2,1,1],[1,1,0],[0,1,1]]
        int[][] box = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(box));

        //6.Walls and gates
        int[][] rooms = {{2147483647, -1, 0, 2147483647},
                        {2147483647, 2147483647, 2147483647, -1},
                        {2147483647, -1, 2147483647, -1},
                        {0, -1, 2147483647, 2147483647}};

        wallsRoom(rooms);

        System.out.println(rooms);

        //7. redundant connection
        int[][] edges = new int[][] {{1,2},{2,3},{3,4}, {1,4}, {1,5}};
        System.out.println(findRedundantConnection(edges));
    }

    //6. In this problem, a tree is an undirected graph that is connected and has no cycles.
    //
    //You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
    //
    //Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

    //Input: edges = [[1,2],[1,3],[2,3]]
    //Output: [2,3]

    //Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
    //Output: [1,4]

    static int[] parent;
    public static int[] findRedundantConnection(int[][] edges) {
        parent = new int[edges.length+1];

        for(int i=0; i<parent.length; i++) {
            parent[i] = i;
        }

        for(int[] edge : edges) {
            if(find(edge[0]) == find(edge[1])) {
                return edge;
            }

            union(edge[0], edge[1]);
        }

        return new int[]{-1,-1};

    }

    // Find Operation
    private static int find(int node) {
        while(parent[node] != node) {
            node = parent[node];
        }

        return node;
    }

    //Find Union Operation
    private static void union(int i, int j) {
        int iRoot = find(i);
        int jRoot = find(j);

        if(iRoot != jRoot) {
            parent[jRoot] = iRoot;
        }
    }

    //6. walls and gate
    // You are given an m*n grid rooms initialised with 3 possible values
    // -1 A wall or an obstacle
    // 0 A gate
    // INF (2147483647) infinity empty room

    //Fill each empty room with the distance to its nearest gate. ig It's possible to reach a gate, If it shouldn't be filled then INF value will be there.
    // INPUT {{2147483647, -1, 0, 2147483647},
    //       {2147483647, 2147483647, 2147483647, -1},
    //       {2147483647, -1, 2147483647, -1},
    //       {0, -1, 2147483647, 2147483647}};

    // OUTPUT {3,-1, 0, 1}
    //        {2, 2, 1, -1}
    //        {1, -1, 2, -1}
    //        {0, -1, 3, 4}

    public static void wallsRoom(int[][] rooms) {
        if(rooms == null || rooms.length == 0 || rooms[0].length ==0) return;

        boolean visited[][] = new boolean[rooms.length][rooms[0].length];

        for(int i =0 ; i< rooms.length; i++){
            for(int j =0; j< rooms[0].length; j++){
                if(rooms[i][j] == 0 ){
                    //bfs(rooms, i, j);
                    dfs(rooms, i, j, visited, 0);
                }
            }
        }
    }

    private static void dfs(int[][] rooms, int x, int y, boolean[][] visited, int dis){

        if(x < 0 || x >= rooms.length
                || y < 0 || y >= rooms[0].length || rooms[x][y] == -1)
            return;
        if(visited[x][y] || dis > rooms[x][y]) return;

        rooms[x][y] = Math.min(rooms[x][y], dis);// since a room is already 0,
        // in first level, there is no set really.
        visited[x][y] = true;//  to avoid loop.
        dfs(rooms, x+1, y, visited, dis+1);
        dfs(rooms, x-1, y, visited, dis+1);
        dfs(rooms, x, y+1, visited, dis+1);
        dfs(rooms, x, y-1, visited, dis+1);
        visited[x][y] =false;

    }

    private static void bfs(int[][]rooms, int x, int y){
        int[][] neighbor = {{1,0},{-1,0},{0,1},{0,-1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];
        int dis = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i =0; i< size; i++){
                int[] top = queue.poll();
                rooms[top[0]][top[1]] = Math.min(rooms[top[0]][top[1]], dis);
                for(int j=0; j< 4;j++){
                    int k = top[0] + neighbor[j][0];
                    int l = top[1] + neighbor[j][1];
                    if(k>=0 && l>=0 && k< rooms.length && l < rooms[0].length && !visited[k][l] && rooms[k][l] > 0){
                        visited[k][l] = true;
                        queue.offer(new int[]{k,l});
                    }
                }
            }
            dis++;
        }
    }


    ////There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
    ////
    ////The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
    ////
    ////The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
    ////
    ////Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
    //
    ////Input: heights = [[1,2,2,3,5],
    //                   [3,2,3,4,4],
    //                   [2,4,5,3,1],
    //                   [6,7,1,4,5],
    //                   [5,1,1,2,4]]
    ////Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
    ////Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
    ////[0,4]: [0,4] -> Pacific Ocean
    ////       [0,4] -> Atlantic Ocean
    ////[1,3]: [1,3] -> [0,3] -> Pacific Ocean
    ////       [1,3] -> [1,4] -> Atlantic Ocean
    ////[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
    ////       [1,4] -> Atlantic Ocean
    ////[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
    ////       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
    ////[3,0]: [3,0] -> Pacific Ocean
    ////       [3,0] -> [4,0] -> Atlantic Ocean
    ////[3,1]: [3,1] -> [3,0] -> Pacific Ocean
    ////       [3,1] -> [4,1] -> Atlantic Ocean
    ////[4,0]: [4,0] -> Pacific Ocean
    ////       [4,0] -> Atlantic Ocean
    ////Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> result=new ArrayList<>();
        if(heights==null || heights.length==0 || heights[0].length==0){
            return result;
        }
        int m=heights.length;
        int n=heights[0].length;


        boolean[][] canReachPacific=new boolean[m][n];
        boolean[][] canReachAtlantic=new boolean[m][n];

        for(int i=0;i<m;i++){
            dfs(heights,canReachPacific,i,0);
        }
        for(int j=0;j<n;j++){
            dfs(heights,canReachPacific,0,j);
        }
        for(int i=0;i<m;i++){
            dfs(heights,canReachAtlantic,i,n-1);
        }
        for(int j=0;j<n;j++){
            dfs(heights,canReachAtlantic,m-1,j);
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(canReachPacific[i][j] && canReachAtlantic[i][j]){
                    List<Integer> cell=new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }

        return result;
    }

    private static void dfs(int[][] heights,boolean[][] canReach,int row,int col){
        if(canReach[row][col]){
            return;
        }

        canReach[row][col]=true;
        int[][] directions={{0,1},{1,0},{0,-1},{-1,0}};
        for(int[] direction:directions){
            int newRow=row+direction[0];
            int newCol=col+direction[1];

            if(newRow>=0 && newRow<heights.length && newCol>=0 && newCol<heights[0].length && !canReach[newRow][newCol] && heights[newRow][newCol]>=heights[row][col]){
                dfs(heights,canReach,newRow,newCol);
            }
        }
    }

    //2. There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
    //
    //For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    //Return true if you can finish all courses. Otherwise, return false.

    //Input: numCourses = 2, prerequisites = [[1,0]]
    //Output: true
    //Explanation: There are a total of 2 courses to take.
    //To take course 1 you should have finished course 0. So it is possible.

    //Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    //Output: false
    //Explanation: There are a total of 2 courses to take.
    //To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i< numCourses; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int[] list : prerequisites) {
            map.get(list[0]).add(list[1]);
        }

        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Set<Integer> set = new HashSet<>();

            int node = entry.getKey();

            if(!dfs(map, node, set)) {return false;}

        }

        return true;

    }

    //3. You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
    //
    //The area of an island is the number of cells with a value 1 in the island.
    //
    //Return the maximum area of an island in grid. If there is no island, return 0.

    //Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
    //              [0,0,0,0,0,0,0,1,1,1,0,0,0],
    //              [0,1,1,0,1,0,0,0,0,0,0,0,0],
    //              [0,1,0,0,1,1,0,0,1,0,1,0,0],
    //              [0,1,0,0,1,1,0,0,1,1,1,0,0],
    //              [0,0,0,0,0,0,0,0,0,0,1,0,0],
    //              [0,0,0,0,0,0,0,1,1,1,0,0,0],
    //              [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    //Output: 6
    //Explanation: The answer is not 11, because the island must be connected 4-directionally.
    public static int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;

        if(m == 0) return 0;

        int[][] directions = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    int area = 1;

                    grid[i][j] = 0;

                    queue.add(new int[] {i, j});

                    while(!queue.isEmpty()) {
                        int[] visited = queue.poll();

                        for(int d = 0; d < 4; d++) {
                            int x = visited[0] + directions[d][0];
                            int y = visited[1] + directions[d][1];

                            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                                area++;
                                queue.add(new int[] {x, y});
                                grid[x][y] = 0;
                            }

                        }
                        maxArea = Math.max(area, maxArea);

                    }

                }
            }
        }

        return maxArea;
    }

    public static boolean dfs(Map<Integer, List<Integer>> map, int node, Set<Integer> set) {
        if(set.contains(node)) {return false;}

        if(map.get(node).isEmpty()) return true;

        set.add(node);

        for(int neighbor : map.get(node)) {
            if(!dfs(map, neighbor, set)) return false;
        }

        set.remove(node);
        map.put(node, new ArrayList<>());
        return true;
    }

    //Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
    //
    //A region is captured by flipping all 'O's into 'X's in that surrounded region.

    //Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
    //Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
    //Explanation: Notice that an 'O' should not be flipped if:
    //- It is on the border, or
    //- It is adjacent to an 'O' that should not be flipped.
    //The bottom 'O' is on the border, so it is not flipped.
    //The other three 'O' form a surrounded region, so they are flipped.

    public static void surroundedRegions(char[][] board) {

        //1.(DFS) convert un-surrounded O into T
        //2. convert O into X
        //3. convert T into O

        int m = board.length;
        int n = board[0].length;

        if(m == 0) return;

        //1
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n ; j++) {
                if(board[i][j] == 'O' && (i == 0 || i == m-1) || (j ==0 || j == n-1)) {
                    dfs(i, j, board);
                }
            }
        }


        //2
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n ; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        //3
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n ; j++) {
                if(board[i][j] == 'T' ) {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void dfs(int r, int c, char[][] board) {
        if(r < 0 || c < 0 || r == board.length
                || c == board[0].length || board[r][c] != 'O') {
            return;
        }

        board[r][c] = 'T';

        dfs(r+1, c, board);
        dfs(r, c+1, board);
        dfs(r-1, c, board);
        dfs(r, c-1, board);

    }


    //4.
    //You are given an m x n grid where each cell can have one of three values:
    //
    //0 representing an empty cell,
    //1 representing a fresh orange, or
    //2 representing a rotten orange.
    //Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
    //
    //Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
    //Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
    //Output: 4

    //Input: grid = [[2,2,2]
    //              ,[0,2,2]
    //              ,[0,2,1]]
    //Output: -1
    //Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (m == 0)
            return -1;

        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int fresh = 0;
        int time = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[] { i, j });
                }
            }
        }

        while (!queue.isEmpty()) {

            int size = queue.size();
            while(size > 0) {

                int[] current = queue.poll();

                for (int[] direction : directions) {
                    int r = current[0] + direction[0];
                    int c = current[1] + direction[1];

                    //if in bound & fresh, make rotten
                    if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        queue.add(new int[] {r,c});
                        fresh--;
                    }
                }
                size--;
            }
            time++;
        }

        return fresh == 0 ? time : -1;
    }

    public int dfs(int[][] graph) {
        Set<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int startNode = 0;

        stack.push(startNode);
        seen.add(startNode);
        int ans = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            //do some logic
            for(int neighbor: graph[node]) {
                if(!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }

        return ans;
    }
}

