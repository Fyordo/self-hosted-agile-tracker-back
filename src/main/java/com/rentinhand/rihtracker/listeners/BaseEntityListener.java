package com.rentinhand.rihtracker.listeners;


import com.rentinhand.rihtracker.entities.CRUDEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class BaseEntityListener {
    @PrePersist
    public void prePersist(CRUDEntity model) {
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(CRUDEntity model) {
        model.setUpdatedAt(LocalDateTime.now());
    }
}
