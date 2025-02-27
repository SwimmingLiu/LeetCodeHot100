package com.swimmingliu.heap;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class Q215 {
    // 1. 快速排序：找到pivot排序后对应的位置，然后不断对左右区间进行划分
    // 2. 第K个最大元素 -> 对应的位置 N - K （比如最大的元素排序后的位置肯定是 N - 1）
    // 3. 对于包含大量重复元素的数组，每轮的哨兵划分都可能将数组划分为长度为 1 和 n−1 的两个部分
    // 4. 这种情况下快速排序的时间复杂度会退化至 O(N^2)
    // 5. 可以分为按照大于、小于和等于选定元素进行划分，分为三个列表 big, equal, small
    // 6. 然后逐个递归，直到找到第K大的元素
    private static int selectTopK(List<Integer> list, int k) {
        List<Integer> big = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        Random random = new Random();
        int pivotIndex = random.nextInt(list.size());
        Integer pivot = list.get(pivotIndex);
        for (Integer x : list) {
            if (x > pivot) big.add(x);
            else if (x < pivot) small.add(x);
            else equal.add(x);
        }
        // topK 在big里面，递归划分
        if (k <= big.size())
            return selectTopK(big, k);
        // topK 在small里面，递归划分；
        // 递归的时候为什么是k - (big.size() + equal.size()) -> 因为递归只有small里面的元素了，K现在应该排除big和equal里面的元素个数
        else if (big.size() + equal.size() < k)
            return selectTopK(small, k - (big.size() + equal.size()));
        else return pivot;
    }

    public static int findKthLargest(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);
        return selectTopK(list, k);
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));
    }
}
