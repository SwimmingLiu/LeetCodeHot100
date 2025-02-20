package com.swimmingliu.graph;

import java.util.*;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
public class Q207 {

    // 构建有向无环图，借助出度和入度的思想。[0, 1] -> 1 -> 0, 1的入度为0， 0的入度为1
    // 判断标准：将所有的入度为0的结点入队，再出队。用count记录入度为0的结点个数是否和选课数相同
//    public static boolean canFinish(int numCourses, int[][] prerequisites) {
//        Map<Integer, Integer> inDegree = new HashMap<>(); // 存储所有入度结点的入度值
//        Map<Integer, List<Integer>> adj = new HashMap<>(); // 存储结点之间的出度关系
//        // 1. 初始化所有结点的入度
//        for (int i = 0; i < numCourses; i ++) inDegree.put(i, 0);
//        // 2. 计算所有结点的入度值 (只有第一列的值，有入度)
//        for (int[] prerequisite : prerequisites) {
//            int key = prerequisite[0];
//            inDegree.put(key, inDegree.get(key) + 1);
//        }
//        // 3. 构建结点的出度关系, 找到依赖结点的所有结点
//        for (int[] prerequisite : prerequisites){
//            int next = prerequisite[0]; // [1, 0] ---> 0 -> 1
//            int cur = prerequisite[1];
//            if (!adj.containsKey(cur))
//                adj.put(cur, new ArrayList<>());
//            adj.get(cur).add(next);
//        }
//        // 4. 将所有入度为0的结点放入队列
//        Queue<Integer> queue = new LinkedList<>();
//        for (int i = 0; i < numCourses; i ++)
//            if (inDegree.get(i) == 0)
//                queue.offer(i);
//        // 5. 将所有入度为0的结点出队，同时清除其依赖结点的入度
//        int count = 0;
//        while (!queue.isEmpty()){
//            count ++;
//            Integer cur = queue.poll();
//            List<Integer> nextNodes = adj.get(cur);
//            if (nextNodes == null || nextNodes.isEmpty()) continue;
//            for (Integer next : nextNodes){
//                inDegree.put(next, inDegree.get(next) - 1);
//                if (inDegree.get(next) == 0) queue.add(next);
//            }
//        }
//        return count == numCourses;
//    }

    // 判断标准：如果最后没有入度的结点，则说明可以完成课程的学习
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> inDegree = new HashMap<>(); // 存储所有入度结点的入度值
        Map<Integer, List<Integer>> adj = new HashMap<>(); // 存储结点之间的出度关系
        // 1. 初始化所有结点的入度
        for (int i = 0; i < numCourses; i ++) inDegree.put(i, 0);
        // 2. 计算所有结点的入度值 (只有第一列的值，有入度)
        for (int[] prerequisite : prerequisites) {
            int key = prerequisite[0];
            inDegree.put(key, inDegree.get(key) + 1);
        }
        // 3. 构建结点的出度关系, 找到依赖结点的所有结点
        for (int[] prerequisite : prerequisites){
            int next = prerequisite[0]; // [1, 0] ---> 0 -> 1
            int cur = prerequisite[1];
            if (!adj.containsKey(cur))
                adj.put(cur, new ArrayList<>());
            adj.get(cur).add(next);
        }
        // 4. 将所有入度为0的结点放入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++)
            if (inDegree.get(i) == 0)
                queue.offer(i);
        // 5. 将所有入度为0的结点出队，同时清除其依赖结点的入度
        while (!queue.isEmpty()){
            Integer cur = queue.poll();
            List<Integer> nextNodes = adj.get(cur);
            if (nextNodes == null || nextNodes.isEmpty()) continue;
            for (Integer next : nextNodes){
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) queue.add(next);
            }
        }
        // 6. 判断当前是否还有入度不为0的结点
        for (Integer key : inDegree.keySet()){
            if (inDegree.get(key) != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        System.out.println(canFinish(numCourses, prerequisites));
    }
}
