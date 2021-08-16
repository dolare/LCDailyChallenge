package dolare.main.leetcode;

public class LC76MinimumWindowSubstring {
/*  Sliding wondow solution:   HashMap (store occurencis of character in string t)    + two pointers
1. Use two pointers: start and end to represent a window.
2. Move end to find a valid window.
3. When a valid window is found, move start to find a smaller window.
*/

    public String minWindow(String s, String t) {
        int[] charsT = new int[128];
        int[] charsS = new int[128];

        int res = Integer.MAX_VALUE, left = 0, right = 0, leftRes = 0, rightRes = 0;

        for (char c: t.toCharArray()) {
            charsT[c - 'A']++;
        }

        while(left <= right || right < s.length() - 1) {
            if (ifContainsT(charsT, charsS)){
                if (right - left + 1 < res) {
                    res = right - left + 1;
                    leftRes = left;
                    rightRes = right;
                    System.out.println(left + " " + right);
                }
                charsS[s.charAt(left) - 'A']--;
                left++;
            } else if (right < s.length()) {
                System.out.println(right);
                charsS[s.charAt(right++) - 'A']++;
            } else {
                break;
            }
        }

        return s.substring(leftRes, rightRes);
    }

    public boolean ifContainsT(int[] charsT, int[] charsS) {
        for (int i = 0; i < 128; i++) {
            if(charsT[i] == 0) continue;
            if (charsT[i] - charsS[i] > 0) return false;
        }

        return true;
    }
}