package medium;

/*
There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

Example 1:
Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph.
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2]
City 1 -> [City 0, City 2, City 3]
City 2 -> [City 0, City 1, City 3]
City 3 -> [City 1, City 2]
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.

Example 2:
Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph.
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1]
City 1 -> [City 0, City 4]
City 2 -> [City 3, City 4]
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3]
The city 0 has 1 neighboring city at a distanceThreshold = 2.
 */

import java.util.Arrays;

public class FindTheCityWithTheSmallestNumberOfNeighbors {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int INF = (int) 1e9 + 7;
        int[][] distanceMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distanceMatrix[i], INF);
            distanceMatrix[i][i] = 0;
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int weight = edge[2];
            distanceMatrix[start][end] = weight;
            distanceMatrix[end][start] = weight;
        }

        floyd(n, distanceMatrix);

        return getCityWithFewestReachable(
                n,
                distanceMatrix,
                distanceThreshold
        );
    }

    private void floyd(int n, int[][] distanceMatrix) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distanceMatrix[i][j] = Math.min(
                            distanceMatrix[i][j],
                            distanceMatrix[i][k] + distanceMatrix[k][j]
                    );
                }
            }
        }
    }

    private int getCityWithFewestReachable(int n,
                                           int[][] distanceMatrix,
                                           int distanceThreshold) {
        int cityWithFewestReachable = -1;
        int fewestReachableCount = n;

        for (int i = 0; i < n; i++) {
            int reachableCount = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (distanceMatrix[i][j] <= distanceThreshold) {
                    reachableCount++;
                }
            }
            if (reachableCount <= fewestReachableCount) {
                fewestReachableCount = reachableCount;
                cityWithFewestReachable = i;
            }
        }
        return cityWithFewestReachable;
    }
}
