package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ScrumColumnService {
    Collection<ScrumColumn> findAll();

    Collection<ScrumColumn> findAllInProject(Project project);

    Optional<ScrumColumn> findById(Long scrumColumnId);

    ScrumColumn createScrumColumn(ScrumColumnDataRequest scrumColumnData, Project project);

    ScrumColumn updateScrumColumn(ScrumColumn scrumColumn, ScrumColumnDataRequest scrumColumnData);

    void deleteScrumColumn(ScrumColumn scrumColumn);
}
