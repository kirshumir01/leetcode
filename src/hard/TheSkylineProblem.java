package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

public class TheSkylineProblem {
    public static class Solution1 {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            SortedSet<Integer> edgeSet = new TreeSet<Integer>();
            for (int[] building : buildings) {
                int left = building[0];
                int right = building[1];
                edgeSet.add(left);
                edgeSet.add(right);
            }
            List<Integer> edges = new ArrayList<Integer>(edgeSet);

            Map<Integer, Integer> edgeIndexMap = new HashMap<>();
            for (int i = 0; i < edges.size(); ++i) {
                edgeIndexMap.put(edges.get(i), i);
            }

            int[] heights = new int[edges.size()];

            for (int[] building : buildings) {
                int left = building[0], right = building[1], height = building[2];
                int leftIndex = edgeIndexMap.get(left), rightIndex = edgeIndexMap.get(right);

                for (int idx = leftIndex; idx < rightIndex; ++idx) {
                    heights[idx] = Math.max(heights[idx], height);
                }
            }

            List<List<Integer>> answer = new ArrayList<>();

            for (int i = 0; i < heights.length; ++i) {
                int currHeight = heights[i], currPos = edges.get(i);

                if (answer.isEmpty() || answer.get(answer.size() - 1).get(1)!=currHeight) {
                    answer.add(Arrays.asList(currPos, currHeight));
                }
            }
            return answer;
        }
    }

    public static class Solution2 {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            SortedSet<Integer> edgeSet = new TreeSet<>();
            for (int[] building : buildings) {
                int left = building[0], right = building[1];
                edgeSet.add(left);
                edgeSet.add(right);
            }
            List<Integer> positions = new ArrayList<Integer>(edgeSet);
            Collections.sort(positions);

            List<List<Integer>> answer = new ArrayList<>();
            int maxHeight, left, right, height;

            for (int position : positions) {
                maxHeight = 0;

                for (int[] building : buildings) {
                    left = building[0];
                    right = building[1];
                    height = building[2];

                    if (left <= position && position < right) {
                        maxHeight = Math.max(maxHeight, height);
                    }
                }

                if (answer.isEmpty() || answer.get(answer.size() - 1).get(1)!=maxHeight) {
                    answer.add(Arrays.asList(position, maxHeight));
                }
            }
            return answer;
        }
    }

    public static class Solution3 {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < buildings.length; ++i) {
                edges.add(Arrays.asList(buildings[i][0], i));
                edges.add(Arrays.asList(buildings[i][1], i));
            }
            Collections.sort(edges, (a, b) -> {
                return a.get(0) - b.get(0);
            });

            Queue<List<Integer>> live = new PriorityQueue<>((a, b) -> {
                return b.get(0) - a.get(0);
            });
            List<List<Integer>> answer = new ArrayList<>();

            int idx = 0;

            while (idx < edges.size()) {
                int currX = edges.get(idx).get(0);

                while (idx < edges.size() && edges.get(idx).get(0)==currX) {
                    int b = edges.get(idx).get(1);

                    if (buildings[b][0]==currX) {
                        int right = buildings[b][1];
                        int height = buildings[b][2];
                        live.offer(Arrays.asList(height, right));
                    }
                    idx += 1;
                }

                while (!live.isEmpty() && live.peek().get(1) <= currX) {
                    live.poll();
                }

                int currHeight = live.isEmpty() ? 0:live.peek().get(0);

                if (answer.isEmpty() || answer.get(answer.size() - 1).get(1)!=currHeight) {
                    answer.add(Arrays.asList(currX, currHeight));
                }
            }
            return answer;
        }
    }

    public static class Solution4 {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < buildings.length; ++i) {
                edges.add(Arrays.asList(buildings[i][0], buildings[i][2]));
                edges.add(Arrays.asList(buildings[i][1], -buildings[i][2]));
            }
            Collections.sort(edges, (a, b) -> {
                return a.get(0) - b.get(0);
            });

            Queue<Integer> live = new PriorityQueue<>((a, b) -> {
                return b - a;
            });

            Queue<Integer> past = new PriorityQueue<>((a, b) -> {
                return b - a;
            });

            List<List<Integer>> answer = new ArrayList<>();

            int idx = 0;

            while (idx < edges.size()) {
                int currX = edges.get(idx).get(0);

                while (idx < edges.size() && edges.get(idx).get(0)==currX) {
                    int height = edges.get(idx).get(1);

                    if (height > 0) {
                        live.offer(height);
                    } else {
                        past.offer(-height);
                    }
                    idx++;
                }

                while (!past.isEmpty() && live.peek().equals(past.peek())) {
                    live.remove();
                    past.remove();
                }

                int currentHeight = live.isEmpty() ? 0:live.peek();

                if (answer.isEmpty() || answer.get(answer.size() - 1).get(1)!=currentHeight) {
                    answer.add(Arrays.asList(currX, currentHeight));
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        Solution3 solution3 = new Solution3();
        Solution4 solution4 = new Solution4();

        int[][] buildings = {{0,2,3},{2,5,3}};

        List<List<Integer>> result1 = solution1.getSkyline(buildings);
        List<List<Integer>> result2 = solution2.getSkyline(buildings);
        List<List<Integer>> result3 = solution3.getSkyline(buildings);
        List<List<Integer>> result4 = solution4.getSkyline(buildings);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
