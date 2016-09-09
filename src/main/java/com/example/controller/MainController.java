package com.example.controller;

import com.example.exceptions.UserAlreadyDeletedException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fghimpeteanu on 9/9/2016.
 */
@Controller
public class MainController {
    private static final String TEXT_RESPONSE_OK = "The request was successful";
    private static final String TEXT_RESPONSE_NOT_OK = "The request was unsuccessful";

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public
    @ResponseBody
    String index() {
        return "This is just the index page";
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST, consumes = {"application/json"},
            produces = "text/plain")
    public @ResponseBody String addUser(@PathVariable("id") int id, @RequestBody User user) {
        try {
            userService.addUser(id, user);
        } catch (UserAlreadyExistsException ex) {
            return TEXT_RESPONSE_NOT_OK;
        }

        return TEXT_RESPONSE_OK;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT,
            produces = "text/plain")
    public @ResponseBody String updateUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.updateUser(id, user);

        return TEXT_RESPONSE_OK;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = "text/plain")
    public @ResponseBody String deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
        } catch (UserAlreadyDeletedException ex) {
            return TEXT_RESPONSE_NOT_OK;
        }



        return TEXT_RESPONSE_OK;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<User> getUsers() {
        return userService.getAllUsers();
    }
}
