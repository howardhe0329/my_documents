package com.howard.leetcode.queue;

/**
 * 自已设计一个循环队列
 *
 * @author howard he
 * @create 2018-12-07 14:14
 */
public class MyCircularQueue {

    private int head;
    private int tail;
    private int size;
    private int[] items;


    public MyCircularQueue(int k) {
        if (k < 1) {
            return;
        }
        head = -1;
        tail = -1;
        size = k;
        items = new int[k];
    }

    /**
     * 入队
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0;
        }
        tail = (tail + 1) % size;
        items[tail] = value;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    /**
     * 从队首获取元素。如果队列为空，返回-1
     * @return
     */
    public int front() {
        if (isEmpty()) {
            return -1;
        }
        return items[head];
    }

    /**
     * 获取队尾元素，如果队列为空，返回-1
     * @return
     */
    public int rear() {
        if (isEmpty()) {
            return -1;
        }
        return items[tail];
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return head == -1;
    }

    /**
     * 是否已满
     * @return
     */
    public boolean isFull() {
        return (tail + 1) % size == head;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        System.out.println(circularQueue.enQueue(1));
        System.out.println(circularQueue.enQueue(2));
        System.out.println(circularQueue.enQueue(3));
        System.out.println(circularQueue.enQueue(4));
        System.out.println(circularQueue.rear());
        System.out.println(circularQueue.isFull());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.enQueue(4));
        System.out.println(circularQueue.rear());
    }
}
