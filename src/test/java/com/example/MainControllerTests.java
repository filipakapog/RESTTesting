package com.example;

import com.example.controller.MainController;
import com.example.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by fghimpeteanu on 9/9/2016.
 */


public class MainControllerTests extends ProjectTesterApplication{
    @Autowired
    MainController mainController;

    //[ Q ] Is it ok to just copy paste these two lines from the project
    private static final String TEXT_RESPONSE_OK = "The request was successful";
    private static final String TEXT_RESPONSE_NOT_OK = "The request was unsuccessful";

    private static final int MAX_AGE = 80;
    private static final int MAX_RANDOM_STRING_SIZE = 5; // characters
    private static final char[] letters;
    private static Random random;

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    String body = "{\n" +
            "\t\"firstName\":\"Maria\",\n" +
            "\t\"lastName\":\"Ioana\",\n" +
            "\t\"age\":25\n" +
            "} ";

    static {
        random = new Random();
        letters = new char[26];
        int i = 0;
        for (char ch = 'a'; ch <= 'z' && i < letters.length; ch++) {
            letters[i++] = ch;
        }
    }


    @Test
    public void testAddUserGoodUrl() {
//         [ Q ] How to perform same behaviour with mocking?
        int userId = 1;
        String theUrl = "http://localhost:8080/users/" + userId;
        HttpClient theClient = new DefaultHttpClient();
        HttpPost theRequest = new HttpPost(theUrl);
        theRequest.addHeader("Content-Type", CONTENT_TYPE);

        // setting the request parameters
        List<NameValuePair> theRequestParameters = new ArrayList<>();

        EntityBuilder entityBuilder = EntityBuilder.create();
        entityBuilder.setText(body);

        User user = getNewUser();
        theRequestParameters.add(new BasicNameValuePair("firstName", user.getFirstName()));
        theRequestParameters.add(new BasicNameValuePair("lastName", user.getLastName()));
        theRequestParameters.add(new BasicNameValuePair("age", "" + user.getAge()));
        try {
//            theRequest.setEntity( new UrlEncodedFormEntity(theRequestParameters));
            theRequest.setEntity(entityBuilder.build());
            HttpResponse response = theClient.execute(theRequest);

            assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testgetUserTest() throws IOException {
        // [ Q ] How to perform same behaviour with mocking?
        int userId = 2;
        String theUrl = "http://localhost:8080/users/" + userId;
        HttpClient theClient = new DefaultHttpClient();
        HttpGet theRequest = new HttpGet(theUrl);
        theRequest.addHeader("User-Agent", USER_AGENT); // should check what happens if omitted
        theRequest.addHeader("Content-Type", CONTENT_TYPE);

        HttpResponse response = theClient.execute(theRequest);

        assertEquals(200, response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        String res = result.toString();
        String expected = "{\"firstName\":\"Maria\",\"lastName\":\"Ioana\",\"age\":25}";
        assertEquals(expected, res);
    }

    @Test
    public void testDeleteUser() throws IOException {
        // [ Q ] How to perform same behaviour with mocking?
        int userId = 3;
        String theUrl = "http://localhost:8080/users/" + userId;
        HttpClient theClient = new DefaultHttpClient();
        HttpDelete theRequest = new HttpDelete(theUrl);
        theRequest.addHeader("User-Agent", USER_AGENT); // should check what happens if omitted
        theRequest.addHeader("Content-Type", CONTENT_TYPE);

        HttpResponse response = theClient.execute(theRequest);

        assertEquals(200, response.getStatusLine().getStatusCode());
    }

//    @Test
//    public void testDeleteNonexistentUser() throws IOException {
//        int userId = 4;
//        String theUrl = "http://localhost:8080/users/" + userId;
//        HttpClient theClient = new DefaultHttpClient();
//        HttpDelete theRequest = new HttpDelete(theUrl);
//        theRequest.addHeader("User-Agent", USER_AGENT); // should check what happens if omitted
//        theRequest.addHeader("Content-Type", CONTENT_TYPE);
//
//        HttpResponse response = theClient.execute(theRequest);
//
//        assertEquals(400, response.getStatusLine().getStatusCode());
//    }



//        // setting the request parameters
//        List<NameValuePair> theRequestParameters = new ArrayList<>();
//
//        User user = getNewUser();
//        theRequestParameters.add(new BasicNameValuePair("firstName", user.getFirstName()));
//        theRequestParameters.add(new BasicNameValuePair("lastName", user.getLastName()));
//        theRequestParameters.add(new BasicNameValuePair("age", "" + user.getAge()));
//        try {
//            theRequest.setEntity( new UrlEncodedFormEntity(theRequestParameters));
//            HttpResponse response = theClient.execute(theRequest);
//
//            Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.OK);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



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
