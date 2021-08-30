package dolare.main.leetcode;

import java.util.*;

public class LC1235MaximumProfitInJobScheduling {
    //  ## similar to 435 but use dp to solve
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // dp[i] represent the max profit for 0 -
        // dp[i] = dp[j_MAX] + profit(j -> i) // j is the closet j to the start i
        // xxxxxxxxj_end xxxx  [j_new_start xxxxx i]
        //    use binary search find j_end and j_new = start[i], i = end[i]
        int n = startTime.length;
        List<int[]> jobs = new ArrayList();

        for (int i = 0; i < n; i++) {
            jobs.add(new int[]{startTime[i], endTime[i], profit[i]});
        }
        // sort by the end time (for non-overlapping, usually sort by endtime)
        Collections.sort(jobs, (a, b) -> a[1] - b[1]);

        Map<Integer, Integer> dp = new HashMap();

        int max = 0;

        for (int i = 0; i < n; i++) {
            int currStart = jobs.get(i)[0];
            int prevEnd = search(jobs, currStart);
            // System.out.println(currStart + " " + prevEnd);
            int currMax = dp.getOrDefault(prevEnd, 0) + jobs.get(i)[2];
            max = Math.max(max, currMax);
            dp.put(jobs.get(i)[1], max); // need to put the max not the current max
        }

        return max;

    }

    public int search(List<int[]> jobs, int time) {// the last time that <= time
        int left = 0, right = jobs.size() - 1;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if (jobs.get(mid)[1] < time) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (jobs.get(left)[1] == time) {
            return time;
        } else if (left == 0) {
            return 0;
        } else {
            return jobs.get(left - 1)[1];
        }
    }
}