package dolare.main.leetcode;

import java.util.Stack;

public class LC224BasicCalculator {
    public int calculate(String s) {
        // Solution: using two stacks, one is for numbers and the other is for signs
        // Notes:
        // 1.need to add + sign at the begaining if charAt(0) != '-'
        // 2.need to add + sign when we see '(' sign
        // 3. then handle sign and number separatly and calculate when meeting the ')' sign
        Stack<Integer> numbers = new Stack();
        Stack<Character> signs = new Stack();
        int res = 0;
        s = s.replaceAll(" ", "");
        int n = s.length();
        if (s.charAt(0) != '-') signs.push('+');
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // handle the signs
            if (c < '0' || c > '9') {
                // do calculation when meet )
                if (c == '(') {
                    signs.push(c);
                    signs.push('+');
                } else if (c == ')') { // need to calculate
                    int newNumber = 0;
                    while(!signs.isEmpty() && signs.peek() != '(') {
                        if (signs.pop() == '-') {
                            newNumber -= numbers.pop();
                        } else {
                            newNumber += numbers.pop();
                        }
                    }
                    if (!signs.isEmpty() && signs.peek() == '(') {
                        signs.pop();
                    }
                    numbers.push(newNumber);
                } else if (c != ' ') {
                    signs.push(c);
                }
                continue;
            }

            StringBuilder num = new StringBuilder();
            while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num.append(s.charAt(i++));
            }
            if (num.length() > 0) {
                numbers.push(Integer.valueOf(num.toString()));
                i--;
            }

        }

        while(!signs.isEmpty() || !numbers.isEmpty()) {
            if (signs.pop() == '-') {
                res -= numbers.pop();
            } else {
                res += numbers.pop();
            }
        }

        return res;

    }
}