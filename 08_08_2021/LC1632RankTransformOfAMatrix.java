public class LC1632RankTransformOfAMatrix {
    public int[][] matrixRankTransform(int[][] matrix) {

        // Solution 1 : union find + greedy, union column and row numbers
        int m = matrix.length, n = matrix[0].length;

        Map<Integer, List<int[]>> map = new TreeMap();

        // store elements in map with their positions in incresing order
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                map.computeIfAbsent(matrix[r][c], e -> new ArrayList()).add(new int[]{r, c});
            }
        }

        int[] rank = new int[m + n];

        for (int key: map.keySet()) {
            List<int[]> list = map.get(key);
            // union column Number and row number (not the elements or index)
            UnionFind uf = new UnionFind(m + n);
            int[] tempRank = rank.clone();

            for (int[] position: list) {
                System.out.println(position[1]);
                int r = uf.find(position[0]), c = uf.find(position[1] + m);
                uf.union(r, c);
                tempRank[c] = Math.max(tempRank[r], tempRank[c]);
            }

            for (int[] position: list) {
                int r = position[0], c = position[1];
                matrix[r][c] =  tempRank[uf.find(r)] + 1;
                rank[r] = matrix[r][c];
                rank[c + m] = matrix[r][c];
            }
        }

        return matrix;
    }

    //ToDo:  Solution2:  Union Find + topologic sort


    class UnionFind {
        int[] parent;

        public UnionFind (int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            if (p != parent[p]) {
                p = find(parent[p]);
            }

            return p;
        }

        public boolean union(int p, int q) {
            int rootQ = find(q);
            int rootP = find(p);

            if (rootQ == rootP) return false; // already in same group
            parent[rootP] = rootQ;

            return false;
        }
    }
}