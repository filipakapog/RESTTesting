package com.example.controller;

import com.example.exceptions.UserAlreadyDeletedException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.messages.ReturnMessages.*;

/**
 * Created by fghimpeteanu on 9/9/2016.
 *
 */
@Controller
public class MainController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public @ResponseBody String index() {
        return "This is just the index page";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST, consumes = {"application/json"},
            produces = "text/plain")
    public ResponseEntity<String> addUser(@PathVariable("id") int id, RequestEntity<User> requestEntity) {
        User userToInsert = requestEntity.getBody();

        try {
            userService.addUser(id, userToInsert);
        } catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(MESSAGE_USER_INSERT_NOT_OK, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(MESSAGE_USER_INSERT_OK, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User userToReturn = userService.getUser(id);

        if (userToReturn == null) return new ResponseEntity<>(userToReturn, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userToReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT,
            produces = "text/plain")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, RequestEntity<User> requestEntity) {
        User userToUpdate = requestEntity.getBody();

        userService.updateUser(id, userToUpdate);

        return new ResponseEntity<>(MESSAGE_USER_UPDATED_OK, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = "text/plain")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
        } catch (UserAlreadyDeletedException ex) {
            return new ResponseEntity<>(MESSAGE_USER_DELETED_NOT_OK, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(MESSAGE_USER_DELETED_OK, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {
        List<User> usersToReturn = userService.getAllUsers();

        if (usersToReturn == null || usersToReturn.size() == 0) {
            return new ResponseEntity<>(usersToReturn, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(usersToReturn, HttpStatus.OK);
    }
}
