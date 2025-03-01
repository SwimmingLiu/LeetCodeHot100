package com.swimmingliu.greedy;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 763. 划分字母区间
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 */
public class Q763 {
//    // 核心思想： 先看一遍记录所有字母出现次数，然后找划分点
//    // 1. st[26] -> 记录所有字母出现次数
//    // 2. path集合 -> 记录当前遍历过的元素
//    // 3. s[i]是否可以作为分割的末尾？
//    // 3.1 st[s[i]] == 0 -> 后面已经没有该元素了
//    // 3.2 path当中的所有元素，后面都没有重复的了
//    // 4. 恢复现场，记录下一个
//    private static List<Integer> res = new ArrayList<>();
//    private static Set<Character> path = new HashSet<>();
//    private static int[] st = new int[26];
//    private static int count = 0; // 记录出现的字母个数
//    public static List<Integer> partitionLabels(String s) {
//        char[] arr = s.toCharArray();
//        for (char c : arr) st[c - 'a'] ++;
//        for (char c : arr){
//            st[c - 'a'] --;
//            path.add(c);
//            count ++;
//            if (st[c - 'a'] != 0) continue;
//            boolean pathIsValid = true;
//            for (Character character : path) {
//                if (st[character - 'a'] > 0) {
//                    pathIsValid =false;
//                    break;
//                }
//            }
//            if (pathIsValid) {
//                res.add(count);
//                count = 0;
//                path = new HashSet<>();
//            }
//        }
//        return res;
//    }
    // 合并区间：把 "ababcc"中所有字母的出现区间，统计出来 -> a = [0, 2], b = [1,3], c = [4, 5]
    // 要划分字母区间，本质就是合并上面的区别，如果能够合并的说明能组成一个子区间
    // 1. last[26] -> 记录所有字母最后出现的下标
    // 2. 用start和end来记录子区间的起始位置和结束位置，默认都为0
    // 3. 遍历s，不断更新end(找到子区间的尾部) ，当 end == i的时候，说明当前元素就是子区间的结束位置
    // 4. 区间长度 = end - start + 1
    public static List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int[] last = new int[26];
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) last[arr[i] - 'a'] = i;
        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            end = Math.max(end, last[arr[i] - 'a']);
            if (end != i) continue;
            res.add(end - start + 1);
            start = i + 1; // 恢复现场
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));
    }
}
