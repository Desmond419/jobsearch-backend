package com.learning.jobsearch.controller;

import com.learning.jobsearch.entity.Education;
import com.learning.jobsearch.entity.Experience;
import com.learning.jobsearch.entity.User;
import com.learning.jobsearch.service.EducationService;
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
public class EducationController {
    Logger logger = LoggerFactory.getLogger(EducationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EducationService educationService;

    @GetMapping("/edu/user/{userId}")
    public ResponseEntity<List<Education>> getEducationByUserId (@PathVariable("userId") String userId) {
        try{
            User foundUser = userService.findUserById(userId);
            if(foundUser != null)
                return new ResponseEntity<>(educationService.findEducationByUserId(foundUser.getId()), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("getEducationByUserId error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/edu")
    public ResponseEntity<Education> addEducationByUserId (@RequestBody Education education) {
        try{
            User foundUser = userService.findUserById(education.getUserId());
            if(foundUser != null){
                education.setId(new GenUUID().getUUID());
                education.setCreateTime(new DateTimeUtils().getCurrentTime());
                education.setUpdateTime(new DateTimeUtils().getCurrentTime());
                educationService.addEducationByUserId(education);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("addEducationByUserId error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edu")
    public ResponseEntity<Void> updateEducation(@RequestBody Education education) {
        try{
            education.setUpdateTime(new DateTimeUtils().getCurrentTime());
            educationService.updateEducation(education);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("updateEducation error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id (education id)
     */
    @DeleteMapping("/edu/{id}")
    public ResponseEntity<Void> deleteEducationById(@PathVariable("id") String id) {
        try{
            educationService.deleteEducationById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("deleteEducationById error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
