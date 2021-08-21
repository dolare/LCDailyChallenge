public class LC37SudokuSolver {
    boolean[][] rowSet = new boolean[9][9]; // r is xth row, c store the number
    boolean[][] colSet = new boolean[9][9]; // r is xth column, c store the number
    boolean[][] boxSet = new boolean[9][9]; // boxNumber is r/3 * 3 + c/3

    public void solveSudoku(char[][] board) {
        // default boolean value is false
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num == '.') continue;
                rowSet[i][num - '1'] = true;
                colSet[j][num - '1'] = true;
                boxSet[i/3 * 3 + j / 3][num - '1'] = true;
            }
        }

        backTracking(board, 0);
    }

    public boolean backTracking(char[][] board, int cell) {
        // dfs from cell 0 -> cell 80 (81 elements)
        // can get i and j from cell
        // for each cell, if no number can fill from 0 - 9, then return false
        // then -> fill the number and go to next cell
        // once we find any false result, need to go back to last cell and reset board and each set(backtracking)
        if (cell == 81) return true;
        int i = cell / 9, j = cell % 9;
        // fill in numbers
        System.out.println(i + " " + j + " " + cell);
        if (board[i][j] != '.') {
            return backTracking(board, cell + 1);
        }
        for (int num = 1; num <= 9; num++) {
            if (!rowSet[i][num - 1] && !colSet[j][num - 1] && !boxSet[i/3 * 3 + j/3][num - 1]) {
                board[i][j] = (char) (num + '0');
                rowSet[i][num - 1] = true;
                colSet[j][num - 1] = true;
                boxSet[i/3 * 3 + j/3][num - 1] = true;
                if (backTracking(board, cell + 1)) {
                    return true;
                }
                board[i][j] = '.';
                rowSet[i][num - 1] = false;
                colSet[j][num - 1] = false;
                boxSet[i/3 * 3 + j/3][num - 1] = false;
            }
        }

        return false;
    }
}
