package com.learning.jobsearch.controller;

import com.learning.jobsearch.entity.Application;
import com.learning.jobsearch.entity.User;
import com.learning.jobsearch.service.ApplicationService;
import com.learning.jobsearch.service.UserService;
import com.learning.jobsearch.utils.DateTimeUtils;
import com.learning.jobsearch.utils.GenUUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplication() {
        try{
            return new ResponseEntity<>(applicationService.getAllApplications(), HttpStatus.OK);
        } catch (Exception e){
            logger.error("getAllApplication error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/applications/user/{userId}")
    public ResponseEntity<List<Application>> getApplicationByUserId(@PathVariable("userId") String userId) {
        try{
            User foundUser = userService.findUserById(userId);
            if(foundUser != null)
                return new ResponseEntity<>(applicationService.getApplicationsByUserId(foundUser.getId()), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("getApplicationByUserId error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/apply-job")
    public ResponseEntity<Application> applyJob(@RequestBody Application application) {
        try{
            application.setId(new GenUUID().getUUID());
            application.setCreateTime(new DateTimeUtils().getCurrentTime());
            application.setUpdateTime(new DateTimeUtils().getCurrentTime());
            applicationService.applyJob(application);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("applyJob error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/application")
    public ResponseEntity<Void> updateStatus(@RequestBody Application status) {
        try{
            status.setUpdateTime(new DateTimeUtils().getCurrentTime());
            applicationService.updateStatus(status);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("updateStatus error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
