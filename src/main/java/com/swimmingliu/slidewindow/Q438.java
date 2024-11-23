package com.swimmingliu.slidewindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到字符串中所有字母异位词
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 */

public class Q438 {
    // 辅助数组
//    public static List<Integer> findAnagrams(String s, String p) {
//        int sLen = s.length();
//        int pLen = p.length();
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int l = 0, r = pLen - 1; r < sLen; l++, r++) {
//            int[] sCount = new int[28];
//            int[] pCount = new int[28];
//            for (int i = l; i <= r; i++)
//                sCount[s.charAt(i) - 'a']++;
//            for (int i = 0; i < pLen; i++)
//                pCount[p.charAt(i) - 'a']++;
//            boolean hasSameString = true;
//            for (int i = 0; i < 28; i++) {
//                if (sCount[i] != pCount[i]) {
//                    hasSameString = false;
//                    break;
//                }
//            }
//            if (hasSameString)
//                list.add(l);
//        }
//        return list;
//    }

    // 辅助数组 (优化v1)
//    public static List<Integer> findAnagrams(String s, String p) {
//        int sLen = s.length();
//        int pLen = p.length();
//        int[] pCount = new int[28];
//        for (int i = 0; i < pLen; i++)
//                pCount[p.charAt(i) - 'a']++;
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int l = 0, r = pLen - 1; r < sLen; l++, r++) {
//            int[] sCount = new int[28];
//            for (int i = l; i <= r; i++)
//                sCount[s.charAt(i) - 'a']++;
//            if (Arrays.equals(pCount, sCount))
//                list.add(l);
//        }
//        return list;
//    }
    // 辅助数组 (优化v2)
    public static List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        ArrayList<Integer> list = new ArrayList<>();
        if (sLen < pLen) return list;
        int[] sCount = new int[28];
        int[] pCount = new int[28];
        for (int i = 0; i < pLen; i++)
            pCount[p.charAt(i) - 'a']++;
        for (int i = 0; i < sLen; i ++) {
            sCount[s.charAt(i) - 'a'] ++;
            int j = i - pLen + 1;
            if (j < 0) continue;
            if (Arrays.equals(pCount, sCount))
                list.add(j);
            sCount[s.charAt(j) - 'a'] --;
        }
        return list;
    }

    public static void main(String[] args) {
        String s = "baa", p = "aa";
        List<Integer> anagrams = findAnagrams(s, p);
        System.out.println(anagrams);
    }
}
