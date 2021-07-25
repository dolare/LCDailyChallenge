public class LC600NonnegativeIntegersWithoutConsecutiveOnes {
    public int findIntegers(int n) {
        int res = 0;
        System.out.println(intToBinaryString(5));

        int[] dp = new int[33]; // 0 - Integer.MAX_VLAUE;

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i <= 32; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // **** --> 10**** + ****0
        }

        String str = intToBinaryString(n);

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') continue;
            res += dp[str.length() - i - 1];
            if (i > 0 && str.charAt(i - 1) == '1') {
                return res;
            }

        }
        return res + 1;
    }

    public String intToBinaryString(int num) {
        StringBuilder sb = new StringBuilder();
        while(num > 0) {
            sb.insert(0, num & 1);
            num >>= 1;
        }
        return sb.toString();
    }
}
