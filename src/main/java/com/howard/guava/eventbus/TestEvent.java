package com.howard.guava.eventbus;

/**
 * Created by howard on 16/4/12.
 */
public class TestEvent {

    private int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("message: " + message);
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
