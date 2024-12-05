package com.swimmingliu.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class Q54 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int used = -999;
        for (int i = 0; i < colLen; i++) {
            result.add(matrix[0][i]);
            matrix[0][i] = used;
        }
        int row = 0, col = colLen - 1;
        while ((row + 1 != rowLen && matrix[row + 1][col] != used)
                || (col + 1 != colLen && matrix[row][col + 1] != used)
                || (row - 1 != -1 && matrix[row - 1][col] != used)
                || (col - 1 != -1 && matrix[row][col - 1] != used)) {
            // 获取当前的下一个节点
            if (col + 1 == colLen || matrix[row][col + 1] == used) {
                if (row + 1 == rowLen || matrix[row + 1][col] == used) {
                    if (col - 1 == -1 || matrix[row][col - 1] == used) {
                        // 向上
                        row = row - 1;
                    } else {
                        // 左转
                        col = col - 1;
                    }
                } else { // 向下
                    row = row + 1;
                }
            } else if ((col - 1 == -1 || matrix[row][col - 1] == used) && matrix[row - 1][col] != used) {
                // 向上
                row = row - 1;
            } else { // 向右
                col = col + 1;
            }
            result.add(matrix[row][col]);
            matrix[row][col] = used;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1}};
        List<Integer> result = spiralOrder(matrix);
        System.out.println(result);
    }
}
