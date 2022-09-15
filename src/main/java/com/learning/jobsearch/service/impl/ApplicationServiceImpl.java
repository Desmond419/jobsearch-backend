package com.learning.jobsearch.service.impl;

import com.learning.jobsearch.dao.ApplicationDao;
import com.learning.jobsearch.entity.Application;
import com.learning.jobsearch.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationDao applicationDao;

    @Override
    public void applyJob(Application application){
        applicationDao.applyJob(application);
    }

    @Override
    public List<Application> getAllApplications(){
        return applicationDao.getAllApplications();
    }

    @Override
    public List<Application> getApplicationsByUserId(String userId){
        return applicationDao.getApplicationsByUserId(userId);
    }

    @Override
    public void updateStatus(Application status){
        applicationDao.updateStatus(status);
    }
}
