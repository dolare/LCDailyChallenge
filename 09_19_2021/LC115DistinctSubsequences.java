package dolare.main.leetcode;

public class LC115DistinctSubsequences {
    public int numDistinct(String s, String t) {
        // Solution: DP
        // 1. dp[i][j] represents the number of subsequence in s ended at index i to match the t.substring(0, j + 1)
        // dp[i][j] = s[i] == t[j] : dp[i][j] = d[i - 1][j-1] + dp[i][j] : d[i-1][j]
        // xxxi  xxxxj  ->
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1];
                }

            }
        }
        // for (int[] arr: dp) {System.out.println(Arrays.toString(arr));}
        return dp[m][n];
    }
}