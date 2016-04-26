package com.howard.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Created by howard on 16/4/12.
 */
public class TestEventBus {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        TestEventListener testEventListener = new TestEventListener();
        eventBus.register(testEventListener);

        eventBus.post(new TestEvent(100));
        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:" + testEventListener.getLastMessage());
    }
}
