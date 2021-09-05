package dolare.main.leetcode;
import java.util.*;
public class LC834SumOfDIstancesInTree {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (edges == null || edges.length == 0) return new int[]{0};
        // O(n) + O(n)
        // build the directed graph using adjencency List
        int[] res = new int[n];
        int[] subTreeNodes = new int[n]; // count number of subtree nodes
        TreeMap<Integer, Set<Integer>>  tree = new TreeMap();

        // construct tree
        for (int[] edge: edges) {
            tree.computeIfAbsent(edge[0], e -> new HashSet()).add(edge[1]);
            tree.computeIfAbsent(edge[1], e -> new HashSet()).add(edge[0]);
        }

        // dfs from node 0
        preOrder(0, -1, tree, res, subTreeNodes);
        postOrder(0, -1, tree, res, subTreeNodes);

        return res;
    }

    // count the number of the nodes in the root's subtree
    public void preOrder(int root, int pre, TreeMap<Integer, Set<Integer>>  tree, int[] res, int[] subTreeNodes) {
        for (int curr : tree.get(root)) {
            if (curr != pre) {
                preOrder(curr, root, tree, res, subTreeNodes);
                subTreeNodes[root] += subTreeNodes[curr];
                res[root] += res[curr] + subTreeNodes[curr];
            }
        }
        subTreeNodes[root]++;
    }

    public void postOrder(int root, int pre, TreeMap<Integer, Set<Integer>>  tree, int[] res, int[] subTreeNodes) {
        for (int curr: tree.get(root)) {
            if (curr != pre) {
                res[curr] = res[root] - subTreeNodes[curr] + res.length - subTreeNodes[curr];
                postOrder(curr, root, tree, res, subTreeNodes);
            }
        }
    }


}