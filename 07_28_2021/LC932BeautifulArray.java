public class LC932BeautifulArray {
    public int[] beautifulArray(int n) {
        // [n] -> [2n]
        // [n * 2 - 1] + [n * 2]
        // {1} => {2} + {1} => {4, 2} + {3, 1} 
        // find 2*length, if 2 * length > n, then remove all elements <=n and keep the original order
        List<Integer> curr = new ArrayList();
        curr.add(1);
        int length = 1;
        while(length < n) {
            int[] left = new int[length];
            int[] right = new int[length];
            // initail left and right
            for (int i = 0; i < length; i++) {
                left[i] = curr.get(i) * 2;
                right[i] = curr.get(i) * 2 - 1;
            }
            // merge
            curr = new ArrayList();
            for (int i = 0; i < length; i++) {
                curr.add(left[i]);
            }
            for (int i = 0; i < length; i++) {
                curr.add(right[i]);
            }
            length *= 2;
        }

        int[] res = new int[n];

        int index = 0, indexList = 0;
        while(index < n) {
            if (curr.get(indexList) <= n) {
                res[index++] = curr.get(indexList++);
            } else {
                indexList++;
            }
        }

        return res;
    }  
}
