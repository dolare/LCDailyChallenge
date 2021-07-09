public class LC300LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        //Solution 1 O(nlogn):  maintain tail[k] represent the tail value in nums that can keep max length subsequence is k
        List<Integer> tails = new ArrayList();
        tails.add(nums[0]);
        for (int i = 1; i < n; i++) {
            // find and update index + 1 of first element that is less than nums[i] (from tail to head)
            int left = 0, right = tails.size();
            while(left < right) {
                int mid = left + (right - left) / 2;
                if (tails.get(mid) < nums[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // System.out.println(left + " " + right);
            if (left == tails.size()) tails.add(nums[i]);
            else {
                tails.set(left, nums[i]);
            }
            // System.out.println(tails);
        }


        return tails.size();

        // DP solution(n^2): dp[i] = dp[j] + 1, j = the max index in dp array where nums[j] < nums[i]
//         int[] dp = new int[n];
//         Arrays.fill(dp, 1);
//         int res = 1;

//         for(int i = 1; i < n; i++) {
//             int max = 0;
//             for (int j = 0; j < i; j++) {
//                 if (nums[j] < nums[i]) {
//                     max = Math.max(max, dp[j]);
//                 }
//             }
//             dp[i] = max + 1;
//             res = Math.max(dp[i], res);
//         }

//         return res;
    }
}
