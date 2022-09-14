package com.learning.jobsearch.service.impl;

import com.learning.jobsearch.dao.ExperienceDao;
import com.learning.jobsearch.entity.Experience;
import com.learning.jobsearch.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    @Autowired
    ExperienceDao experienceDao;

    @Override
    public void addExperienceByUserId(Experience experience){
        experienceDao.addExperienceByUserId(experience);
    }

    @Override
    public List<Experience> findExperienceByUserId(String userId){
        return experienceDao.findExperienceByUserId(userId);
    }

    @Override
    public void updateExperienceByUserId(Experience experience){
        experienceDao.updateExperienceByUserId(experience);
    }

    @Override
    public void deleteExperienceById(String id){
        experienceDao.deleteExperienceById(id);
    }
}
