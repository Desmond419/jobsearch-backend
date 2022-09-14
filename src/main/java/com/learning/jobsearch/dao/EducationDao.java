package com.learning.jobsearch.dao;

import com.learning.jobsearch.entity.Education;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EducationDao {
    void addEducationByUserId(Education education);
    List<Education> findEducationByUserId(String userId);
    void updateEducation(Education education);
    void deleteEducationById(String id);
}
