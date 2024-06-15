package com.fyordo.shatback.dto.responses.dashboard;

import com.fyordo.shatback.dto.task.CurrentTaskDto;
import com.rentinhand.rihtracker.dto.TimePeriodDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DashboardAllResponse{
    private TimePeriodDto todayTime;
    private int countTasks;
    private CurrentTaskDto currentTask;
    private List<TimePeriodDto> graph;
}