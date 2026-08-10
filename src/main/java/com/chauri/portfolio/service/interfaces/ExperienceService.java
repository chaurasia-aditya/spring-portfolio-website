package com.chauri.portfolio.service.interfaces;

import com.chauri.portfolio.entity.Experience;

import java.util.List;

public interface ExperienceService {
    List<Experience> getAllWorkExperiences();

    List<Experience> getPortfolioWorkExperiences();

    Experience findById(Integer id);

    Experience save(Experience workExperience);

    void deleteById(Integer id);
}
