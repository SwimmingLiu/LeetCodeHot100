package com.swimmingliu.binarysearch;


/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
 * （下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，
 * 则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class Q33 {
    // 双指针 + 二分
//    public static int search(int[] nums, int target) {
//        int reverseIndex = spiltSearch(nums);
//        if (reverseIndex == nums.length) {
//            int index = binarySearch(nums, target, 0, nums.length - 1);
//            return index != nums.length && nums[index] == target ? index : -1;
//        }
//        int left = binarySearch(nums, target, 0, reverseIndex - 1);
//        int right = binarySearch(nums, target, reverseIndex, nums.length - 1);
//        if (left != reverseIndex && nums[left] == target) return left;
//        else if (right != nums.length && nums[right] == target) return right;
//        else return -1;
//    }
//    private static int binarySearch(int[] nums, int target, int left, int right){
//        while (left <= right){
//            int mid = left + (right - left) / 2;
//            if (nums[mid] < target) left = mid + 1;
//            else right = mid - 1;
//        }
//        return left;
//    }
//    private static int spiltSearch(int[] nums){
//        if (nums.length <= 1) return 0; // 正常顺序
//        int left = 0, right = nums.length - 1;
//        while (left <= right){
//            if (nums[left] > nums[left + 1]) return left + 1;
//            if (nums[right] < nums[right - 1]) return right;
//            left ++;
//            right --;
//        }
//        return nums.length;
//    }


    // 找最小值 + 二分
    public static int search(int[] nums, int target) {
        int minIndex = findMin(nums);
        int res = 0;
        if (target > nums[nums.length - 1]) {  //大于最右边的元素 -> 在左区间
            res = binarySearch(nums, target, 0, minIndex - 1);
        }else {
            res = binarySearch(nums, target, minIndex, nums.length - 1);
        }
        return res != nums.length && nums[res] == target ? res : -1;
    }
    // 红蓝染色法：红色为最小值左侧，蓝色为最小值或者最小值右侧
    // [3, 0, 1, 2] 把2单独拿出来 -> [3, 0, 1] -> 0 < 2
    // [3, 0, 1] -> [3] -> 3 > 2 -> [0]
    public static int findMin(int[] nums){
        int len = nums.length;
        int left = 0, right = len - 2;
        // [0, n - 2] 中二分查找最小值，因为n - 1 要么为最小值，要么为最小值的右侧
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[len - 1]) // 蓝色
                 right = mid - 1;
            else left = mid + 1; // 红色
        }
        return left;
    }
    private static int binarySearch(int[] nums, int target, int left, int right){
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4,5,6,7,0,1,2};
        int target = 4;
        System.out.println(search(nums, target));
    }
}
