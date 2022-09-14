package com.learning.jobsearch.service;

import com.learning.jobsearch.entity.Experience;
import com.learning.jobsearch.entity.User;

import java.util.List;

public interface ExperienceService {
    void addExperienceByUserId(Experience exp);
    List<Experience> findExperienceByUserId(String userId);
    void updateExperienceByUserId(Experience exp);
    void deleteExperienceById(String id);
}
