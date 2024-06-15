package com.fyordo.shatback.listeners;


import com.fyordo.shatback.entities.CRUDEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

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
