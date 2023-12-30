package com.rentinhand.rihtracker.repo;

import com.rentinhand.rihtracker.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}