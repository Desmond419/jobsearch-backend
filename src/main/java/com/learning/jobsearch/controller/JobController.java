package com.learning.jobsearch.controller;

import com.learning.jobsearch.entity.Job;
import com.learning.jobsearch.service.JobService;
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
public class JobController {
    Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService jobService;

    @GetMapping("/job-vacancy")
    public ResponseEntity<List<Job>> getAllJob() {
        try{
            return new ResponseEntity<>(jobService.getAllJob(), HttpStatus.OK);
        } catch (Exception e){
            logger.error("getAllJob error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/job-search")
    public ResponseEntity<List<Job>> getJobByRelatedTitle(@RequestParam String key) {
        try{
            return new ResponseEntity<>(jobService.getJobByRelatedTitle(key), HttpStatus.OK);
        } catch (Exception e){
            logger.error("getJobByRelatedTitle error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/job")
    public ResponseEntity<Job> addJob(@RequestBody Job job) {
        try{
            job.setId(new GenUUID().getUUID());
            job.setCreateTime(new DateTimeUtils().getCurrentTime());
            job.setUpdateTime(new DateTimeUtils().getCurrentTime());
            jobService.addJob(job);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("addJob error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/job")
    public ResponseEntity<Void> updateJob(@RequestBody Job job) {
        try{
            job.setUpdateTime(new DateTimeUtils().getCurrentTime());
            jobService.updateJobById(job);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("updateJob error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable("id") String id) {
        try{
            jobService.deleteJobById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error("deleteJobById error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
