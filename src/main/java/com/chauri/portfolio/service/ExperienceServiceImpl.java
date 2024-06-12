package com.chauri.portfolio.service;

import com.chauri.portfolio.dao.ExperienceRepository;
import com.chauri.portfolio.entity.Experience;
import com.chauri.portfolio.service.interfaces.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceServiceImpl(ExperienceRepository repository) {
        this.experienceRepository = repository;
    }

    @Override
    public List<Experience> getAllWorkExperiences() {
        return experienceRepository.findAllByOrderByEndDateDesc();
    }

    @Override
    public Experience findById(Integer id) {
        Optional<Experience> tempExperience = experienceRepository.findById(id);
        if(tempExperience.isPresent())
            return tempExperience.get();
        else
            throw new RuntimeException("Experience details not found in database");
    }

    @Override
    public Experience save(Experience workExperience) {
        return experienceRepository.save(workExperience);
    }

    @Override
    public void deleteById(Integer id) {
        experienceRepository.deleteById(id);
    }
}
