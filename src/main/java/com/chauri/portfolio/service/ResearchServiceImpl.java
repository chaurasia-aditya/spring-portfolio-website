package com.chauri.portfolio.service;

import com.chauri.portfolio.dao.ResearchRepository;
import com.chauri.portfolio.entity.Research;
import com.chauri.portfolio.service.interfaces.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResearchServiceImpl implements ResearchService {

    private ResearchRepository researchRepository;

    @Autowired
    public ResearchServiceImpl(ResearchRepository researchRepository) {
        this.researchRepository = researchRepository;
    }

    @Override
    public List<Research> getAllResearch() {
        return researchRepository.findAllByOrderByPublishedDateDesc();
    }

    @Override
    public Research findById(Integer id) {
        Optional<Research> tempResearch = researchRepository.findById(id);
        if (tempResearch.isPresent()) {
            return tempResearch.get();
        }

        throw new RuntimeException("Research entry not found in database");
    }

    @Override
    @Transactional
    public Research save(Research research) {
        return researchRepository.save(research);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        researchRepository.deleteById(id);
    }
}
