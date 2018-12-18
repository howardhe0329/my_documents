package com.howard.leetcode.linkedlist;

/**
 * 环形链表
 *
 * 给定一个链表，判断链表中是否有环。
 *
 * 进阶：
 * 你能否不使用额外空间解决此题？
 *
 * @author howard he
 * @create 2018/10/16 16:15
 */
public class HasCycleSolution {

    public boolean hasCycle(ListNode head) {
        // 边界条件检查
        if (head == null || head.next == null) {
            return false;
        }
        // 慢指针slow指向head
        ListNode slow = head;
        // 快指针fast指向head的下一个结点
        ListNode fast = head.next;
        // 当slow指针和fast指只向同一个结点，那就退出循环。证明链表有环
        while (slow != fast) {
            // 如果为null，证明链表无环
            if (fast == null || fast.next == null) {
                return false;
            }
            // 慢指针走一步
            slow = slow.next;
            // 快指针走两步
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        head.next = second;
        HasCycleSolution solution = new HasCycleSolution();
        boolean result = solution.hasCycle(head);
        System.out.println(result);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
