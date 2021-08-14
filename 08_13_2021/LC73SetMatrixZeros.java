package dolare.main.leetcode;

public class LC73SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // two varaible solution
        // Follow up: one variable solution
        boolean isFirstRowZero = false;
        boolean isFirstColumnZero = false;

        // maintain varaibles above to check if first row and first column will be set to zero later,
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                isFirstRowZero = true;
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                isFirstColumnZero = true;
                break;
            }
        }


        // store the zero info for each column and row to firstRow and firstColumn
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // set zeros based on zero info stored on the first row and column
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // finally handle the first row and column
        if (isFirstRowZero) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (isFirstColumnZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }



        // One varaible solution

        // Only use isFirstColumnZero to check if first column is zero and use matrix[0][0] to
        // check if first row is zero
        // boolean isFirstColumnZero = false;
        // for (int i = 0; i < m; i++) {
        //     if (matrix[i][0] == 0) {
        //         isFirstColumnZero = true;
        //     }
        //     // store column row zero info to the first row and column
        //     for (int j = 1; j < n; j++) {
        //         if (matrix[i][j] == 0) {
        //             matrix[i][0] = matrix[0][j] = 0;
        //         }
        //     }
        // }
        // for (int i = m - 1; i >= 0; i--) {
        //     for (int j = 1; j < n; j++) {
        //         if (matrix[i][0] == 0 || matrix[0][j] == 0) {
        //             matrix[i][j] = 0;
        //         }
        //     }
        //     if (isFirstColumnZero) {
        //         matrix[i][0] = 0;
        //     }
        // }
    }
}