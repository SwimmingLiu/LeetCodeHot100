package com.swimmingliu.substring;

/**
 * 最小覆盖子串
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 */
public class Q76 {
    // 滑动窗口 -> 更新右端点 -> 窗口是否涵盖（循环）
//    public static String minWindow(String s, String t) {
//        int sLen = s.length();
//        int tLen = t.length();
//        // 特判
//        if (tLen > sLen) return "";
//        else if (s.equals(t)) return s;
//        int[] cntT = new int[128];
//        int[] cntS = new int[128];
//        int ansLeft = -1, ansRight = sLen;
//        int left = 0;
//        for (int i = 0; i < tLen; i++) {
//            cntT[t.charAt(i)]++;
//        }
//        for (int right = 0; right < sLen; right++) {
//            cntS[s.charAt(right)]++;
//            while (isCover(cntS, cntT)) {
//                if (right - left < ansRight - ansLeft) {
//                    ansLeft = left;
//                    ansRight = right;
//                }
//                cntS[s.charAt(left)]--;
//                left++;
//            }
//        }
//        return ansLeft == -1 ? "" : s.substring(ansLeft, ansRight + 1);
//    }
//    private static boolean isCover(int[] cntS, int[] cntT) {
//        for (int i = 'A'; i <= 'Z'; i++) {
//            if (cntS[i] < cntT[i]) {
//                return false;
//            }
//        }
//        for (int i = 'a'; i <= 'z'; i++) {
//            if (cntS[i] < cntT[i]) {
//                return false;
//            }
//        }
//        return true;
//    }

    // 滑动窗口 -> 更新右端点 -> 窗口涵盖(出现次数)
    public static String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        // 特判
        if (tLen > sLen) return "";
        else if (s.equals(t)) return s;
        int lessT = 0; // less用于记录S串窗口中少于T串中字母出现的次数
        int[] cnt = new int[128];
        int ansLeft = -1, ansRight = sLen;
        for (char c : t.toCharArray()) {
            if (cnt[c] == 0)
                lessT++;
            cnt[c]++;
        }
        int left = 0;
        for (int right = 0; right < sLen; right++) {
            char cRight = s.charAt(right);
            cnt[cRight]--;
            if (cnt[cRight] == 0) lessT--; //S串窗口中该字母的出现次数不小于T串
            while (lessT == 0) {
                if (right - left < ansRight - ansLeft) {
                    ansLeft = left;
                    ansRight = right;
                }
                // 移动左端点
                char cLeft = s.charAt(left);
                if (cnt[cLeft] == 0) lessT++; // S串窗口中不再存在左端点对应的字母
                cnt[cLeft]++;
                left++;
            }
        }
        return ansLeft == -1 ? "" : s.substring(ansLeft, ansRight + 1);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = minWindow(s, t);
        System.out.println(result);
    }
}
