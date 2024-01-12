package com.rentinhand.rihtracker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project extends CRUDEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "created_user_id")
    private User createdUser;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.PERSIST)
    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project", orphanRemoval = true)
    private Set<Task> tasks = new LinkedHashSet<>();

    public boolean haveAccess( User user){
        return this.getUsers().contains(user) || Objects.equals(this.getCreatedUser().getId(), user.getId());
    }
}