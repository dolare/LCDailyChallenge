package dolare.main.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

public class LC1220CountVowelsPermutation {
    /*
    DP solution: define the reversed A - U String array, then you can find the dp formular very quickly
    */
    private final String[] A_F = {"u", "e", "i"}; // 4, 1, 2
    private final String[] E_F = {"a", "i"}; // 0, 2
    private final String[] I_F = {"e", "o"}; // 1, 3
    private final String[] O_F = {"i"}; // 2
    private final String[] U_F = {"i", "o"}; // 2, 3


    public int countVowelPermutation(int n) {
        // dp[i][j] = how many permutation when the number of letters is j and last element is i
        final long MOD = 1000000007;
        long[][] dp = new long[5][n + 1];
        long count = 0;
        dp[0][1] = 1; // 0 = a
        dp[1][1] = 1; // 1 = e
        dp[2][1] = 1; // 2 = i
        dp[3][1] = 1; // 3 = o
        dp[4][1] = 1; // 4 = u
        for (int i = 2; i <= n; i++) {
            dp[0][i] = (dp[4][i - 1] + dp[1][i - 1] + dp[2][i - 1]) % MOD;
            dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD;
            dp[2][i] = (dp[1][i - 1] + dp[3][i - 1]) % MOD;
            dp[3][i] = (dp[2][i - 1]) % MOD;
            dp[4][i] = (dp[2][i - 1] + dp[3][i - 1]) % MOD;
        }
        for (int i = 0; i <= 4; i++) {
            count += dp[i][n];
        }
        return (int)(count % MOD);
    }


    /*
    TLE: backtracking/bruteForce solution
    */
    private final String[] A = {"e"};
    private final String[] E = {"a", "i"};
    private final String[] I = {"a", "e", "o", "u"};
    private final String[] O = {"i", "u"};
    private final String[] U = {"a"};
    private final String[] V = {"a", "e", "i", "o", "u"};
    private AtomicInteger count = new AtomicInteger(0);

    public int countVowelPermutation1(int n) {
        dfs(V, new StringBuilder(), n);
        return count.get();
    }

    public void dfs(String[] currentAvailableVowels, StringBuilder solution, int n) {

        if (solution.length() == n) {
            count.incrementAndGet();
            return;
        }

        for (String v: currentAvailableVowels) {
            solution.append(v);
            switch(v){
                case "a":
                    dfs(A, solution, n);
                    break;
                case "e":
                    dfs(E, solution, n);
                    break;
                case "i":
                    dfs(I, solution, n);
                    break;
                case "o":
                    dfs(O, solution, n);
                    break;
                case "u":
                    dfs(U, solution, n);

            }
            solution.setLength(solution.length() - 1);
        }
    }
}
