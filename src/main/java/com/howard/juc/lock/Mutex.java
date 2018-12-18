package com.howard.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * implement Lock interface
 * Created by howard on 16/6/2.
 * @author howard
 */
public class Mutex implements Lock {

    /**
     * 静态内部类, 自定义同步器。
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 是否处于占用状态
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 尝试获取锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            //通过CAS将state状态由0 修改为1, 如果修改成功则获取到锁。
            if(compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 尝试释放锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            if(getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 创建Condition
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    /**
     *  当前线程获取锁
     */
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * 当前线程获取锁, 并可中断该线程, 并抛出 InterruptedException
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * 尝试获取锁, 并立即返回结果
     * @return true 表示获取锁成功, 否则失败
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    /**
     * 超时尝试获取锁,
     * @param time 时间
     * @param unit 时间单位
     * @return true 表示获取锁成功,否则失败
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    /**
     * 获取等待通知(wait/notify, notifyAll)组件
     * 当前线程只有获取了锁,才能调用该组件的wait()方法,而调用后,当前线程将释放锁, 当前线程的状态为 WAITING
     * @return
     */
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    /**
     * 同步队列上是否有线程
     * @return
     */
    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }
}
