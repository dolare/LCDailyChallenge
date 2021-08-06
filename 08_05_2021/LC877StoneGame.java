package dolare.main.leetcode;

import java.util.Arrays;

public class LC877StoneGame {
    // from top to bottom:
    // f(i, j) -> the most diff points alex can take = Math.max(piles(i) - f(i + 1, j), piles(j) - f(i, j-1))
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] mem = new int[n][n];
        for (int[] arr: mem) {
            Arrays.fill(arr, Integer.MIN_VALUE);
        }
        return pickStones(piles, 0, n - 1, mem) > 0;
    }

    // current player can pick either i or j
    public int pickStones(int[] piles, int i, int j, int[][] mem) {
        if (j == i) return piles[i];

        if (mem[i][j] != Integer.MIN_VALUE) return mem[i][j];

        // from top to bottom, the first pick is always from alex
        int alexPickLeft = piles[i];
        int leePickThenLeft = pickStones(piles, i + 1, j, mem);

        int alexPickRight = piles[j];
        int leePickThenRight = pickStones(piles, i, j - 1, mem);
        mem[i][j] = Math.max(alexPickLeft - leePickThenLeft, alexPickRight - leePickThenRight);

        return mem[i][j] ;
    }

    // DP Solution:
    // dp[i][j] represents how many scores alex can take more than lee, when alex is picking on i or j
    // dp[i][j] = math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
    public boolean stoneGameDP(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int l = 0; l < n; l++) {
                int r = n - l - 1;
                dp[l][r] = Math.max(piles[l] - (r - 1 < 0 ? 0 : dp[l][r - 1]),
                        piles[r] - (l + 1 == n ? 0 : dp[l + 1][r]));
            }
        }
        return dp[0][n - 1] > 0;
    }
}