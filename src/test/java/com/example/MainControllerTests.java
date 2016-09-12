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


}
