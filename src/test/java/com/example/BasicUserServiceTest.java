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

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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

        Random r = new Random();
        int testId = r.nextInt(10);

        User testUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Dumeata");
        testUser.setLastName("Gogu");

        userService.addUser(testId, testUser);
        assertEquals(testUser, userService.getUser(testId));
        userService.deleteUser(testId);
    }

    @Test
    public void testGetAllUsers() throws UserAlreadyExistsException, UserAlreadyDeletedException {

        Random r = new Random();
        int rand = r.nextInt(10);
        int i;

        User testUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Dumeata");
        testUser.setLastName("Gogu");

        for(i = 1; i <= rand; i++){
            userService.addUser(i, testUser);
        }

        assertEquals(rand, userService.getAllUsers().size());

        for(i = 1; i<= rand; i++)
            userService.deleteUser(i);
    }

    @Test
    public void testDeleteUser() throws UserAlreadyExistsException, UserAlreadyDeletedException {

        Random r = new Random();
        int testId = r.nextInt(10);

        User testUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Dumeata");
        testUser.setLastName("Gogu");

        userService.addUser(testId, testUser);
        int listSize = userService.getAllUsers().size();
        try{
        userService.deleteUser(testId);
        }catch (UserAlreadyDeletedException e) {assertEquals(listSize, userService.getAllUsers().size());}

        assertEquals(listSize - 1, userService.getAllUsers().size());
    }

    @Test
    public void testAddUser() throws UserAlreadyExistsException, UserAlreadyDeletedException {

        Random r = new Random();
        int testId = r.nextInt(10);

        User testUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Dumeata");
        testUser.setLastName("Gogu");

        int listSize = userService.getAllUsers().size();
        userService.addUser(testId, testUser);

        assertEquals(listSize + 1, userService.getAllUsers().size());
        userService.deleteUser(testId);
    }


//    @Test
//    public void clearList() throws UserAlreadyDeletedException {
//        for(int i = 1; i < 11; i++)
//            userService.deleteUser(i);
//    }
}
