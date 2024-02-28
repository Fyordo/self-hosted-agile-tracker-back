package com.rentinhand.rihtracker.repos;

import com.querydsl.core.types.QTuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TaskType;
import com.rentinhand.rihtracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
}