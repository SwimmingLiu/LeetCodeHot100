package com.swimmingliu.slidewindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */

public class Q3 {
    // StringBuffer 子串数组
//    public static int lengthOfLongestSubstring(String s) {
//        // 特判
//        if (s.isEmpty()) return 0;
//        else if (s.length() == 1) return 1;
//        // 子串数组
//        StringBuffer stringBuffer = new StringBuffer();
//        int res = 0, tmp = 0;
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            int charIndex = findCharIndex(stringBuffer, c);
//            if (charIndex == -1) {
//                stringBuffer.append(c);
//                tmp++;
//            } else {
//                res = Math.max(res, tmp);
//                String substring = stringBuffer.substring(charIndex + 1, stringBuffer.length());
//                stringBuffer = new StringBuffer();
//                stringBuffer.append(substring);
//                stringBuffer.append(c);
//                tmp = stringBuffer.length();
//            }
//        }
//        res = Math.max(res, tmp);
//        return res;
//    }
//    public static int findCharIndex(StringBuffer stringBuffer, char c) {
//        for (int j = 0; j < stringBuffer.length(); j++) {
//            if (stringBuffer.charAt(j) == c) {
//                return j;
//            }
//        }
//        return -1;
//    }

    // 滑动窗口 HashMap
//    public static int lengthOfLongestSubstring(String s) {
//        // 特判
//        if (s.isEmpty()) return 0;
//        else if (s.length() == 1) return 1;
//        HashMap<Character, Integer> map = new HashMap<>();
//        int start = 0, res = 0;
//        for (int i = 0; i < s.length(); i ++){
//            char c = s.charAt(i);
//            if (map.containsKey(c)){
//                start = Math.max(start, map.get(c) + 1);
//            }
//            map.put(c, i);
//            res = Math.max(res, i - start + 1);
//        }
//        return res;
//    }

    // 辅助数组 -> 存每个字符在s中的下标
//    public static int lengthOfLongestSubstring(String s) {
//        // 特判
//        int length = s.length();
//        if (length < 2) return length;
//        int[] st = new int[128 + 10]; // ASCII取值范围 0~127
//        Arrays.fill(st, -1); // 初始化
//        int res = 0, l = 0;
//        for (int r = 0; r < length; r ++){
//            char c = s.charAt(r);
//            l = Math.max(l, st[c] + 1);
//            res = Math.max(res, r - l + 1);
//            st[c] = r;
//        }
//        return res;
//    }
    // 滑动窗口 -> 直接找左边界
    public static int lengthOfLongestSubstring(String s) {
        // 特判
        int length = s.length();
        if (length < 2) return length;
        int res = 0, l = 0;
        for (int r = 0; r < length; r ++){
            for (int k = l; k < r; k ++){
                if (s.charAt(k) == s.charAt(r)){
                    l = k + 1;
                    break;
                }
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }


    public static void main(String[] args) {
        String s = "     ";
        int res = lengthOfLongestSubstring(s);
        System.out.println(res);
    }
}
