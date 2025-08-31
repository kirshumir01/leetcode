package hard;

import java.util.*;

/*
#815 Bus Routes

You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

Example 1:
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2

Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

Example 2:
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
 */

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                stopToBuses.computeIfAbsent(stop, k -> new ArrayList<>()).add(bus);
            }
        }

        if (!stopToBuses.containsKey(target)) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedBuses = new HashSet<>();
        queue.offer(new int[]{source, 0});
        visitedStops.add(source);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentStop = current[0];
            int busesTaken = current[1];

            for (int bus : stopToBuses.getOrDefault(currentStop, Collections.emptyList())) {
                if (visitedBuses.contains(bus)) {
                    continue;
                }
                visitedBuses.add(bus);

                for (int nextStop : routes[bus]) {
                    if (nextStop == target) {
                        return busesTaken + 1;
                    }
                    if (!visitedStops.contains(nextStop)) {
                        visitedStops.add(nextStop);
                        queue.offer(new int[]{nextStop, busesTaken + 1});
                    }
                }
            }
        }
        return -1;
    }
}