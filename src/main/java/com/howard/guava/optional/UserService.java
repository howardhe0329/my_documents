package com.howard.guava.optional;

import com.google.common.base.Optional;

/**
 * Created by howard on 16/7/6.
 */
public interface UserService {

    Optional<User> getUserById(int userId);
}
