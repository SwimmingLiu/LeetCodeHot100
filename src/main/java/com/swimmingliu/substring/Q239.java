package com.swimmingliu.substring;


import java.util.*;

/**
 * 滑动窗口最大值
 * <p>
 * <p>
 * 1 <= nums.length <= 105
 * -10e4 <= nums[i] <= 10e4
 * 1 <= k <= nums.length
 */
public class Q239 {

//    // 单调递减队列 -> deque
//    public static int[] maxSlidingWindow(int[] nums, int k) {
//        int len = nums.length;
//        if (len == 1) return new int[]{nums[0]};
//        Deque<Integer> deque = new LinkedList<>();
//        int[] res = new int [len - k + 1];
//        int j = 0;
//        for (int i = 0; i < len; i ++){
//            if (i >= k && deque.peekFirst() == nums[i - k]){
//                // 已形成队列，首先删除队列的第一个元素
//                deque.removeFirst();
//            }
//            // 保持队列递减
//            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
//                deque.removeLast();
//            }
//            deque.addLast(nums[i]);
//            if (i >= k - 1){
//               res[j++] = deque.peekFirst().intValue();
//            }
//        }
//        return res;
//    }
//
    // 拆分未形成窗口和已形成窗口
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) return new int[]{nums[0]};
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int [len - k + 1];
        int j = 0;
        for (int i = 0; i < k; i ++){
             // 保持队列递减
            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[j ++] = deque.peekFirst();
        for (int i = k; i < len; i ++){
            if (deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            // 保持队列递减
            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[j++] = deque.peekFirst().intValue();
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {7,2,4};
        int k = 2;
        int[] result = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }
}
