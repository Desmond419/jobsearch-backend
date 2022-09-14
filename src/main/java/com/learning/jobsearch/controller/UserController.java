package com.learning.jobsearch.controller;

import com.learning.jobsearch.entity.User;
import com.learning.jobsearch.service.UserService;
import com.learning.jobsearch.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/user/email/{email}")
    public ResponseEntity<User> getUserByEmail (@PathVariable("email") String email) {
        try{
            User foundUser = userService.findByUserEmail(email);
            if(foundUser != null)
                return new ResponseEntity<>(foundUser, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("getUserByEmail error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/id/{id}")
    public ResponseEntity<User> getUserById (@PathVariable("id") String id) {
        try{
            User foundUser = userService.findUserById(id);
            if(foundUser != null)
                return new ResponseEntity<>(foundUser, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("getUserById error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        try{
            user.setUpdateTime(new DateTimeUtils().getCurrentTime());
            userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("updateUser error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") String id) {
        try{
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("deleteUserById error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
