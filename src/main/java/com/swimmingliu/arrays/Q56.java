package com.swimmingliu.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * 以数组 intervals  ，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 1 <= intervals.length <= 10e4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10e4
 *
 */
public class Q56 {
    // 排序(先左后右) -> 不可合并: 左侧右边界 < 右侧左边界
//    public static int[][] merge(int[][] intervals) {
//        int length = intervals.length;
//        if (length == 1) return intervals;
//        Arrays.sort(intervals, Comparator.
//                comparingInt((int[] a)  -> a[0])
//                .thenComparingInt(a -> a[1]));
//        List<int[]> list = new ArrayList<>();
//        int L = 0, R = 1; // 子数组的左右边界
//        int left = 0;
//        int rightMax = intervals[0][1];
//        for (int right = 1; right < length; right ++){
//            // 不可合并
//            if (intervals[right][L] > rightMax){
//                list.add(new int[]{intervals[left][L], rightMax});
//                left = right;
//                rightMax = intervals[left][R];
//            }else{
//                // 可以合并记录最大的右端点
//                rightMax = Math.max(rightMax, intervals[right][R]);
//            }
//            // 判断最后一个边界点
//            if (right == length - 1){
//                if (intervals[right][L] > rightMax)
//                    list.add(new int[]{intervals[left][L], intervals[left][R]});
//                else{
//                    list.add(new int[]{intervals[left][L], rightMax});
//                }
//            }
//        }
//        return list.toArray(new int[0][]);
//    }
    // 按左边界排序 -> 不可合并: 左侧右边界 < 右侧左边界
    public static int[][] merge(int[][] intervals) {
        int length = intervals.length;
        if (length == 1) return intervals;
        Arrays.sort(intervals, (l1, l2) -> (l1[0] - l2[0]));
        List<int[]> list = new ArrayList<>();
        int L = 0, R = 1;
        list.add(intervals[0]);
        for (int[] node:intervals){
             // 可以合并
            int[] ansNode = list.get(list.size() - 1);
            if (node[L] <= ansNode[R]){
                int maxRight = Math.max(ansNode[R], node[R]);
                list.set(list.size() - 1, new int[]{ansNode[L],maxRight});
             }else{
                 list.add(node);
             }
        }
        return list.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,4},{0,2},{3,5}};
        int[][] result = merge(intervals);
        System.out.println(Arrays.deepToString(result));
    }
}
