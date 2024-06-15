package com.fyordo.shatback.repos;

import com.fyordo.shatback.entities.ScrumColumn;
import com.fyordo.shatback.entities.Task;
import com.fyordo.shatback.entities.TaskType;
import com.fyordo.shatback.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, QuerydslPredicateExecutor<Task> {
    @Transactional
    @Modifying
    @Query("""
            update Task t set t.title = ?1, t.description = ?2, t.deadline = ?3, t.taskType = ?4, t.scrumColumn = ?5, t.maintainer = ?6
            where t.id = ?7""")
    void updateTask(
            String title,
            String description,
            Timestamp deadline,
            TaskType taskType,
            ScrumColumn scrumColumn,
            User maintainer,
            Long id
    );

    @Query("select t from Task t inner join t.users users where t.scrumColumn.project.id = ?1 and users.id = ?2")
    List<Task> getAllAttachedByProjectId(Long projectId, Long userId);

}