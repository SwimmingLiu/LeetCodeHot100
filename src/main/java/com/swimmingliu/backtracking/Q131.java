package com.swimmingliu.backtracking;

import java.util.*;

/**
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串是向前和向后读都相同的字符串。
 */
public class Q131 {
    // 假设每个字母中间有个逗号，选不选中间的逗号
//    private static final List<List<String>> res = new ArrayList<>();
//    private static final Deque<String> path = new ArrayDeque<>();
//    public static List<List<String>> partition(String s) {
//        dfs(s, 0, 0);
//        return res;
//    }
//    private static void dfs(String str, int index, int start) {
//        if (index == str.length()) {
//            res.add(path.stream().toList());
//            return;
//        }
//        // 不选逗号，让其相连 （最后一个必须选，因为要分割）
//        if (index < str.length() - 1) {
//            dfs(str, index + 1, start);
//        }
//        // 选逗号，让其隔开
//        String substring = str.substring(start, index + 1);
//        if (isPalindrome(substring)) {
//            path.add(substring); // 左闭右开
//            dfs(str, index + 1, index + 1);
//            path.removeLast();
//        }
//    }
//    private static boolean isPalindrome(String s) {
//        return s.contentEquals(new StringBuffer(s).reverse());
//    }

    // 答案视角：枚举子串结束的位置
    private static final List<List<String>> res = new ArrayList<>();
    private static final Deque<String> path = new ArrayDeque<>();
    private static String str = "";
    public static List<List<String>> partition(String s) {
        str = s;
        dfs(0);
        return res;
    }
    private static void dfs(int index) {
        if (index == str.length()) {
            res.add(path.stream().toList());
            return;
        }
        for (int k = index; k < str.length(); k ++){
            String substring = str.substring(index, k + 1);
            if (!isPalindrome(substring)) continue;
            path.add(substring);
            dfs(k + 1);
            path.removeLast();
        }

    }
    private static boolean isPalindrome(String s) {
        int i = 0,j = s.length() - 1;
        while(i <= j) if(s.charAt(i++) != s.charAt(j--)) return false;
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        System.out.println(partition(s));
    }
}
