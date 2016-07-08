package com.howard.guava.optional;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 * Created by howard on 16/7/6.
 */
public class UserServiceImpl implements UserService {

    private static final Map<Integer, User> userMap = Maps.newHashMap();

    static {
        userMap.put(1, new User(1, "sally", 5));
        userMap.put(2, new User(1, "lucy", 10));
        userMap.put(3, new User(1, "lea", 20));
        userMap.put(4, new User(1, "howard", 30));
        userMap.put(5, new User(1, "pill", 40));
    }

    @Override
    public Optional<User> getUserById(int userId) {
        Preconditions.checkArgument(userId >= 0, "arg userId: %s gt zero", userId);
        User user = userMap.get(userId);
        return Optional.fromNullable(user);
    }
}
