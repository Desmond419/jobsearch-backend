package com.learning.jobsearch.dao;

import com.learning.jobsearch.entity.Job;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobDao {
    void addJob(Job job);
    List<Job> getAllJob();
    void updateJobById(Job job);
    void deleteJobById(String id);
    List<Job> getJobByRelatedTitle(String key);
}
