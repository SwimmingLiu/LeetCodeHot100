package com.swimmingliu.dynamicplanwithmultid;


import java.util.Arrays;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 */
public class Q5 {
    // babad -> bab / aba
    // Manacher 马拉夫算法
    // s ("cabac") -> t ("^#c#a#b#a#c#$")
    // 计算s当中的最长回文子串 -> 计算t当中的最长回文子串
    // 1. 计算t当中每个位置的最大子串长度 （最后只需要整个t当中最长的字段）
    // 2. 暴力计算：左右指针 (mid - point, mid + point) 判断 t[mid - point] == t[mid + point]
    // 3. Manacher：当计算到t中的第二个"a"的时候，其实以它为中心的子串长度和第一个a有关系，因为他们都在以"b"为中心的最长回文串里面
    // 3.1 假设第一个"a"回文长度为 hLen[first], 以"b"为中心的最长回文串的最右边界下标 + 1为boxR，第二个"a"的位置为i
    // 3.2 第二个"a"的回文子串长度 = min(hLen[first], boxR - i) + 暴击计算后端的长度
    // 4. 假设t当中最大的回文长度对应的下标为 maxIndex
    // 5. t的最大回文长度为 [maxIndex - point, maxIndex + point]，已知t[i] = 2 * s[i] + 2
    // 5.1 如果除去两边的"#" -> [maxIndex - point + 1, maxIndex + point - 1]
    // 5.2 s的最大回文长度为 [(maxIndex - point - 2) / 2 + 1 , (maxIndex + point - 2) / 2 - 1]
    public static String longestPalindrome(String s) {
        int len = s.length();
        char[] t = new char[2 * len + 3];
        int tLen = t.length;
        Arrays.fill(t, '#');
        t[0] = '^';
        for (int i = 0; i < len; i ++)
            t[2 * i + 2] = s.charAt(i);
        t[tLen - 1] = '$';
        // 定义一个奇回文串的回文半径=(长度+1)/2，即保留回文中心，去掉一侧后的剩余字符串的长度
        // halfLen[i] 表示在 t 上的以 t[i] 为回文中心的最长回文子串的回文半径
        // 即 [i - halfLen[i] + 1, i + halfLen[i] - 1] 是 t 上的一个回文子串
        int[] halLen = new int[tLen - 2]; // 不考虑第一个 "^" 和 最后一个 "$"
        halLen[1] = 1; // 第一个#的回文长度为1
        int maxIndex = 0; // 记录t中最长回文子串的位置
        int boxMid = 0, boxR = 0; // boxMid 和 boxR分别标识回文子串的中心位置，和回文最大右边边界下标+1
        // boxR = boxMid + halLen[boxMid] + 1
        for (int i = 2; i < halLen.length; i++) {
            int point = 1; // 用于暴力计算回文子串的边界
            // Manacher算法
            if (i < boxR) // 如果当前元素，还在前面的最长回文字符串里面
                // 记 i 关于 boxM 的对称位置 i' = boxM * 2 - i (因为 i + i' = boxM * 2)
                // 若以 i' 为中心的最长回文子串范围超出了以 boxM 为中心的回文串的范围（即 i + halfLen[i'] >= boxR）
                // 则 halfLen[i] 应先初始化为已知的回文半径 boxR-i，然后再继续暴力匹配
                // 否则 halfLen[i] 与 halfLen[i'] 相等
                point = Math.min(halLen[boxMid * 2 - i], boxR - i);
            while (t[i - point] == t[i + point]){
                point ++;
                boxMid = i;
                boxR = i + point;
            }
            halLen[i] = point;
            maxIndex = point > halLen[maxIndex] ? i : maxIndex;
        }
        int point = halLen[maxIndex];
        // t的最长回文子串 -> [maxIndex - point, maxIndex + point]
        // 除去t前后的 "#"，t的最长回文子串 [maxIndex - point + 1, maxIndex + point - 1]
        // t[i] = 2 * s[i] + 2
        // s的最长回文子串 -> [(maxIndex - point - 2) / 2 + 1, (maxIndex + point - 2) / 2 - 1]
        // s的最长回文子串 -> [(maxIndex - point) / 2, (maxIndex + point) / 2 - 2]
        return s.substring((maxIndex - point) / 2, (maxIndex + point) / 2 - 1);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }
}
