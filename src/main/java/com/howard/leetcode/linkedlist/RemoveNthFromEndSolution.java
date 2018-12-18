package com.howard.leetcode.linkedlist;

/**
 * 删除链表的倒数第N个节点
 * <p>
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 *
 * @author howard he
 * @create 2018/10/18 15:28
 */
public class RemoveNthFromEndSolution {

    /**
     * 解题思路
     * <p>
     * 前提条件：
     * <p>
     * 链表是无环的链表
     * <p>
     * 1. 在head节点前创建一个假结点（dummy）做为辅助，该结点放在列表的头部。（假结点目的是为了简化某些极端的情况，例如列表中只含
     *  有一个结点，或者需要删除列表的头部。）
     * 2. 定义两个指针的，分别为p1和p2.
     * 3. 要删除第n个节点，那么我们可以先让指针p2先走n步。然后p1和p2同时走，直到p2走到链表尾部。那么
     * p1的位置就是要删除的节点。
     * <p>
     * 考查点：
     * 双指针，用快慢指针的思想。先让一个指针p2先走，然后让另一个指针p1同步走。直到指针p2走到链尾
     *
     * @param head 链表的头部
     * @param n    删除第n个节点
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 判断如果n 小于等0，没有要删除的节点。
        if (n <= 0) {
            return head;
        }
        // 创建一个假节点做为头节点.
        // 为什么要创建假节点呢？
        // 1. 当列表中只含有一个结点时
        // 2. 当需要删除列表的头部时
        ListNode dummy = new ListNode(-1);
        // 新节点的next指针指向head节点。
        dummy.next = head;
        // 定义指针p1并指向新节点
        ListNode first = dummy;
        // 定义指针p2并指向新节点
        ListNode second = dummy.next;
        // 用指针p2去遍历链表
        while (second.next != null) {
            // 指针p2开始先走
            second = second.next;
            if (n > 1) {
                n--;
            } else {
                // 当n == 1时，让指针p1开始走
                first = first.next;
            }
        }
        // first.next指向的节点就是要删除的节点。first就是要删除节点的前一个节点。而first.next.next就是要删除的节点的下一个节点。
        first.next = first.next.next;
        // 要把dummy结点去掉。
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        head.next = second;
        ListNode third = new ListNode(3);
        second.next = third;
        ListNode four = new ListNode(4);
        third.next = four;
        ListNode five = new ListNode(5);
        four.next = five;
        int n = 1;
        RemoveNthFromEndSolution solution = new RemoveNthFromEndSolution();
        ListNode result = solution.removeNthFromEnd(head, n);
        ListNode ln = result;
        while (ln != null) {
            System.out.println(ln);
            ln = ln.next;
        }
    }

    /**
     *
     */
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
