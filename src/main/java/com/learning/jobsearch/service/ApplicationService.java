package com.learning.jobsearch.service;

import com.learning.jobsearch.entity.Application;

import java.util.List;

public interface ApplicationService {
    void applyJob(Application application);
    List<Application> getAllApplications();
    List<Application> getApplicationsByUserId(String userId);
    void updateStatus(Application status);
}
