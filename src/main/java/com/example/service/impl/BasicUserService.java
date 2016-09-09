package com.example.service.impl;

import com.example.UserAlreadyExistsException;
import com.example.model.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fghimpeteanu on 9/9/2016.
 */
@Service
public class BasicUserService implements UserService {
    public static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(BasicUserService.class);
    }

    public Map<Integer, User> userList = new HashMap<>();
    @Override
    public void addUser(int userId, User theUser) throws UserAlreadyExistsException {
        if(theUser != null) {
            if (!userExists(userId)) {
                userList.put(userId, theUser);
                LOGGER.info("[LOCAL]: Added user with user=" + theUser + " and userID=" + userId);
            } else {
                LOGGER.info("[LOCAL]: The User user=" + theUser + " with userID=" + userId + "already exists");
                throw new UserAlreadyExistsException();
            }
        }
    }

    private boolean userExists(int userId) {
        return null != userList.get(userId);
    }

    @Override
    public User getUser(int userId) {
        User newUser = userList.get(userId);

        LOGGER.info("[LOCAL]: Got user with user=" + newUser + " and userID=" + userId);

        return newUser;
    }

    @Override
    public void deleteUser(int userId) {
        User deletedUser = userList.get(userId);
        userList.remove(userId);
        LOGGER.info("[LOCAL]: Deleted user=" + deletedUser + " with userID=" + userId);
    }

    @Override
    public void updateUser(int userId, User newUserData) {
        userList.put(userId, newUserData);
        LOGGER.info("[LOCAL]: Updated user with userID=" + userId + "with newUserData=" + newUserData);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : userList.entrySet()) {
            allUsers.add(entry.getValue());
        }

        LOGGER.info("[LOCAL]: Got the list of users list=" + allUsers);

        return allUsers;
    }
}
