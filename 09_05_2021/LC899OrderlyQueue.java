package dolare.main.leetcode;

import java.util.Arrays;

public class LC899OrderlyQueue {
    public String orderlyQueue(String s, int k) {
        // only can rotate the string s
        String res = s;
        if (k == 1) {
            for (int i = 0; i < s.length(); i++) {
                String temp = s.substring(i, s.length()) + s.substring(0, i);
                if (temp.compareTo(res) < 0) {
                    res = temp;
                }
            }
        } else {
            // can convert string s to any order when k > 1
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            res = new String(charArray);
        }

        return res;
    }
}