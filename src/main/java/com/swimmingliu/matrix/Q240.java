package com.swimmingliu.matrix;

import java.util.Arrays;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10e9 <= matrix[i][j] <= 10e9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10e9 <= target <= 10e9
 */
public class Q240 {
    // 贪心 + 旋转45度变为二叉搜索树
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1, col = 0; // 从左下角开始
        while (row >= 0 && col < matrix[0].length) { // 遍历到右上角结束
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target) {
                row --; // 向左
            } else {
                col ++; // 向右
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0}, {1}};
        int target = 1;
        boolean result = searchMatrix(matrix, target);
        System.out.println(result);
    }
}
