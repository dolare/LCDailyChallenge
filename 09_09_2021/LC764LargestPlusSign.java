package dolare.main.leetcode;

public class LC764LargestPlusSign {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        // dp[i][j][d] means the largest length in d direction for position[i][j]
        int[][][] dp = new int[n][n][4]; // 0-> left    1->top    2->right   3-> bottom
        // default is 0, then we set position in mines to -1 instead of 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = 1;
                }
            }
        }
        for (int[] mine: mines) {
            dp[mine[0]][mine[1]][0] = 0;
            dp[mine[0]][mine[1]][1] = 0;
            dp[mine[0]][mine[1]][2] = 0;
            dp[mine[0]][mine[1]][3] = 0;
        }
        // dp[i][j][0] = dp[i][j - 1][0] + 1;
        // dp[i][j][1] = dp[i - 1][j][1] + 1;
        // dp[i][j][2] = dp[i][j + 1][2] + 1;
        // dp[i][j][3] = dp[i + 1][j][3] + 1;
        for (int i = 0; i < n; i++) { // top left -> bottom right
            for (int j = 0; j < n; j++) {
                if (i == 0 && dp[i][j][1] != 0) { // first row -> top
                    dp[i][j][1] = 1;
                } else if (dp[i][j][1] != 0) {
                    dp[i][j][1] = dp[i - 1][j][1] + 1;
                }
                if (j == 0 && dp[i][j][0] != 0) { // first column -> left
                    dp[i][j][0] = 1;
                } else if (dp[i][j][0] != 0){
                    dp[i][j][0] = dp[i][j - 1][0] + 1;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) { // bottom right -> top left
            for (int j = n - 1; j >= 0; j--) {
                if (j == n - 1 && dp[i][j][2] != 0) { // last column -> right
                    dp[i][j][2] = 1;
                } else if (dp[i][j][2] != 0) {
                    dp[i][j][2] = dp[i][j + 1][2] + 1;
                }
                if (i == n - 1 && dp[i][j][3] != 0) { // last row -> bottom
                    dp[i][j][3] = 1;
                } else if (dp[i][j][0] != 0) {
                    dp[i][j][3] = dp[i + 1][j][3] + 1;
                }
            }
        }

        // find solution
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // System.out.println(dp[i][j][0] + " " + dp[i][j][1] + " " + dp[i][j][2] + " " + dp[i][j][3]);
                int tempMax = Math.min(dp[i][j][0], dp[i][j][1]);
                tempMax = Math.min(tempMax, dp[i][j][2]);
                tempMax = Math.min(tempMax, dp[i][j][3]);
                max = Math.max(max, tempMax);
            }
        }

        return max;
    }
}