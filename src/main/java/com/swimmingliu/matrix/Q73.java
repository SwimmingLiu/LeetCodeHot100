package com.swimmingliu.matrix;

import java.util.*;

/**
 * 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * m == matrix.length -> row
 * n == matrix[0].length -> col
 * 1 <= m, n <= 200
 */
public class Q73 {
    // List<int[]> 辅助数组
//    public static void setZeroes(int[][] matrix) {
//        if (matrix.length == 1 && matrix[0].length == 0)
//            return;
//        ArrayList<int[]> zeroCoordinates = new ArrayList<>();
//        for (int i = 0; i < matrix.length; i++)
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (matrix[i][j] == 0)
//                    zeroCoordinates.add(new int[]{i, j});
//            }
//        for (int[] cor:zeroCoordinates) {
//            int row = cor[0];
//            int col = cor[1];
//            // 把行变为0
//            for (int i = 0; i < matrix.length; i++) {
//                matrix[i][col] = 0;
//            }
//            Arrays.fill(matrix[row], 0);
//        }
//    }
    // row数组 + col数组: 分别记录行或列当中为0的索引
    public static void setZeroes(int[][] matrix) {
        if (matrix.length == 1 && matrix[0].length == 0)
            return;
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0)
                    row[i] = col[j] = true;
            }
        for (int i = 0; i < matrix.length; i ++)
            for (int j = 0; j < matrix[i].length; j++){
                if (row[i] || col[j])
                    matrix[i][j] = 0;
            }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
