class LC611ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        
        Arrays.sort(nums);
        
        int res = 0;
        for (int i = 2; i < nums.length; i++) {
            int left = 0, right = i - 1;
            
            while(left < right) {
                if(right > left && nums[left] + nums[right] > nums[i]) {
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        
        return res;
    } 
}
