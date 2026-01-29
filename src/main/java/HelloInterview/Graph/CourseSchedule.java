package HelloInterview.Graph;

//DESCRIPTION (inspired by Leetcode.com)
//You have to take a total of numCourses courses, which are labeled from 0 to numCourses - 1. You are given a list of prerequisites pairs, where prerequisites[i] = [a, b] indicates that you must complete course b before course a.
//
//Given the total number of courses and a list of prerequisite pairs, write a function to determine if it is possible to finish all courses.
//
//Example 1:
//
//Input:
//
//numCourses = 3
//prerequisites = [[1, 0], [2, 1]]
//Output: true
//
//Explanation: You can take courses in the following order: 0, 1, 2.
//
//Example 2:
//
//Input:
//
//numCourses = 3
//prerequisites = [[1, 0], [0, 1],[1,2]]
//Output: false
//
//Explanation: It is impossible to finish all courses, as you must finish course 1 before course 0 and course 0 before course 1.

import java.util.*;

public class CourseSchedule {

    public static void main(String[] args) {
        int numbCourses = 4;
        int[][] prerequisites = new int[][] {{1,0}, {2,1}, {3,1}, {2,3}};

        System.out.println(canFinish(numbCourses, prerequisites));
    }


    public static Boolean canFinish(Integer numCourses, int[][] prerequisites) {

        //get indegree
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] arr : prerequisites) {
                int source = arr[1];
                int destination = arr[0];
                graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
                indegree[destination]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i< numCourses; i++) {
            if(indegree[i] == 0) {
                queue.add(indegree[i]);
            }
        }

        int count = 0;
        while(!queue.isEmpty()) {
            int course = queue.poll();
            count++;

            for(int neighbor: graph.getOrDefault(course, new ArrayList<>())) {
                indegree[neighbor]--;
                if(indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return count == numCourses;
    }
}
