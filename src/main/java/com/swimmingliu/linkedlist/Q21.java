package com.swimmingliu.linkedlist;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Q21 {
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
//    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        if (list1 == null && list2 == null) return null;
//        else if (list1 == null) return list2;
//        else if (list2 == null) return list1;
//        ListNode combineHead;
//        if (list1.val <= list2.val) {
//            combineHead = list1;
//            list1 = list1.next;
//        } else {
//            combineHead = list2;
//            list2 = list2.next;
//        }
//        ListNode combineNode = combineHead;
//        while (list1 != null && list2 != null) {
//            if (list1.val <= list2.val) {
//                combineNode.next = list1;
//                combineNode = combineNode.next;
//                list1 = list1.next;
//            } else {
//                combineNode.next = list2;
//                combineNode = combineNode.next;
//                list2 = list2.next;
//            }
//        }
//        while (list1 != null) {
//            combineNode.next = list1;
//            combineNode = combineNode.next;
//            list1 = list1.next;
//        }
//        while (list2 != null) {
//            combineNode.next = list2;
//            combineNode = combineNode.next;
//            list2 = list2.next;
//        }
//        return combineHead;
//    }
//

    // 优化
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode combineHead = new ListNode();
        ListNode combineNode = combineHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                combineNode.next = new ListNode(list1.val);
                combineNode = combineNode.next;
                list1 = list1.next;
            } else {
                combineNode.next =  new ListNode(list2.val);
                combineNode = combineNode.next;
                list2 = list2.next;
            }
        }
        while (list1 != null) {
            combineNode.next = new ListNode(list1.val);
            combineNode = combineNode.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            combineNode.next = new ListNode(list2.val);
            combineNode = combineNode.next;
            list2 = list2.next;
        }
        return combineHead.next;
    }


    public static void main(String[] args) {
        int[] list1 = {1, 2, 4};
        int[] list2 = {1, 3, 4};
        ListNode listNode = new ListNode(list1[0]);
        ListNode listHead1 = listNode;
        for (int i = 1; i < list1.length; i++) {
            ListNode node = new ListNode(list1[i]);
            listNode.next = node;
            listNode = node;
        }
        listNode = new ListNode(list2[0]);
        ListNode listHead2 = listNode;
        for (int i = 1; i < list1.length; i++) {
            ListNode node = new ListNode(list2[i]);
            listNode.next = node;
            listNode = node;
        }
        ListNode result = mergeTwoLists(listHead1, listHead2);
        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }

    }
}
