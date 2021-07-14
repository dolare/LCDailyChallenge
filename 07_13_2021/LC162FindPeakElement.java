class LC162FindPeakElement {
    public int findPeakElement(int[] nums) {
        // use binray search to get the first/last element that less than its next element
        // eg: 1 5 3 4 7 5 -> get 7
        // eg: 1 5 3 4 7 9 -> get 9
        if (nums.length == 1) return 0;
        int left = 0, right = nums.length - 1;
        
        while(left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
