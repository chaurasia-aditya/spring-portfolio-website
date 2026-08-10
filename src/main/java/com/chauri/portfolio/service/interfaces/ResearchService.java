package com.chauri.portfolio.service.interfaces;

import com.chauri.portfolio.entity.Research;

import java.util.List;

public interface ResearchService {
    List<Research> getAllResearch();

    Research findById(Integer id);

    Research save(Research research);

    void deleteById(Integer id);
}
