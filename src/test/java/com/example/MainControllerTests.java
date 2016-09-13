package com.example;

import com.example.controller.MainController;
import com.example.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import static org.junit.Assert.*;

import org.apache.http.util.EntityUtils;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;

import java.util.Random;




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

    private static String body = "{\"firstName\":\"Maria\",\"lastName\":\"Ioana\",\"age\":25}";
    private static String bodyUpdate = "{\"firstName\":\"Ion\",\"lastName\":\"Popescu\",\"age\":73}";

    static {
        random = new Random();
        letters = new char[26];
        int i = 0;
        for (char ch = 'a'; ch <= 'z' && i < letters.length; ch++) {
            letters[i++] = ch;
        }
    }

    @Test
    public void testAddUser() throws IOException {
        // [ Q ] How to perform same behaviour with mocking?

        int userId = 1;
        String theUrl = "http://localhost:8080/users/" + userId;
        HttpClient theClient = new DefaultHttpClient();
        HttpPost theRequest = new HttpPost(theUrl);
        theRequest.addHeader("Content-Type", "application/json");

        EntityBuilder entityBuilder = EntityBuilder.create();
        entityBuilder.setText(body);
        theRequest.setEntity(entityBuilder.build());
        HttpResponse response = theClient.execute(theRequest);

        assertEquals(response.getStatusLine().getStatusCode(), 200);
        String mimeType = EntityUtils.getContentMimeType(response.getEntity());
        assertEquals("text/plain", mimeType);
    }

    @Test
    public void testGetUser() throws IOException {
        int userId = 1;
        String theUrl = "http://localhost:8080/users/" + userId;
        HttpClient theClient = new DefaultHttpClient();
        HttpGet theRequest = new HttpGet(theUrl);
        theRequest.addHeader("Content-Type", "text/plain");

        HttpResponse response = theClient.execute(theRequest);

        EntityBuilder entityBuilder = EntityBuilder.create();
        entityBuilder.setText(body);

        // I think that's enough to test the response code
        assertEquals(response.getStatusLine().getStatusCode(), 200);
        String mimeType = EntityUtils.getContentMimeType(response.getEntity());
        assertEquals("application/json", mimeType);

        // otherwise if testUpdateUser is ran before testGetUser, the data in the body of the response will change
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        String res = result.toString();
//        String expected = body;
//        assertEquals(expected, res);
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
    public void testUpdateUser() throws IOException {
        int userId = 1;
        String theUrl = "http://localhost:8080/users/" + userId;
        HttpClient theClient = new DefaultHttpClient();
        HttpPut theRequest = new HttpPut(theUrl);
        theRequest.addHeader("Content-Type", "application/json");

        EntityBuilder entityBuilder = EntityBuilder.create();
        entityBuilder.setText(bodyUpdate);
        theRequest.setEntity(entityBuilder.build());
        HttpResponse response = theClient.execute(theRequest);

        assertEquals(200, response.getStatusLine().getStatusCode());
        String mimeType = EntityUtils.getContentMimeType(response.getEntity());
        assertEquals("text/plain", mimeType);
    }

    @Test
    public void testDeleteUser() throws IOException {
        int userId = 1;
        String theUrl = "http://localhost:8080/users/" + userId;
        HttpClient theClient = new DefaultHttpClient();
        HttpDelete theRequest = new HttpDelete(theUrl);
        theRequest.addHeader("Content-Type", "text/plain");

        HttpResponse response = theClient.execute(theRequest);

        assertEquals(200, response.getStatusLine().getStatusCode());
        String mimeType = EntityUtils.getContentMimeType(response.getEntity());
        assertEquals("text/plain", mimeType);
    }

    @Test
    public void testDeleteNonexistentUser() throws IOException {
        int userId = -1;
        String theUrl = "http://localhost:8080/users/" + userId;
        HttpClient theClient = new DefaultHttpClient();
        HttpDelete theRequest = new HttpDelete(theUrl);
        theRequest.addHeader("Content-Type", "text/plain");

        HttpResponse response = theClient.execute(theRequest);

        assertEquals(400, response.getStatusLine().getStatusCode());
        String mimeType = EntityUtils.getContentMimeType(response.getEntity());
        assertEquals("text/plain", mimeType);
    }

    @Test
    public void testGetAllUsers() throws IOException {
        String theUrl = "http://localhost:8080/users";
        HttpClient theClient = new DefaultHttpClient();
        HttpGet theRequest = new HttpGet(theUrl);
        theRequest.addHeader("Content-Type", "text/plain");

        HttpResponse response = theClient.execute(theRequest);

        assertEquals(200, response.getStatusLine().getStatusCode());
        String mimeType = EntityUtils.getContentMimeType(response.getEntity());
        assertEquals("application/json", mimeType);
    }
}

