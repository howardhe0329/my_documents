package com.howard.juc.thread;

/**
 * 使用synchronized语法出现死锁的例子:
 * 我们发现在线程1和线程2的State是BLOCKED.
 * 然后我们thread dump, 发现有死锁现象发生
 * Found one Java-level deadlock:
     =============================
     "thread2":
     waiting to lock monitor 0x00007fc82b812d58 (object 0x0000000795790b58, a java.lang.Object),
     which is held by "thread1"
     "thread1":
     waiting to lock monitor 0x00007fc82c8d0208 (object 0x0000000795790b68, a java.lang.Object),
     which is held by "thread2"

     Java stack information for the threads listed above:
     ===================================================
     "thread2":
     at com.howard.juc.thread.DeadLockTest2.lambda$main$1(DeadLockTest2.java:29)
     - waiting to lock <0x0000000795790b58> (a java.lang.Object)
     - locked <0x0000000795790b68> (a java.lang.Object)
     at com.howard.juc.thread.DeadLockTest2$$Lambda$2/764977973.run(Unknown Source)
     at java.lang.Thread.run(Thread.java:745)
     "thread1":
     at com.howard.juc.thread.DeadLockTest2.lambda$main$0(DeadLockTest2.java:21)
     - waiting to lock <0x0000000795790b68> (a java.lang.Object)
     - locked <0x0000000795790b58> (a java.lang.Object)
     at com.howard.juc.thread.DeadLockTest2$$Lambda$1/931919113.run(Unknown Source)
     at java.lang.Thread.run(Thread.java:745)

     Found 1 deadlock.

 * Created by howard on 16/1/18.
 */
public class DeadLockTest2 {

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Object object1 = new Object();
        Object object2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                sleep(100);
                synchronized (object2) {
                    System.out.println("get lock object2");
                }
            }
        }, "thread1");
        thread1.start();
        Thread thread2 = new Thread(() -> {
            synchronized (object2) {
                synchronized (object1) {
                    System.out.println("get lock object1");
                }
            }
        }, "thread2");
        thread2.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread name: " + thread1.getName() + ", state: " + thread1.getState().name());
            System.out.println("Thread name: " + thread2.getName() + ", state: " + thread2.getState().name());
            sleep(1000);
        }
    }
}
