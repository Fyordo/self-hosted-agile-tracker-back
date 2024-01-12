package com.rentinhand.rihtracker.dto.responses.task;

import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.dto.user.UserDto;
import com.rentinhand.rihtracker.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Long id;

    private String title;

    private UserDto createdUser;

    private Set<User> users;

    private String description;

    private Timestamp deadline;

    private List<TimeEntry> timeEntries;

    private TaskType taskType;

    private Project project;

    private ScrumColumn scrumColumn;

    private User maintainer;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.createdUser = new UserDto(task.getCreatedUser());
        this.users = task.getUsers();
        this.description = task.getDescription();
        this.deadline = task.getDeadline();
        this.timeEntries = task.getTimeEntries();
        this.taskType = task.getTaskType();
        this.project = task.getProject();
        this.scrumColumn = task.getScrumColumn();
        this.maintainer = task.getMaintainer();
    }
}
