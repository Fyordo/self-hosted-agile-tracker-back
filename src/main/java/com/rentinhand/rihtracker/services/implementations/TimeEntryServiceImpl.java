package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryUpdateRequest;
import com.rentinhand.rihtracker.entities.TimeEntry;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.repos.*;
import com.rentinhand.rihtracker.services.TimeEntryService;
import com.rentinhand.rihtracker.utilities.SecurityWorkspace;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TimeEntryServiceImpl implements TimeEntryService {
    private TimeEntryRepository timeEntryRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Override
    public Optional<TimeEntry> findById(Long timeEntryId) {
        return timeEntryRepository.findById(timeEntryId);
    }

    @Override
    @Transactional
    public TimeEntry createTimeEntry(TimeEntryCreateRequest timeEntryData) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TimeEntry timeEntry = modelMapper.map(timeEntryData, TimeEntry.class);
        timeEntryRepository.save(timeEntry);

        return timeEntry;
    }

    @Override
    public TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeEntryUpdateRequest timeEntryData) {
        timeEntry.setDescription(timeEntryData.getDescription());
        timeEntry.setTimeEnd(timeEntryData.getTimeEnd());
        timeEntry.setTimeStart(timeEntryData.getTimeStart());

        timeEntryRepository.save(timeEntry);
        return timeEntry;
    }

    public TimeEntry setTask(Long taskId, TimeEntry timeEntry) {
        taskRepository.findById(taskId).ifPresent(timeEntry::setTask);
        return timeEntry;
    }

    public TimeEntry setUser(Long userId, TimeEntry timeEntry) {
        userRepository.findById(userId).ifPresent(timeEntry::setUser);
        return timeEntry;
    }


    @Override
    public boolean deleteTimeEntry(TimeEntry timeEntry) {
        timeEntryRepository.delete(timeEntry);
        return false;
    }
}
