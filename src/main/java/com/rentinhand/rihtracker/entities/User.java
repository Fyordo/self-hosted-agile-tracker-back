package com.rentinhand.rihtracker.entities;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "createdUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> createdTasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "maintainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> maintainedTasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTask> userTasks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("timeStart")
    private List<TimeEntry> timeEntries = new ArrayList<>();

}