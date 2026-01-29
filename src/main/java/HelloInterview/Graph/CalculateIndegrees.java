package HelloInterview.Graph;

//List of Edges
//If our graph is given to us as list of edges, we can calculate the indegree of each node by iterating through the edges and incrementing the indegree of the destination node.
//DESCRIPTION
//You are given a graph with n nodes, where each node has an integer value from 0 to n - 1.
//The graph is represent by a list of edges, where edges[i] = [u, v] is a directed edge from node u to node v. Write a function to calculate the indegree of each node in the graph.
//Example
//Input:
//edges = [(0, 1), (1, 2), (1, 3), (3, 2), (3, 4)]
//n = 5
//2
//3
//4
//1
//0
//Output:
//[0, 1, 2, 1, 1]
//Note that we can output the indegrees as an array because the nodes are numbered from 0 to n - 1. (We can look up the indegree of node i at index i in the array.) If the nodes were numbered differently, we would need a dictionary to map each node to its indegree.

import java.util.*;

public class CalculateIndegrees {

    public static void main(String[] args) {

        Map<Integer, List<Integer>> input = new HashMap<>();

        input.put(0, new ArrayList<>(Arrays.asList(1)));
        input.put(1, new ArrayList<>(Arrays.asList(2,3)));
        input.put(2, new ArrayList<>());
        input.put(3, new ArrayList<>(Arrays.asList(2,4)));
        input.put(4, new ArrayList<>());

        int n = 5;

        int[] result = new int[n];

        for(int key : input.keySet()) {
            List<Integer> nodes = input.get(key);
            for(int value : nodes) {
                result[value] += 1;
            }
        }

        System.out.println(Arrays.toString(result));

        System.out.println(topologicalSort1(input, n));
    }

    static List<Integer> topologicalSort(Map<Integer, List<Integer>> adjList, int n) {

        int[] indegree = new int[n];

        for(int key: adjList.keySet()) {
            List<Integer> nodes = adjList.get(key);
            for(int value: nodes) {
                indegree[value]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int u = 0; u < n; u++) {
            if(indegree[u] == 0) {
                queue.add(indegree[u]);
            }
        }

        List<Integer> orders = new ArrayList<>();
        while(!queue.isEmpty()) {
            int u = queue.poll();

            orders.add(u);

            for(int v : adjList.getOrDefault(u, new ArrayList<>())) {
                indegree[v]--;

                if(indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return orders.size() == n ? orders : new ArrayList<>();
    }

    public static List<Integer> topologicalSort1(Map<Integer, List<Integer>> adjList, int n) {
        // calculate indegree of each node
        int[] indegree = new int[n];
        for (int u : adjList.keySet()) {
            for (int v : adjList.get(u)) {
                indegree[v]++;
            }
        }
        // enqueue nodes with indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            if (indegree[u] == 0) {
                queue.offer(u);
            }
        }
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);

            for (int v : adjList.getOrDefault(u, new ArrayList<>())) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return order.size() == n ? order : new ArrayList<>();
    }
}
