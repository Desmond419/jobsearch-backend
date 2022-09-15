package com.learning.jobsearch.dao;

import com.learning.jobsearch.entity.Application;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplicationDao {
    void applyJob(Application application);
    List<Application> getAllApplications();
    List<Application> getApplicationsByUserId(String userId);
    void updateStatus(Application status);
}
