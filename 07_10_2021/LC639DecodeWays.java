class LC91DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        final long MOD = 1000000007;
        int n = s.length();
        long[] dp = new long[n];
        // initial dp[0]
        if (s.charAt(0) == '*') {
            dp[0] = 9;
        } else if (s.charAt(0) == '0') {
            dp[0] = 0;
        } else {
            dp[0] = 1;
        }
        // dp[i] = dp[i - 1] * a + dp[i - 2] * b
        for (int i = 1; i < n; i++) {
            char c2 = s.charAt(i);
            char c1 = s.charAt(i - 1);
            int a = 0, b = 0;
            if (c2 == '*') {
                a = 9; // 1 - 9
            } else if (c2 == '0') {
                a = 0; // no match
            } else {
                a = 1; // 1
            }
            if (c1 == '0') {
                b = 0;
            } else if (c1 == '*') {
                if (c2 == '*') {
                    b = 15; // 11 - 19, 21 - 26
                } else if (c2 > '6') {
                    b = 1; // 17
                } else {
                    b = 2; // 13, 23
                }
            } else if (c1 == '1') {
                if (c2 == '*') {
                    b = 9; // 11 - 19
                } else {
                    b = 1; // 10
                }
            } else if (c1 == '2') {
                if (c2 == '*') {
                    b = 6; // 21 - 26
                } else if (c2 > '6') {
                    b = 0; // no match
                } else {
                    b = 1; // 23
                }
            } else {
                b = 0;
            }
            long dpi_2 = i == 1 ? 1 : dp[i - 2];
            dp[i] = (dp[i - 1] * a + dpi_2 * b) % MOD;
            
        }
        
        return (int)(dp[n - 1] % MOD);
    }
}
