package com.swimmingliu.stack;

import java.util.Stack;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 */
public class Q394 {
    // 1. 如果是数字，count记录重复次数，每次乘以10再想加 -> 直到碰到 "[" -> 表示重复次数的数完整了
    // 2. 如果是字母，直接拼接到res上
    // 3. 如果是"["，将count重复次数放入num栈，将res压入stack栈，然后刷新count和res
    // 4. 如果是"]"，将num栈的最后一位元素出栈(curCount),res = stack出栈(上一次的res) + res重复curCount次
    private static final Stack<String> stack = new Stack<>();
    private static final Stack<Integer> num = new Stack<>();
    private static StringBuilder res = new StringBuilder();
    private static int count = 0;
    public static String decodeString(String s) {
        for (Character c : s.toCharArray()) {
            if (Character.isDigit(c)) count = count * 10 + (c - '0');
            else if (c == '['){ // 把lastRes入栈
                num.push(count);
                stack.push(res.toString());
                count = 0;
                res = new StringBuilder();
            }
            else if (c == ']') { // 拼接lastRes + 新入栈的元素
                StringBuilder tmp = new StringBuilder();
                int currentCount = num.pop();
                tmp.append(res.toString().repeat(currentCount));
                res = new StringBuilder(stack.pop() + tmp);
            }
            else res.append(c);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "100[leetcode]";
        System.out.println(decodeString(s));
    }
}
