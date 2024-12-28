package com.swimmingliu.linkedlist;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class Q19 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    // 暴力解
//    public static ListNode removeNthFromEnd(ListNode head, int n) {
//        if (head.next == null)
//            return null;
//        ListNode fast = head;
//        ListNode slow = head;
//        while (fast.next != null) {
//            fast = fast.next;
//            if (n <= 0) {
//                slow = slow.next;
//            }
//            n --;
//        }
//        if (n <= 0) { // 慢指针起作用了, 删除节点
//            slow.next = slow.next.next;
//        }else{ // 要删除第一个节点
//            head = head.next;
//        }
//        return head;
//    }

    // 优化
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null)
            return null;
        ListNode ans = new ListNode(0, head);
        ListNode prev = ans;
        ListNode tail = ans;
        while (n > 0){ // 走n步，最多走到最后一个节点
            prev = prev.next;
            n --;
        }
        while (prev.next != null){ // 快慢指针同时移动
            prev = prev.next;
            tail = tail.next;
        }
        tail.next = tail.next.next;
        return ans.next;
    }

    public static void main(String[] args) {
        int[] list = {1, 2};
        ListNode listNode = new ListNode(list[0]);
        ListNode listHead = listNode;
        for (int i = 1; i < list.length; i++) {
            ListNode node = new ListNode(list[i]);
            listNode.next = node;
            listNode = node;
        }
        ListNode result = removeNthFromEnd(listHead, 2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
