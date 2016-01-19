package com.howard.juc.lock;

import java.util.Collection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by howard on 16/1/19.
 */
public class ExtendsReentrantLock extends ReentrantLock {

    @Override
    protected Collection<Thread> getQueuedThreads() {
        return super.getQueuedThreads();
    }

    @Override
    protected Collection<Thread> getWaitingThreads(Condition condition) {
        return super.getWaitingThreads(condition);
    }

    @Override
    protected Thread getOwner() {
        return super.getOwner();
    }
}
