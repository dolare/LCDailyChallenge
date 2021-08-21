public class LC36ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] setR = new int[10];
            int[] setC = new int[10];
            // row on column i | column on row i validation
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                char r = board[j][i];
                // check by col
                if (c != '.' && setC[c - '1'] == 0) {
                    setC[c - '1']++;
                } else if(c != '.') {
                    return false;
                }
                // validate by row
                if (r != '.' && setR[r - '1'] == 0) {
                    setR[r - '1']++;
                } else if (r != '.') {
                    return false;
                }

            }
            // i stands for block 0 - 9 validation
            int[] setB = new int[10]; // r = ((boxNumber) / 3) * 3 c = boxNumbre % 3 * 3
            for (int r = (i / 3) * 3; r <= (i / 3) * 3 + 2; r++) {
                for (int c = (i % 3 * 3); c <= (i % 3 * 3) + 2; c++) {
                    char b = board[r][c];
                    if (b != '.' && setB[b - '1'] == 0) {
                        setB[b - '1']++;
                    } else if (b != '.') {
                        System.out.println(c + " " + r + "bbbb");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}