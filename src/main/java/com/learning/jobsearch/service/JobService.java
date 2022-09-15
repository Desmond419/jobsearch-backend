package com.learning.jobsearch.service;

import com.learning.jobsearch.entity.Job;

import java.util.List;

public interface JobService {
    void addJob(Job job);
    List<Job> getAllJob();
    void updateJobById(Job job);
    void deleteJobById(String id);
    List<Job> getJobByRelatedTitle(String key);
}
