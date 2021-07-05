package dolare.main.leetcode;

public class LC566ReshapeTheMatrix {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length;
        int m = mat[0].length;
        if (m * n != r * c) return mat;
        int cr = m * n / r; // how many columns needed in the new matrix
        int[][] res = new int[r][cr];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < cr; j++) {
                int rr = ((i * cr) + j) / m; // the row index in the original matrix
                int cc = ((i * cr) + j) % m; // the col index in the original matrix
                res[i][j] = mat[rr][cc];
            }
        }
        return res;
    }
}
