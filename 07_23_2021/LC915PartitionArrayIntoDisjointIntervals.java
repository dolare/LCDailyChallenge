class LC915PartitionArrayIntoDisjointIntervals {
    public int partitionDisjoint(int[] nums) {
        // O(n) + O(1) solution
        int max = nums[0];
        int currMax = max;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < max) {
                max = currMax; // only updae currMax when found smaller one
                res = i + 1;
            } else {
                currMax = Math.max(currMax, nums[i]);
            }
        }
        return res;
        // O(n) + O(n) solution
        // int[] maxLeft = new int[nums.length];
        // int[] minRight = new int[nums.length];
        // int max = nums[0], min = nums[nums.length - 1];
        // for (int i = 0; i < maxLeft.length; i++) {
        //     max = Math.max(nums[i], max);
        //     maxLeft[i] = max;
        // }
        // for (int i = minRight.length - 1; i >= 0; i--) {
        //     min = Math.min(nums[i], min);
        //     minRight[i] = min;
        // }
        // for (int i = 0; i < nums.length - 1; i++) {
        //     if (maxLeft[i] <= minRight[i + 1]) {
        //         return i + 1;
        //     }
        // }
        
        // return 0;
    }
}
