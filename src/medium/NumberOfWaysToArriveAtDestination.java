package medium;

import java.util.*;

/*
#1976 NUMBER OF WAYS TO ARRIVE DESTINATION

You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections.
The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel.
You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
Return the number of ways you can arrive at your destination in the shortest amount of time.
Since the answer may be large, return it modulo 109 + 7.

Example 1:
Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6

Example 2:
Input: n = 2, roads = [[1,0,10]]
Output: 1
Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
 */

public class NumberOfWaysToArriveAtDestination {
    public int countPaths(int n, int[][] roads) {
        final int MOD = 1_000_000_007;
        List<List<int[]>> graph= new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int startNode = road[0];
            int endNode = road[1];
            int travelTime = road[2];

            graph.get(startNode).add(new int[] {endNode, travelTime});
            graph.get(endNode).add(new int[] {startNode, travelTime});
        }

        PriorityQueue<long[]> minHeap = new PriorityQueue<>(
                Comparator.comparingLong(a -> a[0])
        );

        long[] shortestTime = new long[n];
        Arrays.fill(shortestTime, Long.MAX_VALUE);
        int[] pathCount = new int[n];

        shortestTime[0] = 0;
        pathCount[0] = 1;

        minHeap.offer(new long[] {0, 0});

        while (!minHeap.isEmpty()) {
            long[] top = minHeap.poll();
            long currentTime = top[0];
            int currentNode = (int) top[1];

            if (currentTime > shortestTime[currentNode]) {
                continue;
            }

            for (int[] neighbor : graph.get(currentNode)) {
                int neighborNode = neighbor[0];
                long timeToNeighbor = neighbor[1];

                if (currentTime + timeToNeighbor < shortestTime[neighborNode]) {
                    shortestTime[neighborNode] = currentTime + timeToNeighbor;
                    pathCount[neighborNode] = pathCount[currentNode];
                    minHeap.offer(
                            new long[] {shortestTime[neighborNode], neighborNode}
                    );
                }
                else if (currentTime + timeToNeighbor == shortestTime[neighborNode]) {
                    pathCount[neighborNode] = (pathCount[neighborNode] + pathCount[currentNode]) % MOD;
                }
            }
        }
        return pathCount[n - 1];
    }
}