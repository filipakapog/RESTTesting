package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fghimpeteanu on 9/9/2016.
 */
@Controller
public class MainController {
    private static final String TEXT_RESPONSE_OK = "The request was successful";

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public
    @ResponseBody
    String index() {
        return "This is just the index page";
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST, consumes = {"application/json"},
            produces = "text/html")
    public
    @ResponseBody
    String addUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.addUser(id, user);

        return TEXT_RESPONSE_OK;
}

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }
}
