package com.rentinhand.rihtracker.dto.responses.task;

import com.rentinhand.rihtracker.dto.project.ProjectShortDto;
import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.dto.taskType.TaskTypeShortDto;
import com.rentinhand.rihtracker.dto.user.UserDto;
import com.rentinhand.rihtracker.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Long id;

    private String title;

    private UserDto createdUser;

    private List<UserDto> users;

    private String description;

    private Timestamp deadline;

    private List<TimeEntry> timeEntries;

    private TaskTypeShortDto taskType;

    private ProjectShortDto project;

    private ScrumColumn scrumColumn;

    private UserDto maintainer;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.createdUser = new UserDto(task.getCreatedUser());
        this.users = task.getUsers().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());;
        this.description = task.getDescription();
        this.deadline = task.getDeadline();
        this.timeEntries = task.getTimeEntries();
        this.taskType = new TaskTypeShortDto(task.getTaskType());
        this.project = new ProjectShortDto(task.getProject());
        this.scrumColumn = task.getScrumColumn();
        this.maintainer = new UserDto(task.getMaintainer());
    }
}
