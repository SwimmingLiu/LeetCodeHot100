package com.swimmingliu.binarysearch;

/**
 * 74. 搜索二维矩阵
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */
public class Q74 {
    // 先列后行 + 二分法
    public static boolean searchMatrix(int[][] matrix, int target) {
        int[] nums = new int[matrix.length];
        for (int i = 0; i < matrix.length; i ++){
            nums[i] = matrix[i][0];
        }
        int row = binarySearch(nums, target);
        if (row < nums.length && nums[row] == target) return true;
        row = row - 1; // 从上一行当中查询
        if (!inArea(row, 0, matrix)) return false; // 判断是否为边界
        int col = binarySearch(matrix[row], target);
        return inArea(row, col, matrix) && matrix[row][col] == target;
    }

    private static boolean inArea(int row, int col, int[][] matrix){
        return 0 <= row && row < matrix.length && 0 <= col && col < matrix[0].length;
    }

    private static int binarySearch(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left ++;
            else right --;
        }
        return left;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1}};
        int target = 2;
        System.out.println(searchMatrix(matrix, target));
    }
}
