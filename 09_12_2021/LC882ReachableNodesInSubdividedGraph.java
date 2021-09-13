package dolare.main.leetcode;
import java.util.*;
public class LC882ReachableNodesInSubdividedGraph {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        //  [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3

        // construct the graph first
        Map<Integer, List<int[]>> graph = new HashMap(); // int[0] = nodeNumber, int[1] = newNodes between them
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[0], e -> new ArrayList()).add(new int[]{edge[1], edge[2] + 1});
            graph.computeIfAbsent(edge[1], e -> new ArrayList()).add(new int[]{edge[0], edge[2] + 1});
        }

        // find shortest path/nodes in undirected weighted graph
        // BFS + pq
        Queue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        queue.offer(new int[]{0, 0});
        int[] distance = new int[n]; // 0 - n-1 nodes
        Set<Integer> found = new HashSet();
        while(!queue.isEmpty()) {
            int[] currNode = queue.poll();
            if (found.contains(currNode[0])) continue;
            // System.out.println(Arrays.toString(currNode));
            distance[currNode[0]] = currNode[1]; // found the shortest path from 0 - currNode[0]
            found.add(currNode[0]);
            if (!graph.containsKey(currNode[0])) continue;
            for(int[] nextNode: graph.get(currNode[0])) {
                if (found.contains(nextNode[0]) || currNode[1] + nextNode[1] > maxMoves) continue;
                queue.offer(new int[]{nextNode[0], currNode[1] + nextNode[1]});
            }
        }

        // find all reachable nodes
        int nodes = 0;
        // add all small nodes first
        for (int[] edge: edges) {
            int addNodes = 0;
            if (found.contains(edge[0]) && distance[edge[0]] < maxMoves) {
                addNodes += maxMoves - distance[edge[0]];
            }
            if (found.contains(edge[1]) && distance[edge[1]] < maxMoves) {
                addNodes += maxMoves - distance[edge[1]];
            }
            // System.out.println(addNodes + "->" + Math.min(edge[2], addNodes));
            nodes += Math.min(edge[2], addNodes);
        }
        // System.out.println("1:" + distance[1]);
        // add big nodes
        nodes += found.size();

        return nodes;
    }
}