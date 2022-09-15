package com.learning.jobsearch.service.impl;

import com.learning.jobsearch.dao.JobDao;
import com.learning.jobsearch.entity.Job;
import com.learning.jobsearch.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobDao jobDao;

    @Override
    public void addJob(Job job){
        jobDao.addJob(job);
    }

    @Override
    public List<Job> getAllJob(){
        return jobDao.getAllJob();
    }

    @Override
    public void updateJobById(Job job){
        jobDao.updateJobById(job);
    }

    @Override
    public void deleteJobById(String id){
        jobDao.deleteJobById(id);
    }

    @Override
    public List<Job> getJobByRelatedTitle(String key){
        return jobDao.getJobByRelatedTitle(key);
    }
}
