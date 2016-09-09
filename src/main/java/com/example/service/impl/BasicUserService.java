package com.example.service.impl;

import com.example.model.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by fghimpeteanu on 9/9/2016.
 */
@Service
public class BasicUserService implements UserService {
    public static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(BasicUserService.class);
    }

    @Override
    public void addUser(int userId, User theUser) {
        LOGGER.info("[LOCAL]: Added user with user=" + theUser + " and userID=" + userId);
    }

    @Override
    public User getUser(int userId) {
        User newUser = new User();
        newUser.setFirstName("Ion");
        newUser.setLastName("Mihai");
        newUser.setAge(20);

        LOGGER.info("[LOCAL]: Got user with user=" + newUser + " and userID=" + userId);

        return newUser;
    }

    @Override
    public void deleteUser(int userId) {
        LOGGER.info("[LOCAL]: Deleted user with userID=" + userId);
    }

    @Override
    public void updateUser(int userId, User newUserData) {
        LOGGER.info("[LOCAL]: Updated user with userID=" + userId + "with newUserData=" + newUserData);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        User newUser1 = new User();
        newUser1.setFirstName("Ion1");
        newUser1.setLastName("Mihai1");
        newUser1.setAge(20);
        allUsers.add(newUser1);

        User newUser2 = new User();
        newUser2.setFirstName("Ion2");
        newUser2.setLastName("Mihai2");
        newUser2.setAge(21);
        allUsers.add(newUser2);

        User newUser3 = new User();
        newUser3.setFirstName("Ion3");
        newUser3.setLastName("Mihai3");
        newUser3.setAge(23);
        allUsers.add(newUser3);

        LOGGER.info("[LOCAL]: Got the list of users list=" + allUsers);

        return allUsers;
    }
}
