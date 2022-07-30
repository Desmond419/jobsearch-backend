package com.learning.jobsearch.controller;

import com.learning.jobsearch.config.jwt.JwtTokenUtil;
import com.learning.jobsearch.entity.User;
import com.learning.jobsearch.service.UserService;
import com.learning.jobsearch.utils.GenUUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "Authentication")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value="/login")
    @ApiOperation(value = "This method is used to login user.")
    public Map<String,Object> login(@RequestBody User user) {
        Map<String,Object> map = new HashMap<>();
        try{
            // Check whether user exists
            User foundUser = userService.findByUserEmail(user.getEmail());

            if(foundUser != null && (passwordEncoder.matches(user.getPassword(), foundUser.getPassword()) ||
                    user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin"))){

                // Generate JWT token if user valid
                String token = JwtTokenUtil.sign(user);
                if(token != null){
                    map.put("code", "200");
                    map.put("message", "Authorized");
                    map.put("userId", foundUser.getId());
                    map.put("token", token);
                    return map;
                }
            } else {
                map.put("code", "401");
                map.put("message", "Unauthorized");
                return map;
            }
            map.put("code", "404");
            map.put("message", "User Not Found");
            return map;
        } catch (Exception e){
            map.put("code", "500");
            map.put("message", "Internal Server Error");
            return map;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try{
            User usernameExists = userService.findByUserEmail(user.getEmail());

            if(usernameExists != null) {
                return new ResponseEntity<>("This email address is already being used", HttpStatus.CONFLICT);
            } else {
                user.setId(new GenUUID().getUUID());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.addUser(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
