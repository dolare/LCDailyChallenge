class LC542_01Matrix {
    public int[][] updateMatrix0(int[][]) {
        // dp solution:
        // fomular: dp[i][j] = Math.min(dp[newI][newJ]);  newI and newJ coming from four different directions
        // we can start from left top then can determin direction right and down
        // then scan from right bottom then can deternmin direction left and up
        int m = mat.length, n = mat[0].length;
        int[][] directionRightDown = {{-1, 0}, {0, -1}};
        int[][] directionLeftUp = {{0, 1}, {1, 0}};
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = mat[i][j] == 0 ? 0 : 1000000;
            }
        }
        
        // to right and down
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] direction: directionRightDown) {
                    int newI = i + direction[0], newJ = j + direction[1];
                    if (newI >= 0  && newJ >= 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[newI][newJ] + 1);
                    }
                }
            }
        }
        
        // to right and down
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int[] direction: directionLeftUp) {
                    int newI = i + direction[0], newJ = j + direction[1];
                    if (newI < m && newJ < n) {
                        dp[i][j] = Math.min(dp[i][j], dp[newI][newJ] + 1);
                    }
                }
            }
        }
        
        return dp;
    }
    
    public int[][] updateMatrix(int[][] mat) {
        // BFS Solution, should push all 0 position to the queue, then can avoid many duplicate calculation.
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
