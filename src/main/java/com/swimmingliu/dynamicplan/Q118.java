package com.swimmingliu.dynamicplan;


import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 */
public class Q118 {
    // 杨辉三角 -> 往左推
    //       1             1
    //     1   1    ---->  1 1
    //   1   2   1         1 2 1
//    private static final List<List<Integer>> res = new ArrayList<>();
//    public static List<List<Integer>> generate(int numRows) {
//        int[][] triangel = new int[numRows][numRows];
//        for (int i = 0; i < triangel.length; i++) triangel[i][0] = 1;
//        for (int i = 1; i < triangel.length; i++) {
//            for (int j = 1; j < i + 1; j++) {
//                triangel[i][j] = triangel[i - 1][j - 1] + triangel[i - 1][j];
//            }
//        }
//        for (int i = 0; i < triangel.length; i++) {
//            List<Integer> list = new ArrayList<>();
//            for (int j = 0; j < i + 1; j++) {
//                list.add(triangel[i][j]);
//            }
//            res.add(list);
//        }
//        return res;
//    }

    // 优化写法
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>(numRows);
        List<Integer> list = List.of(1);
        res.add(list);
        for (int i = 1; i < numRows; i++) {
            List<Integer> tmp = new ArrayList<>(i + 1);
            List<Integer> pre = res.get(i - 1);
            tmp.add(1); // 最左边
            for (int k = 1; k < i; k ++) tmp.add(pre.get(k) + pre.get(k - 1));
            tmp.add(1); // 最右边
            res.add(tmp);
        }
        return  res;
    }

    public static void main(String[] args) {
        System.out.println(generate(2));
    }
}
