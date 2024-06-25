package com.chauri.portfolio.dao;

import com.chauri.portfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public List<Project> findAllByOrderByStartDateDesc();
}
