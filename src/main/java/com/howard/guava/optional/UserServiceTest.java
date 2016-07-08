package com.howard.guava.optional;

import com.google.common.base.Optional;

/**
 * Created by howard on 16/7/6.
 */
public class UserServiceTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        Optional<User> userOptional = userService.getUserById(-1);
        User user = userOptional.get();
        System.out.println(user);
    }
}
