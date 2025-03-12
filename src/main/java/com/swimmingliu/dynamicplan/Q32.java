package com.swimmingliu.dynamicplan;


import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class Q32 {
//    // 栈：括号匹配，首先考虑到的应该是栈，用栈来记录对应的下标
//    public static int longestValidParentheses(String s) {
//        ArrayDeque<Integer> stack = new ArrayDeque<>();
//        stack.push(-1); // 初始化
//        int res = 0;
//        for (int i = 0; i < s.length(); i ++) {
//            if (s.charAt(i) == '(') stack.push(i);
//            else {
//                if (stack.peek() == -1 || s.charAt(stack.peek()) == ')') stack.push(i);
//                else {
//                    stack.pop();
//                    // 为什么是 i - stack.peek(), 而不是i - stack.pop() + 1?
//                    // )()() -> 如果是i - stack.pop() + 1，则不能计算到第一对的()
//                    res = Math.max(res, i - stack.peek());
//                }
//            }
//        }
//        return res;
//    }

    // dp递推 -> f[i] 表示当前位置对应的最长括号长度
    // 边界条件：f[0] = 0
    // 如果s[i] == ")"，计算 f[i]
    // 如果s[i - 1] == "(", 则 f[i] = f[i - 2] + 2
    // 如果s[i - 1] == ")", 则需要判断 s[i - f[i - 1] - 1] 是否为 "("
    // 如果s[i - f[i - 1] - 1] == "(", 则 f[i] = f[i - 1] + 2 + f[i - f[i - 1] - 2]
    public static int longestValidParentheses(String s) {
        int res = 0;
        char[] str = s.toCharArray();
        int[] f = new int[s.length()];
        for (int i = 0; i < str.length; i++) {
            if (i == 0 || str[i] == '(') continue;
            if (str[i - 1] == '(') f[i] = i - 2 >= 0 ? f[i - 2] + 2 : 2;
            else if (i - f[i - 1] - 1 >= 0  && str[i - f[i - 1] - 1] == '(')
                f[i] = f[i - 1] + 2 + (i - f[i - 1] - 2 >= 0 ? f[i - f[i - 1] - 2] : 0);
            res = Math.max(res, f[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "()";
        System.out.println(longestValidParentheses(s));
    }
}
