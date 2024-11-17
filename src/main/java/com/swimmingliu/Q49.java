package com.swimmingliu;

import java.util.*;

/**
 *  字母异位词分组
 *  示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */
public class Q49 {
    // 利用ascii构造
//    public static List<List<String>> groupAnagrams(String[] strs) {
//        HashMap<String, List<String>> listsMap = new HashMap<>();
//        for (String str:strs){
//            int[] charValues = new int[26];
//            for (int i = 0; i < str.length(); i ++){
//                charValues[str.charAt(i) - 'a']++;
//            }
//            StringBuffer strKeyBuffer = new StringBuffer();
//            for (int i = 0; i < charValues.length; i++){
//                if (charValues[i] == 0) continue;
//                strKeyBuffer.append(i+'a');
//                strKeyBuffer.append(charValues[i]);
//            }
//            String stringKey = strKeyBuffer.toString();
//            if (listsMap.containsKey(stringKey)){
//                List<String> strSublist = new ArrayList<>(listsMap.get(stringKey));
//                strSublist.add(str);
//                listsMap.put(stringKey,strSublist);
//            }
//            else {
//                listsMap.put(stringKey, Collections.singletonList(str));
//            }
//        }
//        return listsMap.values().stream().toList();
//    }

    // 快速排序 + 构造新key
     public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> listsMap = new HashMap<>();
        for (String str:strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);
            ArrayList<String> subStringList = new ArrayList<>();
            if (listsMap.containsKey(sortedString)){
                subStringList.addAll(listsMap.get(sortedString));
            }
            subStringList.add(str);
            listsMap.put(sortedString, subStringList);
        }
        return listsMap.values().stream().toList();
    }

    public static void main(String[] args) {
        String[] string = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = groupAnagrams(string);
        for (List list:lists){
            System.out.println(list.toString());
        }
    }
}
