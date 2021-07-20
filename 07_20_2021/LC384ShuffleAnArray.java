class LC384ShuffleAnArray {
    int[] originalNums;
    
    public Solution(int[] nums) {
        originalNums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return originalNums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shuffle = new int[originalNums.length];
        int index = 0;
        Set<Integer> set = new HashSet();
        while(set.size() < originalNums.length) {
            int random = (int) (Math.random() * originalNums.length);
            if (set.contains(random)) continue;
            shuffle[index++] = originalNums[random];
            set.add(random);
        }
        return shuffle;
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
