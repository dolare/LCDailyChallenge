package dolare.main.leetcode;

public class LC330PatchingArray {
    public int minPatches(int[] nums, int n) {
        // when we add k to the nums array, the sum set will be sum => sum[i] + k,
        long sumMax = 0;
        int index = 0,  sumCount = 0;;

        while(sumMax < n) {
            if (index < nums.length && nums[index] <= sumMax + 1) {
                sumMax += nums[index]; // new sumSet will be [1, sum] -> [1, sum] && [(1 + nums[index]) -> (sum +  nums[index])]; =>  newMaxSum = nums[index] + sum
                index++;
            } else { // when there is gap between [1, sum] && [1 + num, sum + num] =>
                sumMax += sumMax + 1; // add sum + 1 now to make range -> [1 sum + 1 + sum]
                sumCount++;
            }
        }
        return sumCount;
    }
}