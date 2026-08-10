package com.chauri.portfolio.dao;

import com.chauri.portfolio.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    @Query("""
            SELECT e FROM Experience e
            ORDER BY CASE WHEN e.endDate IS NULL THEN 0 ELSE 1 END,
                     e.endDate DESC,
                     e.startDate DESC
            """)
    List<Experience> findAllForAdmin();

    @Query("""
            SELECT e FROM Experience e
            WHERE e.displayOnPortfolio = true
            ORDER BY CASE WHEN e.endDate IS NULL THEN 0 ELSE 1 END,
                     e.endDate DESC,
                     e.startDate DESC
            """)
    List<Experience> findAllForPortfolio();
}
