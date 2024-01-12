package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnCreateRequest;
import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnUpdateRequest;
import com.rentinhand.rihtracker.entities.ScrumColumn;

import java.util.List;
import java.util.Optional;

public interface ScrumColumnService {
    Optional<ScrumColumn> findById(Long scrumColumnId);
    List<ScrumColumn> findAll();
    ScrumColumn createScrumColumn(ScrumColumnCreateRequest scrumColumnData);
    ScrumColumn updateScrumColumn(ScrumColumn scrumColumn, ScrumColumnUpdateRequest scrumColumnData);
    boolean deleteScrumColumn(ScrumColumn scrumColumn);
}
