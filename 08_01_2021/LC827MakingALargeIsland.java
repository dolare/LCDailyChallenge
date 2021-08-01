import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LC827MakingALargeIsland {
    // Solution 1： dfs, visie 1 cell and find the island size for each 1 cell
    //  then combine 0 cell's 4 direction's non-duplcate island'size
    // solution 2: Union Find: same as dfs, but use union find to get size and check the island
    public int largestIsland(int[][] grid) {
        UnionFind uf = new UnionFind();
        int n = grid.length;
        int[][] directions = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
        int max = 0;
        List<int[]> zeroCells = new ArrayList();
        // initial uf
        // construct the unionfind set
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    zeroCells.add(new int[]{i, j});
                    continue;
                }
                String curr = generatePosition(i, j);
                uf.union(curr, curr);
                for (int[] direction: directions) {
                    int newI = i + direction[0];
                    int newJ = j + direction[1];
                    String newCurr = generatePosition(newI, newJ);
                    if (newI < n && newI >= 0 && newJ < n && newJ >= 0 && grid[newI][newJ] == 1) {
                        uf.union(curr, newCurr);
                    }
                }
                // max = Math.max(max, uf.getMaxSetSize());
                max = Math.max(max, uf.maxSize);
            }
        }
        // test each 0 cell
        for (int[] zeroCell: zeroCells) {
            int i = zeroCell[0], j = zeroCell[1];
            String curr = generatePosition(i, j); // don't need to real union this time, just find root size
            int tempMax = 1;
            Set<String> possibleNewRoot = new HashSet();
            for (int[] direction: directions) {
                int newI = i + direction[0];
                int newJ = j + direction[1];
                String newCurr = generatePosition(newI, newJ);
                if (newI < n && newI >= 0 && newJ < n && newJ >= 0 && grid[newI][newJ] == 1) {
                    possibleNewRoot.add(uf.findRoot(newCurr));
                }
            }
            for (String root: possibleNewRoot) {
                if (root == null) continue;
                tempMax += uf.getSetSize(root);
            }
            // TLE
            // max = Math.max(max, newUf.getMaxSetSize());
            max = Math.max(max, tempMax);
        }

        return max;
    }


    public String generatePosition(int i, int j) {
        return i + "," + j;
    }

    class UnionFind {

        public Map<String, String> parent;
        public Map<String, Integer> rank;
        public int maxSize = 0;

        public UnionFind() {
            parent = new HashMap();
            rank = new HashMap();
        }

        public UnionFind(UnionFind uf) {
            // new from existing one
            parent = new HashMap(uf.parent);
            rank = new HashMap(uf.rank);
        }
        public int getSetSize(String root) {
            return rank.get(root);
        }
        public String findRoot(String p) {
            if (!parent.containsKey(p)) return null;
            while(!p.equals(parent.get(p))) {
                p = parent.get(p);
            }
            return p;
        }

        public String find(String p) {
            if (!parent.containsKey(p)) {
                rank.put(p, 1);
                parent.put(p, p);
            }

            if(!p.equals(parent.get(p))) {
                String str = find(parent.get(p));
                parent.put(p, str);
            }

            return parent.get(p);
        }

        public boolean union(String p, String q) {
            if (p.equals(q)) maxSize = Math.max(1, maxSize);
            String rootP = find(p);
            String rootQ = find(q);
            if (rootP.equals(rootQ)) {
                return false; // already in same unionset
            }

            // Key Point: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            if (rank.get(rootP) >= rank.get(rootQ)) {
                parent.put(rootQ, rootP);
                rank.put(rootP, rank.get(rootP) + rank.get(rootQ)); // this step is very important
            } else {                                        // sometimes not all the child pointed to the root parent, so that we need to add its count when union this kind of child
                rank.put(rootQ, rank.get(rootQ) + rank.get(rootP)); // this step is very important
                parent.put(rootP, rootQ);
            }
            maxSize = Math.max(maxSize , Math.max(rank.get(rootP), rank.get(rootQ)));
            return true;

        }
    }
}