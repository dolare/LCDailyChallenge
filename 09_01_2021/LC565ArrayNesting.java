public class LC565ArrayNesting {
    public int arrayNesting(int[] nums) {
        // Solution1: regular DFS and set to avoid double counting
        Set<Integer> setForAll = new HashSet();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(setForAll.contains(i)) continue;
            Set<Integer> setForDfs = new HashSet();
            count = Math.max(count , dfs(nums, i, setForDfs));
            setForAll.addAll(setForDfs);
        }

        return count;
    }

    public int dfs(int[] nums, int i, Set<Integer> set) {
        if (set.contains(i)) {
            return 0;
        }
        set.add(i);
        return dfs(nums, nums[i], set) + 1;
    }


    //solution2: is it possible to use union find to solve this problem?
    class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind (int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i; // initailly every node is root/its parent
            }
        }

        public int find(int p) {
            if (p != parent[p]) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }

        public int union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) {
                return -1; // already unioned
            }

            if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
                rank[rootP]++;
            } else {
                parent[rootP] = rootQ;
                rank[rootQ]++;
            }

            return Math.max(rank[rootP], rank[rootQ]);
        }
    }
}