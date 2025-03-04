package com.swimmingliu.dynamicplan;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。
 * 如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */
public class Q139 {
    // dfs(i) 表示s[0, i - 1]能否划分成若干段组成，且每段都在wordDict里面
//    private HashSet<String> wordDict;
//    private int[] memo;
//    private int maxLen = 0;
//    public boolean wordBreak(String s, List<String> wordDict) {
//        this.wordDict = new HashSet<>(wordDict);
//        wordDict.forEach(word -> maxLen = Math.max(maxLen, word.length()));
//        this.memo = new int[s.length() + 1];
//        Arrays.fill(memo, -1);
//        return dfs(s.length(), s) == 1;
//    }
//    private int dfs(int i, String s) {
//        if (i == 0) return 1; // 已经把s全部分段了
//        if (memo[i] != -1) return memo[i];
//        // 判断s[0, i - 1]能否划分成若干段组成，且每段都在wordDict里面
//        // 枚举k = i-1, i-2,..., max(i-MaxLen, 0), 找到满足 s[k, i)在wordDict && dfs(j)就成功了。
//        // 其实就是先找到一个分段wordDict.contains(s.substring(k, i))
//        // 然后同时确保后面的也能正常分段，dfs(k)
//        for (int k = i - 1; k >= Math.max(i - maxLen, 0); k --){
//            if (wordDict.contains(s.substring(k, i)) && dfs(k, s) == 1)
//                return memo[i] = 1;
//        }
//        return memo[i] = 0; // 没有找到
//    }

    // 递归 -> 递推
    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        int maxLen = 0;
        for (String string : wordSet) maxLen = Math.max(maxLen, string.length());
        int len = s.length();
        int[] f = new int[len + 1];
        f[0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int k = i - 1; k >= Math.max(i - maxLen, 0); k--) {
                if (wordDict.contains(s.substring(k, i)) && f[k] == 1)
                    f[i] = 1;
            }
        }
        return f[len] == 1;
    }


    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(wordBreak(s, List.of("leet", "code")));
    }
}
