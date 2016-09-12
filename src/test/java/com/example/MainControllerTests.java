package com.example;

import com.example.controller.MainController;
import com.example.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by fghimpeteanu on 9/9/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTests {
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


    @Test
    public void testAddUser() {

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

    }

    @Test
    public void testUpdateUser(){


    }
}
