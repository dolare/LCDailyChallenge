class LC1338ReduceArraySizeToTheHalf {
    public int minSetSize(int[] arr) {
        int[] frequency = new int[100001];
        
        // Count the frequency of each integer in the array.
        for (int i: arr) {
            frequency[i]++;
        }
        
        // sort the frequency array then we can get maximum frequency count
        Arrays.sort(frequency);
        
        int count = 0, res = 0;
        
        // add count until count >= half elements
        for(int i = frequency.length - 1; i >= 0; i--) {
            if (count >= arr.length/2) break;
            else {
                count += frequency[i];
                res++;
            }
        }
        
        return res;
    }
}
