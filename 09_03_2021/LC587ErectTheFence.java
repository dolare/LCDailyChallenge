public class LC587ErectTheFence {
    public int[][] outerTrees(int[][] trees) {
        // Solution:
        // sort all the points and swap/scan one by one
        // if see any three points are not convex hull, pop the last points and check again.
        /// untill all the continous three points in the stack are convex hull
        Arrays.sort(trees, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        Stack<int[]> points = new Stack();
        // add all convex hull and ingore others from left to right
        for (int[] tree: trees) {
            int size = points.size();
            while(size >= 2 &&
                    orientation(points.get(size - 2), points.get(size - 1), tree)) {
                // pop it when it is not convex hull
                points.pop();
                size--;
            }
            points.push(tree);
        }

        // connect the points from right to left again to build the circle
        for (int i = trees.length - 1; i >= 0; i--) {
            int size = points.size();
            while(size >= 2 &&
                    orientation(points.get(size - 2), points.get(size - 1), trees[i])) {
                // pop it when it is not convex hull
                points.pop();
                size--;
            }
            points.push(trees[i]);
        }

        // the circle created may have duplicate points, remove them
        Set<int[]> pointsSet = new HashSet();
        while(!points.isEmpty()) {
            pointsSet.add(points.pop());
        }

        // construct result as an array
        int[][] res = new int[pointsSet.size()][2];
        int index = 0;
        for(int[] key: pointsSet) {
            res[index++] = key;
        }
        return res;
    }

    public boolean orientation(int[] p1, int[] p2, int[] p3) {
        if ((p1[1] - p2[1]) * (p3[0] - p1[0]) - (p1[0] - p2[0]) * (p3[1] - p1[1]) > 0) {
            return true;
        } else {
            return false;
        }
    }
}