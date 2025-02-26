package com.swimmingliu.binarysearch;


import java.util.stream.Stream;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
public class Q4 {
    // 双指针法
    // 1. 选出nums1和nums2比较长的数组，分别为a数组和b数组
    // 2. a数组和b数组，最前面都加上-∞，最后面后加上∞，为了避免出现 i-1 == -1 和 i + 1 == length的情况
    // 3. 把a数组和b数组，想象成有两个均匀数组(第一组的最大值 <= 第二组的最小值)
    // 4. i就表示a数组当中有几个数在第一组，j就表示b数组当中有几个数在第一组
    // 5. 所以，当i = 0， j = (m + n) / 2 或者 j = (m + n + 1) / 2 (奇数的情况，默认第一组比第二组多一个数)
    // 6. 当碰到第一组的最大值 <= 第二组的最小值 ==> a[i] <= b[j + 1] && b[j] <= a[i + 1]
    // 7. 如果m + n为偶数，答案为第一组的最大值和第二组的最小值的平均数
    // 8. 如果m + n为奇数，答案为第一组的最大值
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //如果 m>n，我们没法从 i=0 开始枚举。
        // 以 m=5,n=3 为例，i=0 时，b 数组需要有 4 个数在第一组，但 n=3<4，无法做到。
        // 保证 m≤n 可以让我们从 i=0 开始枚举，写起来更方便。
        if (nums1.length > nums2.length){ // 确保 nums1 比 nums2短
            int[] tmp;
            tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int m = nums1.length;
        int n = nums2.length;
        int[] a = new int[m + 2];
        int[] b = new int[n + 2];
        a[0] = b[0] = Integer.MIN_VALUE;
        a[m + 1] = b[n + 1] = Integer.MAX_VALUE;
        System.arraycopy(nums1, 0, a, 1, m);
        System.arraycopy(nums2, 0, b, 1, n);
        // 枚举 nums1 有 i 个数在第一组
        // 那么 nums2 有 (m + n + 1) / 2 - i 个数在第一组
        int i = 0, j = (m + n + 1) / 2;
        while (true){
            if (a[i] <= b[j + 1] && b[j] <= a[i + 1]){ // 找到该均匀分组的位置
                double max = Math.max(a[i], b[j]); // 第一组最大值
                double min = Math.min(a[i + 1], b[j + 1]); // 第二组最小值
                return (m + n) % 2 != 0 ? max : (max + min) / 2;
            }
            i ++;
            j --;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
