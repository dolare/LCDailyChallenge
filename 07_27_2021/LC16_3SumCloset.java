public class LC16_3SumCloset {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        long res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) return target;
                else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
                if (Math.abs(target - sum) < Math.abs(target - res)) {

                    res = sum;
                }
            }
        }
        return (int)res;
    }
}
