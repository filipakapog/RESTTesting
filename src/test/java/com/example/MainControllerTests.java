package com.example;

import com.example.controller.MainController;
import com.example.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by fghimpeteanu on 9/9/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTests {
//    private static final MainController mainController = new MainController();

    @Autowired
    MainController mainController;

    //[ Q ] Is it ok to just copy paste these two lines from the project
    private static final String TEXT_RESPONSE_OK = "The request was successful";
    private static final String TEXT_RESPONSE_NOT_OK = "The request was unsuccessful";

    private static final int MAX_AGE = 80;
    private static final int MAX_RANDOM_STRING_SIZE = 5; // characters
    private static final char[] letters;
    private static Random random;

    static {
        random = new Random();
        letters = new char[26];
        int i = 0;
        for (char ch = 'a'; ch <= 'z' && i < letters.length; ch++) {
            letters[i++] = ch;
        }
    }

    User newUser;
    ArrayList<User> listOfUsers;

    @Before void initEnv() {
        newUser = getNewUser();
    }


    @Test
    public void testAddUser() {
        User newUser = getNewUser();
        String response = mainController.addUser(1, newUser);
        assertEquals(TEXT_RESPONSE_OK, response);
    }

    private User getNewUser() {
        User newUser = new User();
        newUser.setFirstName(getRandomString());
        newUser.setLastName(getRandomString());
        newUser.setAge(random.nextInt(MAX_AGE));
        return newUser;
    }

    private String getRandomString() {
        StringBuilder theRandomString = new StringBuilder("User-");
        int randomIdx = random.nextInt(letters.length);
        for (int i = 0; i < MAX_RANDOM_STRING_SIZE; i++) {
            theRandomString.append(letters[randomIdx]);
            randomIdx = random.nextInt(letters.length);
        }
        return theRandomString.toString();
    }


    @Test
    public void testAddUserTwice() {
        mainController.addUser(1, newUser);
        String response = mainController.addUser(1, newUser);
        assertNotEquals(TEXT_RESPONSE_OK, response);
    }

    @Test
    public void testUpdateUser() {
        User updatedUser = getNewUser();

        mainController.addUser(1, newUser);
        mainController.updateUser(1, updatedUser);

        assertNotEquals(newUser, mainController.getUser(1));
    }


}
