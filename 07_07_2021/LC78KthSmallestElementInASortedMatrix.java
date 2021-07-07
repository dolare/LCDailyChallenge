class LC78KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        
        // idea: 最小值永远出现在当前最小值的左下方或者已经被加入到pq中的元素
        int m = matrix.length;
        int n = matrix[0].length;
       
        // use set to avoid duplicate elements added to the pq
        Set<String> set = new HashSet();
        // use pq to poll the min element all the time
        Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
            return a[0] - b[0];
        });
        
        int[] firstElement = {matrix[0][0], 0, 0};
        pq.offer(firstElement);
        int count = 1;
        
        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            if (count++ == k) return temp[0];
            System.out.println(temp[0]);
            int newR = temp[1] + 1;
            int newC = temp[2] + 1;
            
            if (newR < m && !set.contains(newR + ";" + "temp[2]")) {
                int[] tempR = {matrix[newR][temp[2]], newR, temp[2]};
                set.add(newR + ";" + "temp[2]");
                pq.offer(tempR);
            }
            if (newC < n && !set.contains(temp[1] + ";" + newC)) {
                int[] tempC = {matrix[temp[1]][newC], temp[1], newC};
                set.add(temp[1] + ";" + newC);
                pq.offer(tempC);
            }
        }
        
        return -1;
        // brute force
//         List<Integer> list = new ArrayList();
        
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 list.add(matrix[i][j]);
//             }
//         }
//         Collections.sort(list);
//         return list.get(k - 1);
    }
}
