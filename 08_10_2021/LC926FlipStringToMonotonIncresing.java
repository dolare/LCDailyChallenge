public class LC926FlipStringToMonotonIncresing {
    public int minFlipsMonoIncr(String s) {
        // dp[i][0] min flips to make substring(0, i) increading when ending with 0
        // dp[i][1] min flips to make substring(0, i) increading when ending with 1
        // dp[i][0] = min(dp[i - 1][0], dp[i - 1][0] + 1)
        // dp[i][1] = min(dp[i - 1][0] + 1, dp[i - 1][1])
        //dp[0][0] = s.charAt(0) == 0 ? 0 : 1
        //dp[0][1] = s.charAt(0) == 1 ? 0 : 1
        int n = s.length();

        int[][] dp = new int[n][2];
        dp[0][0] = s.charAt(0) == '0' ? 0 : 1;
        dp[0][1] = s.charAt(1) == '1' ? 0 : 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = s.charAt(i) == '0' ? dp[i - 1][0] : dp[i - 1][0] + 1;
            dp[i][1] = s.charAt(i) == '1' ? Math.min(dp[i - 1][1], dp[i - 1][0]) : Math.min(dp[i - 1][0] + 1, dp[i - 1][1] + 1);
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
}