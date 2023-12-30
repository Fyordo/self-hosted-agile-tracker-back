package com.rentinhand.rihtracker.repo;

import com.rentinhand.rihtracker.entities.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {
}