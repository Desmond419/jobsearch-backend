package com.learning.jobsearch.service;

import com.learning.jobsearch.entity.Education;

import java.util.List;

public interface EducationService {
    void addEducationByUserId(Education education);
    List<Education> findEducationByUserId(String userId);
    void updateEducation(Education education);
    void deleteEducationById(String id);
}
