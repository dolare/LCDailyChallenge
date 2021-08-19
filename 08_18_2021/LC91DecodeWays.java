public class LC91DecodeWays {
    public int numDecodings(String s) {
        // dp[i] means the number of ways to decode substring(0, i - 1);
        // xxxxx 2 s[i] => xxxxx|21 - 26/ xxxxxx2| 1-6         = dp[i - 2] * dp[i - 1]
        // xxxxx 2 s[i] => xxxxx|2 0        = dp[i - 2]
        // xxxxx 2 s[i] => xxxxx|2 7        = dp[i - 1]
        // xxxxx 1 s[i] => 11 - 19         = = dp[i - 2] * dp[i - 1]
        // xxxxx 1 s[i] => 10     = dp[i - 2]
        // xxxxx 3 s[i] => xxxxx| 1- 9    = dp[i - 1]
        // xxxxx 3 s[i] => xxxxx| 0    = dp[i - 1] not possible
        // xxxxx x s[i] => 1 - 9           += dp[i - 1];
        // xxxxx 0     only match dp[i - 1]

        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = s.charAt(0) > '0' && s.charAt(0) <= '9' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            char prev = s.charAt(i - 2);
            char curr = s.charAt(i - 1);
            if ((prev == '2' && curr >= '1' && curr <= '6') ||
                    (prev == '1' && curr >= '1' && curr <= '9')){
                dp[i] = dp[i - 2] + dp[i - 1];
            }
            if ((prev == '2' || prev == '1') && curr == '0') {
                dp[i] = dp[i - 2];
            }
            if ((prev >= '3' || prev == '0') && curr >= '1' && curr <= '9'){
                dp[i] = dp[i - 1];
            }
            if (prev == '2' && curr >= '7') {
                dp[i] = dp[i - 1];
            }
            if ((prev >= '3' || prev == '0') && curr == '0') {
                dp[i] = 0;
            }
        }

        return dp[n];
    }
}