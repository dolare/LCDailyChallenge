public class LC132PalindromePartitioningII {
    public int minCut(String s) {

        // DP solution:
        // dp[i] -> the mincut for the substring(0, i)
        // dp[i] = Math.min(dp[0]....d[j]) + 1 only when substring(j + 1, i) is palindrom

        int n = s.length();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindrom(s, j, i)) {
                    if (j == 0) {
                        // the whole substring(0, i) is palindrom
                        dp[i] = 0;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }

        return dp[n - 1];
    }

    // can use dp[i][j] to maintin isPalindrom for substring(i, j),
    // TC: n^2 SC: n^2 to construct the two dimentional array
    // TC: 1 SC: 1 to get isPalindrom after creating the array above
    public boolean isPalindrom(String s, int left, int right) {
        while(left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}
