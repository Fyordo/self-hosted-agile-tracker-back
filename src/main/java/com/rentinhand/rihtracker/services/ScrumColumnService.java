package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.entities.ScrumColumn;

import java.util.List;
import java.util.Optional;

public interface ScrumColumnService {
    List<ScrumColumn> findAll();
    Optional<ScrumColumn> findById(Long scrumColumnId);
    ScrumColumn createScrumColumn(ScrumColumnDataRequest scrumColumnData);
    ScrumColumn updateScrumColumn(ScrumColumn scrumColumn, ScrumColumnDataRequest scrumColumnData);
    void deleteScrumColumn(ScrumColumn scrumColumn);
}
