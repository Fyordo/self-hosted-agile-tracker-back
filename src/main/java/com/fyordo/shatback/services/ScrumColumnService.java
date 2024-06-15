package com.fyordo.shatback.services;

import com.fyordo.shatback.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.fyordo.shatback.entities.Project;
import com.fyordo.shatback.entities.ScrumColumn;

import java.util.Collection;
import java.util.Optional;

public interface ScrumColumnService {
    Collection<ScrumColumn> findAll();

    Collection<ScrumColumn> findAllInProject(Project project);

    Optional<ScrumColumn> findById(Long scrumColumnId);

    ScrumColumn createScrumColumn(ScrumColumnDataRequest scrumColumnData, Project project);

    ScrumColumn updateScrumColumn(ScrumColumn scrumColumn, ScrumColumnDataRequest scrumColumnData);

    void deleteScrumColumn(ScrumColumn scrumColumn);
}
