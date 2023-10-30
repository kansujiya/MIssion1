package Graph;

import java.util.HashSet;
import java.util.Set;

public class DFSRecursive {

    //For the graph templates, assume the nodes are numbered from 0 to n - 1
    // and the graph is given as an adjacency list. Depending on the problem,
    // you may need to convert the input into an equivalent adjacency list before using the templates.

    Set<Integer> seen = new HashSet<>();

    public int function(int[][] graph) {
        int startNode = 0;
        seen.add(startNode);
        return dfs(startNode, graph);
    }

    public int dfs(int startNode, int[][] graph) {
        int ans = 0;
        //do some logic

        for(int neighbor: graph[startNode]) {
            if(!seen.contains(neighbor)) {
                seen.add(neighbor);
                ans += dfs(neighbor, graph);
            }
        }

        return ans;
    }
}
