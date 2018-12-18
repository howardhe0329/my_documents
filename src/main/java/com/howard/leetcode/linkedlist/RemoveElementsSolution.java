package com.howard.leetcode.linkedlist;

/**
 * 移除链表元素
 *
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @author howard he
 * @create 2018/11/8 15:46
 */
public class RemoveElementsSolution {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;
        while (fast != null) {
            // 删除
            if (fast.val == val) {
                slow.next = fast.next;
            } else {
                slow = slow.next;
            }
            fast = fast.next;
        }
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            ListNode n = this.next;
            final StringBuilder sb = new StringBuilder();
            sb.append(val);
            while (n != null) {
                sb.append("->").append(n.val);
                n = n.next;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        int val = 6;
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);
        head.next = n1;
        ListNode n2 = new ListNode(6);
        n1.next = n2;
        ListNode n3 = new ListNode(3);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;
        ListNode n5 = new ListNode(5);
        n4.next = n5;
        ListNode n6 = new ListNode(6);
        n5.next = n6;

        RemoveElementsSolution solution = new RemoveElementsSolution();
        System.out.println(head);
        ListNode result = solution.removeElements(head, val);
        System.out.println(result);
    }
}
