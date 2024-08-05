package com.fyordo.shatback.services.implementations;

import com.fyordo.shatback.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.fyordo.shatback.entities.Project;
import com.fyordo.shatback.entities.ScrumColumn;
import com.fyordo.shatback.repos.ScrumColumnRepository;
import com.fyordo.shatback.services.ScrumColumnService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrumColumnServiceImpl implements ScrumColumnService {
    private final ScrumColumnRepository scrumColumnRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<ScrumColumn> findAll() {
        return scrumColumnRepository.findAll();
    }

    @Override
    public Collection<ScrumColumn> findAllInProject(Project project) {
        return project.getColumns();
    }

    @Override
    public Optional<ScrumColumn> findById(Long scrumColumnId) {
        return scrumColumnRepository.findById(scrumColumnId);
    }

    @Override
    public ScrumColumn createScrumColumn(ScrumColumnDataRequest scrumColumnData, Project project) {
        ScrumColumn scrumColumn = mapper.map(scrumColumnData, ScrumColumn.class);
        scrumColumn.setProject(project);
        scrumColumnRepository.save(scrumColumn);
        return scrumColumn;
    }

    @Override
    public ScrumColumn updateScrumColumn(ScrumColumn scrumColumn, ScrumColumnDataRequest scrumColumnData) {
        scrumColumnRepository.updateScrumColumn(
                scrumColumnData.getTitle(), 
                scrumColumnData.getColor(),
                scrumColumn.getId()
        );
        return scrumColumn;
    }

    @Override
    public void deleteScrumColumn(ScrumColumn scrumColumn) {
        scrumColumnRepository.delete(scrumColumn);
    }
}
