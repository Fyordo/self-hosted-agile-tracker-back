package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnCreateRequest;
import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnUpdateRequest;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.repos.ScrumColumnRepository;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScrumColumnServiceImpl implements ScrumColumnService {
    private ScrumColumnRepository scrumColumnRepository;

    @Override
    public Optional<ScrumColumn> findById(Long scrumColumnId) {
        return scrumColumnRepository.findById(scrumColumnId);
    }

    public List<ScrumColumn> findAll() {
        return scrumColumnRepository.findAll();
    }

    @Override
    public ScrumColumn createScrumColumn(ScrumColumnCreateRequest scrumColumnData) {
        ModelMapper modelMapper = new ModelMapper();
        ScrumColumn scrumColumn = modelMapper.map(scrumColumnData, ScrumColumn.class);
        scrumColumnRepository.save(scrumColumn);
        return scrumColumn;
    }

    @Override
    public ScrumColumn updateScrumColumn(ScrumColumn scrumColumn, ScrumColumnUpdateRequest scrumColumnData) {
        scrumColumn.setTitle(scrumColumnData.getTitle());
        scrumColumn.setColor(scrumColumnData.getColor());

        scrumColumnRepository.save(scrumColumn);
        return scrumColumn;
    }

    @Override
    public boolean deleteScrumColumn(ScrumColumn scrumColumn) {
        scrumColumnRepository.delete(scrumColumn);
        return false;
    }
}
