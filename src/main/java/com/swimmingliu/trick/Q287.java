package com.swimmingliu.trick;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），
 * 可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 */
public class Q287 {
    // 快慢指针：将原数组当成链表来看 -> next = num[next] -> next = next->next
    // 假如数组元素为 [1, 2, 3, 4, 5, 3] -> 经过 1, 2, 3, [4, 5]
    // 其中[4, 5]组成环形， 入口元素为 3 (num[3]就进入环形了)
    // 对于环形链表可以采用快慢指针找到环内的位置 (快慢指针会在环内相遇)
    // 假设环前面的长度为m, 环内的长度为c，slow走n步之后和fast相遇。
    // fast比slow多走了n步，而这n步都是在环里面，所以n % c == 0 (相当于n步是循环了好几圈)
    // fast 和 last 相遇之后，设置第三个指针 finder，它从起点开始和 slow(在 fast 和 slow 相遇处)同步前进，
    // 当 finder 和 slow 相遇时，就是在环的入口处相遇，也就是重复的那个数字相遇。
    // fast 和 slow 相遇时，slow 在环内走的距离是n - m，其中n % c == 0。
    // 这时我们再让slow前进 m 步 (也就是在环中走了 n 步了)，它就会回到起点的位置
    public static int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) break;
        }
        int finder = 0;
        while (true){
            finder = nums[finder];
            slow = nums[slow];
            // 找到入口了, finder就是入口元素，num[finder]就是环内第一个元素
            if (finder == slow) break;
        }
        return finder;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));
    }
}
