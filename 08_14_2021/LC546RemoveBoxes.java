package dolare.main.leetcode;

public class LC546RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;

        // top -> bottom solution
        // f(i, j, k) stands for the max points when removing elements from (i, j) + the k elements that equals boxes[j]
        // f(i, j, k) = max(f(i, j - 1, 0) + (k + 1)^2, (f(l, i, k + 1) + f(i + 1, r - 1, 0)))
        // final solution then is f(0, n - 1, 0) removing all the elements from (0, n - 1)
        int[][][] mem = new int[n][n][n];

        return removeBoxes(boxes, 0, n - 1, 0, mem);
    }

    public int removeBoxes(int[] boxes, int left, int right, int k, int[][][] mem) {
        if (left > right) return 0;
        if (mem[left][right][k] != 0) return mem[left][right][k];

        int max = removeBoxes(boxes, left ,right - 1, 0, mem) + (k + 1) * (k + 1);
        for (int i = left; i < right; i++) {
            if (boxes[i] == boxes[right]) {
                max = Math.max(max, removeBoxes(boxes, left, i, k + 1, mem) + removeBoxes(boxes, i + 1, right - 1, 0, mem));
            }
        }

        mem[left][right][k] = max;
        return max;
    }
}
