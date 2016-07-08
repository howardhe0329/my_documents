package com.howard.guava;

import com.howard.guava.optional.User;

/**
 * Created by howard on 16/7/6.
 */
public class MoreObjectsDemo {

    public static void main(String[] args) {
        User user = new User(2, null, 1);
        System.out.println(user);

        System.out.println(user.hashCode());
    }
}
