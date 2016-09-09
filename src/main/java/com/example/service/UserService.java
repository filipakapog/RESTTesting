package com.example.service;

import com.example.UserAlreadyExistsException;
import com.example.model.User;

import java.util.List;

/**
 * Created by fghimpeteanu on 9/9/2016.
 */
public interface UserService {
    void addUser(int userId, User theUser) throws UserAlreadyExistsException;
    User getUser(int userId);
    void deleteUser(int userId);
    void updateUser(int userId, User newUserData);

    List<User> getAllUsers();
}
