package dolare.main.leetcode;

public class LC1275FindWinnerOnATicTacToeGame {
    public String tictactoe(int[][] moves) {
        int[] player = {1, -1};
        int[] rows = new int[3], cols = new int[3], diagonals = new int[2];

        int n = moves.length;

        for (int i = 0; i < n; i++) {
            int row = moves[i][0], col = moves[i][1];
            rows[row] += i % 2 == 0 ? 1 : -1;
            cols[col] += i % 2 == 0 ? 1 : -1;
            if (row == col) {
                diagonals[0] += i % 2 == 0 ? 1 : -1;
            }
            if (row == 2 - col) {
                diagonals[1] += i % 2 == 0 ? 1 : -1;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (rows[i] == 3 || cols[i] == 3 || (i < 2 && diagonals[i] == 3)) {
                return "A";
            } else if (rows[i] == -3 || cols[i] == -3 || (i < 2 && diagonals[i] == -3)) {
                return "B";
            }
        }

        return n < 9 ? "Pending" : "Draw";
    }
}