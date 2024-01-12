package com.rentinhand.rihtracker.repos;

import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}