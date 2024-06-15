package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.responses.dashboard.DashboardAllResponse;

public interface DashboardService {
    DashboardAllResponse getAll(Long projectId);
}
