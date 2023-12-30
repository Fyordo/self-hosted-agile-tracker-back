package com.rentinhand.rihtracker.repo;

import com.rentinhand.rihtracker.entities.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
}