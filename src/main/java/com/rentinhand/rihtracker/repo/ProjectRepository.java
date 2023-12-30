package com.rentinhand.rihtracker.repo;

import com.rentinhand.rihtracker.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}