package com.rentinhand.rihtracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task extends CRUDEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private Timestamp deadline;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "task_type_id")
    private TaskType taskType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "column_id")
    private ScrumColumn scrumColumn;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "created_user_id")
    private User createdUser;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "maintainer_id")
    private User maintainer;

    @OneToMany(mappedBy = "task", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @OrderBy("timeStart")
    private List<TimeEntry> timeEntries = new ArrayList<>();

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.PERSIST)
    private Set<User> users = new LinkedHashSet<>();

}