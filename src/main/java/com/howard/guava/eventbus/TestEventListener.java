package com.howard.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created by howard on 16/4/12.
 */
public class TestEventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent testEvent) {
        lastMessage = testEvent.getMessage();
        System.out.println("Message:" + lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
