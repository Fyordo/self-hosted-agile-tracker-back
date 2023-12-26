package com.rentinhand.rihtracker.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "scrum_columns")
public class ScrumColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "color", length = 7, nullable = false)
    private String color;

    @OneToMany(mappedBy = "scrumColumn", orphanRemoval = true)
    private Set<Task> tasks = new LinkedHashSet<>();

}