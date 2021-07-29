class LC542_01Matrix {
    public int[][] updateMatrix(int[][] mat) {
        Map<String, Integer> visited = new HashMap();
        int m = mat.length, n = mat[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = bfs(mat, i, j);
                }
            }
        }
        return res;
    }
    
    public int bfs(int[][] mat, int i, int j) {
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<String> queue = new PriorityQueue();
        Set<String> visitedCell = new HashSet();
        
        queue.offer(positionToString(i, j));
        visitedCell.add(positionToString(i, j));
        int steps = 0;
        
        while(!queue.isEmpty()) {
            List<String> currLevel = new ArrayList();
            
            while(!queue.isEmpty()) {
                String position = queue.poll();
                int currI = Integer.valueOf(position.split(",")[0]), currJ = Integer.valueOf(position.split(",")[1]);
                
                if (mat[currI][currJ] == 0) {
                    // System.out.println("found");
                    return steps;
                }
                currLevel.add(position);
            }
            
            for (String position: currLevel) {
                for (int[] direction: directions) {
                    int newI = Integer.valueOf(position.split(",")[0]) + direction[0];
                    int newJ = Integer.valueOf(position.split(",")[1]) + direction[1];
                    // System.out.println(newI + " " + newJ + " " + mat[newI][newJ]);
                    if (newI >= 0 && newI < mat.length && newJ >= 0 && newJ < mat[0].length) {
                        if (visitedCell.contains(positionToString(newI, newJ))) continue;
                        queue.offer(positionToString(newI, newJ));
                        visitedCell.add(positionToString(newI, newJ));
                    }
                }     
            }
            steps++;
        }
        
        return steps;
    }
    
    public String positionToString(int i, int j) {
        return i + "," + j;
    }
    
}
