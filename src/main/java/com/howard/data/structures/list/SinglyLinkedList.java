package com.howard.data.structures.list;

/**
 * 单向链表
 *
 * @author howard he
 * @create 2018/4/12 16:52
 */
public class SinglyLinkedList<T> {

    /**
     * set head node
     */
    private Node<T> head;
    /**
     * 链表长度
     */
    private int size;

    public SinglyLinkedList() {
        head = null;
    }

    /**
     * 插入到头节点
     * <p>
     * 时间复杂度$O(1)$
     *
     * @param value
     */
    public void insertHead(T value) {
        // 创建一个新节点 cur
        Node<T> cur = new Node(value);
        // 将新节点cur的"next"链接到head
        cur.next = head;
        // 最后将cur指定为head
        head = cur;
        size++;
    }

    /**
     * 插入到指定的位置position
     *
     * @param value
     * @param position
     * @return
     */
    public Node<T> insertNth(T value, int position) {
        // 创建一个新的节点 cur
        Node cur = new Node(value);
        // 将 cur的next链接到指定位置的node
        cur.next = node(position);

        return head;
    }

    /**
     * 指定索引位置的节点
     *
     * @param index
     * @return
     */
    private Node node(int index) {
        Node n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n;
    }

    /**
     * 删除头节点
     * <p>
     * 时间复杂为O(1)
     *
     * @return
     */
    public Node<T> removeHead() {
        if (isEmpty()) {
            return null;
        }
        Node temp = head;
        head = head.next;
        return temp;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.getValue());
            current = current.next;
            if (current != null) {
                sb.append("->");
            }
        }
        return sb.toString();
    }

    private class Node<T> {
        /**
         * The value of the node
         */
        private T value;
        /**
         * Point to the next node
         */
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> myList = new SinglyLinkedList<>();

        System.out.println(myList.isEmpty());

        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(9);

        System.out.println(myList);

        myList.removeHead();

        System.out.println(myList);
    }
}
