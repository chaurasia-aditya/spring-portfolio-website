package com.chauri.portfolio.dao;

import com.chauri.portfolio.entity.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchRepository extends JpaRepository<Research, Integer> {
    List<Research> findAllByOrderByPublishedDateDesc();
}
