package com.learning.jobsearch.dao;

import com.learning.jobsearch.entity.Experience;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExperienceDao {
    void addExperienceByUserId(Experience exp);
    List<Experience> findExperienceByUserId(String userId);
    void updateExperienceByUserId(Experience exp);
    void deleteExperienceById(String id);
}
