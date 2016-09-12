package com.example;

import com.example.controller.MainController;
import com.example.exceptions.UserAlreadyDeletedException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.model.User;
import com.example.service.impl.BasicUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by bcaramihai on 9/12/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicUserServiceTest {

    @Autowired
    BasicUserService userService;

    @Test
    public void testUpdateUser() throws UserAlreadyExistsException, UserAlreadyDeletedException {

        Random r = new Random();
        int testId = r.nextInt(10);

        User testUser = new User();
        User updatedTestUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Mike");
        testUser.setLastName("Johnny");

        updatedTestUser.setAge(24);
        updatedTestUser.setFirstName("M1k3");
        updatedTestUser.setLastName("J0hnny");

        userService.addUser(testId, testUser);
        userService.updateUser(testId, updatedTestUser);

        assertNotEquals(testUser, userService.getUser(1));
        userService.deleteUser(testId);

    }

    @Test
    public void testGetUser() throws UserAlreadyExistsException, UserAlreadyDeletedException {

        User testUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Dumeata");
        testUser.setLastName("Gogu");

        userService.addUser(1, testUser);
        assertEquals(testUser, userService.getUser(1));
        userService.deleteUser(1);
    }

    @Test
    public void testGetAllUsers(){

    }
}
