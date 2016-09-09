package com.example;

import com.example.controller.MainController;
import com.example.model.User;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fghimpeteanu on 9/9/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTests {
    private static final MainController maincontroller = new MainController();

    public void testAddUser() {
        User newUser = new User();
        newUser.setFirstName("Ion");
        newUser.setLastName("Popescu");
        newUser.setAge(22);
    }
}
