package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSIterative {

    public int bfs(int[][] graph) {
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int ans = 0;
        int startNode = 0;

        queue.add(startNode);
        seen.add(startNode);

        while(!queue.isEmpty()) {
            int node  = queue.remove();
            //do some logic
            for(int neighbour : graph[node]) {
                if(!seen.contains(neighbour)) {
                    seen.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return ans;
    }

}
