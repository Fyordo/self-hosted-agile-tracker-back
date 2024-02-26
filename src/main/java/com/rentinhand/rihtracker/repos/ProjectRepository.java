package com.rentinhand.rihtracker.repos;

import com.rentinhand.rihtracker.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Transactional
    @Modifying
    @Query("update Project p set p.title = ?1, p.avatar = ?2 where p.id = ?3")
    void updateProject(String title, String avatar, Long id);
}