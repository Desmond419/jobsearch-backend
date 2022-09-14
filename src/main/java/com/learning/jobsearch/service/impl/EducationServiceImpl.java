package com.learning.jobsearch.service.impl;

import com.learning.jobsearch.dao.EducationDao;
import com.learning.jobsearch.entity.Education;
import com.learning.jobsearch.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    EducationDao educationDao;

    @Override
    public void addEducationByUserId(Education education){
        educationDao.addEducationByUserId(education);
    }

    @Override
    public List<Education> findEducationByUserId(String userId){
        return educationDao.findEducationByUserId(userId);
    }

    @Override
    public void updateEducation(Education education){
        educationDao.updateEducation(education);
    }

    @Override
    public void deleteEducationById(String id){
        educationDao.deleteEducationById(id);
    }
}
