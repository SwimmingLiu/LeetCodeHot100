package com.swimmingliu.heap;


import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class Q347 {
    // 使用Collections.sort() -> 归并排序
//    public static int[] topKFrequent(int[] nums, int k) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums){
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//        // map -> List<键值对>
//        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//        Collections.sort(list, (a, b) -> b.getValue() - a.getValue());
//        int[] res = new int[k];
//        for (int i = 0; i < k; i ++)
//            res[i] = list.get(i).getKey();
//        return res;
//    }
    // 使用PriorityQueue
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 优先队列来排序
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for (Integer key: map.keySet()) queue.add(key);
        int[] res = new int[k];
        for (int i = 0; i < k; i ++)
            res[i] = queue.poll();
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 3, 3, 2};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }
}
