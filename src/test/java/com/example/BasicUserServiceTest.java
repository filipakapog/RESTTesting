package com.example;

import com.example.controller.MainController;
import com.example.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by bcaramihai on 9/12/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicUserServiceTest {

    @Autowired
    MainController mainController;

    @Test
    public void testUpdateUser(){

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
        mainController.deleteUser(1);
    }

    @Test
    public void testGetUser(){

        User testUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Dumeata");
        testUser.setLastName("Gogu");

        mainController.addUser(1, testUser);
        assertEquals(testUser, mainController.getUser(1));
        mainController.deleteUser(1);
    }

    @Test
    public void testGetAllUsers(){

    }
}
