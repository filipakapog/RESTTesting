package com.example;

import com.example.exceptions.UserAlreadyExistsException;
import com.example.model.User;
import com.example.service.impl.BasicUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fghimpeteanu on 9/12/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicUserServiceTests {
    @Autowired
    BasicUserService userService;



    @Test
    public void testAddUser() {
        User user = new User();
        user.setFirstName("Ion");
        user.setLastName("Popescu");
        user.setAge(21);

        try {
            userService.addUser(1, user);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
