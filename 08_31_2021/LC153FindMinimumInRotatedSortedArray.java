public class LC153FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left)/2;
            // only min value is the bottom, corner cases: first and last element
            int prev = mid == 0 ? Integer.MAX_VALUE : nums[mid - 1];
            int next = mid == nums.length - 1 ? Integer.MAX_VALUE : nums[mid + 1];
            if (nums[mid] < prev && nums[mid] < next){
                return nums[mid];
            }
            // use >= here because sometimes, mid == left, then we need to move to the next elements
            if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}