package com.learning.jobsearch.controller;

import com.learning.jobsearch.entity.Experience;
import com.learning.jobsearch.entity.User;
import com.learning.jobsearch.service.ExperienceService;
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
public class ExperienceController {
    Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/exp/user/{userId}")
    public ResponseEntity<List<Experience>> getExperienceByUserId (@PathVariable("userId") String userId) {
        try{
            User foundUser = userService.findUserById(userId);
            if(foundUser != null)
                return new ResponseEntity<>(experienceService.findExperienceByUserId(foundUser.getId()), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("getExperienceByUserId error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/exp")
    public ResponseEntity<Experience> addExperienceByUserId (@RequestBody Experience experience) {
        try{
            User foundUser = userService.findUserById(experience.getUserId());
            if(foundUser != null){
                experience.setId(new GenUUID().getUUID());
                experience.setCreateTime(new DateTimeUtils().getCurrentTime());
                experience.setUpdateTime(new DateTimeUtils().getCurrentTime());
                experienceService.addExperienceByUserId(experience);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            logger.error("addExperienceByUserId error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/exp")
    public ResponseEntity<Void> updateExperience(@RequestBody Experience experience) {
        try{
            experience.setUpdateTime(new DateTimeUtils().getCurrentTime());
            experienceService.updateExperienceByUserId(experience);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("updateExperience error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id (experience id)
     */
    @DeleteMapping("/exp/{id}")
    public ResponseEntity<Void> deleteExperienceById(@PathVariable("id") String id) {
        try{
            experienceService.deleteExperienceById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("deleteExperienceById error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
