package com.swimmingliu.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class Q48 {
    // 辅助数组: 四角遍历
//    public static void rotate(int[][] matrix) {
//        int n = matrix.length;
//        if (n == 1) return;
//        int[][] result = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                result[i][j] = matrix[i][j];
//        int start = 0, end = n - 1;
//        while (start < end) {
//            // 最上方
//            for (int i = start; i <= end; i++)
//                result[i][end] = matrix[start][i];
//            // 右侧
//            for (int i = start; i <= end; i++)
//                result[end][i] = matrix[end - (i - start)][end];
//            // 最下方
//            for (int i = start; i <= end; i++)
//                result[i][start] = matrix[end][i];
//            // 左侧
//            for (int i = start; i <= end; i++)
//                result[start][i] = matrix[end - (i - start)][start];
//            start++;
//            end--;
//        }
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                matrix[i][j] = result[i][j];
//    }
    // 辅助数组：旋转公式：result[j][n - 1 - i] = matrix[i][j]
//    public static void rotate(int[][] matrix) {
//        int n = matrix.length;
//        if (n == 1) return;
//        int[][] result = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                result[i][j] = matrix[i][j];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                matrix[j][n - i - 1] = result[i][j];
//    }
    // 原地旋转 -> 四个角落 -> 1/4 小正方形 !!!
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return;
        for (int i = 0; i < n / 2; i++)
            for (int j = 0; j < (n + 1) / 2; j++) {
                // temp -> matrix[j][n - 1 - i] -> matrix[n - 1 - i][n - 1 - j] ->
                // matrix[n - 1 - j][i] -> matrix[i][j]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
