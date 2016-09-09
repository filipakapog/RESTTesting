package com.example;

import com.example.controller.MainController;
import com.example.model.User;
import com.example.service.UserService;
import com.sun.javaws.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by fghimpeteanu on 9/9/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTests {

    @Autowired
    MainController mainController;

    @Test
    public void testUpdateUser(){

        final Map<Integer, User> userList = new HashMap<>();

        User testUser = new User();
        User updatedTestUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Mike");
        testUser.setLastName("Johnny");

        updatedTestUser.setAge(24);
        updatedTestUser.setFirstName("M1k3");
        updatedTestUser.setLastName("J0hnny");

        mainController.addUser(1, testUser);
        mainController.updateUser(1, updatedTestUser);

        assertNotEquals(testUser, mainController.getUser(1));
    }
}
