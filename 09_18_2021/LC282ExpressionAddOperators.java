package dolare.main.leetcode;

import java.util.*;

public class LC282ExpressionAddOperators {
    private final char[] operators = {'+', '-', '*'};
    public List<String> addOperators(String num, int target) {
        // Solution: backtracking
        // 1.use currResult to store/pass in result to next level
        // 2.use holdResult to store previous hold number in case need to be used to calculate multiply operation
        // corner case1: ["1*0+5","1*05","10-5"], 05 is illegle
        // corner case2: string may have too long digits not able to convert to Integer. need to use Long instead
        List<String> res = new ArrayList<>();

        backTracking(res, new StringBuilder(), num, 0, target, 0, 0);

        return res;
    }

    public void backTracking(List<String> result, StringBuilder solution, String num, int index,  int target, long currResult, long holdResult) {
        // System.out.println(solution.toString());
        if (index == num.length()) {
            if (currResult > Integer.MAX_VALUE) return;
            if ((int)currResult == target) result.add(solution.toString());
            return;
        }

        for (int i = index; i < num.length(); i++) {
            String str = num.substring(index, i + 1);
            if (str.length() >= 2 && str.charAt(0) == '0') break;
            long number = Long.valueOf(str);
            int oldLen = solution.length();
            if (index == 0) {
                solution.append(str);
                backTracking(result, solution, num, i + 1, target, number, number);
                solution.setLength(oldLen);
            } else {
                for (char c: operators) {
                    solution.append(c);
                    solution.append(str);
                    long newCurrResult = 0;
                    long newHoldResult = 0;
                    if (c == '+') {
                        newCurrResult = currResult + number;
                        newHoldResult = number;
                    } else if (c == '-') {
                        newCurrResult = currResult - number;
                        newHoldResult = -number;
                    } else {
                        newCurrResult = currResult - holdResult + holdResult * number;
                        newHoldResult = holdResult * number;
                    }
                    backTracking(result, solution, num, i + 1, target, newCurrResult, newHoldResult);
                    solution.setLength(oldLen);
                }
            }
        }
    }
}