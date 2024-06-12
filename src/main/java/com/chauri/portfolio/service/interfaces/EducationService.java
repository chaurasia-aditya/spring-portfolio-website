package com.chauri.portfolio.service.interfaces;

import com.chauri.portfolio.entity.Education;
import com.chauri.portfolio.entity.Skill;

import java.util.List;

public interface EducationService {
    List<Education> getAllEducationDetails();

    Education findById(int theId);

    Education save(Education theEducation);

    void deleteById(int theId);
}
