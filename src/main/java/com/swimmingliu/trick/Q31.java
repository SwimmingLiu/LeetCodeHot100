package com.swimmingliu.trick;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
 * 更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
 * 那么数组的下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，
 * 那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * 必须 原地 修改，只允许使用额外常数空间。
 */
public class Q31 {
    // 下一个排列：[1,2,3,4] -> [1,2,4,3]-> [1,3,2,4] -> [1,3,4,2] -> ... -> [4,3,2,1]
    // 下一个排列满足: 1234 < 1243 < 1324 < 1342 < ... < 4321
    // 以 [1,2,4,3]-> [1,3,2,4] 为例
    // 1. 需要找下一个排列，而它比当前数要大。所以肯定要把后面的大数和前面的小数互换位置 (1234 -> 1243)
    // 2. 每一步互换位置之后的数字，必须是比前面的数只大一点点的。
    // 2.1 在尽可能靠右的位置进行交换 -> 从后向前找
    // 2.2 把后面比较小的"大数"和前面的"小数进行交换" (1243 -> 1324， 但不能是 1243 -> 1423)
    // 2.3 交换顺序之后，确保后面是升序的。(比如 12465 -> 12546 而不是 12564)
    // 实际算法操作步骤
    // 1. 假如有pre、next作为前后指针。
    // 2. 从后向前找, 直到找到nums[pre] < nums[next]的位置为止，确保[next, end)是降序的
    // 3. 找到之后，寻找[next, end) 里面第一个比nums[pre]大的元素。也就是，num[cur] > num[pre]
    // 4. 然后，交换num[pre] 和 num[cur]的位置，再把[next, end)进行逆序，就是下一个排列了
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int next = len - 1, pre = len - 2;
        while (pre >= 0) {
            if (nums[pre] >= nums[next]) {
                pre--;
                next--;
                continue;
            }
            int cur = len - 1; // 找[next, end)中第一个大于num[pre]的元素
            while (nums[cur] <= nums[pre]) cur--; // 必须找到nums[cur] > nums[pre]的元素
            swap(nums, cur, pre);
            break;
        }
        reverseList(nums, next, len - 1);
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private static void reverseList(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
