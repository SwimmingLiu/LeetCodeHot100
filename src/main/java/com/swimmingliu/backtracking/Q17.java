package com.swimmingliu.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Q17 {
    // dfs HashMap + Deque 暴力搜索
//    private static List<String> res = new ArrayList<>();
//    private static Deque<Character> path = new ArrayDeque<>();
//    private static Map<Character, String> map = new HashMap<>();
//    private static void initMap(Map<Character, String> map) {
//        map.put('2', "abc");
//        map.put('3', "def");
//        map.put('4', "ghi");
//        map.put('5', "jkl");
//        map.put('6', "mno");
//        map.put('7', "pqrs");
//        map.put('8', "tuv");
//        map.put('9', "wzxy");
//    }
//    public static List<String> letterCombinations(String digits) {
//        if (digits.isEmpty()) return new ArrayList<>();
//        initMap(map);
//        dfs(digits.toCharArray(), 0);
//        return res;
//    }
//    private static void dfs(char[] digits, int index) {
//        if (digits.length == index) {
//            res.add(path.stream().map(String::valueOf).collect(Collectors.joining()));
//            return;
//        }
//        for (char c : map.get(digits[index]).toCharArray()) {
//            path.add(c);
//            dfs(digits, index + 1);
//            path.removeLast();
//        }
//    }

    // DFS搜索 优化版本
    private static List<String> res = new ArrayList<>();
    private static char[] path;
    private static String[] map = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wzxy"};
    public static List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();
        path = new char[digits.length()];
        dfs(digits.toCharArray(), 0);
        return res;
    }
    private static void dfs(char[] digits, int index) {
        if (digits.length == index) {
            res.add(new String(path));
            return;
        }
        for (char c : map[digits[index] - '0'].toCharArray()) {
            path[index] = c; // 直接覆盖，不需要恢复现场，因为必须要长度为len，才能add
            dfs(digits, index + 1);
        }
    }


    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }
}
