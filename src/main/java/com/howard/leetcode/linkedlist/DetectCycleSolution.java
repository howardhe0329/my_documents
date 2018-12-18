package com.howard.leetcode.linkedlist;

/**
 * 环形链表2
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 *
 * @author howard he
 * @create 2018/10/18 10:34
 */
public class DetectCycleSolution {

    /**
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        // 慢指针
        ListNode slow = head;
        // 快指针
        ListNode fast = head;
        while (true) {
            // 说明链表是无环的
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            // 如果slow == fast 说明链表有环
            if (slow == fast) {
                break;
            }
        }
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(0);
        ListNode four = new ListNode(-4);
        head.next = second;
        second.next = third;
        third.next = four;
        four.next = head;
        DetectCycleSolution solution = new DetectCycleSolution();
        System.out.println(solution.detectCycle(head));
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ListNode{");
            sb.append("val=").append(val);
            sb.append('}');
            return sb.toString();
        }
    }
}
