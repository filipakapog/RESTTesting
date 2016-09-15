package com.example;

import com.example.controller.MainController;
import com.example.exceptions.UserAlreadyDeletedException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.model.User;
import com.example.service.impl.BasicUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by bcaramihai on 9/12/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicUserServiceTest {

    User testUser;
    User updatedTestUser;
    List<User> newUserList;

    @Autowired
    BasicUserService userService;

    @BeforeClass
    public void beforeClass(){

        testUser = new User();
        updatedTestUser = new User();

        testUser.setAge(22);
        testUser.setFirstName("Dumeata");
        testUser.setLastName("Gogu");

        updatedTestUser.setAge(24);
        updatedTestUser.setFirstName("M1k3");
        updatedTestUser.setLastName("J0hnny");

        newUserList = new ArrayList<>();
        newUserList.add(1, testUser);
        newUserList.add(2, updatedTestUser);
    }

    @Before
    public void create() throws UserAlreadyExistsException, UserAlreadyDeletedException {
        userService = mock(BasicUserService.class);

        doReturn(updatedTestUser).when(userService).getUser(1);
        doReturn(newUserList).when(userService).getAllUsers();
    }

    @Test
    public void testUpdateUser() throws UserAlreadyExistsException, UserAlreadyDeletedException {

//        Random r = new Random();
//        int testId = r.nextInt(10);
//
//        User testUser = new User();
//        User updatedTestUser = new User();
//
//        testUser.setAge(22);
//        testUser.setFirstName("Dumeata");
//        testUser.setLastName("Gogu");
//
//        updatedTestUser.setAge(24);
//        updatedTestUser.setFirstName("M1k3");
//        updatedTestUser.setLastName("J0hnny");
//
//        userService.addUser(testId, testUser);
//        userService.updateUser(testId, updatedTestUser);
//
//        assertNotEquals(testUser, userService.getUser(1));
//        userService.deleteUser(testId);

        assertEquals(updatedTestUser, userService.getUser(1));
    }

    @Test
    public void testGetUser() throws UserAlreadyExistsException, UserAlreadyDeletedException {

//        Random r = new Random();
//        int testId = r.nextInt(10);
//
//        User testUser = new User();
//
//        testUser.setAge(22);
//        testUser.setFirstName("Dumeata");
//        testUser.setLastName("Gogu");
//
//        userService.addUser(testId, testUser);
//        assertEquals(testUser, userService.getUser(testId));
//        userService.deleteUser(testId);

        assertEquals(userService.getUser(1), updatedTestUser);
    }

    @Test
    public void testGetAllUsers() throws UserAlreadyExistsException, UserAlreadyDeletedException {

//        Random r = new Random();
//        int rand = r.nextInt(10);
//        int i;
//
//        User testUser = new User();
//
//        testUser.setAge(22);
//        testUser.setFirstName("Dumeata");
//        testUser.setLastName("Gogu");
//
//        for(i = 1; i <= rand; i++){
//            userService.addUser(i, testUser);
//        }
//
//        assertEquals(rand, userService.getAllUsers().size());
//
//        for(i = 1; i<= rand; i++)
//            userService.deleteUser(i);

        assertEquals(newUserList, userService.getAllUsers());
    }

    @Test
    public void testDeleteUser() throws UserAlreadyExistsException, UserAlreadyDeletedException {

//        Random r = new Random();
//        int testId = r.nextInt(10);
//
//        User testUser = new User();
//
//        testUser.setAge(22);
//        testUser.setFirstName("Dumeata");
//        testUser.setLastName("Gogu");
//
//        userService.addUser(testId, testUser);
//        int listSize = userService.getAllUsers().size();
//        try{
//        userService.deleteUser(testId);
//        }catch (UserAlreadyDeletedException e) {assertEquals(listSize, userService.getAllUsers().size());}
//
//        assertEquals(listSize - 1, userService.getAllUsers().size());

        userService.deleteUser(anyInt());
        verify(userService, times(1)).deleteUser(anyInt());
    }

    @Test
    public void testAddUser() throws UserAlreadyExistsException, UserAlreadyDeletedException {

//        Random r = new Random();
//        int testId = r.nextInt(10);
//
//        User testUser = new User();
//
//        testUser.setAge(22);
//        testUser.setFirstName("Dumeata");
//        testUser.setLastName("Gogu");
//
//        int listSize = userService.getAllUsers().size();
//        userService.addUser(testId, testUser);
//
//        assertEquals(listSize + 1, userService.getAllUsers().size());
//        userService.deleteUser(testId);

        //assertEquals(testUser, userService.addUser(1, testUser));
        userService.addUser(1, testUser);
        verify(userService, times(1)).addUser(1, testUser);
    }


//    @Test
//    public void clearList() throws UserAlreadyDeletedException {
//        for(int i = 1; i < 11; i++)
//            userService.deleteUser(i);
//    }
}
