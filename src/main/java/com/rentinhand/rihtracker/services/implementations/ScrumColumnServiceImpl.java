package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.repos.ScrumColumnRepository;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrumColumnServiceImpl implements ScrumColumnService {
    private final ScrumColumnRepository scrumColumnRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<ScrumColumn> findAll() {
        return scrumColumnRepository.findAll();
    }

    @Override
    public Optional<ScrumColumn> findById(Long scrumColumnId) {
        return scrumColumnRepository.findById(scrumColumnId);
    }

    @Override
    public ScrumColumn createScrumColumn(ScrumColumnDataRequest scrumColumnData) {
        ScrumColumn scrumColumn = mapper.map(scrumColumnData, ScrumColumn.class);
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
