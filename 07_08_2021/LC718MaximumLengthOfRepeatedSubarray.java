class LC718MaximumLengthOfRepeatedSubarray {
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        int left = 1, right = Math.min(m, n);
        
        while(left < right) {
            int mid = left + (right - left)/2;
            
            if (helper(nums1, nums2, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return helper(nums1, nums2, left) ? left : left - 1;
    }
    
    public boolean helper(int[] nums1, int[] nums2, int guess) {
        Set<List<Integer>> set = new HashSet();
        List<Integer> list = new LinkedList();
        System.out.println("++++" + guess);
        for (int i = 0; i < nums1.length; i++) {
            if(list.size() < guess) {
                list.add(nums1[i]);
            } else {
                list.remove(0);
                list.add(nums1[i]);
            }
            if (list.size() == guess) {
                set.add(new LinkedList(list));
            }
        }
        list = new LinkedList();
        for (int i = 0; i < nums2.length; i++) {
            if(list.size() < guess) {
                list.add(nums2[i]);
            } else {
                list.remove(0);
                list.add(nums2[i]);
            }
            if (list.size() == guess && set.contains(list)) {
                return true;
            }
        }
        
        return false;        
    }
}
