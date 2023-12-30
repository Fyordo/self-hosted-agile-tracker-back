package com.rentinhand.rihtracker.repo;

import com.rentinhand.rihtracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}