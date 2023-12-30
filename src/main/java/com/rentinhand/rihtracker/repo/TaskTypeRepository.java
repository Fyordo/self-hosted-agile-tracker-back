package com.rentinhand.rihtracker.repo;

import com.rentinhand.rihtracker.entities.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
}