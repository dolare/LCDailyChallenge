class LC18_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet();
        
        for(int i = 1; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int left = 0, right = nums.length - 1;
                while(left < i && right > j) {
                    int sum = nums[left] + nums[right] + nums[i] + nums[j];
                    // System.out.println(nums[left] + " " + nums[right] + " " + nums[i] + " " + nums[j]);
                    if (sum == target) {
                        List<Integer> solution = new ArrayList();
                        solution.add(nums[left]);
                        solution.add(nums[right]);
                        solution.add(nums[i]);
                        solution.add(nums[j]);
                        set.add(solution);
                        left++;
                        right--;
                    } else if(sum > target) {
                        right--;
                    } else if(sum < target) {
                        left++;
                    } 
                }
            }
        }
        return new ArrayList(set);
    }
}
