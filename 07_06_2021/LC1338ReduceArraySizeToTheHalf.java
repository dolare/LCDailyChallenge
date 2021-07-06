class LC1338ReduceArraySizeToTheHalf {
    public int minSetSize(int[] arr) {
        int[] frequency = new int[100001];
        
        // Count the frequency of each integer in the array.
        for (int i: arr) {
            frequency[i]++;
        }
        
        // define a maxHeap using prioirtyQueue
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return frequency[b] - frequency[a];
        });
        
        
        // store frequencis in the maxHeap
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != 0) {
                queue.offer(i);
            }
        }
        
        // use maxHeap to pop the maximun frequency count all the time until the removed elements reach half of the intergers in the array
        int count = 0, res = 0;
        while(!queue.isEmpty() && count < arr.length/2) {
            count += frequency[queue.poll()];
            System.out.println(count);
            res++;
        }
        
        return res;
    }
}
