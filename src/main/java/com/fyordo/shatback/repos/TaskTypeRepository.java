package com.fyordo.shatback.repos;

import com.fyordo.shatback.entities.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
    @Transactional
    @Modifying
    @Query("update TaskType t set t.title = ?1, t.color = ?2 where t.id = ?3")
    void updateTaskType(String title, String color, Long id);
}