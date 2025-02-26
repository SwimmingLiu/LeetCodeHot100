package com.swimmingliu.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
public class Q20 {
//    // 每次判断栈中是否有匹配元素
//    public static boolean isValid(String s) {
//        Stack<Character> stack = new Stack<>();
//        for (Character c : s.toCharArray()){
//            if (c == '(' || c == '[' || c == '{'){
//                stack.push(c);
//                continue;
//            }
//            if (stack.isEmpty()) return false;
//            Character top = stack.pop();
//            if (c == ')' && top != '(') return false;
//            else if (c == ']' && top != '[') return false;
//            else if (c == '}' && top != '{') return false;
//        }
//        return stack.isEmpty();
//    }

    // 优化写法
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()){
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        String s = "()";
        System.out.println(isValid(s));
    }
}
