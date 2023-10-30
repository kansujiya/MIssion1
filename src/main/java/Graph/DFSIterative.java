package Graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFSIterative {

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
