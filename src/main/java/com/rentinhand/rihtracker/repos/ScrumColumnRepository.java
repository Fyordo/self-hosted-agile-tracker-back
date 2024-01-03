package com.rentinhand.rihtracker.repos;

import com.rentinhand.rihtracker.entities.ScrumColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrumColumnRepository extends JpaRepository<ScrumColumn, Long> {
}